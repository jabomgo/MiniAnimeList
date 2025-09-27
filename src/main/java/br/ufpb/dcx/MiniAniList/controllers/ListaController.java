package br.ufpb.dcx.MiniAniList.controllers;

import br.ufpb.dcx.MiniAniList.dtos.listaUsuario.ListaUsuarioRequestDTO;
import br.ufpb.dcx.MiniAniList.dtos.listaUsuario.ListaUsuarioResponseDTO;
import br.ufpb.dcx.MiniAniList.models.AnimeUsuario;
import br.ufpb.dcx.MiniAniList.services.ListaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{userId}/lista")
public class ListaController {

    private final ListaService listaService;

    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @PostMapping
    public ListaUsuarioResponseDTO adicionarALista(@Valid @RequestBody ListaUsuarioRequestDTO dto){
        AnimeUsuario animeUsuario = listaService.adicionarAnimeNaLista(dto);

        return listaUsuarioResponseDTO(animeUsuario);
    }

    @GetMapping
    public List<ListaUsuarioResponseDTO> listaUsuario(@PathVariable Long userId){
        List<AnimeUsuario> lista = listaService.listarPorUsuario(userId);
        return lista.stream()
                .map(this::listaUsuarioResponseDTO)
                .toList();
    }

    @DeleteMapping("/{animeId}")
    public void deletarAnimeDaLista(@PathVariable Long animeId, @PathVariable Long userId){
        listaService.deletarAnimeDaListaDoUsuario(animeId, userId);
    }

    private ListaUsuarioResponseDTO listaUsuarioResponseDTO(AnimeUsuario animeUsuario){
        ListaUsuarioResponseDTO dto = new ListaUsuarioResponseDTO();
        dto.setNomeAnime(animeUsuario.getAnime().getTitulo());
        dto.setStatus(animeUsuario.getStatus());
        return dto;
    }
}

