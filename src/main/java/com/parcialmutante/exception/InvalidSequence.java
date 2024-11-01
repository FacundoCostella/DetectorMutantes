package com.parcialmutante.exception;


//Excepcion para manejar secuencias no validas de ADN
public class InvalidSequence extends RuntimeException {
    public InvalidSequence(String message) {
        super(message);
    }
}

