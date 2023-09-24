package com.alura.controladores;

import com.alura.modelo.categoria.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categoria")
public class controladorCategoria{
   @Autowired
   repositorioCategoria repC;
   @PostMapping
   public ResponseEntity<datosMostrarC> guardarC(@RequestBody @Valid datosGuardarC datC,
                                                 UriComponentsBuilder uri){
      
      Categoria categoria = repC.save(new Categoria(datC));
      
      datosMostrarC datM = new datosMostrarC(categoria);
      
      return ResponseEntity.
              created(uri.path("/categoria/{id}").
                         buildAndExpand(categoria.getId()).
                         toUri()).
              body(datM);
   }
   @GetMapping
   public ResponseEntity<Page<datosMostrarC>> mostrarC(Pageable paginacion){
      
      return ResponseEntity.ok(repC.findAll(paginacion).
                                   map(categoria -> new datosMostrarC(categoria)));
   }
   @GetMapping("/{id}")
   public ResponseEntity<datosMostrarCU> mostrarC(@PathVariable Long id){
      
      return ResponseEntity.ok(
              new datosMostrarCU(
                      repC.findById(id).
                          get()));
   }
   @PutMapping
   @Transactional
   @SecurityRequirement(name = "bearer-key")
   public ResponseEntity<datosMostrarC> actualizarC(@RequestBody @Valid datosMostrarC datA){
      
      Categoria categoria = repC.getReferenceById(datA.id());
      
      categoria.actualizar(datA);
      
      return ResponseEntity.ok(new datosMostrarC(categoria));
   }
   @DeleteMapping("/{id}")
   @SecurityRequirement(name = "bearer-key")
   public ResponseEntity eliminarC(@PathVariable Long id){
      
      repC.deleteById(id);
      
      return ResponseEntity.noContent().build();
   }
}
