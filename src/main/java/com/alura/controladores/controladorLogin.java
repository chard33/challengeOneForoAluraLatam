package com.alura.controladores;

import com.alura.infra.seguridad.datosJWToken;
import com.alura.infra.seguridad.servicioToken;
import com.alura.modelo.usuario.Usuario;
import com.alura.modelo.usuario.datosAutenticarU;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class controladorLogin {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private servicioToken servT;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid datosAutenticarU guaU){

        Authentication auToken = new UsernamePasswordAuthenticationToken(
                guaU.email(),
                guaU.contrasena()
        );

        var usuarioAut = authenticationManager.authenticate(auToken);
        
        var JWToken = servT.generarToken((Usuario) usuarioAut.getPrincipal());

        return ResponseEntity.ok().body(new datosJWToken(JWToken));
    }
}
