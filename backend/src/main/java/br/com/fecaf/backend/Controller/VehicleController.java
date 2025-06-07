package br.com.fecaf.backend.Controller;

import br.com.fecaf.backend.Model.Vehicle;
import br.com.fecaf.backend.Services.VehicleServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*")
public class VehicleController {
    
    @Autowired
    private VehicleServices vehicleServices;

    @GetMapping("/list")
    public List<Vehicle> listVehicles() {
        return vehicleServices.listVehicles();
    }

    @PostMapping("/add")
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle vehicle){
        Vehicle saveVehicle = vehicleServices.saveVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveVehicle);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        vehicleServices.deleteVehicle(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicle) {
       try{
            Vehicle updatedVehicle = vehicleServices.updateVehicle(id, vehicle);
            return ResponseEntity.ok(updatedVehicle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }
}