package scm

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LoadTestSimulation extends Simulation {

  object CreateMessage {
    val create = repeat(10, "n") {
      exec(http("CreateMessage ${n}")
        .post("/messages/${n}")
        .body(StringBody("""{ "myContent": "myValue" }"""))
        .asJSON)
    }
  }

  object ProcessMessages {
    val processMessages = repeat(10, "n") {
      exec(http("GetMessages ${n}")
          .get("/messages/${n}")
        .check(
          jsonPath("$[*]").ofType[Map[String,Any]].findAll.saveAs("msgs"))
      )
        .foreach("${msgs}", "message") {
          exec(http("DeleteMessages ${n}")
            .delete("/messages/${n}/${message.messageId}/${message.timestamp}")
              .ignoreDefaultChecks
          )
        }
    }
  }
  val httpConf = http
    .baseURL("http://localhost:8080")

  val createMessages = scenario("CreateMessages").exec(CreateMessage.create)
  val processMessages = scenario("ProcessMessages").exec(ProcessMessages.processMessages)

  setUp(
    createMessages.inject(rampUsersPerSec(2) to(50) during(2 minutes)),
    processMessages.inject(rampUsers(20).over(4 minutes))
  ).protocols(httpConf)
}