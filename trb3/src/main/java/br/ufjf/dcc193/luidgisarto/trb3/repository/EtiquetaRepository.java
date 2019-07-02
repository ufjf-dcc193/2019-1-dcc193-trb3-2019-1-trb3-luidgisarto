package br.ufjf.dcc193.luidgisarto.trb3.repository;

import br.ufjf.dcc193.luidgisarto.trb3.models.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer> {

}
