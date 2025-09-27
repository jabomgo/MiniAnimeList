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

    @Column(length = 256)
    @Size(max = 256)
    private String descricao;

    @Column(nullable = false, length = 256)
    @NotNull
    private String genero;

    @Column(nullable = false)
    @NotNull
    private int episodios;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnimeUsuario> animesUsuario = new ArrayList<>();

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<AnimeUsuario> getAnimesUsuario() {
        return animesUsuario;
    }

    public void setAnimesUsuario(List<AnimeUsuario> animesUsuario) {
        this.animesUsuario = animesUsuario;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<AnimeUsuario> getListaUsuarios() {
        return animesUsuario;
    }

    public void setListaUsuarios(List<AnimeUsuario> animesUsuario) {
        this.animesUsuario = animesUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
