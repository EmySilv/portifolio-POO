package br.com.fecaf.backend.Repository;

import br.com.fecaf.backend.Model.Models;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelsRepository extends JpaRepository<Models, Integer> {    
}
