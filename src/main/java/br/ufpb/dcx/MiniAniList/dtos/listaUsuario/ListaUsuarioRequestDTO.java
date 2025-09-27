package br.ufpb.dcx.MiniAniList.dtos.listaUsuario;

import jakarta.validation.constraints.NotNull;

public class ListaUsuarioRequestDTO {
    @NotNull
    private Long usuarioId;

    @NotNull
    private Long animeId;

    private String status;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
