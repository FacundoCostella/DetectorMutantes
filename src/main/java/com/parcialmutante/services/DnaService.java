package com.parcialmutante.services;

import com.parcialmutante.entities.Dna;
import com.parcialmutante.exception.InvalidSequence;
import com.parcialmutante.repositories.DnaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DnaService {   //MUTANT SERVICE

    private static final int ADNMutante = 4;            //4 letras iguales indican que es mutante

    private final DnaRepositories dnaRepository;

    @Autowired
    public DnaService(DnaRepositories dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    //METODO PRINCIPAL BOOLEAN
    public boolean isMutant(String[] dna) {

        SequenceCheck(dna);                             //Verifico si la secuencia es válida

        int n = dna.length;                             //Con n indico la longitud de la cadena que envie (6)
        int secuenciasCont = 0;                         //Inicializo secuencias encontradas en 0

        //Llamo al metodo de FILAS
        secuenciasCont += checkFilas(dna, n);           //Como los métodos check me retornan secuenciasCont,
        if (secuenciasCont > 1) return true;            //las voy sumando para ver si hay mas de 1
                                                        // Mas de una secuencia encontrada -> true (Mutante)
        //Llamo al metodo de COLUMNAS
        secuenciasCont += checkColumns(dna, n);
        if (secuenciasCont > 1) return true;

        //Llamo al metodo de DIAGONALES
        secuenciasCont += checkDiagonalPpal(dna, n);
        if (secuenciasCont > 1) return true;

        //Llamo al metodo de CONTRADIAGONALES
        secuenciasCont += checkContrDiagonal(dna, n);
        if (secuenciasCont > 1) return true;

        //Si no se encuentran más de una secuencia retorno false
        return false;
    }


    //Metodo para validar las secuencias de ADN
    private void SequenceCheck(String[] dna) {
        int n = dna.length;

        //Verifico que cada fila que envíe tenga la cantidad correcta de caracteres
        for (String row : dna) {
            if (row.length() != n) {
                throw new InvalidSequence("Tamaño NO válido en secuencia de ADN.");
            }
            //Verifico caracteres válidos
            if (!row.matches("[ATCG]+")) {
                throw new InvalidSequence("Caracteres NO válidos en la secuencia de ADN.");
            }
        }
    }

    //----- Metodo para recorrer FILAS
    public static int checkFilas(String[] dna, int n) {
        int secMutante = 0;
        for (int i = 0; i < n; i++) {                               //Arranco variando las filas
            int contador = 1;                                       //Arranco contando el 1° caracter coincidente
            //Por cada fila vario tambien la columna
            for (int j = 1; j < n; j++) {
                if (dna[i].charAt(j) == dna[i].charAt(j - 1)) {     //Comparo a ver si las letras son iguales
                    contador++;                                     //Si son iguales sumo 1 al contador
                    if (contador == ADNMutante) {                   //Comparo para ver si llevo 4 letras iguales
                        secMutante++;                               //4 letras iguales indican 1 secuencia mutante
                        if (secMutante > 1)
                            return secMutante;                      //Si ya hay más de 1 secuencia indica que es mutante y finaliza el metodo
                        contador = 1;                               //Reinicio el contador
                    }
                } else {                                            //Si no encuentro nada sigo con el contador en 1
                    contador = 1;
                }
            }
        }
        return secMutante;                                      //Si no encontré ninguna secuencia devuelvo 0
    }

    //----- Metodo para recorrer COLUMNAS
    public static int checkColumns(String[] dna, int n) {
        int secMutante = 0;
        for (int j = 0; j < n; j++) {                               //Arranco variando las columnas
            int contador = 1;                                       //Cuento el 1° elemento
            for (int i = 1; i < n; i++) {                           //Por cada columna voy variando las filas
                if (dna[i].charAt(j) == dna[i - 1].charAt(j)) {    //Comparo para ver si la letra actual coincide con su anterior
                    contador++;                                     //Si coincide sumo 1 al contador
                    if (contador == ADNMutante) {                   //Si el contador llega a 4 es una secuencia mutante
                        secMutante++;                               //Sumo 1 a la cantidad de secuencias mutantes encontradas
                        if (secMutante > 1)
                            return secMutante;                      //Si hay más de 1 secuencia mutante, la persona es Mutante
                        contador = 1;                               //Reinicio el contador
                    }
                } else {                                            //Si no encuentro nada sigo con el contador en 1
                    contador = 1;
                }
            }
        }
        return secMutante;                                          //Si no encontré ninguna secuencia devuelvo 0
    }

    //----- Metodo para recorrer DIAGONALES
    public static int checkDiagonalPpal(String[] dna, int n) {
        int secMutante = 0;
        //Diagonal principal y diagonales por debajo de la diagonal principal
        for (int i = 0; i <= n - ADNMutante; i++) {                             //i varia hasta la tercer fila (n-4 = 2)
            int contador = 1;
            for (int j = 1; j < n - i; j++) {                                   //Arranco desde la segunda columna y comparo con la anterior
                if (dna[i + j].charAt(j) == dna[i + j - 1].charAt(j - 1)) {     //Comparo con el elemento anterior en diagonal
                    contador++;
                    if (contador == ADNMutante) {
                        secMutante++;
                        if (secMutante > 1) return secMutante;
                        contador = 1;
                    }
                } else {
                    contador = 1;
                }
            }
        }
        //Diagonales por encima de la diagonal principal
        for (int j = 1; j <= n - ADNMutante; j++) {                             //Arranco desde la segunda columna
            int contador = 1;
            for (int i = 1; i < n - j; i++) {                                   //Arranco desde la segunda fila
                if (dna[i].charAt(i + j) == dna[i - 1].charAt(i + j - 1)) {     //Comparo con el elemento anterior en diagonal
                    contador++;
                    if (contador == ADNMutante) {
                        secMutante++;
                        if (secMutante > 1) return secMutante;
                        contador = 1;
                    }
                } else {
                    contador = 1;
                }
            }
        }
        return secMutante;                                      //Si no encontré ninguna secuencia devuelvo 0
    }

    //----- Metodo para recorrer CONTRADIAGONAL
    public static int checkContrDiagonal(String[] dna, int n) {
        int secMutante = 0;
                                                                //Contradiagonal principal y contradiagonales por encima de la
        for (int i = 0; i <= n - ADNMutante; i++) {             //contradiagonal principal
            int contador = 1;
            for (int j = 1; j < n - i && j < n; j++) {
                if (dna[i + j].charAt(n - j - 1) == dna[i + j - 1].charAt(n - j)) {
                    contador++;
                    if (contador == ADNMutante) {
                        secMutante++;
                        if (secMutante > 1) return secMutante;
                        contador = 1;
                    }
                } else {
                    contador = 1;
                }
            }
        }

        //Contradiagonales por debajo de la contradiagonal principal
        for (int j = n - 2; j >= 3; j--) {                              //(Borde derecho de la matriz)
            int contador = 1;
            for (int i = 1; i <= j && i < n; i++) {
                if (dna[i].charAt(j - i) == dna[i - 1].charAt(j - i + 1)) {
                    contador++;
                    if (contador == ADNMutante) {
                        secMutante++;
                        if (secMutante > 1) return secMutante;
                        contador = 1;
                    }
                } else {
                    contador = 1;
                }
            }
        }

        return secMutante;
    }

    //Metodo para verificar si existe la secuencia en la Base de Datos
    public boolean analizarDna(String[] dna){
        String dnaSequence = String.join(",", dna);

        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);

        if (existingDna.isPresent()){
            return existingDna.get().isMutant();
        }else {
            boolean isMutant = isMutant(dna);
            Dna dna1 = Dna.builder().dna(dnaSequence).isMutant(isMutant).build();
            dnaRepository.save(dna1);
            return isMutant(dna);
        }
    }

}
