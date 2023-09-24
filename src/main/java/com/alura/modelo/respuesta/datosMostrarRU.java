package com.alura.modelo.respuesta;

import java.time.LocalDateTime;

public record datosMostrarRU(
        String mensaje,
        Long id_topico,
        LocalDateTime fecha_creacion,
        Long id_autor,
        Boolean solucion){
   public datosMostrarRU(Respuesta respuesta){
      
      this(respuesta.getMensaje(),
           respuesta.getId_topico(),
           respuesta.getFecha_creacion(),
           respuesta.getId_autor(),
           respuesta.getSolucion());
   }
}
