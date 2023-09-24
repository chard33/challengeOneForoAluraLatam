package com.alura.modelo.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record datosGuardarC(
        @NotBlank String nombre,
        @NotNull Long id_categoria
){
}
