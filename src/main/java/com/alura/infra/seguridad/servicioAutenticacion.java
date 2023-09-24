package com.alura.infra.seguridad;

import com.alura.modelo.usuario.repositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class servicioAutenticacion
        implements UserDetailsService{
   @Autowired
   private repositorioUsuario repU;
   @Override
   public UserDetails loadUserByUsername(String username) throws
                                                          UsernameNotFoundException{

      return repU.findByEmail(username);
   }
}
