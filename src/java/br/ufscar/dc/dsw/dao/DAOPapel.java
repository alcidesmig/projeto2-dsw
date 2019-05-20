/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Papel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author igor
 */
public class DAOPapel extends GenericDAO<Papel> {

    @Override
    public Papel get(long id) {
        EntityManager em = this.getEntityManager();
        Papel papel = em.find(Papel.class, id);
        em.close();
        return papel;
    }

    @Override
    public List<Papel> getAll() {
        EntityManager em = this.getEntityManager();
        TypedQuery<Papel> query = em.createNamedQuery("Papel.findAll", Papel.class);
        List<Papel> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public void save(Papel t) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(t);
        tx.commit();
        em.close();
    }

    @Override
    public void update(Papel t) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(t);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(Papel t) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        t = em.getReference(Papel.class, t.getId());
        tx.begin();
        em.remove(t);
        tx.commit();
    }
    
}
