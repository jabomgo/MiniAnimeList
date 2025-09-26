package br.ufpb.dcx.MiniAniList.repository;

import br.ufpb.dcx.MiniAniList.models.AnimeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaAnimeUsuarioRepository extends JpaRepository<AnimeUsuario, Long> {
}
