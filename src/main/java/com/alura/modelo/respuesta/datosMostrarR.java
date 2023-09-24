package com.alura.modelo.respuesta;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record datosMostrarR(
        
        @NotNull Long id,
        String mensaje,
        Long id_topico,
        LocalDateTime fecha_creacion,
        Long id_autor,
        Boolean solucion
){
   public datosMostrarR(Respuesta respuesta){
      
      this(respuesta.getId(),
           respuesta.getMensaje(),
           respuesta.getId_topico(),
           respuesta.getFecha_creacion(),
           respuesta.getId_autor(),
           respuesta.getSolucion());
   }
}
