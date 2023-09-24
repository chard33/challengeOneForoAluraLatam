package com.alura.controladores;

import com.alura.modelo.usuario.*;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class controladorUsuario{
   @Autowired
   repositorioUsuario repU;

   @PostMapping
   public ResponseEntity<datosMostrarU> guardarU(@RequestBody @Valid datosGuardarU guaU,
                                  UriComponentsBuilder uri){

      Usuario usuario = repU.save(new Usuario(guaU));
      
      datosMostrarU datU = new datosMostrarU(usuario);
      
      URI url = uri.path("/usuario/{id}").
                   buildAndExpand(usuario.getId()).
                   toUri();
      
      return ResponseEntity.created(url).body(datU);
   }
   @GetMapping
   public ResponseEntity<Page<datosMostrarU>> mostrarU(Pageable paginacion){
      
      return ResponseEntity.ok(
              repU.findAll(paginacion).
                  map(usuario -> new datosMostrarU(usuario)));
   }
   @GetMapping("/{id}")
   public ResponseEntity<datosMostrarUU> mostrarU(@PathVariable Long id){
      
      return ResponseEntity.ok(
              new datosMostrarUU(
                      repU.findById(id).
                          get()));
   }
   @PutMapping
   @Transactional
   @SecurityRequirement(name = "bearer-key")
   public ResponseEntity<datosMostrarU> actualizarU(@RequestBody @Valid datosMostrarU datU){
      
      Usuario usuario = repU.getReferenceById(datU.id());
      
      usuario.actualizar(datU);
      
      return ResponseEntity.ok(new datosMostrarU(usuario));
   }
   @DeleteMapping("/{id}")
   @SecurityRequirement(name = "bearer-key")
   public ResponseEntity eliminarU(@PathVariable Long id){
      
      repU.deleteById(id);
      
      return ResponseEntity.noContent().build();
   }
}
