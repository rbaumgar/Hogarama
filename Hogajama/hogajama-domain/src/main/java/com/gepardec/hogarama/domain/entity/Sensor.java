package com.gepardec.hogarama.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Sensor implements Serializable {

    @Id
    @GeneratedValue(generator = "SensorIdGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SensorIdGenerator", sequenceName = "seq_sensor_id")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sensor_type_id", nullable = false)
    private SensorType sensorType;

    private String name;

    @Column(name = "device_id", unique = true, nullable = false)
    private String deviceId;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}