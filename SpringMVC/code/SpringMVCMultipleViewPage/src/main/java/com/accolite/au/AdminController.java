package com.accolite.au;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	static Logger logger = null;
	Session sessionObj1 = null;
	String username = null;
	String restrictionLevel = null;

	public static Session getSession() {
		logger = Logger.getLogger(HibernateUtility.class.getName());
		logger.info("Trying to create a test connection with the database.");
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		logger.info("Test connection with the database created successfuly.");
		return session;
	}
	@RequestMapping("/admin")
	public String setRestrictionLevel(HttpServletRequest req, Model m) {
		
		username = req.getParameter("username");
		restrictionLevel = req.getParameter("rl");
		Session sessionObj2 = null;
		try {
			sessionObj2 = getSession();
			//sessionObj.beginTransaction();
			Transaction transaction = sessionObj2.beginTransaction();
			String str16 = "update User u set u.restrictionLevel =:restrictionLevel where u.username = :username";
			org.hibernate.Query q16 = sessionObj2.createQuery(str16);
			q16.setParameter("restrictionLevel",Integer.valueOf(restrictionLevel) );
			q16.setParameter("username", username);
			q16.executeUpdate();
			sessionObj2.flush();
			//sessionObj1.clear();
			//sessionObj.beginTransaction().commit();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			sessionObj2.close();
		}
		
		String mess = "Reseting Restriction Level Successful";
		m.addAttribute("resetSuccessfull", mess);
		
		
		return "admin";
	}

}
