package br.ufscar.dc.dsw.dao;
import br.ufscar.dc.dsw.pojo.SalaDeTeatro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAOSalaDeTeatro extends GenericDAO<SalaDeTeatro> {

    @Override
    public SalaDeTeatro get(Long id) {
        EntityManager em = this.getEntityManager();
        SalaDeTeatro sala = em.find(SalaDeTeatro.class, id);
        em.close();
        return sala;
    }

    @Override
    public List<SalaDeTeatro> getAll() {
        EntityManager em = this.getEntityManager();  
        Query q = em.createQuery("select s from SalaDeTeatro s", SalaDeTeatro.class);
        List<SalaDeTeatro> salas = q.getResultList();
        em.close();
        return salas;
    }

    @Override
    public void save(SalaDeTeatro sala) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(sala);
        tx.commit();
        em.close();
    }

    @Override
    public void update(SalaDeTeatro sala) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(sala);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(SalaDeTeatro sala) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        sala = em.getReference(SalaDeTeatro.class, sala.getId());
        tx.begin();
        em.remove(sala);
        tx.commit();
    }

    public SalaDeTeatro getByCnpj(String cnpj) {
        EntityManager em = this.getEntityManager();
        String s = "select s from SalaDeTeatro s where s.cnpj = :cnpj";
        TypedQuery<SalaDeTeatro> q = em.createQuery(s, SalaDeTeatro.class);
        q.setParameter("cnpj", cnpj);
        return q.getResultList().get(0);
    }

    public SalaDeTeatro getByNome(String nome) {
        EntityManager em = this.getEntityManager();
        String s = "select s from SalaDeTeatro s where s.nome = :nome";
        TypedQuery<SalaDeTeatro> q = em.createQuery(s, SalaDeTeatro.class);
        q.setParameter("nome", nome);
        return q.getResultList().get(0);
    }

}
