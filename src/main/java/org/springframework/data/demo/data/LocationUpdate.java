package org.springframework.data.demo.data;

import com.mysema.query.annotations.QueryEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Corneil du Plessis
 */

@Entity
@QueryEntity
public class LocationUpdate {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 40)
    private String id;

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

    public Date getLocTime() {
        return locTime;
    }

    public void setLocTime(Date locTime) {
        this.locTime = locTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatX() {
        return latX;
    }

    public void setLatX(double latX) {
        this.latX = latX;
    }

    public double getLatY() {
        return latY;
    }

    public void setLatY(double latY) {
        this.latY = latY;
    }

    public String getLocDetail() {
        return locDetail;
    }

    public void setLocDetail(String locDetail) {
        this.locDetail = locDetail;
    }

    public DeviceInfo getDevice() {
        return device;
    }

    public void setDevice(DeviceInfo device) {
        this.device = device;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocationUpdate{");
        sb.append("id='").append(id).append('\'');
        sb.append(", latX=").append(latX);
        sb.append(", latY=").append(latY);
        sb.append(", locDetail='").append(locDetail).append('\'');
        sb.append(", locTime=").append(locTime);
        sb.append(", device=").append(device);
        sb.append('}');
        return sb.toString();
    }

}
