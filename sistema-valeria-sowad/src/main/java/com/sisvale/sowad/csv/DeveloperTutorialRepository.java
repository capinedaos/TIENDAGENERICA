package com.sisvale.sowad.csv;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sisvale.sowad.entity.Plato;

@Repository
public interface DeveloperTutorialRepository extends JpaRepository<Plato, Long>{
}
