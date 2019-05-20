package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PROMOCAO")
@NamedQueries({
    @NamedQuery(name = "Promocao.findAll", query = "SELECT p FROM Promocao p")
//,
//@NamedQuery(name = "Automovel.findByMontadora", query = "SELECT a FROM Automovel a WHERE a.montadora = :montadora")
//,
// @NamedQuery(name = "Automovel.findByDono", query = "SELECT a FROM Automovel a WHERE a.dono = :dono")
})

public class Promocao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id_promocao;
    private double preco;
    private String datetime;
    @ManyToOne
    private SiteDeVenda siteDeVenda;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "publicador")
    @ManyToOne
    private SalaDeTeatro teatro;
    private String nome_peca;

    public Promocao(int id_promocao, double preco, String datetime, SiteDeVenda siteDeVenda, SalaDeTeatro teatro, String nome_peca) {
        this.id_promocao = id_promocao;
        this.preco = preco;
        this.datetime = datetime;
        this.siteDeVenda = siteDeVenda;
        this.teatro = teatro;
        this.nome_peca = nome_peca;
    }

    public Promocao(double preco, String datetime, SiteDeVenda siteDeVenda, SalaDeTeatro teatro, String nome_peca) {
        this.preco = preco;
        this.datetime = datetime;
        this.siteDeVenda = siteDeVenda;
        this.teatro = teatro;
        this.nome_peca = nome_peca;
    }
    
    public Promocao(){
    }

    public Promocao(int id) {
        this.id_promocao = id;
    }

    public Integer getId_promocao() {
        return id_promocao;
    }

    public void setId_promocao(Integer id_promocao) {
        this.id_promocao = id_promocao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public SiteDeVenda getSiteDeVenda() {
        return siteDeVenda;
    }

    public void setSiteDeVenda(SiteDeVenda siteDeVenda) {
        this.siteDeVenda = siteDeVenda;
    }

    public SalaDeTeatro getTeatro() {
        return teatro;
    }

    public void setTeatro(SalaDeTeatro teatro) {
        this.teatro = teatro;
    }

    public String getNome_peca() {
        return nome_peca;
    }

    public void setNome_peca(String nome_peca) {
        this.nome_peca = nome_peca;
    }

}
