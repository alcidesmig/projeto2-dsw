/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.TokenLogin;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author igor
 */
public class DAOTokenLogin extends GenericDAO<TokenLogin> {

    @Override
    public TokenLogin get(long id) {
        EntityManager em = this.getEntityManager();
        TokenLogin token = em.find(TokenLogin.class, id);
        em.close();
        return token;
    }

    @Override
    public List<TokenLogin> getAll() {
        EntityManager em = this.getEntityManager();
        TypedQuery<TokenLogin> query = em.createNamedQuery("TokenLogin.findAll", TokenLogin.class);
        List<TokenLogin> result = query.getResultList();
        em.close();
        return result;
    }
    
    public TokenLogin get(String token) {
        EntityManager em = this.getEntityManager();
        TypedQuery<TokenLogin> query = em.createNamedQuery("TokenLogin.token", TokenLogin.class);
        query.setParameter("token", token);
        TokenLogin t = query.getSingleResult();
        em.close();
        return t;
    }

    @Override
    public void save(TokenLogin t) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(t);
        tx.commit();
        em.close();
    }

    @Override
    public void update(TokenLogin t) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(t);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(TokenLogin t) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        t = em.getReference(TokenLogin.class, t.getId());
        tx.begin();
        em.remove(t);
        tx.commit();
    }
    
}
