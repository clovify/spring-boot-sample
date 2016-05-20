package com.spartasystems.boot.domain;

public interface Message {
    long getMessageSize();

    default boolean exceedsSize(long maxSize) {
        return getMessageSize() > maxSize;
    }

    default boolean isExternal() {
        return false;
    }

    int getDeliverToTenantId();
}
