package com.alura.modelo.respuesta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Entity
@Table(name = "respuesta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuesta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;
	private Long id_topico;
	private LocalDateTime fecha_creacion = LocalDateTime.now();
	private Long id_autor;
	private Boolean solucion = false;
	
	public Respuesta(datosGuardarR datR){
		
		this.mensaje = datR.mensaje();
		this.id_topico = datR.id_topico();
		this.id_autor = datR.id_autor();
	}
	
	public void actualizar(datosMostrarR datA){
		
		if(datA.mensaje() != null){
			
			this.mensaje = datA.mensaje();
		}
		if(datA.solucion() != null){
			
			this.solucion = datA.solucion();
		}
	}
}
