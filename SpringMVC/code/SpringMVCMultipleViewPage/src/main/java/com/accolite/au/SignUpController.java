package com.accolite.au;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUpController {
	
	
	static Logger logger = null;
	
	public static Session getSession()
	{
		logger = Logger.getLogger(HibernateUtility.class.getName());
		logger.info("Trying to create a test connection with the database.");
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
		Session  session = sessionFactory.openSession();
		logger.info("Test connection with the database created successfuly.");
		return session;
	}
	
	private void insert(String username, String password) {
		// TODO Auto-generated method stub
				Session sessionObj = null;
				
				try {

					// Getting Session Object From SessionFactory
					sessionObj = getSession();
					
					
					// Getting Transaction Object From Session Object
					sessionObj.beginTransaction();
					
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					user.setAttempt(3);
					user.setRestrictionLevel(1);
					user.setTs1((long) 0);
					user.setTs2((long) 0);
					user.setTs3((long) 0);
					sessionObj.save(user);
					
					


					sessionObj.getTransaction().commit();
					logger.info("\nUser With username?= " + username + " Signed up Successfully\n");
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
			System.out.println("exist: "+username);

			// Getting Session Object From SessionFactory
			sessionObj = getSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			String hql = "From User u where u.username = :username ";

			org.hibernate.Query query = sessionObj.createQuery(hql);

			query.setParameter("username", username);
			//query.executeUpdate();
			
			User user = (User) query.uniqueResult();
			System.out.println("exist: "+user);
			//flag = list.isEmpty();
			if(user == null)
				flag = true;
			
			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			
			logger.info("\n user With username?= " + username + " Is Exist In Database\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		}
		return !flag;

	}
	
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("/signup")
	public String signUp(HttpServletRequest req, Model m)
	{
		
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("signup: "+username);
		if(exists(username))
		{
			System.out.println("user exist");
			String mess = "User exist";
			m.addAttribute("existUser", mess);
			String mess1 = "http://localhost:8080/SpringMVCAU/api/login";
			m.addAttribute("loginLink", mess1);
			return "index";
			
		}
		else
		{
			Session sessionObj = null;
			try{
				sessionObj = getSession();
				sessionObj.beginTransaction();
				
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setAttempt(3);
				user.setRestrictionLevel(1);
				user.setTs1((long) 0);
				user.setTs2((long) 0);
				user.setTs3((long) 0);
				user.setFlagLevel1(1);
				user.setFlagLevel2(1);
				user.setFlagLevel3(1);
			
				sessionObj.save(user);
				System.out.println("Entering in database successfull");
				
				sessionObj.flush();

				sessionObj.getTransaction().commit();
				String mess1 = username + "\n Signed up Successfully\n";
				m.addAttribute("newHere", mess1);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				sessionObj.close();
			}
			
			
			// Getting Transaction Object From Session Object
			
			
			
			
			return "index";
		}

			
		
		
	}



}
