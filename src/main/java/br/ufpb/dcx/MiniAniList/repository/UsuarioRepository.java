package br.ufpb.dcx.MiniAniList.repository;

import br.ufpb.dcx.MiniAniList.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
