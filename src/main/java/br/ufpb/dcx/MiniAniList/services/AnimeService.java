package br.ufpb.dcx.MiniAniList.services;

import br.ufpb.dcx.MiniAniList.dtos.anime.AnimeRequestDTO;
import br.ufpb.dcx.MiniAniList.dtos.anime.AnimeResponseDTO;
import br.ufpb.dcx.MiniAniList.models.Anime;
import br.ufpb.dcx.MiniAniList.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimeService {

    private AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public AnimeResponseDTO criarAnime(AnimeRequestDTO dto) {
        Anime anime = new Anime();
        anime.setTitulo(dto.getTitulo());
        anime.setGenero(dto.getGenero());
        anime.setEpisodios(dto.getEpisodios());

        Anime salvo = animeRepository.save(anime);
        return mapParaResponseDTO(salvo);
    }

    public List<AnimeResponseDTO> listarAnimes() {
        return animeRepository.findAll()
                .stream()
                .map(this::mapParaResponseDTO)
                .collect(Collectors.toList());
    }

    public AnimeResponseDTO buscarAnimePorId(Long id) {
        Anime anime = animeRepository.findById(id).orElseThrow(() -> new RuntimeException("Anime não encontrado"));
        return mapParaResponseDTO(anime);
    }

    public AnimeResponseDTO atualizarAnime(Long id, AnimeRequestDTO dto) {
        Anime anime = animeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anime não encontrado"));

        anime.setTitulo(dto.getTitulo());
        anime.setGenero(dto.getGenero());
        anime.setEpisodios(dto.getEpisodios());

        Anime atualizado = animeRepository.save(anime);
        return mapParaResponseDTO(atualizado);
    }

    public void deletarAnime(Long id) {
        Anime anime = animeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anime não encontrado"));
        animeRepository.delete(anime);
    }

    private AnimeResponseDTO mapParaResponseDTO(Anime anime) {
        AnimeResponseDTO dto = new AnimeResponseDTO();
        dto.setId(anime.getId());
        dto.setTitulo(anime.getTitulo());
        dto.setGenero(anime.getGenero());
        dto.setEpisodios(anime.getEpisodios());

        return dto;
    }
}
