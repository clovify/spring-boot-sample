package com.spartasystems.boot.route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spartasystems.boot.domain.RetrievedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(path="/tenant", method= RequestMethod.POST, consumes = "text/plain")
    public Integer createTenant(@RequestBody int tenantId) {
        return tenantId;
    }

    @RequestMapping(path="/tenant/{tenantId}", method= RequestMethod.DELETE)
    public void deleteTenant(@PathVariable("tenantId") int tenantId) {
    }

    @RequestMapping(path = "/messages/{tenantId}", method=RequestMethod.POST, consumes = "application/json")
    public String createMessage(@PathVariable("tenantId") int tenantId, String body) {
        try {
            return messageService.createMessage(tenantId, body);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(path = "/messages/{tenantId}", method=RequestMethod.GET, produces = "application/json")
    public Collection<RetrievedMessage> listMessages(@PathVariable("tenantId") int tenantId) {
        try {
            return messageService.list(tenantId);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(path = "/messages/{tenantId}/{messageId}/{timestamp}", method = RequestMethod.DELETE)
    public void deleteMessage(@PathVariable("messageId") String messageId) {
        messageService.delete(messageId);
    }
}
