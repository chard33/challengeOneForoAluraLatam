package com.alura.infra.seguridad;

import com.alura.modelo.usuario.repositorioUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class filtroSeguridadEndP
        extends OncePerRequestFilter{
   @Autowired
   private servicioToken servT;
   @Autowired
   private repositorioUsuario repU;
   @Override
   protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain) throws
                                                            ServletException,
                                                            IOException{
      var encabezadoAut = request.getHeader("Authorization");

      if(encabezadoAut != null){

         var token = encabezadoAut.replace("Bearer ", "");

         var sujeto = servT.obtenerSujeto(token);

         if(sujeto != null){

            var usuario = repU.findByEmail(sujeto);

            var usuarioAut = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    usuario.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(usuarioAut);
         }
      }

      filterChain.doFilter(request, response);
   }
}
