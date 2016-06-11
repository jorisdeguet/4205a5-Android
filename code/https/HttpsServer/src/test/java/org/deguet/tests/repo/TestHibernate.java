package org.deguet.tests.repo;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.deguet.AppConfig;
import org.deguet.model.NQPerson;
import org.deguet.model.NQPerson.Sex;
import org.junit.Test;

public class TestHibernate {
	@Test
	public void test2(){
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(AppConfig.get().getProperty("database"));
			//EntityManagerFactory factory = Persistence.createEntityManagerFactory("persi");
			EntityManager em = factory.createEntityManager();
			assertNotNull(em);
			System.out.println("EM is "+ em);
			NQPerson person = new NQPerson();
			person.password = new byte[10];
			person.setId(UUID.randomUUID().toString());
			person.sex = Sex.Male;
			
			em.getTransaction().begin();
			em.persist(person);
			em.getTransaction().commit();
			System.out.println("Initial    id "+ person.getId());

			NQPerson p = (NQPerson) em.find(NQPerson.class, person.getId());
			System.out.println("Recovered  id "+ p.getId());
			System.out.println("Recovered  sex "+ p.sex);
			//em.flush();
			List<NQPerson> list = findAll(em);
			System.out.println("Liste " +list);
			assertNotNull(p);
		}
		catch(Throwable t){t.printStackTrace();}
	}

	public List<NQPerson> findAll(EntityManager em) {
		javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(NQPerson.class));
		return em.createQuery(cq).getResultList();
	}
	
}
