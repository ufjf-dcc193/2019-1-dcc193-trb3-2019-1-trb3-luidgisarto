package br.ufjf.dcc193.luidgisarto.trb3.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "etiqueta_id")
    private Integer id;
    private String titulo;
    private String descricao;
    private String url;

    @ManyToMany(mappedBy = "etiquetas")
    private List<Item> Itens;

    public Etiqueta() {
    }

    public Etiqueta(String titulo, String descricao, String url) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Item> getItens() {
        return Itens;
    }

    public void setItens(List<Item> itens) {
        Itens = itens;
    }

    @Override
    public String toString() {
        return this.titulo;
    }
}
