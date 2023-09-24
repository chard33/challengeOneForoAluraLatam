package com.alura.infra.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class configuracionSeguridad{
   @Autowired
   private filtroSeguridadEndP filtEnP;
   @Bean
   public SecurityFilterChain filtroSeguridad(HttpSecurity httpS) throws
                                                                  Exception{

      return httpS.
              csrf().
              disable().
              sessionManagement().
              sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .authorizeRequests()
              .requestMatchers(HttpMethod.POST,
                               "/login",
                               "/usuario",
                               "/categoria",
                               "/curso",
                               "/respuesta",
                               "/topico")
              .permitAll()
              .requestMatchers( //HttpMethod.GET default
                      "/usuario",
                                "/usuario/{id}",
                                "/categoria",
                                "/categoria/{id}",
                                "/curso",
                                "/curso/{id}",
                                "/respuesta",
                                "/respuesta/{id}",
                                "/topico",
                                "/topico/{id}",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-ui/**")
              .permitAll()
              .anyRequest()
              .authenticated()
              .and()
              .addFilterBefore(filtEnP, UsernamePasswordAuthenticationFilter.class)
              .build();
   }
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration autC) throws Exception {

      return autC.getAuthenticationManager();
   }
   @Bean
   public PasswordEncoder passEnc(){

      return new BCryptPasswordEncoder();
   }
}
