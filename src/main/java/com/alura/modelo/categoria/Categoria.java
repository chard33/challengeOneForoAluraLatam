package com.alura.modelo.categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria{
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;
   String nombre;
   
   public Categoria(datosGuardarC datC){
      
      this.nombre = datC.nombre();
   }
   
   public void actualizar(datosMostrarC datA){
      
      this.nombre = datA.nombre();
   }
}
