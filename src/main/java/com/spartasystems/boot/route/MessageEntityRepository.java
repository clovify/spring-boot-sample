package com.spartasystems.boot.route;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageEntityRepository extends CrudRepository<MessageEntity, String> {
    List<MessageEntity> findFirst60ByTenantId(int tenantId);
}
