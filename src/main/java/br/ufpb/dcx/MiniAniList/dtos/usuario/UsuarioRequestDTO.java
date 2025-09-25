package br.ufpb.dcx.MiniAniList.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {
    @NotNull
    @Size(max = 256)
    private String nome;

    @NotNull
    @Email
    @Size(max = 128)
    private String email;

    @NotNull
    @Size(min = 8, max = 128)
    private String senha;
}

