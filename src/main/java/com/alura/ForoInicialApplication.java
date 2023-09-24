package com.alura;

import com.alura.controladores.controladorCurso;
import com.alura.controladores.controladorRespuesta;
import com.alura.controladores.controladorTopico;
import com.alura.controladores.controladorUsuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ForoInicialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoInicialApplication.class, args);
	}

}
