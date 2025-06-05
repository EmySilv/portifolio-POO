package br.com.fecaf.backend.Services;

import br.com.fecaf.backend.Model.Models;
import br.com.fecaf.backend.Repository.ModelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelsServices {

    @Autowired
    private ModelsRepository modelsRepository;

    public List<Models> listModels() {
        return modelsRepository.findAll();
    }

    public Models saveModels(Models model) {
        return modelsRepository.save(model);
    }

    public void deleteModels(Integer id) {
        modelsRepository.deleteById(id);
    }

    public Models updateModels(Integer id, Models model) {
        Models existingModels = modelsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found with id: " + id));

        existingModels.setName(model.getName());

        return modelsRepository.save(existingModels);
    }

}
