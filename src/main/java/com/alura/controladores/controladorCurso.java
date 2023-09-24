package com.alura.controladores;

import com.alura.modelo.curso.*;
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
@RequestMapping("/curso")
public class controladorCurso{
   @Autowired
   repositorioCurso repC;
   @PostMapping
   public ResponseEntity<datosMostrarC> guardarC(@RequestBody @Valid datosGuardarC datC,
                                                 UriComponentsBuilder uri){
      
      Curso curso = repC.save(new Curso(datC));
      
      datosMostrarC datM = new datosMostrarC(curso);
      
      return ResponseEntity.
              created(uri.path("/curso/{id}").
                         buildAndExpand(curso.getId()).
                         toUri()).
              body(datM);
   }
   @GetMapping
   public ResponseEntity<Page<datosMostrarC>> mostrarC(Pageable paginacion){
      
      return ResponseEntity.ok(repC.findAll(paginacion).
                                   map(curso -> new datosMostrarC(curso)));
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
   public ResponseEntity<datosMostrarC> actualizarC(@RequestBody @Valid datosMostrarC datC){
      
      Curso curso = repC.getReferenceById(datC.id());
      
      curso.actualizar(datC);
      
      return ResponseEntity.ok(new datosMostrarC(curso));
   }
   @DeleteMapping("/{id}")
   @SecurityRequirement(name = "bearer-key")
   public ResponseEntity eliminarC(@PathVariable Long id){
      
      repC.deleteById(id);
      
      return ResponseEntity.noContent().build();
   }
}
