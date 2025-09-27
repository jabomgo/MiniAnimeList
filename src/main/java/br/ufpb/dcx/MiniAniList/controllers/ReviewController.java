package br.ufpb.dcx.MiniAniList.controllers;

import br.ufpb.dcx.MiniAniList.dtos.review.ReviewRequestDTO;
import br.ufpb.dcx.MiniAniList.dtos.review.ReviewResponseDTO;
import br.ufpb.dcx.MiniAniList.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(path = "/reviews")
    public ReviewResponseDTO criarReview(@Valid @RequestBody ReviewRequestDTO requestDTO) {
        return reviewService.criarReview(requestDTO);
    }

    @GetMapping("/reviews/{id}")
    public ReviewResponseDTO buscarReviewPorId(@PathVariable Long id) {
        return reviewService.buscarReviewPorId(id);
    }

    @GetMapping("/reviews/anime/{animeId}")
    public List<ReviewResponseDTO> listarReviewsPorAnime(@PathVariable Long animeId) {
        return reviewService.listarReviewsPorAnime(animeId);
    }

    @PutMapping("/reviews/{id}")
    public ReviewResponseDTO atualizarReview(@PathVariable Long id, @Valid @RequestBody ReviewRequestDTO requestDTO) {
        return reviewService.atualizarReview(id, requestDTO);
    }

    @DeleteMapping("/reviews/{id}")
    public void deletarReview(@PathVariable Long id) {
        reviewService.deletarReview(id);
    }
}
