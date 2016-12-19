package org.springframework.data.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@org.springframework.data.mongodb.core.mapping.Document
@Data
@EqualsAndHashCode(of = {"locTime", "device"})
public class LocationUpdate {
    @DBRef
    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private DeviceInfo device;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Indexed(unique = false)
    private double latX;
    @Indexed(unique = false)
    private double latY;
    private String locDetail;
    @Indexed(unique = false)
    @NotNull
    private Date locTime;

}
