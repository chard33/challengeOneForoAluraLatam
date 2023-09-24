package com.alura.modelo.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record datosGuardarT(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long id_autor,
        @NotNull Long id_curso
){
}
