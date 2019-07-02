package br.ufjf.dcc193.luidgisarto.trb3.repository;

import br.ufjf.dcc193.luidgisarto.trb3.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
