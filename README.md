# Primer parcial de Desarrollo de Software 
## Introducción:
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Mens. 
Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN. 
Para eso te ha pedido crear un programa con un método o función con la siguiente firma: 

    boolean isMutant(String[] dna); 


## Funcionamiento:
Recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. 

Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

Sabrás si un humano es mutante o no, si encuentras MÁS DE UNA secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical. 

> Un ejemplo de secuencia válida como mutante es: {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

Cada cadena del array representa una fila de la matriz.

Al cargar la matriz, se emplea una función la cual verifica si existen más de una secuencia de cuatro letras iguales, ya sea de forma oblicua, horizontal o vertical. 
Si existen tales secuencias estamos en presencia de un ADN correspondiente a un mutante, en caso contrario es un ADN humano.

## Ejecución:
El proyecto se ha deployado en Render, se puede acceder desde el siguiente link:

https://detectormutantes-myuc.onrender.com

## EndPoints:
- **POST /mutant/ :** Recibe un JSON con las cadenas correspondientes a la matriz a verificar, por ejemplo:
```json
{
    "dna": [
        "AAAAGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CACCGA",
        "TCACTG"
    ]
}
```
- **GET /stats/ :** Devuelve un JSON con la cantidad de humanos y mutantes encontrados, por ejemplo:
```json
{
    "countMutant": 4,
    "countHuman": 2,
    "totalDeRegistros": 6,
    "porcentajeDeMutantes": 66.66667
}
```
## Ejemplos de distintas secuencias de ADN:
- Matriz con secuencia ADN correspondiente a un **Mutante**:
```json
{
    "dna": [
        "ATGCGA",
        "ATGTGC",
        "ATATGT",
        "AGATGG",
        "CCGCTA",
        "TCGCTA"
    ]
}
```
- Matriz con secuencia ADN correspondiente a un **Mutante**:
```json
{
    "dna": [
        "TGCATC",
        "TAAGCG",
        "CTACGG",
        "GCCATC",
        "ACAGAC",
        "TGCTAT"
    ]
}

```
- Matriz con secuencia ADN correspondiente a un **Humano**:
```json
{
    "dna": [
        "TGCATC",
        "TAAGCG",
        "CTTCGG",
        "GCCATC",
        "ACAGAC",
        "TGCTAT"
    ]
}
```
- Matriz con secuencia ADN correspondiente a un **Humano**:
```json
{
    "dna": [
        "TGCATC",
        "TAAGCG",
        "CTGCGG",
        "GTCATC",
        "ACAGAC",
        "TGCTAT"
    ]
}
```
