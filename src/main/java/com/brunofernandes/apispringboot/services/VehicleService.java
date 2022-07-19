package com.brunofernandes.apispringboot.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.brunofernandes.apispringboot.entities.Vehicle;
import com.brunofernandes.apispringboot.repositories.VehicleRepository;
import com.brunofernandes.apispringboot.services.exceptions.DatabaseException;
import com.brunofernandes.apispringboot.services.exceptions.ResourceNotFoundException;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository repository;

	public List<Vehicle> findAll() {
		return repository.findAll();
	}

	public Vehicle findById(Long id) {
		Optional<Vehicle> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Vehicle insert(Vehicle obj) {
		List<Vehicle> list = this.findAll();
		for (Vehicle v : list) {
			if (v.getVehicle_model().equals(obj.getVehicle_model())) {
				throw new DatabaseException("Model already registered");
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

	@SuppressWarnings("deprecation")
	public Vehicle update(Long id, Vehicle obj) {
		try {
			Vehicle entity = repository.getOne(id);
			List<Vehicle> list = this.findAll();
			for (Vehicle v : list) {
				if (v.getVehicle_model().equals(obj.getVehicle_model())) {
					if (v.getVehicle_id() == id) {
						UpdateData(entity, obj);
						return repository.save(entity);
					} else {
						throw new DatabaseException("Model already registered");
					}
				}
			}
			UpdateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void UpdateData(Vehicle entity, Vehicle obj) {
		entity.setVehicle_model(obj.getVehicle_model());
		entity.setVehicle_total_volume(obj.getVehicle_total_volume());
		entity.setVehicle_connected(obj.getVehicle_connected());
		entity.setVehicle_software_updates(obj.getVehicle_software_updates());
	}
}
