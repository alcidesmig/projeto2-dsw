package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.DAOSiteDeVenda;
import br.ufscar.dc.dsw.dao.DAOUsuario;
import br.ufscar.dc.dsw.pojo.SiteDeVenda;
import br.ufscar.dc.dsw.pojo.Usuario;
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
    DAOSiteDeVenda daoSite = new DAOSiteDeVenda();
    DAOUsuario daoUser = new DAOUsuario();
    int op;
    private String erro;
    private String operacao;

    public String gerenciar() {
        try {
            if (BeanUsuario.getUserLogado().getIsAdmin()) {
                op = 1;
                sites = daoSite.getAll();
                return "/views/templates_site_de_venda/gerenciar.xhtml";
            } else {
            return "/403.xhtml";
            }
        } catch (Exception e) {
            return "/403.xhtml";

        }
    }

    public String lista() {
        op = 0;
        sites = daoSite.getAll();
        return "/views/templates_site_de_venda/lista.xhtml";
    }

    public String cadastra() {
        try {
            if (BeanUsuario.getUserLogado().getIsAdmin()) {
                site = new SiteDeVenda();
                erro = "";
                operacao = "Cadastro de Site de Venda";
                return "/views/templates_site_de_venda/form.xhtml";
            } else {
            return "/403.xhtml";
            }
        } catch (Exception e) {
            return "/403.xhtml";
        }
    }

    public String edita(Long id) {
        site = daoSite.get(id);
        operacao = "Edição de Site de Venda";
        return "form.xhtml";
    }

    public String salva() {
        try {
            if (site.getId() == 0) {
                Usuario user = new Usuario(site.getNome(), site.getEmail(), site.getSenha());
                user.setIsSalaDeTeatro(false);
                user.setIsSiteDeVenda(true);
                user.setIsAdmin(false);
                daoUser.save(user);
                daoSite.save(site);
            } else {
                Usuario user = daoUser.get(site.getEmail());
                user.setEmail(site.getEmail());
                user.setNome(site.getNome());
                user.setSenha(site.getSenha());
                daoUser.update(user);
                daoSite.update(site);
            }
            sites = daoSite.getAll();
            if (op == 1) {
                return "gerenciar.xhtml";
            }
            return "lista.xhtml";
        } catch (Exception e) {
            return "/500.xhtml";
        }
    }

    public String delete(Long id) {
        // Gambeta da madrugada kkk eu vo mata o web face
        daoUser.delete(daoUser.get(daoSite.get(id).getEmail()));
        daoSite.delete(daoSite.get(id));
        sites = daoSite.getAll();
        return "gerenciar.xhtml";
    }

    public String volta() {
        sites = daoSite.getAll();
        return "views/templates_site_de_venda/lista.xhtml?faces-redirect=true";
    }

    public List<SiteDeVenda> getSitesDeVenda() throws SQLException {
        return sites;
    }

    public List<SiteDeVenda> getSites() throws SQLException {
        return daoSite.getAll();
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

}
