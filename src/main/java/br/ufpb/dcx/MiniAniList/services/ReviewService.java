package br.ufpb.dcx.MiniAniList.services;

import br.ufpb.dcx.MiniAniList.dtos.review.ReviewRequestDTO;
import br.ufpb.dcx.MiniAniList.dtos.review.ReviewResponseDTO;
import br.ufpb.dcx.MiniAniList.models.Anime;
import br.ufpb.dcx.MiniAniList.models.Review;
import br.ufpb.dcx.MiniAniList.models.Usuario;
import br.ufpb.dcx.MiniAniList.repository.AnimeRepository;
import br.ufpb.dcx.MiniAniList.repository.ReviewRepository;
import br.ufpb.dcx.MiniAniList.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AnimeRepository animeRepository;
    private final UsuarioRepository usuarioRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         AnimeRepository animeRepository,
                         UsuarioRepository usuarioRepository) {

        this.reviewRepository = reviewRepository;
        this.animeRepository = animeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ReviewResponseDTO criarReview(ReviewRequestDTO requestDTO) {
        Anime anime = animeRepository.findById(requestDTO.getAnimeId())
                .orElseThrow(() -> new RuntimeException("Anime não encontrado"));

        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Review review = new Review();
        review.setAnime(anime);
        review.setUsuario(usuario);
        review.setComentario(requestDTO.getComentario());
        review.setNota(requestDTO.getNota());

        review = reviewRepository.save(review);
        return converteParaResponseDTO(review);
    }

    public ReviewResponseDTO buscarReviewPorId(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));
        return converteParaResponseDTO(review);
    }

    public List<ReviewResponseDTO> listarReviewsPorAnime(Long animeId) {
        List<Review> reviews = reviewRepository.findAllByAnimeId(animeId);
        return reviews.stream()
                .map(this::converteParaResponseDTO)
                .collect(Collectors.toList());
    }

    public ReviewResponseDTO atualizarReview(Long id, ReviewRequestDTO requestDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));

        if (requestDTO.getComentario() != null) {
            review.setComentario(requestDTO.getComentario());
        }

        review.setNota(requestDTO.getNota());


        review = reviewRepository.save(review);
        return converteParaResponseDTO(review);
    }

    @Transactional
    public void deletarReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));
        reviewRepository.delete(review);
    }

    private ReviewResponseDTO converteParaResponseDTO(Review review) {
        ReviewResponseDTO response = new ReviewResponseDTO();
        response.setId(review.getId());
        response.setNomeUsuario(review.getUsuario().getNome());
        response.setNota(review.getNota());
        response.setTituloAnime(review.getAnime().getTitulo());
        response.setComentario(review.getComentario());
        return response;
    }
}
