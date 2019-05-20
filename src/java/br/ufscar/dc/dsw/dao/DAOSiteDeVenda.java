package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.SiteDeVenda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAOSiteDeVenda<T> extends GenericDAO<SiteDeVenda>{

    @Override
    public SiteDeVenda get(long id) {
        EntityManager em = this.getEntityManager();
        SiteDeVenda site = em.find(SiteDeVenda.class, id);
        em.close();
        return site;
    }
    
    public List<SiteDeVenda> getByName(String name) {
        EntityManager em = this.getEntityManager();
        TypedQuery<SiteDeVenda> query=  em.createNamedQuery("SiteDeVenda.getByNome", SiteDeVenda.class);
        query.setParameter("nome", "%" + name + "%");
        em.close();
        return query.getResultList();
    }

    @Override
    public List<SiteDeVenda> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createNamedQuery("SiteDeVenda.findAll", SiteDeVenda.class);
        List<SiteDeVenda> site = q.getResultList();
        em.close();
        return site;
    }

    @Override
    public void save(SiteDeVenda site) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(site);
        tx.commit();
        em.close();
    }

    @Override
    public void update(SiteDeVenda site) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(site);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(SiteDeVenda site) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        site = em.getReference(SiteDeVenda.class, site.getId());
        tx.begin();
        em.remove(site);
        tx.commit();
    }

}
