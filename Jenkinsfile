node("build_node") {
    stage "Compile"
    git url: 'git@github.com:SpartaSystems/spring-boot-sample.git', branch: 'master'
    env.JAVA_HOME = tool 'JDK8'
    sh "gradle clean installDist"
    stage "Performance Testing"
    dir("build/install/spring-boot-sample/bin") {
        sh "./spring-boot-sample > out.log &"
    }
    sh "gradlew perf"
    sh "tar -czf perfresults.tar.gz build/gatling*"
    archive "perfresults.tar.gz"
}