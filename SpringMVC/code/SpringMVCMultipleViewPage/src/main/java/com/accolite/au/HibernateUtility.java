package com.accolite.au;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

//import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;


public class HibernateUtility {

	private static Session session = null;
	static Logger logger = null;

	static {

		logger = Logger.getLogger(HibernateUtility.class.getName());
		logger.info("Trying to create a test connection with the database.");
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
		session = sessionFactory.openSession();
		logger.info("Test connection with the database created successfuly.");
	}

	public static Session getSession() {
		return session;
	}

	public static void createOps(String username, String password) {

		Session sessionObj = null;

		try {

			sessionObj = HibernateUtility.getSession();
			// Transaction transaction = (Transaction) sessionObj.beginTransaction();

			sessionObj.getTransaction().commit();

		} catch (Exception e) {
			if (null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public static void updateOps(Integer id) {
		Session sessionObj = null;

		// TODO Auto-generated meth
		try {

			// Getting Session Object From SessionFactory
			sessionObj = HibernateUtility.getSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			String hql = "update Answer   set answer= :answer  where qId = :qId ";

			org.hibernate.Query query = sessionObj.createQuery(hql);
			query.setParameter("answer", "max");
			query.setParameter("qId", id);
			query.executeUpdate();

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("\nStudent With Id?= " + id + " Is Successfully Updated In The Database!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		}
	}

	public static void deleteOps(Integer id1) {
		// TODO Auto-generated method stub
		Session sessionObj = null;
		try {

			// Getting Session Object From SessionFactory
			sessionObj = HibernateUtility.getSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			String hql = "delete from Answer where qId =:qId ";

			org.hibernate.Query query = sessionObj.createQuery(hql);

			query.setParameter("qId", id1);
			query.executeUpdate();

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("\nStudent With Id?= " + id1 + " Is Successfully Deleted From The Database!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		}
	}

	public static boolean exists(String username) {

		// TODO Auto-generated method stub
		Session sessionObj = null;
		boolean flag = false;
		try {

			// Getting Session Object From SessionFactory
			sessionObj = HibernateUtility.getSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			String hql = "From User u where u.username = :username ";

			org.hibernate.Query query = sessionObj.createQuery(hql);

			query.setParameter("username", username);
			query.executeUpdate();
			
			List<User> list = query.list();
			
			flag = list.isEmpty();
			
			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("\nUser With username?= " + username + " Is Exist In Database\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		}
		return !flag;

	}

}
