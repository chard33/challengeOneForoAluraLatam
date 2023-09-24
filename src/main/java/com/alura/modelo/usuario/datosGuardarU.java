package com.alura.modelo.usuario;

import jakarta.validation.constraints.NotBlank;

public record datosGuardarU(
        @NotBlank String nombre,
        @NotBlank String email,
        @NotBlank String contrasena
){

}
