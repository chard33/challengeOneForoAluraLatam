package com.alura.infra.seguridad;

import com.alura.modelo.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class servicioToken{
   @Value("${api.security.secret}")
   private String secretoP;
   
   public String generarToken(Usuario usuario){
      
      try {
         
         Algorithm algorithm = Algorithm.HMAC256(secretoP);
         
         return JWT.create()
                   .withIssuer("Alura_foro")
                   .withSubject(usuario.getEmail())
                   .withClaim("ID", usuario.getId())
                   .withExpiresAt(generarFechaExpiracion())
                   .sign(algorithm);
          
      } catch (JWTCreationException exception){
         
         throw new RuntimeException();
      }
   }
   
   private Instant generarFechaExpiracion(){
      
      return LocalDateTime.
              now().
              plusHours(2).
              toInstant(ZoneOffset.of("-06:00"));
   }
   
   public String obtenerSujeto(String token){

      if(token == null){

         throw new RuntimeException("Token nulo");
      }
      
      DecodedJWT sujeto = null;
      
      try {
         Algorithm algorithm = Algorithm.HMAC256(secretoP);
         JWTVerifier verifier = JWT.require(algorithm)
                                   .withIssuer("Alura_foro")
                                   .build();
         
         sujeto = verifier.verify(token);
         
      } catch (JWTVerificationException exception){
         
         System.out.println(exception);
      }
      
      if(sujeto.getSubject() == null){
         
         throw new RuntimeException("Verificacion fallida");
      }
      
      return sujeto.getSubject();
   }
}
