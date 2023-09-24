package com.alura.modelo.curso;

public record datosMostrarCU(
        String nombre,
        Long id_categoria){
   public datosMostrarCU(Curso curso){
      
      this(curso.getNombre(),
           curso.getId_categoria());
   }
}
