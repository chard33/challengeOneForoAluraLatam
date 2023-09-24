package com.alura.modelo.categoria;

import jakarta.validation.constraints.NotNull;

public record datosMostrarC(
        @NotNull Long id,
        String nombre
){
   public datosMostrarC(Categoria categoria){
      
      this(categoria.id, categoria.nombre);
   }
}
