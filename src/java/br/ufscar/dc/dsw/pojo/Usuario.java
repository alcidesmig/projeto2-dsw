package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "USUARIO")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    ,
    @NamedQuery(name = "Usuario.username", query = "SELECT u FROM Usuario u WHERE u.email = :email")
})
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private long id;
    private String email;
    private String nome;
    private String senha;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_criacao;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "papel_usuario",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "papel_id")
    )
    private Set<Papel> papeis = new HashSet<>();

    /*@OneToMany(
        mappedBy = "post",
        cascade = CascadeType.ALL
    )
    private List<TokenLogin> tokens = new ArrayList<>();
     */
    public Usuario() {
    }

    public Usuario(String email, String nome, String senha, Date data_criacao) throws NoSuchAlgorithmException {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.data_criacao = data_criacao;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", nome=" + nome + ", senha=" + senha + ", data_criacao=" + data_criacao + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public List<TokenLogin> getTokens() {
//        return tokens;
//    }
//
//    public void addTokens(TokenLogin tokens) {
//        this.tokens.add(tokens);
//    }
//    
//    public void removeTokens(TokenLogin tokens) {
//        this.tokens.remove(tokens);
//    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String nickname) {
        this.email = nickname;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Papel> getPapeis() {
        return this.papeis;
    }

    public void addPapel(Papel papel) throws SQLException {
        this.papeis.add(papel);
    }

    public void removePapel(Papel papel) {
        this.papeis.remove(papel);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }
}
