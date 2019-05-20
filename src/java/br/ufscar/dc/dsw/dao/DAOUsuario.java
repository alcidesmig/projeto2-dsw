/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author igor
 */
public class DAOUsuario extends GenericDAO<Usuario> {

    @Override
    public Usuario get(Long id) {
        EntityManager em = this.getEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;
    }

    @Override
    public List<Usuario> getAll() {
        EntityManager em = this.getEntityManager();
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
        List<Usuario> result = query.getResultList();
        em.close();
        return result;
    }
    
    public Usuario get(String email) {
        EntityManager em = this.getEntityManager();
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.username", Usuario.class);
        query.setParameter("email", email);
        Usuario user = query.getSingleResult();
        em.close();
        return user;
    }

    @Override
    public void save(Usuario t) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(t);
        tx.commit();
        em.close();
    }

    @Override
    public void update(Usuario t) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(t);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(Usuario t) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        t = em.getReference(Usuario.class, t.getId());
        tx.begin();
        em.remove(t);
        tx.commit();
    }
}
