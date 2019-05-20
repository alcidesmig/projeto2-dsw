package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Promocao;
import br.ufscar.dc.dsw.pojo.SalaDeTeatro;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAOUsuario extends GenericDAO<Usuario> {

    @Override
    public Usuario get(long id) {
        EntityManager em = this.getEntityManager();
        Usuario user = em.find(Usuario.class, id);
        em.close();
        return user;
    }

    @Override
    public List<Usuario> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select u from Usuario u", Usuario.class);
        List<Usuario> user = q.getResultList();
        em.close();
        return user;
    }

    @Override
    public void save(Usuario user) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        em.close();
    }

    @Override
    public void update(Usuario user) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(user);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(Usuario user) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        user = em.getReference(Usuario.class, user.getId());
        tx.begin();
        em.remove(user);
        tx.commit();
    }

    public List<Promocao> getByTeatro(SalaDeTeatro salaDeTeatro) {
        EntityManager em = this.getEntityManager();
        String s = "select p from Promocao p where p.saladeteatro_id = :idteatro";
        TypedQuery<Promocao> q = em.createQuery(s, Promocao.class);
        q.setParameter("idteatro", salaDeTeatro.getId());
        return q.getResultList();
    }

}
