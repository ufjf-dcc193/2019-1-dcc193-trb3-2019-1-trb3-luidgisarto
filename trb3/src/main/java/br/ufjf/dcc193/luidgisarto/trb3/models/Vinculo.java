package br.ufjf.dcc193.luidgisarto.trb3.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vinculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vinculo_id")
    private Integer id;

    @ManyToOne
    private Item itemOrigem;

    @ManyToOne
    private Item itemDestino;

    @OneToMany
    private List<Etiqueta> etiquetas;

    @OneToMany
    private List<Anotacao> anotacoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItemOrigem() {
        return itemOrigem;
    }

    public void setItemOrigem(Item itemOrigem) {
        this.itemOrigem = itemOrigem;
    }

    public Item getItemDestino() {
        return itemDestino;
    }

    public void setItemDestino(Item itemDestino) {
        this.itemDestino = itemDestino;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }
}
