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
}

