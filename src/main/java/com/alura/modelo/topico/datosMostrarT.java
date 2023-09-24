package com.alura.modelo.topico;

import com.alura.modelo.estatusT.StatusTopico;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record datosMostrarT(
        @NotNull Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        StatusTopico status,
        Long id_autor,
        Long id_curso
){
   public datosMostrarT(Topico topico){
      
      this(topico.getId(),
           topico.getTitulo(),
           topico.getMensaje(),
           topico.getFecha_creacion(),
           topico.getStatus(),
           topico.getId_autor(),
           topico.getId_curso());
   }
}
