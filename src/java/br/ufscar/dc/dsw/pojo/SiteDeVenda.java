package br.ufscar.dc.dsw.pojo;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
@Entity
@Table(name = "SITEDEVENDA")
@NamedQueries({
    @NamedQuery(name = "SiteDeVenda.findAll", query = "SELECT s FROM SiteDeVenda s")
//,
//@NamedQuery(name = "Automovel.findByMontadora", query = "SELECT a FROM Automovel a WHERE a.montadora = :montadora")
//,
// @NamedQuery(name = "Automovel.findByDono", query = "SELECT a FROM Automovel a WHERE a.dono = :dono")
})
public class SiteDeVenda implements Serializable {
    @Id
    private String email;
    private String senha;
    private String url;
    private String nome;
    private String telefone;

    public SiteDeVenda(String email, String senha, String url, String nome, String telefone) {
        this.email = email;
        this.senha = senha;
        this.url = url;
        this.nome = nome;
        this.telefone = telefone;
    }

    public SiteDeVenda(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}
