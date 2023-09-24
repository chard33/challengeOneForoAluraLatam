package com.alura.modelo.categoria;

public record datosMostrarCU(String nombre){
   public datosMostrarCU(Categoria categoria){
      
      this(categoria.nombre);
   }
}
