package com.alura.modelo.topico;

import com.alura.modelo.estatusT.StatusTopico;

import java.time.LocalDateTime;

public record datosMostrarTU(
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        StatusTopico status,
        Long id_autor,
        Long id_curso
){
   public datosMostrarTU(Topico topico){
      
      this(topico.getTitulo(),
           topico.getMensaje(),
           topico.getFecha_creacion(),
           topico.getStatus(),
           topico.getId_autor(),
           topico.getId_curso());
   }
}
