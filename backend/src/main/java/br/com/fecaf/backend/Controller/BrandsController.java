package br.com.fecaf.backend.Controller;

import br.com.fecaf.backend.Model.Brands;
import br.com.fecaf.backend.Services.BrandsServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/brands")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*")
public class BrandsController {

    @Autowired
    private BrandsServices brandsServices;

    @GetMapping("/list")
    public List<Brands> listBrands() {
        return brandsServices.listBrands();
    }

    @PostMapping("/add")
    public ResponseEntity<Brands> saveBrands(@RequestBody Brands brand) {
        Brands createdBrand = brandsServices.saveBrands(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBrands(@PathVariable Integer id) {
        brandsServices.deleteBrands(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Brands> updateBrands(@PathVariable int id, @RequestBody Brands brand) {
        try {
            Brands updatedBrand = brandsServices.updateBrands(id, brand);
            return ResponseEntity.ok(updatedBrand);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}