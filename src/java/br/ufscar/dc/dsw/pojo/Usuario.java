package br.ufscar.dc.dsw.pojo;

import br.ufscar.dc.dsw.dao.DAOUsuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries(value = {
    @NamedQuery(name = "Usuario.findByEmail",
            query = "SELECT c FROM Usuario c "
            + "WHERE c.email = :email")})
@Table(name = "Usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Transient
    public static final String FIND_BY_EMAIL = "Usuario.findByEmail";

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column(unique = true)
    private String email;

    @Column
    private String senha;

    @Column
    private Boolean isSalaDeTeatro;
    @Column
    private Boolean isSiteDeVenda;
    @Column
    private Boolean isAdmin;

    public Usuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getId() {
        return id;
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha.trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        return obj.equals(this);
    }

    public Usuario isUsuarioReadyToLogin(String email, String senha) {
        DAOUsuario dao = new DAOUsuario();
        email = email.toLowerCase().trim();
        Usuario retorno = dao.get(email);
        return retorno;
    }

    public Boolean getIsSalaDeTeatro() {
        return isSalaDeTeatro;
    }

    public void setIsSalaDeTeatro(Boolean isSalaDeTeatro) {
        this.isSalaDeTeatro = isSalaDeTeatro;
    }

    public Boolean getIsSiteDeVenda() {
        return isSiteDeVenda;
    }

    public void setIsSiteDeVenda(Boolean isSiteDeVenda) {
        this.isSiteDeVenda = isSiteDeVenda;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
