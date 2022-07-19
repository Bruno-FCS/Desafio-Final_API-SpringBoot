package com.brunofernandes.apispringboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brunofernandes.apispringboot.entities.VehicleData;

@Repository
public interface VehicleDataRepository extends JpaRepository<VehicleData, Long> {

	@Query(value = "SELECT * FROM Vehicle_Data vd WHERE vd.vehicledata_vin = ?1", nativeQuery = true)
	public Optional<VehicleData> findByVin(String vehicledata_vin);
}
