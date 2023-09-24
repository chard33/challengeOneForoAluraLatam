package com.alura.controladores;

import com.alura.modelo.respuesta.*;
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
@RequestMapping("/respuesta")
public class controladorRespuesta{
   @Autowired
   repositorioRespuesta repR;
   @PostMapping
   public ResponseEntity<datosMostrarR> guardarR(@RequestBody @Valid datosGuardarR datR,
                                                 UriComponentsBuilder uri){
      
      Respuesta respuesta = repR.save(new Respuesta(datR));
      
      datosMostrarR datM = new datosMostrarR(respuesta);
      
      return ResponseEntity.
              created(uri.path("/respuesta/{id}").
                         buildAndExpand(respuesta.getId()).
                         toUri()).
              body(datM);
   }
   @GetMapping
   public ResponseEntity<Page<datosMostrarR>> mostrarR(Pageable paginacion){
      
      return ResponseEntity.ok(
              repR.findAll(paginacion).
                  map(respuesta -> new datosMostrarR(respuesta)));
   }
   @GetMapping("/{id}")
   public ResponseEntity<datosMostrarRU> mostrarR(@PathVariable Long id){
      
      return ResponseEntity.ok(
              new datosMostrarRU(
                      repR.findById(id).
                          get()));
   }
   @PutMapping
   @Transactional
   @SecurityRequirement(name = "bearer-key")
   public ResponseEntity<datosMostrarR> actualizarR(@RequestBody @Valid datosMostrarR datA){
      
      Respuesta respuesta = repR.getReferenceById(datA.id());
      
      respuesta.actualizar(datA);
      
      return ResponseEntity.ok(new datosMostrarR(respuesta));
   }
   @DeleteMapping("/{id}")
   @SecurityRequirement(name = "bearer-key")
   public ResponseEntity eliminarR(@PathVariable Long id){
      
      repR.deleteById(id);
      
      return ResponseEntity.noContent().build();
   }
}
