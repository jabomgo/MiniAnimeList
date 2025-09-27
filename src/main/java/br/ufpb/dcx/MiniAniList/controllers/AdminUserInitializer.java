package br.ufpb.dcx.MiniAniList.controllers;

import br.ufpb.dcx.MiniAniList.models.Role;
import br.ufpb.dcx.MiniAniList.models.Usuario;
import br.ufpb.dcx.MiniAniList.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminUserInitializer.class);

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        String emailAdmin = "admin@admin.com";

        if(userRepository.findByEmail(emailAdmin).isEmpty()) {
            log.info("Admin user does not exist");

            Usuario user = new Usuario();
            user.setNome("Admin");
            user.setEmail(emailAdmin);
            user.setSenha(passwordEncoder.encode("admin123"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            log.info("Usuário administrador padrão criado com sucesso! Email: '{}'", emailAdmin);
        }else {
            log.info("Usuário administrador já existe. Nenhuma ação necessária.");
        }
    }
}
