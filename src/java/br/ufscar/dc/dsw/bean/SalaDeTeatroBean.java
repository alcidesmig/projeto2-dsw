package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.DAOSalaDeTeatro;
import br.ufscar.dc.dsw.dao.DAOSiteDeVenda;
import br.ufscar.dc.dsw.pojo.SalaDeTeatro;
import br.ufscar.dc.dsw.pojo.SiteDeVenda;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped

public class SalaDeTeatroBean implements Serializable {

    private SalaDeTeatro saladeteatro;
    private List<SalaDeTeatro> salasdeteatros;
    DAOSalaDeTeatro dao = new DAOSalaDeTeatro();

    public String lista() {
        return "views/templates_sala_de_teatro/lista.xhtml";
    }

    public String cadastra() {
        saladeteatro = new SalaDeTeatro();
        return "views/templates_sala_de_teatro/form.xhtml";
    }

    public String edita(Long id) {
        saladeteatro = dao.get(id);
        return "views/templates_sala_de_teatro/form.xhtml";
    }

    public String salva() {
        if (saladeteatro.getId() == 0) {
            dao.save(saladeteatro);
        } else {
            dao.update(saladeteatro);
        }
        salasdeteatros = dao.getAll();
        return "views/templates_sala_de_teatro/lista.xhtml";
    }

    public String delete(SalaDeTeatro saladeteatro) {
        dao.delete(saladeteatro);
        salasdeteatros = dao.getAll();
        return "views/templates_sala_de_teatro/lista.xhtml";
    }

    public String volta() {
        salasdeteatros = dao.getAll();
        return "views/templates_sala_de_teatro/lista.xhtml?faces-redirect=true";
    }

    public List<SalaDeTeatro> getSalasDeTeatro() throws SQLException {
        return salasdeteatros;
    }

    public String getSalaDeTeatroByName() throws SQLException {
        String nome = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("nome");
        saladeteatro = dao.getByNome(nome);
        return "views/templates_sala_de_teatro/lista.xhtml";
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

    public SalaDeTeatro getSalaDeTeatro() {
        return saladeteatro;
    }
}
