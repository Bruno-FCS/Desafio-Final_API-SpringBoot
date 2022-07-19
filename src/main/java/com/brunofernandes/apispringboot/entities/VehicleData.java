package com.brunofernandes.apispringboot.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VehicleData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicledata_id;

	@Column(nullable = false)
	private String vehicledata_vin;

	@Column(nullable = false)
	private String vehicledata_odometer;

	@Column(nullable = false)
	private String vehicledata_tire_pressure;

	@Column(nullable = false)
	private String vehicledata_status;

	@Column(nullable = false)
	private String vehicledata_battery_status;

	@Column(nullable = false)
	private String vehicledata_fuel_level;

	@Column(nullable = false)
	private String vehicledata_lat;

	@Column(nullable = false)
	private String vehicledata_long;

	public VehicleData() {
	}

	public VehicleData(Long vehicledata_id, String vehicledata_vin, String vehicledata_odometer,
			String vehicledata_tire_pressure, String vehicledata_status, String vehicledata_battery_status,
			String vehicledata_fuel_level, String vehicledata_lat, String vehicledata_long) {
		super();
		this.vehicledata_id = vehicledata_id;
		this.vehicledata_vin = vehicledata_vin;
		this.vehicledata_odometer = vehicledata_odometer;
		this.vehicledata_tire_pressure = vehicledata_tire_pressure;
		this.vehicledata_status = vehicledata_status;
		this.vehicledata_battery_status = vehicledata_battery_status;
		this.vehicledata_fuel_level = vehicledata_fuel_level;
		this.vehicledata_lat = vehicledata_lat;
		this.vehicledata_long = vehicledata_long;
	}

	public Long getVehicledata_id() {
		return vehicledata_id;
	}

	public void setVehicledata_id(Long vehicledata_id) {
		this.vehicledata_id = vehicledata_id;
	}

	public String getVehicledata_vin() {
		return vehicledata_vin;
	}

	public void setVehicledata_vin(String vehicledata_vin) {
		this.vehicledata_vin = vehicledata_vin;
	}

	public String getVehicledata_odometer() {
		return vehicledata_odometer;
	}

	public void setVehicledata_odometer(String vehicledata_odometer) {
		this.vehicledata_odometer = vehicledata_odometer;
	}

	public String getVehicledata_tire_pressure() {
		return vehicledata_tire_pressure;
	}

	public void setVehicledata_tire_pressure(String vehicledata_tire_pressure) {
		this.vehicledata_tire_pressure = vehicledata_tire_pressure;
	}

	public String getVehicledata_status() {
		return vehicledata_status;
	}

	public void setVehicledata_status(String vehicledata_status) {
		this.vehicledata_status = vehicledata_status;
	}

	public String getVehicledata_battery_status() {
		return vehicledata_battery_status;
	}

	public void setVehicledata_battery_status(String vehicledata_battery_status) {
		this.vehicledata_battery_status = vehicledata_battery_status;
	}

	public String getVehicledata_fuel_level() {
		return vehicledata_fuel_level;
	}

	public void setVehicledata_fuel_level(String vehicledata_fuel_level) {
		this.vehicledata_fuel_level = vehicledata_fuel_level;
	}

	public String getVehicledata_lat() {
		return vehicledata_lat;
	}

	public void setVehicledata_lat(String vehicledata_lat) {
		this.vehicledata_lat = vehicledata_lat;
	}

	public String getVehicledata_long() {
		return vehicledata_long;
	}

	public void setVehicledata_long(String vehicledata_long) {
		this.vehicledata_long = vehicledata_long;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vehicledata_id == null) ? 0 : vehicledata_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleData other = (VehicleData) obj;
		if (vehicledata_id == null) {
			if (other.vehicledata_id != null)
				return false;
		} else if (!vehicledata_id.equals(other.vehicledata_id))
			return false;
		return true;
	}
}
