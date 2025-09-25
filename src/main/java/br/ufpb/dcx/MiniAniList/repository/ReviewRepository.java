package br.ufpb.dcx.MiniAniList.repository;

import br.ufpb.dcx.MiniAniList.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByAnimeId(Long animeId);
}
