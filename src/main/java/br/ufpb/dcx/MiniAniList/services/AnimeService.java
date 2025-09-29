package br.ufpb.dcx.MiniAniList.services;

import br.ufpb.dcx.MiniAniList.dtos.anime.AnimeRequestDTO;
import br.ufpb.dcx.MiniAniList.dtos.anime.AnimeResponseDTO;
import br.ufpb.dcx.MiniAniList.exceptions.ItemNotFoundExeption;
import br.ufpb.dcx.MiniAniList.models.Anime;
import br.ufpb.dcx.MiniAniList.repository.AnimeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        anime.setDescricao(dto.getDescricao());

        Anime salvo = animeRepository.save(anime);
        return mapParaResponseDTO(salvo);
    }

    public Page<AnimeResponseDTO> listarAnimes(Pageable pageable) {
        return animeRepository.findAll(pageable)
                .map(this::mapParaResponseDTO);
    }

    public AnimeResponseDTO buscarAnimePorId(Long id) {
        Anime anime = animeRepository.findById(id).orElseThrow(() -> new ItemNotFoundExeption("Anime não encontrado"));
        return mapParaResponseDTO(anime);
    }

    public AnimeResponseDTO atualizarAnime(Long id, AnimeRequestDTO dto) {
        Anime anime = animeRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundExeption("Anime não encontrado"));

        anime.setTitulo(dto.getTitulo());
        anime.setGenero(dto.getGenero());
        anime.setEpisodios(dto.getEpisodios());
        anime.setDescricao(dto.getDescricao());

        Anime atualizado = animeRepository.save(anime);
        return mapParaResponseDTO(atualizado);
    }

    public void deletarAnime(Long id) {
        Anime anime = animeRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundExeption("Anime não encontrado"));
        animeRepository.delete(anime);
    }

    private AnimeResponseDTO mapParaResponseDTO(Anime anime) {
        AnimeResponseDTO dto = new AnimeResponseDTO();
        dto.setId(anime.getId());
        dto.setTitulo(anime.getTitulo());
        dto.setGenero(anime.getGenero());
        dto.setEpisodios(anime.getEpisodios());
        dto.setDescricao(anime.getDescricao());

        return dto;
    }
}
