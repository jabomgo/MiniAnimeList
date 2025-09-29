package br.ufpb.dcx.MiniAniList.repository;

import br.ufpb.dcx.MiniAniList.models.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Page<Anime> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
    Page<Anime> findByGeneroContainingIgnoreCase(String genero, Pageable pageable);
    Page<Anime> findByTituloContainingIgnoreCaseAndGeneroContainingIgnoreCase(String titulo, String genero, Pageable pageable);
}
