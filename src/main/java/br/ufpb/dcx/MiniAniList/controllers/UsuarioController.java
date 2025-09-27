package br.ufpb.dcx.MiniAniList.controllers;

import br.ufpb.dcx.MiniAniList.dtos.usuario.UsuarioCreateDTO;
import br.ufpb.dcx.MiniAniList.dtos.usuario.UsuarioResponseDTO;
import br.ufpb.dcx.MiniAniList.dtos.usuario.UsuarioUpdateDTO;
import br.ufpb.dcx.MiniAniList.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(path = "/usuarios")
    public UsuarioResponseDTO criarUsuario(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO){
        return usuarioService.createUsuario(usuarioCreateDTO);
    }

    @GetMapping("/usuarios")
    public Page<UsuarioResponseDTO> listarUsuarios(Pageable pageable) {
        return usuarioService.listarUsuarios(pageable);
    }

    @GetMapping("/usuarios/{userId}")
    public UsuarioResponseDTO buscarUsuarioPorId(@PathVariable Long userId){
        return usuarioService.buscarPorId(userId);
    }

    @PutMapping("/usuarios/{userId}")
    public UsuarioResponseDTO atualizarUsuario(@PathVariable Long userId, @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO){
        return usuarioService.updateUsuario(usuarioUpdateDTO);
    }

    @DeleteMapping("/usuarios/{userId}")
    public void deletarUsuario(@PathVariable Long userId){
        usuarioService.deletarUsuario(userId);
    }
}
