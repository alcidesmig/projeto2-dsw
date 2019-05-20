package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Promocao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAOPromocao extends GenericDAO<Promocao> {

    @Override
    public Promocao get(Long id) {
        EntityManager em = this.getEntityManager();
        Promocao palpite = em.find(Promocao.class, id);
        em.close();
        return palpite;
    }

    @Override
    public List<Promocao> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select p from Promocao p", Promocao.class);
        List<Promocao> promocoes = q.getResultList();
        em.close();
        return promocoes;
    }

    @Override
    public void save(Promocao prom) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(prom);
        tx.commit();
        em.close();
    }

    @Override
    public void update(Promocao prom) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(prom);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(Promocao prom) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        prom = em.getReference(Promocao.class, prom.getId_promocao());
        tx.begin();
        em.remove(prom);
        tx.commit();
    }
    
//    public List<Promocao> getAllPorTime(String time) {
//        EntityManager em = this.getEntityManager();
//        String s = "select p from Palpite p where p.campeao = :nome or p.vice = :nome";
//        TypedQuery<Promocao> q = em.createQuery(s, Promocao.class);
//        q.setParameter("nome", time);
//        return q.getResultList();
//    }
}
