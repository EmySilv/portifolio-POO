package br.com.fecaf.backend.Controller;

import br.com.fecaf.backend.Model.Models;
import br.com.fecaf.backend.Services.ModelsServices;

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
@RequestMapping("/api/models")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*")
public class ModelsController {

    @Autowired
    private ModelsServices modelsServices;

    @GetMapping("/list")
    public List<Models> listModels() {
        return modelsServices.listModels();
    }

    @PostMapping("/add")
    public ResponseEntity<Models> createModel(@RequestBody Models model) {
        Models createdModel = modelsServices.saveModels(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Integer id) {
        modelsServices.deleteModels(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Models> updateModel(@PathVariable int id, @RequestBody Models model) {
        try {
            Models updatedModel = modelsServices.updateModels(id, model);
            return ResponseEntity.ok(updatedModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
