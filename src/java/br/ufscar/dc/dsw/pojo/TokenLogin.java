/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author igor
 */
@Entity
@Table(name = "token_login")
@NamedQueries({
    @NamedQuery(name = "TokenLogin.findAll", query = "SELECT t FROM token_login t"),
    @NamedQuery(name = "TokenLogin.token", query = "SELECT t FROM token_login t WHERE t.token = :token")
})
public class TokenLogin implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private long id;
    private String token;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    private Date data_login;
    
    public TokenLogin(String token, Usuario usuario, Date data_login) {
        this.token = token;
        this.usuario = usuario;
        this.data_login = data_login;
    }
    
    public TokenLogin(String token, Usuario usuario) {
        this.token = token;
        this.usuario = usuario;
        this.data_login = new Date();
    }
    
    public TokenLogin(Usuario usuario) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[256];
        random.nextBytes(bytes);
        this.token =  bytes.toString();
        this.usuario = usuario;
        this.data_login = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Date getData_login() {
        return data_login;
    }

    public void setData_login(Date data_login) {
        this.data_login = data_login;
    }
    
}
