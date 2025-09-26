package br.ufpb.dcx.MiniAniList.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioCreateDTO {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

