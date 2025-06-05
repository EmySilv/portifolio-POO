package br.com.fecaf.backend.Services;

import br.com.fecaf.backend.Model.Brands;
import br.com.fecaf.backend.Repository.BrandsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandsServices {

    @Autowired
    private BrandsRepository brandsRepository;

    public List<Brands> listBrands() {
        return brandsRepository.findAll();
    }

    public Brands saveBrands(Brands brands) {
        return brandsRepository.save(brands);
    }

    public void deleteBrands(Integer id) {
        brandsRepository.deleteById(id);
    }

    public Brands updateBrands(Integer id, Brands brand) {
        Brands existingBrands = brandsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));

        existingBrands.setName(brand.getName());

        return brandsRepository.save(existingBrands);
    }
}
