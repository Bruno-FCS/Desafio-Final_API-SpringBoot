package com.brunofernandes.apispringboot.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicle_id;

	@Column(nullable = false)
	private String vehicle_model;

	@Column(nullable = false)
	private Integer vehicle_total_volume;

	@Column(nullable = false)
	private Integer vehicle_connected;

	@Column(nullable = false)
	private Integer vehicle_software_updates;

	public Vehicle() {
	}

	public Vehicle(Long vehicle_id, String vehicle_model, Integer vehicle_total_volume, Integer vehicle_connected,
			Integer vehicle_software_updates) {
		super();
		this.vehicle_id = vehicle_id;
		this.vehicle_model = vehicle_model;
		this.vehicle_total_volume = vehicle_total_volume;
		this.vehicle_connected = vehicle_connected;
		this.vehicle_software_updates = vehicle_software_updates;
	}

	public Long getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(Long vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getVehicle_model() {
		return vehicle_model;
	}

	public void setVehicle_model(String vehicle_model) {
		this.vehicle_model = vehicle_model;
	}

	public Integer getVehicle_total_volume() {
		return vehicle_total_volume;
	}

	public void setVehicle_total_volume(Integer vehicle_total_volume) {
		this.vehicle_total_volume = vehicle_total_volume;
	}

	public Integer getVehicle_connected() {
		return vehicle_connected;
	}

	public void setVehicle_connected(Integer vehicle_connected) {
		this.vehicle_connected = vehicle_connected;
	}

	public Integer getVehicle_software_updates() {
		return vehicle_software_updates;
	}

	public void setVehicle_software_updates(Integer vehicle_software_updates) {
		this.vehicle_software_updates = vehicle_software_updates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vehicle_id == null) ? 0 : vehicle_id.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (vehicle_id == null) {
			if (other.vehicle_id != null)
				return false;
		} else if (!vehicle_id.equals(other.vehicle_id))
			return false;
		return true;
	}
}
