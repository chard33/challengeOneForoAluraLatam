package com.alura.controladores;

import com.alura.modelo.estatusT.StatusTopico;
import com.alura.modelo.topico.*;
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
@RequestMapping("/topico")
public class controladorTopico{
   @Autowired
   repositorioTopico repT;
   @PostMapping
   public ResponseEntity<datosMostrarT> guardarT(@RequestBody @Valid datosGuardarT datT,
                                                 UriComponentsBuilder uri){
      
      Topico topico = repT.save(new Topico(datT));
      
      datosMostrarT datM = new datosMostrarT(topico);
      
      return ResponseEntity.
              created(uri.path("/topico/{id}").
                         buildAndExpand(topico.getId()).
                         toUri()).
              body(datM);
   }
   @GetMapping
   public ResponseEntity<Page<datosMostrarT>> mostrarT(Pageable paginacion){
      
      return ResponseEntity.ok(
              repT.findByStatusNot(StatusTopico.CERRADO, paginacion).
                  map(topico -> new datosMostrarT(topico)));
   }
   @GetMapping("/{id}")
   public ResponseEntity<datosMostrarTU> mostrarT(@PathVariable Long id){
      
      return ResponseEntity.ok(
              new datosMostrarTU(
                      repT.findById(id).
                          get()));
   }
   @PutMapping
   @Transactional
   @SecurityRequirement(name = "bearer-key")
   public ResponseEntity<datosMostrarT> actualizarT(@RequestBody @Valid datosMostrarT datA){
      
      Topico topico = repT.getReferenceById(datA.id());
      
      topico.actualizar(datA);
      
      return ResponseEntity.ok(new datosMostrarT(topico));
   }
   @DeleteMapping("/{id}")
   @Transactional
   @SecurityRequirement(name = "bearer-key")
   public ResponseEntity eliminarT(@PathVariable Long id){
      
      Topico topico = repT.getReferenceById(id);
      
      topico.cerrar();
      
      return ResponseEntity.noContent().build();
   }
}
