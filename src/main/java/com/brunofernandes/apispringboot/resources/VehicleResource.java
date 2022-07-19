package com.brunofernandes.apispringboot.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brunofernandes.apispringboot.entities.Vehicle;
import com.brunofernandes.apispringboot.entities.Vehicles;
import com.brunofernandes.apispringboot.services.VehicleService;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleResource {

	@Autowired
	private VehicleService service;

	@GetMapping
	public ResponseEntity<Vehicles> findAll() {
		List<Vehicle> list = service.findAll();
		Vehicles vehicles = new Vehicles(list);
		return ResponseEntity.ok().body(vehicles);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Vehicle> findById(@PathVariable Long id) {
		Vehicle obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Vehicle> insert(@RequestBody Vehicle obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getVehicle_id())
				.toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
