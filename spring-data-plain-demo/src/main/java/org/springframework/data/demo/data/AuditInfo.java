package org.springframework.data.demo.data;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by corneil on 3/28/14.
 */
@Entity
@Document(indexName = "auditinfo")
public class AuditInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")

    private String id;

    @Indexed(unique = false)
    @NotNull
    private String name;

    private String beforeValue;

    private String afterValue;

    public AuditInfo(String name, String beforeValue, String afterValue) {
        this.name = name;
        this.beforeValue = beforeValue;
        this.afterValue = afterValue;
    }

    public AuditInfo() {
    }

    public AuditInfo(String name, String afterValue) {
        this.name = name;
        this.afterValue = afterValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeforeValue() {
        return beforeValue;
    }

    public void setBeforeValue(String beforeValue) {
        this.beforeValue = beforeValue;
    }

    public String getAfterValue() {
        return afterValue;
    }

    public void setAfterValue(String afterValue) {
        this.afterValue = afterValue;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (beforeValue != null ? beforeValue.hashCode() : 0);
        result = 31 * result + (afterValue != null ? afterValue.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuditInfo auditInfo = (AuditInfo) o;

        if (afterValue != null ? !afterValue.equals(auditInfo.afterValue) : auditInfo.afterValue != null) {
            return false;
        }
        if (beforeValue != null ? !beforeValue.equals(auditInfo.beforeValue) : auditInfo.beforeValue != null) {
            return false;
        }
        if (name != null ? !name.equals(auditInfo.name) : auditInfo.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuditInfo{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", beforeValue='").append(beforeValue).append('\'');
        sb.append(", afterValue='").append(afterValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}