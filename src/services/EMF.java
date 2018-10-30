package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EMF {
	private static EntityManagerFactory emf;

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public static void contextInitialized() {
		emf = Persistence.createEntityManagerFactory("TPE");
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public static void contextDestroyed() {
//		EntityManager entityManager= emf.createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.createNativeQuery("DROP DATABASE CACIC2018").executeUpdate();
//		entityManager.getTransaction().commit();
//		entityManager.close();
		emf.close();
	}

	public static EntityManager createEntityManager() {
		if (emf == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return emf.createEntityManager();
	}
}