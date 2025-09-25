package br.ufpb.dcx.MiniAniList.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioResponseDTO {

    @NotNull
    @NotBlank
    private Long id;

    @Size(max = 256)
    private String nome;
    private String email;
}
