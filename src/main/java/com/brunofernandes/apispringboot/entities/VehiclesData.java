package com.brunofernandes.apispringboot.entities;

import java.io.Serializable;
import java.util.List;

public class VehiclesData implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<VehicleData> vehicleData;

	public VehiclesData() {
	}

	public VehiclesData(List<VehicleData> vehicleData) {
		super();
		this.vehicleData = vehicleData;
	}

	public List<VehicleData> getVehicledata() {
		return vehicleData;
	}

	public void setVehicledata(List<VehicleData> vehicleData) {
		this.vehicleData = vehicleData;
	}
}
