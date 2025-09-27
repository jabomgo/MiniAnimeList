package br.ufpb.dcx.MiniAniList.dtos.listaUsuario;

public class ListaUsuarioResponseDTO {
    private String nomeAnime;
    private String status;

    public String getNomeAnime() {
        return nomeAnime;
    }

    public void setNomeAnime(String nomeAnime) {
        this.nomeAnime = nomeAnime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
