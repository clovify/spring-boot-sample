package com.spartasystems.boot.domain;

public class BasicMessage implements Message {
    private final int deliverToTenantId;
    private final String messageBody;

    public BasicMessage(int deliverToTenantId, String messageBody) {
        this.messageBody = messageBody;
        this.deliverToTenantId = deliverToTenantId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    @Override
    public int getDeliverToTenantId() {
        return deliverToTenantId;
    }

    @Override
    public long getMessageSize() {
        return messageBody.length();
    }
}
