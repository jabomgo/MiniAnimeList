package br.ufpb.dcx.MiniAniList.repository;

import br.ufpb.dcx.MiniAniList.models.ListaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaUsuarioRepository extends JpaRepository<ListaUsuario, Long> {
}
