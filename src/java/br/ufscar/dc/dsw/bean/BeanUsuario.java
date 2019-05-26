/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.DAOUsuario;
import br.ufscar.dc.dsw.pojo.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
  
  
/**
 * Controla o LOGIN e LOGOUT do Usuário
 * */
@ManagedBean(name = "beanUsuario")
@SessionScoped
public class BeanUsuario {
  
    private static final long serialVersionUID = 1L;
  
  
    @ManagedProperty(value = "#{userBO}")
    private Usuario userBO;
  
    private String email;
    private String login;
    private String senha;
  
    public String urlLogin(){
        return "/views/templates_auth/login.xhtml";

    }
    
    /**
     * Retorna usuario logado
     * */
    public Usuario getUser() {
       return (Usuario) SessionContext.getInstance().getUsuarioLogado();
    }
  
    public String doLogin() {
       try {
           Usuario user = new Usuario().isUsuarioReadyToLogin(login, senha);
  
           if (user == null) {
             FacesContext.getCurrentInstance().validationFailed();
             return "";
           }
  
           Usuario usuario = new DAOUsuario().get(user.getEmail());
           SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
           return "/index.xhtml?faces-redirect=true";
       } catch (Exception e) {
           FacesContext.getCurrentInstance().validationFailed();
           e.printStackTrace();
           return "";
       }
  
    }
  
    public String doLogout() {
       SessionContext.getInstance().encerrarSessao();
       return "/security/form_login.xhtml?faces-redirect=true";
    }
  
    
  
    public Usuario getUserBO() {
       return userBO;
    }
  
    public void setUserBO(Usuario userBO) {
       this.userBO = userBO;
    }
  
    public String getLogin() {
       return login;
    }
  
    public void setLogin(String login) {
       this.login = login;
    }
  
    public String getSenha() {
       return senha;
    }
  
    public void setSenha(String senha) {
       this.senha = senha;
    }
  
    public String getEmail() {
       return email;
    }
  
    public void setEmail(String email) {
       this.email = email;
    }
  
}