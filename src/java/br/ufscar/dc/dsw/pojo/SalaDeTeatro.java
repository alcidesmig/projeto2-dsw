package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SALADETEATRO")
@NamedQueries({
    @NamedQuery(name = "SalaDeTeatro.findAll", query = "SELECT s FROM SalaDeTeatro s")
//,
//@NamedQuery(name = "Automovel.findByMontadora", query = "SELECT a FROM Automovel a WHERE a.montadora = :montadora")
//,
// @NamedQuery(name = "Automovel.findByDono", query = "SELECT a FROM Automovel a WHERE a.dono = :dono")
})
public class SalaDeTeatro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private long id;
    private String email;
    private String senha;
    @Column(unique = true)
    private String cnpj;
    private String nome;
    private String cidade;

    public SalaDeTeatro() {
    }

    public SalaDeTeatro(String email, String senha, String cnpj, String nome, String cidade) {
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.nome = nome;
        this.cidade = cidade;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public long getId() {
        return id;
    }

}
