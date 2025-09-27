package br.ufpb.dcx.MiniAniList.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthRequestDTO {
    @NotBlank(message = "O email não pode ficar em branco")
    @Email
    private String email;

    @NotBlank(message = "A senha não pode ficar em branco")
    private String senha;

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