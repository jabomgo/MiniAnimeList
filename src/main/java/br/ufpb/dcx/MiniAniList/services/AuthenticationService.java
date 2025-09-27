package br.ufpb.dcx.MiniAniList.services;

import br.ufpb.dcx.MiniAniList.dtos.auth.AuthRequestDTO;
import br.ufpb.dcx.MiniAniList.dtos.auth.AuthResponseDTO;
import br.ufpb.dcx.MiniAniList.repository.UsuarioRepository;
import br.ufpb.dcx.MiniAniList.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsuarioRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsuarioRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDTO login(@Valid AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getEmail(),
                        authRequestDTO.getSenha()
                )
        );

        var user = userRepository.findByEmail(authRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado após autenticação bem-sucedida."));

        String token = jwtService.generateToken(user);

        return new AuthResponseDTO(token);
    }

}