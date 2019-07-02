package br.ufjf.dcc193.luidgisarto.trb3.repository;

import br.ufjf.dcc193.luidgisarto.trb3.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
