package br.ufpb.dcx.MiniAniList.services;

import br.ufpb.dcx.MiniAniList.dtos.usuario.UsuarioCreateDTO;
import br.ufpb.dcx.MiniAniList.dtos.usuario.UsuarioResponseDTO;
import br.ufpb.dcx.MiniAniList.dtos.usuario.UsuarioUpdateDTO;
import br.ufpb.dcx.MiniAniList.dtos.usuario.UsuarioUpdatePasswordDTO;
import br.ufpb.dcx.MiniAniList.models.Role;
import br.ufpb.dcx.MiniAniList.models.Usuario;
import br.ufpb.dcx.MiniAniList.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return convertToResponseDTO(usuario);
    }

    public UsuarioResponseDTO updatePassword(UsuarioUpdatePasswordDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!bCryptPasswordEncoder.matches(dto.getSenhaAntiga(), usuario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha antiga incorreta");
        }

        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getNovaSenha()));

        return convertToResponseDTO(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO createUsuario(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setRole(Role.USER);

        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        return convertToResponseDTO(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO updateUsuario(UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario = usuarioRepository.save(usuario);
        return convertToResponseDTO(usuario);
    }

    public Page<UsuarioResponseDTO> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(this::convertToResponseDTO);
    }

    public void deletarUsuario(Long id){
        Usuario usuarioToDelete = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.delete(usuarioToDelete);
    }

    private UsuarioResponseDTO convertToResponseDTO(Usuario usuario) {
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(usuario.getId());
        responseDTO.setEmail(usuario.getEmail());
        responseDTO.setNome(usuario.getNome());
        return responseDTO;
    }
}
