package br.ufjf.dcc193.luidgisarto.trb3.repository;

import br.ufjf.dcc193.luidgisarto.trb3.models.Vinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinculoRepository extends JpaRepository<Vinculo, Integer> {
}
