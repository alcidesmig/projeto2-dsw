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
    private String erro;
    private String operacao;

    public String gerenciar() {
        try {
            if (BeanUsuario.getUserLogado().getIsSalaDeTeatro() || BeanUsuario.getUserLogado().getIsAdmin()) {
                promocoes = dao.getAll();
                op = 1;
                return "/views/templates_promocao/gerenciar.xhtml";
            } else {
                return "403.xhtml";
            }
        } catch (Exception e) {
            return "403.xhtml";

        }
    }

    public String lista() {
        try {
            if (BeanUsuario.getUserLogado().getIsSiteDeVenda() || BeanUsuario.getUserLogado().getIsAdmin()) {
                promocoes = dao.getAll();
                op = 0;
                return "/views/templates_promocao/lista.xhtml";
            } else {
                return "403.xhtml";
            }
        } catch (Exception e) {
            return "403.xhtml";

        }
    }

    public String cadastra() {
        try {
            if (BeanUsuario.getUserLogado().getIsSalaDeTeatro() || BeanUsuario.getUserLogado().getIsAdmin()) {
                promocao = new Promocao();
                erro = "";
                operacao = "Cadastro de Promoção";
                return "/views/templates_promocao/form.xhtml";
            } else {
                return "403.xhtml";
            }
        } catch (Exception e) {
            return "403.xhtml";

        }
    }

    public String edita(Long id) {
        promocao = dao.get(id);
        operacao = "Edição de Promoção";
        return "form.xhtml";
    }

    public String salva() {
        promocoes = dao.getAll();
        for (Promocao prom : promocoes) {
            if (prom.getSiteDeVenda().equals(promocao.getSiteDeVenda())) {
                if (prom.getDatetime().equals(promocao.getDatetime())) {
                    erro = "Error!";
                    return "form.xhtml";
                }
            }
        }
        if (dao.get(promocao.getId_promocao()) != null) {
            dao.save(promocao);
        } else {
            dao.update(promocao);
        }
        promocoes = dao.getAll();
        erro = "";
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
        return "/views/templates_promocao/lista.xhtml?faces-redirect=true";
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
