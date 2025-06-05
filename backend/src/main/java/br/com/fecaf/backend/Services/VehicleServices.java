package br.com.fecaf.backend.Services;

import br.com.fecaf.backend.Model.Vehicle;
import br.com.fecaf.backend.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServices {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> listVehicles() {
        return vehicleRepository.findAll();
    }
    
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Integer id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle updateVehicle(Integer id, Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
        
        existingVehicle.setYear(vehicle.getYear());
        existingVehicle.setColor(vehicle.getColor());
        existingVehicle.setMileage(vehicle.getMileage());
        existingVehicle.setPrice(vehicle.getPrice());
        existingVehicle.setStatus(vehicle.getStatus());

        return vehicleRepository.save(existingVehicle);
    }
}