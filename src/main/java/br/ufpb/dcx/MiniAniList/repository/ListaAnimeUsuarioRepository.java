package br.ufpb.dcx.MiniAniList.repository;

import br.ufpb.dcx.MiniAniList.models.AnimeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ListaAnimeUsuarioRepository extends JpaRepository<AnimeUsuario, Long> {
    List<AnimeUsuario> findByUsuarioId(Long usuarioId);
    Optional<AnimeUsuario> findByUsuarioIdAndAnimeId(Long usuarioId, Long animeId);
    void deleteByUsuarioIdAndAnimeId(Long usuarioId, Long animeId);
}
