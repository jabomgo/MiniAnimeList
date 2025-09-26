package br.ufpb.dcx.MiniAniList.models;

import jakarta.persistence.*;

@Entity
public class AnimeUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;

    public AnimeUsuario() {}

    private String status;

    public AnimeUsuario(Usuario usuario, Anime anime, String status) {
        this.usuario = usuario;
        this.anime = anime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
