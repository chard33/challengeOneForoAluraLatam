package com.alura.modelo.usuario;

import jakarta.validation.constraints.NotNull;

public record datosMostrarU(
        @NotNull Long id,
        String nombre,
        String email
){
   
   
   public datosMostrarU(Usuario usuario){
      
      this(usuario.getId(),
           usuario.getNombre(),
           usuario.getEmail());
   }
}
