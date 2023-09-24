package com.alura.modelo.topico;

import com.alura.modelo.estatusT.StatusTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "topico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fecha_creacion = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
	private Long id_autor;
	private Long id_curso;
	
	public Topico(datosGuardarT datT){
		
		this.titulo = datT.titulo();
		this.mensaje = datT.mensaje();
		this.id_autor = datT.id_autor();
		this.id_curso = datT.id_curso();
	}
	
	public void actualizar(datosMostrarT datA){
		
		if(datA.titulo() != null){
			
			this.titulo = datA.titulo();
		}
		if(datA.mensaje() != null){
			
			this.mensaje = datA.mensaje();
		}
		if(datA.fecha_creacion() != null){
			
			this.fecha_creacion = datA.fecha_creacion();
		}
		if(datA.status() != null){
			
			this.status = datA.status();
		}
		if(datA.id_autor() != null){
			
			this.id_autor = datA.id_autor();
		}
		if(datA.id_curso() != null){
			
			this.id_curso = datA.id_curso();
		}
	}
	
	public void cerrar(){
		
		this.status = StatusTopico.CERRADO;
	}
}
