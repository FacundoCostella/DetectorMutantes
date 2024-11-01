package com.parcialmutante.repositories;

import com.parcialmutante.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaRepositories extends JpaRepository<Dna, Long> {
    Optional<Dna> findByDna(String dnaSequence);

    //METODO PARA CONTAR TODOS LOS REGISTROS ADN
    long count();


    //METODO PARA CONTAR HUMANOS O MUTANTES
    long countByIsMutant(boolean isMutant);
}
