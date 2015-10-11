package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
	
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA");
	
	public static List<Product> whatInBox(Box b) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select p from Box b join Containment c join Grouping g join Product p"
				+ " where c.contains = p and c.isContainedIn = " + b);
		em.close();
		return query.getResultList();
	}
	
	public static List<Product> whatInInvoice(Invoice i) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select p from Invoice i join Containment c join Grouping g join Product p"
				+ " where c.contains = p and c.isContainedIn = " + i);
		em.close();
		return query.getResultList();
	}

/*Which boxes contain a collection of items that exceeds the total volume limitation or maximum weight limitation?*/
	public void q1() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select b from Box b");
		for (Box box : (List<Box>) query.getResultList()) {
			double weight = 0, volume = 0;
			for (Product product : Main.whatInBox(box)) {
				weight += product.getWeight();
				volume += product.getVolume();
			}
			if (box.getMaximumWeight() < weight || box.getVolume() < volume)
				System.out.println(box.getId());
		}
		em.close();
	}
	
	public void q2() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select i from Invoice i");
		for (Invoice invoice : (List<Invoice>) query.getResultList()) {
			List pii = whatInInvoice(invoice);
			ArrayList<Product> pib = new ArrayList<Product>();
			Query q = em.createQuery("select b from Box b join Invoice i where b.fulfills = " + invoice);
			for (Box box : (List<Box>) q.getResultList()) {
				pib.addAll(whatInBox(box));
			}
			
			for (int i = 0; i < pii.size(); i++) {
				if (!pib.contains(pii.get(i))) {
					System.out.println(invoice.getId());
					break;
				}
			}
			em.getTransaction().commit();
			em.close();
		}
	}
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.q1();
		main.q2();
		factory.close();
	}

}
