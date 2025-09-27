package br.ufpb.dcx.MiniAniList.services;

import br.ufpb.dcx.MiniAniList.dtos.listaUsuario.ListaUsuarioRequestDTO;
import br.ufpb.dcx.MiniAniList.models.Anime;
import br.ufpb.dcx.MiniAniList.models.AnimeUsuario;
import br.ufpb.dcx.MiniAniList.models.Usuario;
import br.ufpb.dcx.MiniAniList.repository.AnimeRepository;
import br.ufpb.dcx.MiniAniList.repository.ListaAnimeUsuarioRepository;
import br.ufpb.dcx.MiniAniList.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {

    private final ListaAnimeUsuarioRepository listaAnimeUsuarioRepository;
    private final AnimeRepository animeRepository;
    private final UsuarioRepository usuarioRepository;

    public ListaService(ListaAnimeUsuarioRepository listaAnimeUsuarioRepository, AnimeRepository animeRepository, UsuarioRepository usuarioRepository) {
        this.listaAnimeUsuarioRepository = listaAnimeUsuarioRepository;
        this.animeRepository = animeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<AnimeUsuario> listarPorUsuario(Long userId) {
        return listaAnimeUsuarioRepository.findByUsuarioId(userId);
    }

    public AnimeUsuario adicionarAnimeNaLista(ListaUsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Anime anime = animeRepository.findById(dto.getAnimeId()).orElseThrow(() -> new RuntimeException("Anime não encontrado"));

        AnimeUsuario animeUsuario = new AnimeUsuario(usuario, anime, dto.getStatus());

        return listaAnimeUsuarioRepository.save(animeUsuario);
    }
    public void deletarAnimeDaListaDoUsuario(Long animeId, Long userId) {
        AnimeUsuario animeUsuario = listaAnimeUsuarioRepository.findByUsuarioIdAndAnimeId(userId, animeId)
                .orElseThrow(() -> new RuntimeException("Anime não encontrado na lista do usuário"));

        listaAnimeUsuarioRepository.delete(animeUsuario);
    }

}

