package com.spartasystems.boot.route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spartasystems.boot.domain.RetrievedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

@Component
public class MessageService {
    @Autowired
    private MessageEntityRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String createMessage(int tenantId, Object body) throws JsonProcessingException {
        String message = null;
        if(body instanceof String) {
            message = (String)body;
        }
        else {
            message = objectMapper.writeValueAsString(body);
        }
        String generatedId = UUID.randomUUID().toString();
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setBody(message);
        messageEntity.setCreateDate(new Date());
        messageEntity.setTenantId(tenantId);
        messageEntity.setMessageId(generatedId);

        repository.save(messageEntity);
        return generatedId;
    }

    public Collection<RetrievedMessage> list(int tenantId) throws IOException {
        return repository.findFirst60ByTenantId(tenantId).stream().map(this::fromMessageEntity).collect(toSet());
    }

    private RetrievedMessage fromMessageEntity(MessageEntity messageEntity) {
        String messageId = messageEntity.getMessageId();
        Map json = null;
        try {
            json = objectMapper.readValue(messageEntity.getBody(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RetrievedMessage(messageId, messageEntity.getCreateDate().getTime(), messageEntity.getTenantId(), json);
    }

    public void delete(String messageId) {
        MessageEntity messageEntity = repository.findOne(messageId);
        repository.delete(messageEntity);
    }
}
