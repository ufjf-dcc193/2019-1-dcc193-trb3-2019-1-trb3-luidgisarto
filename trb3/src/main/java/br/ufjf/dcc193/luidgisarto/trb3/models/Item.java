package br.ufjf.dcc193.luidgisarto.trb3.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Integer id;

    private String titulo;

    @ManyToMany()
    private List<Etiqueta> etiquetas;

    @ManyToMany()
    private List<Anotacao> anotacoes;

    @ManyToMany(mappedBy = "itens", fetch = FetchType.EAGER)
    private List<Vinculo> vinculos;

    public Item() {
    }

    public Item(String titulo, List<Etiqueta> etiquetas, List<Anotacao> anotacoes) {
        this.titulo = titulo;
        this.etiquetas = etiquetas;
        this.anotacoes = anotacoes;
    }

    public Item(String titulo, Etiqueta etiqueta, Anotacao anotacao) {
        this.etiquetas = new ArrayList<>();
        this.anotacoes = new ArrayList<>();
        this.titulo = titulo;
        this.etiquetas.add(etiqueta);
        this.anotacoes.add(anotacao);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public List<Vinculo> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<Vinculo> vinculos) {
        this.vinculos = vinculos;
    }

    @Override
    public String toString() {
        return this.titulo;
    }
}
