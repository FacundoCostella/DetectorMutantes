package com.parcialmutante;

import com.parcialmutante.services.DnaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MutanteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutanteApplication.class, args);

		System.out.println("APLICACION ANDANDO");


	}

}
