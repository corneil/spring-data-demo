package org.springframework.data.neo4j.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NodeEntity
@Data
@EqualsAndHashCode(of = {"device", "locTime"})
public class LocationUpdate {
    @NotNull
    @Relationship
    private DeviceInfo device;
    @GraphId
    private Long id;
    private double latX;
    private double latY;
    private String locDetail;
    @NotNull
    private Date locTime;
}
