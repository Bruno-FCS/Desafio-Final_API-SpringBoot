package com.brunofernandes.apispringboot.entities;

import java.io.Serializable;
import java.util.List;

public class Vehicles implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Vehicle> vehicles;

	public Vehicles() {
	}

	public Vehicles(List<Vehicle> vehicles) {
		super();
		this.vehicles = vehicles;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}
