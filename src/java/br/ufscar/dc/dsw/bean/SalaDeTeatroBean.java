package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.DAOSalaDeTeatro;
import br.ufscar.dc.dsw.dao.DAOSiteDeVenda;
import br.ufscar.dc.dsw.dao.DAOUsuario;
import br.ufscar.dc.dsw.pojo.SalaDeTeatro;
import br.ufscar.dc.dsw.pojo.SiteDeVenda;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "salaDeTeatroBean")
@SessionScoped

public class SalaDeTeatroBean implements Serializable {

    private SalaDeTeatro saladeteatro;
    private List<SalaDeTeatro> salas;
    DAOSalaDeTeatro daoTeatro = new DAOSalaDeTeatro();
    DAOUsuario daoUser = new DAOUsuario();
    int op;
    private String erro;
    private String operacao;

    public String gerenciar() {
        if (BeanUsuario.getUserLogado().getIsAdmin()) {
            op = 1;
            salas = daoTeatro.getAll();
            return "/views/templates_sala_de_teatro/gerenciar.xhtml";
        } else {
            return "403.xhtml";
        }
    }

    public String lista() {
        op = 0;
        salas = daoTeatro.getAll();
        return "/views/templates_sala_de_teatro/lista.xhtml";
    }

    public String cadastra() {
        if (BeanUsuario.getUserLogado().getIsAdmin()) {
            saladeteatro = new SalaDeTeatro();
            erro = "";
            operacao = "Cadastro de Sala de Teatro";
            return "/views/templates_sala_de_teatro/form.xhtml";
        } else {
            return "403.xhtml";
        }
    }

    public String edita(Long id) {
        saladeteatro = daoTeatro.get(id);
        operacao = "Edição de Sala de Teatro";
        return "form.xhtml";
    }

    public String salva() {
        if (saladeteatro.getId() == 0) {
            Usuario user = new Usuario(saladeteatro.getNome(), saladeteatro.getEmail(), saladeteatro.getSenha());
            user.setIsSalaDeTeatro(true);
            daoUser.save(user);
            daoTeatro.save(saladeteatro);
        } else {
            Usuario user = daoUser.get(saladeteatro.getEmail());
            user.setEmail(saladeteatro.getEmail());
            user.setNome(saladeteatro.getNome());
            user.setSenha(saladeteatro.getSenha());
            daoUser.update(user);
            daoTeatro.update(saladeteatro);
        }
        salas = daoTeatro.getAll();
        if (op == 1) {
            return "gerenciar.xhtml";
        }
        return "lista.xhtml";
    }

    public String delete(Long id) {
        daoTeatro.delete(daoTeatro.get(id)); //problema
        salas = daoTeatro.getAll();
        return "gerenciar.xhtml";
    }

    public String volta() {
        salas = daoTeatro.getAll();
        return "/views/templates_sala_de_teatro/lista.xhtml?faces-redirect=true";
    }

    public List<SalaDeTeatro> getSalasDeTeatro() throws SQLException {
        return salas;
    }

    public String getSalaDeTeatroByName() throws SQLException {
        String nome = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("nome");
        saladeteatro = daoTeatro.getByNome(nome);
        return "/views/templates_sala_de_teatro/lista.xhtml";
    }

    public String busca() throws SQLException {
        return getSalaDeTeatroByName();
    }

    // Não sei se tem utilidade, mas vou manter
    public List<SiteDeVenda> getSitesDeVenda() throws SQLException {
        DAOSiteDeVenda dao_s = new DAOSiteDeVenda();
        return dao_s.getAll();
    }

    public List<SalaDeTeatro> getTeatros() throws SQLException {
        DAOSalaDeTeatro dao_s = new DAOSalaDeTeatro();
        return dao_s.getAll();
    }

    public SalaDeTeatro getSaladeteatro() {
        return saladeteatro;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public void setSaladeteatro(SalaDeTeatro saladeteatro) {
        this.saladeteatro = saladeteatro;
    }

    public List<SalaDeTeatro> getSalas() {
        return salas;
    }

}
