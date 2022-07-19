package com.brunofernandes.apispringboot.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.brunofernandes.apispringboot.entities.VehicleData;
import com.brunofernandes.apispringboot.repositories.VehicleDataRepository;
import com.brunofernandes.apispringboot.services.exceptions.DatabaseException;
import com.brunofernandes.apispringboot.services.exceptions.ResourceNotFoundException;

@Service
public class VehicleDataService {

	@Autowired
	private VehicleDataRepository repository;

	public List<VehicleData> findAll() {
		return repository.findAll();
	}

	public VehicleData findById(Long id) {
		Optional<VehicleData> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public VehicleData findByVin(String vehicledata_vin) {
		Optional<VehicleData> obj = repository.findByVin(vehicledata_vin);
		if (!obj.isEmpty()) {
			return obj.get();
		} else {
			return new VehicleData();
		}
	}

	public VehicleData insert(VehicleData obj) {
		List<VehicleData> list = this.findAll();
		for (VehicleData vd : list) {
			if (vd.getVehicledata_vin().equals(obj.getVehicledata_vin())) {
				throw new DatabaseException("VIN already registered");
			}
		}
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void deleteByVin(String vehicledata_vin) {
		VehicleData obj = this.findByVin(vehicledata_vin);
		try {
			repository.deleteById(obj.getVehicledata_id());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(vehicledata_vin);
		}
	}

	@SuppressWarnings("deprecation")
	public VehicleData update(Long id, VehicleData obj) {
		try {
			VehicleData entity = repository.getOne(id);
			List<VehicleData> list = this.findAll();
			for (VehicleData vd : list) {
				if (vd.getVehicledata_vin().equals(obj.getVehicledata_vin())) {
					if (vd.getVehicledata_id() == id) {
						UpdateData(entity, obj);
						return repository.save(entity);
					} else {
						throw new DatabaseException("VIN already registered");
					}
				}
			}
			UpdateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void UpdateData(VehicleData entity, VehicleData obj) {
		entity.setVehicledata_vin(obj.getVehicledata_vin());
		entity.setVehicledata_odometer(obj.getVehicledata_odometer());
		entity.setVehicledata_tire_pressure(obj.getVehicledata_tire_pressure());
		entity.setVehicledata_status(obj.getVehicledata_status());
		entity.setVehicledata_battery_status(obj.getVehicledata_battery_status());
		entity.setVehicledata_fuel_level(obj.getVehicledata_fuel_level());
		entity.setVehicledata_lat(obj.getVehicledata_lat());
		entity.setVehicledata_long(obj.getVehicledata_long());
	}

	public VehicleData updateVehicleData(VehicleData obj) {
		try {
			VehicleData entity = this.findByVin(obj.getVehicledata_vin());
			UpdateVehicleData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(obj.getVehicledata_vin());
		}
	}

	private void UpdateVehicleData(VehicleData entity, VehicleData obj) {
		entity.setVehicledata_odometer(obj.getVehicledata_odometer());
		entity.setVehicledata_tire_pressure(obj.getVehicledata_tire_pressure());
		entity.setVehicledata_status(obj.getVehicledata_status());
		entity.setVehicledata_battery_status(obj.getVehicledata_battery_status());
		entity.setVehicledata_fuel_level(obj.getVehicledata_fuel_level());
		entity.setVehicledata_lat(obj.getVehicledata_lat());
		entity.setVehicledata_long(obj.getVehicledata_long());
	}
}
