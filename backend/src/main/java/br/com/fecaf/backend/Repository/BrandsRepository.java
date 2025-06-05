package br.com.fecaf.backend.Repository;

import br.com.fecaf.backend.Model.Brands;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brands, Integer> {
}