package local.kapinos.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import local.kapinos.jpa.NewsEntity;

/**
 * Session Bean implementation class NewsEntityFacade
 */
@Stateless
public class NewsEntityFacade {

	@PersistenceContext(unitName = "NewsApp-ejbPU")
	private EntityManager em;

	public void create(NewsEntity newsEntity) {
		em.persist(newsEntity);
	}

	public void edit(NewsEntity newsEntity) {
		em.merge(newsEntity);
	}

	public void remove(NewsEntity newsEntity) {
		em.remove(em.merge(newsEntity));
	}

	public NewsEntity find(Object id) {
		return em.find(NewsEntity.class, id);
	}

	public List<NewsEntity> findAll() {
		CriteriaQuery<NewsEntity> cq = em.getCriteriaBuilder().createQuery(NewsEntity.class);
		cq.select(cq.from(NewsEntity.class));
		return em.createQuery(cq).getResultList();
	}

	public List<NewsEntity> findRange(int[] range) {
		CriteriaQuery<NewsEntity> cq = em.getCriteriaBuilder().createQuery(NewsEntity.class);
		cq.select(cq.from(NewsEntity.class));
		TypedQuery<NewsEntity> q = em.createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count() {
		CriteriaQuery<NewsEntity> cq = em.getCriteriaBuilder().createQuery(NewsEntity.class);
		Root<NewsEntity> rt = cq.from(NewsEntity.class);
		cq.multiselect(em.getCriteriaBuilder().count(rt));
		Query q = em.createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
}