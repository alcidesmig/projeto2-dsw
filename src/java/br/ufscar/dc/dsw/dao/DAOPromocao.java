package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Promocao;
import br.ufscar.dc.dsw.pojo.SalaDeTeatro;
import br.ufscar.dc.dsw.pojo.SiteDeVenda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAOPromocao extends GenericDAO<Promocao> implements Serializable {

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
        System.out.println("Inicio");
        em.persist(prom);
        tx.commit();
        System.out.println("Fim");
        em.close();
        System.out.println("Fecha conn" + prom.getNome_peca());

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
        em.close();
    }

    public List<Promocao> getByTeatro(SalaDeTeatro salaDeTeatro) {
        EntityManager em = this.getEntityManager();
        String s = "select p from Promocao p";
        TypedQuery<Promocao> q = em.createQuery(s, Promocao.class);
        List<Promocao> result = new ArrayList<Promocao>();
        for (Promocao promocao : q.getResultList()) {
            if (promocao.getTeatro().getId() == salaDeTeatro.getId()) {
                result.add(promocao);
            }
        }
        em.close();
        return result;
    }

    public List<Promocao> getBySite(SiteDeVenda site) {
        EntityManager em = this.getEntityManager();
        String s = "select p from Promocao p";
        TypedQuery<Promocao> q = em.createQuery(s, Promocao.class);
        List<Promocao> result = new ArrayList<Promocao>();
        for (Promocao promocao : q.getResultList()) {
            if (promocao.getSiteDeVenda().getId() == site.getId()) {
                result.add(promocao);
            }
        }
        em.close();
        return result;
    }

    public List<Promocao> getByName(String nome) {
        EntityManager em = this.getEntityManager();
        String s = "select p from Promocao p";
        TypedQuery<Promocao> q = em.createQuery(s, Promocao.class);
        List<Promocao> query = q.getResultList();
        List<Promocao> result = new ArrayList<Promocao>();
        for (Promocao promocao : query) {
            if (promocao.getNome_peca().contains(nome)) {
                result.add(promocao);
            }
        }
        em.close();
        return result;
    }

}
