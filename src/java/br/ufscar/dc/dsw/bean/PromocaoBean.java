package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.DAOPromocao;
import br.ufscar.dc.dsw.dao.DAOSalaDeTeatro;
import br.ufscar.dc.dsw.dao.DAOSiteDeVenda;
import br.ufscar.dc.dsw.pojo.Promocao;
import br.ufscar.dc.dsw.pojo.SalaDeTeatro;
import br.ufscar.dc.dsw.pojo.SiteDeVenda;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PromocaoBean implements Serializable {

    private Promocao promocao;
    private int op;
    private List<Promocao> promocoes;
    DAOPromocao daoProm = new DAOPromocao();
    DAOSalaDeTeatro daoTeatro = new DAOSalaDeTeatro();
    private String erro;
    private String operacao;

    public String gerenciar() {
        try {
            if (BeanUsuario.getUserLogado().getIsAdmin()) {
                promocoes = daoProm.getAll();
                op = 1;
                return "/views/templates_promocao/gerenciar.xhtml";
            } else if (BeanUsuario.getUserLogado().getIsSalaDeTeatro()) {
                promocoes = daoProm.getByTeatro(daoTeatro.getByEmail(BeanUsuario.getUserLogado().getEmail()));
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
            if (BeanUsuario.getUserLogado().getIsAdmin()) {
                promocoes = daoProm.getAll();
                op = 1;
                return "/views/templates_promocao/lista.xhtml";
            } else if (BeanUsuario.getUserLogado().getIsSalaDeTeatro()) {
                promocoes = daoProm.getByTeatro(daoTeatro.getByEmail(BeanUsuario.getUserLogado().getEmail()));
                op = 1;
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
            return "500.xhtml";
        }
    }

    public String edita(Long id) {
        promocao = daoProm.get(id);
        operacao = "Edição de Promoção";
        return "form.xhtml";
    }

    public String salva() {
        promocoes = daoProm.getAll();
        for (Promocao prom : promocoes) {
            if (prom.getTeatro().equals(promocao.getTeatro())) {
                if (prom.getDatetime().equals(promocao.getDatetime())) {
                    erro = "Error!";
                    return "form.xhtml";
                }
            }
        }
        if (promocao.getId_promocao() != 0) {
            daoProm.save(promocao);
        } else {
            daoProm.update(promocao);
        }
        promocoes = daoProm.getAll();
        erro = "";
        if (op == 1) {
            return "gerenciar.xhtml";
        }
        return "lista.xhtml";

    }

    public String delete(Long id) {
        daoProm.delete(daoProm.get(id));
        promocoes = daoProm.getAll();
        return "gerenciar.xhtml";
    }

    public String volta() {
        promocoes = daoProm.getAll();
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
        if (BeanUsuario.getUserLogado().getIsAdmin()) {
            DAOSalaDeTeatro dao = new DAOSalaDeTeatro();
            return dao.getAll();
        } else {
            DAOSalaDeTeatro dao = new DAOSalaDeTeatro();
            List<SalaDeTeatro> lista = new ArrayList<>();
            lista.add(dao.getByEmail(BeanUsuario.getUserLogado().getEmail()));
            return lista;
        }
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
