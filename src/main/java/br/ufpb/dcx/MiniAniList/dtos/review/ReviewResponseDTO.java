package br.ufpb.dcx.MiniAniList.dtos.review;

public class ReviewResponseDTO {
    private Long id;
    private float nota;
    private String comentario;
    private String nomeUsuario;
    private String tituloAnime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTituloAnime() {
        return tituloAnime;
    }

    public void setTituloAnime(String tituloAnime) {
        this.tituloAnime = tituloAnime;
    }
}
