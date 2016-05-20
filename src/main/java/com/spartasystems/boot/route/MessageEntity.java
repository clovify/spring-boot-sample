package com.spartasystems.boot.route;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
@Table(name="messages")
public class MessageEntity implements Serializable{

    @Id
    private String messageId;

    @Column(name="TENANT_ID")
    private int tenantId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_DATE")
    private Date createDate;

    @Lob
    @Column(name="BODY")
    private String body;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageEntity{");
        sb.append("id=").append(id);
        sb.append(", createDate=").append(createDate);
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
