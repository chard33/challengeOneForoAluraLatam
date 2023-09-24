package com.alura.modelo.curso;

import jakarta.validation.constraints.NotNull;

public record datosMostrarC(
        @NotNull Long id,
        String nombre,
        Long id_categoria
){
   public datosMostrarC(Curso curso){
      
      this(curso.getId(),
           curso.getNombre(),
           curso.getId_categoria());
   }
}
