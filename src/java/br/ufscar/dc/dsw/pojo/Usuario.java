package br.ufscar.dc.dsw.pojo;

import br.ufscar.dc.dsw.dao.DAOPapel;
import br.ufscar.dc.dsw.dao.DAOPapelUsuario;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
//,
//@NamedQuery(name = "Automovel.findByMontadora", query = "SELECT a FROM Automovel a WHERE a.montadora = :montadora")
//,
// @NamedQuery(name = "Automovel.findByDono", query = "SELECT a FROM Automovel a WHERE a.dono = :dono")
})
public class Usuario implements Serializable {

    @Id
    private String email;
    private String nome;
    private String senha;
    private Date data_criacao;

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

    public List<Papel> getPapeis() {
        DAOPapelUsuario papel_usuario = new DAOPapelUsuario();
        return papel_usuario.getPapelByUser(this);
    }

    public void addPapel(Papel papel) throws SQLException {
        DAOPapel daoPapel = new DAOPapel();
        List<Papel> list = daoPapel.getAll();
        boolean exist = false;
        for (Papel a : list) {
            if (a.getNome().equalsIgnoreCase(papel.getNome())) {
                exist = true;
                papel.setId(a.getId());
            }
        }
        if (!exist) {
            daoPapel.insert(papel);
            list = daoPapel.getAll();
            for (Papel a : list) {
                if (a.getNome().equalsIgnoreCase(papel.getNome())) {
                    papel.setId(a.getId());
                }
            }
        }
        DAOPapelUsuario papel_usuario = new DAOPapelUsuario();
        papel_usuario.insert(new PapelUsuario(this.getEmail(), papel.getId()));
    }

    public void removePapel(Papel papel) {
        DAOPapelUsuario papel_usuario = new DAOPapelUsuario();
        papel_usuario.deletePapel(new PapelUsuario(this.getEmail(), papel.getId()));
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
