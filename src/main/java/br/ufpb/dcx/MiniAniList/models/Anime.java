package br.ufpb.dcx.MiniAniList.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 256)
    @Size(max = 256)
    private String titulo;

    @Column(nullable = false, length = 256)
    @NotNull
    private String genero;

    @Column(nullable = false)
    @NotNull
    private int episodios;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListaUsuario> listaUsuarios = new ArrayList<>();
}
