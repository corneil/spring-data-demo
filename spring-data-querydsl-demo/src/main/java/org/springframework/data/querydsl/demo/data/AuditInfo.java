package org.springframework.data.querydsl.demo.data;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@QueryEntity
@Data
@EqualsAndHashCode(of="name")
public class AuditInfo {
    private String afterValue;
    private String beforeValue;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Indexed(unique = false)
    @NotNull
    private String name;

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

}
