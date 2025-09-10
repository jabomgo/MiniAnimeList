package br.ufpb.dcx.MiniAniList.models;

import jakarta.persistence.*;

@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String titulo;

    @Column
    private String genero;

    @Column
    private int episodios;
}
