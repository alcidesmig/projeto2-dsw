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

@ManagedBean
@SessionScoped

public class PromocaoBean implements Serializable {

    private Promocao promocao;
    private String operacao;

    public String lista() {
        return "views/template_promocao/lista.xhtml";
    }

    public String cadastra() {
        promocao = new Promocao();
        return "views/template_promocao/form.xhtml";
    }

    public String edita(Long id) {
        DAOPromocao dao = new DAOPromocao();
        promocao = dao.get(id);
        return "views/template_promocao/form.xhtml";
    }

    public String salva() {
        DAOPromocao dao = new DAOPromocao();
        if (promocao.getId_promocao() == null) {
            dao.save(promocao);
        } else {
            dao.update(promocao);
        }
        return "views/template_promocao/lista.xhtml";
    }

    public String delete(Promocao promocao) {
        DAOPromocao dao = new DAOPromocao();
        dao.delete(promocao);
        return "views/template_promocao/lista.xhtml";
    }

    public String volta() {
        return "views/template_promocao/lista.xhtml?faces-redirect=true";
    }

    public List<Promocao> getPromocoes() throws SQLException {
        DAOPromocao dao = new DAOPromocao();
        return dao.getAll();
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
}
