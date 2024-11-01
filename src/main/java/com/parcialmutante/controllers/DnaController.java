package com.parcialmutante.controllers;

import com.parcialmutante.dto.DnaRequest;
import com.parcialmutante.dto.DnaResponse;
import com.parcialmutante.exception.InvalidSequence;
import com.parcialmutante.services.DnaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/mutant/")
public class DnaController {

    private DnaService dnaService;

    //INYECCION DE DEPENDECIAS
    @Autowired
    public DnaController(DnaService dnaService){
        this.dnaService = dnaService;
    }

    //METODO POST
    @PostMapping
    public ResponseEntity<DnaResponse> isMutant (@Valid @RequestBody DnaRequest dnaRequest){
        boolean isMutant = dnaService.analizarDna(dnaRequest.getDna());
        DnaResponse dnaResponse = new DnaResponse(isMutant);

        if (isMutant){
            return ResponseEntity.ok(dnaResponse);                                  //HTTP 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dnaResponse);   //HTTP 403 Forbidden
        }
    }

    // Manejador de excepción para InvalidSequence
    @ExceptionHandler(InvalidSequence.class)
    public ResponseEntity<String> handleInvalidDna(InvalidSequence e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
