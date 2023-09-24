package com.alura.infra.errores;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class tratadorErrores{
   @ExceptionHandler(NoSuchElementException.class)
   public ResponseEntity tratarError404(){
      
      return ResponseEntity.notFound().build();
   }
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity tratarError400(MethodArgumentNotValidException ex){
      
      var errores = ex.getFieldErrors().
                      stream().
                      map(e -> new datosErrorM(e)).
                      toList();
      
      return ResponseEntity.
              badRequest().
              body(errores);
   }
   
   public record datosErrorM(String campo, String mensaje){
      
      public datosErrorM(FieldError fErr){
         
         this(fErr.getField(),
              fErr.getDefaultMessage());
      }
   }
   
   @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
   public ResponseEntity tratarErrorDuplicados(SQLIntegrityConstraintViolationException duplicados){
      
      var error = duplicados.getMessage().replaceAll("\\s[k].+\\.", " '");
      
      return ResponseEntity.badRequest().body(error);
   }
}
