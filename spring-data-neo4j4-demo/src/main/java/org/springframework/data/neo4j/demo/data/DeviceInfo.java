package org.springframework.data.neo4j.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.validation.constraints.NotNull;

@NodeEntity
@Data
@EqualsAndHashCode(of = "deviceId")
public class DeviceInfo {
    @NotNull
    private String deviceId;
    private String deviceName;
    @GraphId
    private Long id;
}
