package org.springframework.data.demo.data;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@org.springframework.data.mongodb.core.mapping.Document
@Data
public class DeviceInfo {
    @NotNull
    @Indexed(unique = true)
    @Column(unique = true)
    private String deviceId;
    private String deviceName;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
}
