package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.DAOSiteDeVenda;
import br.ufscar.dc.dsw.pojo.SiteDeVenda;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean()
@SessionScoped

public class SiteDeVendaBean implements Serializable {

    private SiteDeVenda site;
    private List<SiteDeVenda> sites;
    DAOSiteDeVenda dao = new DAOSiteDeVenda();
    int op;
    private String erro;
    private String operacao;

    public String gerenciar() {
        op = 1;
        return "views/templates_sala_de_teatro/lista.xhtml";
    }

    public String lista() {
        op = 0;
        return "views/templates_sala_de_teatro/lista.xhtml";
    }

    public String cadastra() {
        site = new SiteDeVenda();
        erro = "";
        operacao = "Cadastro de Site de Venda";
        return "views/templates_site_de_venda/form.xhtml";
    }

    public String edita(Long id) {
        site = dao.get(id);
        operacao = "Edição de Sala de Teatro";
        return "form.xhtml";
    }

    public String salva() {
        if (site.getId() == 0) {
            dao.save(site);
        } else {
            dao.update(site);
        }
        sites = dao.getAll();
        if (op == 1) {
            return "gerenciar.xhtml";
        }
        return "lista.xhtml";
    }

    public String delete(SiteDeVenda saladeteatro) {
        dao.delete(saladeteatro);
        sites = dao.getAll();
        return "gerenciar.xhtml";
    }

    public String volta() {
        sites = dao.getAll();
        return "views/templates_site_de_venda/lista.xhtml?faces-redirect=true";
    }

    public List<SiteDeVenda> getSitesDeVenda() throws SQLException {
        return sites;
    }

    public String getSalaDeTeatroByName() throws SQLException {
        String nome = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("nome");
        // Gambeta pra pegar o primeiro
        site = (SiteDeVenda) dao.getByName(nome).get(0);
        return "views/templates_site_de_venda/lista.xhtml";
    }

    public String busca() throws SQLException {
        return getSalaDeTeatroByName();
    }

    public List<SiteDeVenda> getSites() throws SQLException {
        DAOSiteDeVenda dao_s = new DAOSiteDeVenda();
        return dao_s.getAll();
    }

    public SiteDeVenda getSiteDeVenda() {
        return site;
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

    public void setSaladeteatro(SiteDeVenda sitedevenda) {
        this.site = sitedevenda;
    }
}
