package com.spartasystems.boot.domain;

import java.util.Map;

public class RetrievedMessage {
    private final String messageId;
    private final long timestamp;
    private final int tenantId;
    private final Map json;

    public RetrievedMessage(String messageId, long timestamp, int tenantId, Map json) {
        this.messageId = messageId;
        this.timestamp = timestamp;
        this.tenantId = tenantId;
        this.json = json;
    }

    public String getMessageId() {
        return messageId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public Map getJson() {
        return json;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RetrievedMessage{");
        sb.append("messageId='").append(messageId).append('\'');
        sb.append(", tenantId=").append(tenantId);
        sb.append('}');
        return sb.toString();
    }
}
