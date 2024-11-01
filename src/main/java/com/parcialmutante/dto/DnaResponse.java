package com.parcialmutante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DnaResponse {
    private boolean isMutant;               //Devuelvo si es mutante o no

    public boolean isMutant (){
        return isMutant;
    }
}
