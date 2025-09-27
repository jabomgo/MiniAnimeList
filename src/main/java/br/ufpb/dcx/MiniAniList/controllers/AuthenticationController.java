package br.ufpb.dcx.MiniAniList.controllers;

import br.ufpb.dcx.MiniAniList.dtos.auth.AuthRequestDTO;
import br.ufpb.dcx.MiniAniList.dtos.auth.AuthResponseDTO;
import br.ufpb.dcx.MiniAniList.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody AuthRequestDTO authRequestDTO){
        return authenticationService.login(authRequestDTO);
    }
}
