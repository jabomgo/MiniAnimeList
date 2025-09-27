package br.ufpb.dcx.MiniAniList.dtos.anime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnimeRequestDTO {
    @Size(max = 256)
    private String titulo;

    @Size(max = 256)
    private String genero;

    @NotNull
    private int episodios;

    @Size(max = 256)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEpisodios() {
        return episodios;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }

}

