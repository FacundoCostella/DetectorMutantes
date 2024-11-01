package com.parcialmutante.services;

import com.parcialmutante.dto.StatsResponse;
import com.parcialmutante.repositories.DnaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private DnaRepositories dnaRepository;

    //INYECCION DE DEPENDECNIAS
    @Autowired
    public StatsService(DnaRepositories dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public StatsResponse getStats(){
        long countTotal = dnaRepository.count();
        long countMutant = dnaRepository.countByIsMutant(true);
        long countHuman = dnaRepository.countByIsMutant(false);

//PORCENTAJE DE MUTANTES
        float percentageMutant = countTotal == 0 ? 0 : ((float) countMutant / countTotal) * 100;

        return new StatsResponse(countMutant, countHuman, countTotal, percentageMutant);
    }
}