package com.alura.modelo.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private Long id_categoria;
	
	public Curso(datosGuardarC datC){
	
		this.nombre = datC.nombre();
		this.id_categoria = datC.id_categoria();
	}
	
	public void actualizar(datosMostrarC datC){
		
		if(datC.nombre() != null){
			
			this.nombre = datC.nombre();
		}
		if(datC.id_categoria() != null){
			
			this.id_categoria = datC.id_categoria();
		}
	}
}
