package br.ufpb.dcx.MiniAniList.controllers;

import br.ufpb.dcx.MiniAniList.dtos.anime.AnimeRequestDTO;
import br.ufpb.dcx.MiniAniList.dtos.anime.AnimeResponseDTO;
import br.ufpb.dcx.MiniAniList.services.AnimeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnimeController {

    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @PostMapping(path = "/animes")
    public AnimeResponseDTO criarAnime(@Valid @RequestBody AnimeRequestDTO animeRequestDTO) {
        return animeService.criarAnime(animeRequestDTO);
    }

    @GetMapping(path = "/animes")
    public Page<AnimeResponseDTO> buscarAnimes(Pageable pageable){
        return animeService.listarAnimes(pageable);
    }

    @GetMapping(path = "/animes/{animeId}")
    public AnimeResponseDTO buscarAnimePorId(@PathVariable Long animeId){
        return animeService.buscarAnimePorId(animeId);
    }

    @PutMapping(path = "/animes/{animeId}")
    public AnimeResponseDTO atualizarAnime(@PathVariable Long animeId, @Valid @RequestBody AnimeRequestDTO requestDTO){
        return animeService.atualizarAnime(animeId, requestDTO);
    }

    @DeleteMapping(path = "/animes/{animeId}")
    public void deletarAnime(@PathVariable Long animeId){
        animeService.deletarAnime(animeId);
    }
}
