package org.springframework.data.querydsl.demo.data;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@QueryEntity
@org.springframework.data.mongodb.core.mapping.Document
@Data
@EqualsAndHashCode(of = "deviceId")
public class DeviceInfo {
    @NotNull
    @Indexed(unique = true)
    @Column(unique = true)
    private String deviceId;
    private String deviceName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
}
