package com.alura.modelo.usuario;

import jakarta.validation.constraints.NotBlank;

public record datosAutenticarU(
        @NotBlank String email,
        @NotBlank String contrasena
) {
}
