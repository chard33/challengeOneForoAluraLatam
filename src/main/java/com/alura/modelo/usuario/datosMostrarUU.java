package com.alura.modelo.usuario;

public record datosMostrarUU(
        String nombre,
        String email
){
   public datosMostrarUU(Usuario usuario){
      
      this(usuario.getNombre(),
           usuario.getEmail());
   }
}
