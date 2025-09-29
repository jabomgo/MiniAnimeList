package br.ufpb.dcx.MiniAniList.services;

import br.ufpb.dcx.MiniAniList.dtos.listaUsuario.ListaUsuarioRequestDTO;
import br.ufpb.dcx.MiniAniList.exceptions.ItemNotFoundExeption;
import br.ufpb.dcx.MiniAniList.exceptions.UnauthorizedUserOperationsExeption;
import br.ufpb.dcx.MiniAniList.models.Anime;
import br.ufpb.dcx.MiniAniList.models.AnimeUsuario;
import br.ufpb.dcx.MiniAniList.models.Usuario;
import br.ufpb.dcx.MiniAniList.repository.AnimeRepository;
import br.ufpb.dcx.MiniAniList.repository.ListaAnimeUsuarioRepository;
import br.ufpb.dcx.MiniAniList.repository.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        Usuario usuarioAlvo = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ItemNotFoundExeption("Usuário não encontrado"));

        Anime anime = animeRepository.findById(dto.getAnimeId())
                .orElseThrow(() -> new ItemNotFoundExeption("Anime não encontrado"));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuarioLogado = usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ItemNotFoundExeption("Usuário logado não encontrado"));

        if (!usuarioLogado.getRoles().contains("ADMIN") && !usuarioLogado.getId().equals(usuarioAlvo.getId())) {
            throw new UnauthorizedUserOperationsExeption("Você não pode modificar a lista de outro usuário");
        }

        AnimeUsuario animeUsuario = new AnimeUsuario(usuarioAlvo, anime, dto.getStatus());
        return listaAnimeUsuarioRepository.save(animeUsuario);
    }

    public void deletarAnimeDaListaDoUsuario(Long usuarioId, Long animeId) {
        Usuario usuarioAlvo = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ItemNotFoundExeption("Usuário não encontrado"));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuarioLogado = usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ItemNotFoundExeption("Usuário logado não encontrado"));

        if (!usuarioLogado.getRoles().contains("ADMIN") && !usuarioLogado.getId().equals(usuarioAlvo.getId())) {
            throw new UnauthorizedUserOperationsExeption("Você não pode modificar a lista de outro usuário");
        }

        listaAnimeUsuarioRepository.deleteByUsuarioIdAndAnimeId(usuarioId, animeId);
    }

}

