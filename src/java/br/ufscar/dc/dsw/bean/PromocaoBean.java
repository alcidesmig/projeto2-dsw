package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.DAOPromocao;
import br.ufscar.dc.dsw.dao.DAOSalaDeTeatro;
import br.ufscar.dc.dsw.dao.DAOSiteDeVenda;
import br.ufscar.dc.dsw.pojo.Promocao;
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
public class PromocaoBean implements Serializable {

    private Promocao promocao;
    private String operacao;
    private List<Promocao> promocoes;
    DAOPromocao dao = new DAOPromocao();

    public String lista() {
        return "views/templates_promocao/lista.xhtml";
    }

    public String cadastra() {
        promocao = new Promocao();
        return "views/templates_promocao/form.xhtml";
    }

    public String edita(Long id) {
        promocao = dao.get(id);
        return "form.xhtml";
    }

    public String salva() {
        if (promocao.getId_promocao() == 0) {
            dao.save(promocao);
        } else {
            dao.update(promocao);
        }
        promocoes = dao.getAll();
        System.out.println(promocao.getSiteDeVenda().getNome());
        return "lista.xhtml";
    }

    public String delete(Promocao promocao) {
        dao.delete(promocao);
        promocoes = dao.getAll();
        return "views/templates_promocao/lista.xhtml";
    }

    public String volta() {
        promocoes = dao.getAll();
        return "views/templates_promocao/lista.xhtml?faces-redirect=true";
    }

    public List<Promocao> getPromocoes() throws SQLException {
        return promocoes;
    }

    public String getPromocoesByName() throws SQLException {
        String nome = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("nome");
        promocoes = dao.getByName(nome);
        return "lista.xhtml";
    }

    public String busca() throws SQLException {
        return getPromocoesByName();
    }

    public List<SiteDeVenda> getSitesDeVenda() throws SQLException {
        DAOSiteDeVenda dao = new DAOSiteDeVenda();
        return dao.getAll();
    }

    public List<SalaDeTeatro> getTeatros() throws SQLException {
        DAOSalaDeTeatro dao = new DAOSalaDeTeatro();
        return dao.getAll();
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

}
