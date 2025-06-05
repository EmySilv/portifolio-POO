package br.com.fecaf.backend.Repository;

import br.com.fecaf.backend.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}