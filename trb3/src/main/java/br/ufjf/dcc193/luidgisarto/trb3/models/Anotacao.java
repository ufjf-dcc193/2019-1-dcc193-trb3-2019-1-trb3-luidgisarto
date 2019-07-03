package br.ufjf.dcc193.luidgisarto.trb3.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Anotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "anotacao_id")
    private Integer id;

    private String titulo;
    private String descricao;
    private String url;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataInclusao;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;
    @ManyToOne
    private Usuario usuario;

    @ManyToMany(mappedBy = "anotacoes")
    private List<Item> Itens;

    public Anotacao() {
    }

    public Anotacao(String titulo, String descricao, String url, Date dataInclusao, Date dataAlteracao, Usuario usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.dataInclusao = dataInclusao;
        this.dataAlteracao = dataAlteracao;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
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
