package com.brunofernandes.apispringboot.resources;

import java.net.URI;
import java.util.ArrayList;
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

import com.brunofernandes.apispringboot.entities.VehicleData;
import com.brunofernandes.apispringboot.entities.VehiclesData;
import com.brunofernandes.apispringboot.services.VehicleDataService;

@RestController
@RequestMapping(value = "/vehiclesdata")
public class VehicleDataResource {

	@Autowired
	private VehicleDataService service;

	@GetMapping
	public ResponseEntity<VehiclesData> findAll() {
		List<VehicleData> list = service.findAll();
		VehiclesData vehiclesData = new VehiclesData(list);
		return ResponseEntity.ok().body(vehiclesData);
	}

	@GetMapping(value = "/{vehicledata_vin}")
	public ResponseEntity<VehiclesData> findByVin(@PathVariable String vehicledata_vin) {
		VehicleData obj = service.findByVin(vehicledata_vin);
		if (obj.getVehicledata_id() != null) {
			List<VehicleData> list = new ArrayList<VehicleData>();
			list.add(obj);
			VehiclesData vehiclesData = new VehiclesData(list);
			return ResponseEntity.ok().body(vehiclesData);
		} else {
			List<VehicleData> list = new ArrayList<VehicleData>();
			VehiclesData vehiclesData = new VehiclesData(list);
			return ResponseEntity.ok().body(vehiclesData);
		}
	}

	/*
	 * @GetMapping(value = "/{id}") public ResponseEntity<VehicleData>
	 * findById(@PathVariable Long id) { VehicleData obj = service.findById(id);
	 * return ResponseEntity.ok().body(obj); }
	 */

	@PostMapping
	public ResponseEntity<VehicleData> insert(@RequestBody VehicleData obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getVehicledata_id())
				.toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<VehicleData> updateVehicleData(@RequestBody VehicleData obj) {
		obj = service.updateVehicleData(obj);
		return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<VehicleData> update(@PathVariable Long id, @RequestBody VehicleData obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{vehicledata_vin}")
	public ResponseEntity<Void> deleteByVin(@PathVariable String vehicledata_vin) {
		service.deleteByVin(vehicledata_vin);
		return ResponseEntity.noContent().build();
	}

	/*
	 * @DeleteMapping(value = "/{id}") public ResponseEntity<Void>
	 * delete(@PathVariable Long id) { service.delete(id); return
	 * ResponseEntity.noContent().build(); }
	 */
}
