package org.springframework.data.querydsl.demo.data;

import com.mysema.query.annotations.QueryEntity;
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
public class LocationUpdate {
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
    @DBRef
    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private DeviceInfo device;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationUpdate that = (LocationUpdate) o;
        if (Double.compare(that.latX, latX) != 0) {
            return false;
        }
        if (Double.compare(that.latY, latY) != 0) {
            return false;
        }
        if (!device.equals(that.device)) {
            return false;
        }
        if (!locTime.equals(that.locTime)) {
            return false;
        }
        return true;
    }

    public DeviceInfo getDevice() {
        return device;
    }

    public BigInteger getId() {
        return id;
    }

    public double getLatX() {
        return latX;
    }

    public double getLatY() {
        return latY;
    }

    public String getLocDetail() {
        return locDetail;
    }

    public Date getLocTime() {
        return locTime;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latX);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + locTime.hashCode();
        result = 31 * result + device.hashCode();
        return result;
    }

    public void setDevice(DeviceInfo device) {
        this.device = device;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setLatX(double latX) {
        this.latX = latX;
    }

    public void setLatY(double latY) {
        this.latY = latY;
    }

    public void setLocDetail(String locDetail) {
        this.locDetail = locDetail;
    }

    public void setLocTime(Date locTime) {
        this.locTime = locTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocationUpdate{");
        sb.append("id=").append(id);
        sb.append(", latX=").append(latX);
        sb.append(", latY=").append(latY);
        sb.append(", locDetail='").append(locDetail).append('\'');
        sb.append(", locTime=").append(locTime);
        sb.append(", device=").append(device);
        sb.append('}');
        return sb.toString();
    }
}
