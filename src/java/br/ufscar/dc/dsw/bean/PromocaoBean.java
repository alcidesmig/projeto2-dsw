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
    private int op;
    private List<Promocao> promocoes;
    DAOPromocao dao = new DAOPromocao();

    public String gerenciar() {
        promocoes = dao.getAll();
        op = 1;
        return "views/templates_promocao/gerenciar.xhtml";
    }

    public String lista() {
        promocoes = dao.getAll();
        op = 0;
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
        if (dao.get(promocao.getId_promocao()) != null) {
            dao.save(promocao);
        } else {
            dao.update(promocao);
        }
        promocoes = dao.getAll();
        if (op == 1) {
            return "gerenciar.xhtml";
        }
        return "lista.xhtml";

    }

    public String delete(Long id) {
        dao.delete(dao.get(id));
        promocoes = dao.getAll();
        return "gerenciar.xhtml";
    }

    public String volta() {
        promocoes = dao.getAll();
        return "views/templates_promocao/lista.xhtml?faces-redirect=true";
    }

    public List<Promocao> getPromocoes() throws SQLException {
        return promocoes;
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

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

}
