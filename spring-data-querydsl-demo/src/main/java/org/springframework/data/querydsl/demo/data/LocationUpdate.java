package org.springframework.data.querydsl.demo.data;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Entity
@QueryEntity
@org.springframework.data.mongodb.core.mapping.Document
@Data
@EqualsAndHashCode(of = {"device", "locTime"})
public class LocationUpdate {
    @DBRef
    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private DeviceInfo device;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Indexed(unique = false)
    private double latX;
    @Indexed(unique = false)
    private double latY;
    private String locDetail;
    @Indexed(unique = false)
    @NotNull
    private Date locTime;

}
