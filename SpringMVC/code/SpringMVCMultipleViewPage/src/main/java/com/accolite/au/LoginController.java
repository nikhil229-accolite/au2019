package com.accolite.au;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	String password = null;
	String username = null;
	Date date = null;
	Boolean flagLevel1 = true;
	Boolean flagLevel2 = true;
	Boolean flagLevel3 = true;
	String dbUsername = null;
	String dbPassword = null;
	Integer dbRestrictionLevel = null;
	Integer dbAttempt = null;
	Integer dbFlagLevel1 = null;
	Integer dbFlagLevel2 = null;
	Long dbTs1 = null;
	Long dbTs2 = null;

	static Logger logger = null;
	Session sessionObj = null;

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

	@RequestMapping("/login")
	public String login(HttpServletRequest req, Model m) {

		// m.addAttribute("attempt", totalAttempt);
		// Db.loginMap.get(username).attempt = 3;
		return "login";

	}

	@RequestMapping("/loginAttempt")
	public String loginAttempt(HttpServletRequest req, Model m) {

		m.addAttribute("attempt", Db.loginMap.get(username).attempt);

		return "loginAttempt";

	}

	@RequestMapping("/check")
	public String check(HttpServletRequest req, Model m) {

		username = req.getParameter("username");
		password = req.getParameter("password");
		sessionObj = getSession();

		// only for admin need updation
		if ("admin".equals(username) && "admin".equals(password)) {
			String str1 = "From User";
			org.hibernate.Query q1 = sessionObj.createQuery(str1);

			List<User> u = q1.list();
			for (User user : u) {

				m.addAttribute("adminView", user);
			}
			return "admin";
		}
		// username.equals("admin"))

		// if user exist in database else if user tries to login w/o signing up
		if (SignUpController.exists(username))//
		{

			// user exist first checking users restriction level if yes then returns users
			// maximum restriction reached
			// q1 = query to get current restriction level

			String str1 = "select restrictionLevel from User where username = :username";
			org.hibernate.Query q1 = sessionObj.createQuery(str1);
			q1.setParameter("username", username);
			dbRestrictionLevel = (Integer) q1.uniqueResult();
			if (dbRestrictionLevel == 3) {

				String mess = "User: " + username + " Has Reached Maximum Restriction Level";
				m.addAttribute("UserRestrictionLevelCheckAtBegining", mess);
				return "index";
			} else// if users maximum restriction level did not reached
			// then we will check its attempt available for current level
			// now we try to access the attempt available for the user
			{

				// q2 = query to get attempts available
				String str2 = "select attempt from User where username = :username";
				org.hibernate.Query q2 = sessionObj.createQuery(str2);
				q2.setParameter("username", username);
				dbAttempt = (Integer) q2.uniqueResult();

				// if user has some attempt remaining then we let user to login
				if (dbAttempt != 0) {
					// now for that attempt we try to let him login and match users password

					// q3 access password for that user from database
					String str3 = "select password from User where username = :username";
					org.hibernate.Query q3 = sessionObj.createQuery(str3);
					q3.setParameter("username", username);
					dbPassword = (String) q3.uniqueResult();

					// if password entered by the user matches with the database then we reset
					// restriction level, attempt and redirect him to home page
					if (dbPassword.equals(password)) {
						// updating all restriction level and attempt queries
						// q4 = updating restriction level
						Transaction transaction = sessionObj.beginTransaction();
						String str4 = "update User set restrictionLevel= :rLevel  where username = :username ";
						org.hibernate.Query q4 = sessionObj.createQuery(str4);
						q4.setParameter("rLevel", 1);
						q4.setParameter("username", username);
						q4.executeUpdate();
						transaction.commit();

						// q5 = updating attempts
						Transaction transaction2 = sessionObj.beginTransaction();
						String str5 = "update User set attempt= :attempt  where username = :username ";
						org.hibernate.Query q5 = sessionObj.createQuery(str5);
						q5.setParameter("attempt", 3);
						q5.setParameter("username", username);
						q5.executeUpdate();
						transaction2.commit();

						// now after updating all variables redirecting user to home page
						return "loginS";
					} else
					// if password does not match first time we let him to attempt again and reduces
					// the attempt remaining
					{
						// query getting current dbAttempt
						/*
						 * String str6 = "select attempt from User where username = :username";
						 * org.hibernate.Query q51 = sessionObj.createQuery(str6);
						 * q51.setParameter("username", username); dbAttempt = (Integer)
						 * q51.uniqueResult();
						 */

						// updating the attempts remaining in the database
						// q6 = updating the attempt in database

						dbAttempt -= 1;
						Session sessionObj1 = null;
						try {
							sessionObj1 = getSession();
							// sessionObj.beginTransaction();
							sessionObj1.beginTransaction();
							String str6 = "update User u  set u.attempt= :attempt where u.username = :username";
							org.hibernate.Query q6 = sessionObj1.createQuery(str6);
							q6.setParameter("username", username);
							q6.setParameter("attempt", dbAttempt);

							q6.executeUpdate();
							sessionObj1.flush();
							// sessionObj1.clear();
							// sessionObj.beginTransaction().commit();
							sessionObj1.getTransaction().commit();
							System.out.println("sucess dbAttempt value: " + dbAttempt);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						} finally {
							sessionObj1.close();
						}

						// logger.info("\nStudent With Id?= " + + " Is Successfully Updated In The
						// Database!\n");

						/*
						 * //getting DbAttempt String str61 =
						 * "select attempt from User where username = :username"; org.hibernate.Query
						 * q61 = sessionObj.createQuery(str61); q61.setParameter("username", username);
						 * dbAttempt = (Integer) q61.uniqueResult();
						 */

						// redirecting to login attempt page where user can attempt login again and
						// again as long as it reaches maximum attempts
						m.addAttribute("attempt", dbAttempt);
						// System.out.println("attempts: "+ totalAttempt);
						return "loginAttempt";
					}

				} else
				// if users consumes all of his available attempt then we try to restrict him
				{

					// q7 = getting flagLevel1 from the database for username
					String str7 = "select flagLevel1 from User where username = :username";
					org.hibernate.Query q7 = sessionObj.createQuery(str7);
					q7.setParameter("username", username);
					dbFlagLevel1 = (Integer) q7.uniqueResult();

					// q8 = getting attempt from table
					String str8 = "select attempt from User where username = :username";
					org.hibernate.Query q8 = sessionObj.createQuery(str8);
					q8.setParameter("username", username);
					dbAttempt = (Integer) q8.uniqueResult();

					// q9 = getting restriction level for current user
					String str9 = "select restrictionLevel from User where username = :username";
					org.hibernate.Query q9 = sessionObj.createQuery(str9);
					q9.setParameter("username", username);
					dbRestrictionLevel = (Integer) q9.uniqueResult();

					// sessionObj.close();
					// restrict logic;
					// after consuming 3 attempt
					// runs only one time after first level failure attempt
					if ((dbFlagLevel1 != 0) && dbAttempt == 0 && dbRestrictionLevel == 1) {
						java.sql.Time time = new Time(System.currentTimeMillis());
						String cur_timeS = time.toString();
						cur_timeS = cur_timeS.replaceAll(":", "");
						long cur_timeL = Long.parseLong(cur_timeS);

						Session sessionObj3 = null;
						try {
							sessionObj3 = getSession();
							// sessionObj.beginTransaction();
							sessionObj3.beginTransaction();

							// q10 = updating time in milli seconds in ts1 variable
							String str10 = "update User set ts1= :ts1  where username = :username ";
							org.hibernate.Query q10 = sessionObj3.createQuery(str10);
							q10.setParameter("ts1", cur_timeL);
							q10.setParameter("username", username);
							q10.executeUpdate();

							sessionObj3.flush();
							// sessionObj1.clear();
							// sessionObj.beginTransaction().commit();
							sessionObj3.getTransaction().commit();

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						} finally {
							sessionObj3.close();
						}

						// Db.loginMap.get(username).ts1 = cur_timeL;

						/*
						 * //q11 = accessing ts1 for current user String str11 =
						 * "select ts1 from User where username = :username"; org.hibernate.Query q11 =
						 * sessionObj.createQuery(str11); q11.setParameter("username", username); dbTs1
						 * = (Long) q11.uniqueResult();
						 */

						System.out.println("currr " + cur_timeL);
						// System.out.println("stored: " + (dbTs1 + 20));

						// q12 = updating dbFlagLevel1 for current user;

						Session sessionObj4 = null;
						try {
							sessionObj4 = getSession();
							// sessionObj.beginTransaction();
							sessionObj4.beginTransaction();
							String str12 = "update User set flagLevel1 = :flagLevel1  where username = :username";
							org.hibernate.Query q12 = sessionObj4.createQuery(str12);
							q12.setParameter("flagLevel1", 0);
							q12.setParameter("username", username);
							q12.executeUpdate();

							sessionObj4.flush();
							// sessionObj1.clear();
							// sessionObj.beginTransaction().commit();
							sessionObj4.getTransaction().commit();

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						} finally {
							sessionObj4.close();
						}

						// flagLevel1 = false;
					}

					/*
					 * //q13 = getting restriction level from database String str13 =
					 * "select restrictionLevel from User where username = :username";
					 * org.hibernate.Query q13 = sessionObj.createQuery(str13);
					 * q13.setParameter("username", username); dbRestrictionLevel = (Integer)
					 * q13.uniqueResult();
					 */

					// runs for user who's restriction level is 1
					// let him wait for some amount of time
					if (dbRestrictionLevel == 1) {
						// disable button and wait for 5 min

						// m.addAttribute("current", cur_timeL);
						java.sql.Time time = new Time(System.currentTimeMillis());
						String cur_timeS = time.toString();
						cur_timeS = cur_timeS.replaceAll(":", "");
						long cur_timeL = Long.parseLong(cur_timeS);

						System.out.println("curr: " + cur_timeL);

						Session session1 = getSession();
						// q14 = accessing ts1 from data base
						String str14 = "select ts1 from User where username = :username";
						org.hibernate.Query q14 = session1.createQuery(str14);
						q14.setParameter("username", username);
						dbTs1 = (Long) q14.uniqueResult();

						Long temp = dbTs1 + 10;
						System.out.println("stored: " + dbTs1);
						System.out.println("check: " + temp);

						// let user wait for 20 sec
						if (cur_timeL < temp) {
							String mess = "try after " + (temp - cur_timeL) + " sec. 1 level";
							m.addAttribute("mess", mess);
							return "login";
						}

						// wait is over then reseting restriction level to 2 and attempt to 3
						if (cur_timeL > temp) {

							// q15 = updating attempt to 3

							Session sessionObj1 = null;
							try {
								sessionObj1 = getSession();
								// sessionObj.beginTransaction();
								Transaction transaction = sessionObj1.beginTransaction();
								String str15 = "update User u set u.attempt= :attempt  where u.username = :username";
								org.hibernate.Query q15 = sessionObj1.createQuery(str15);
								q15.setParameter("attempt", 3);
								q15.setParameter("username", username);
								q15.executeUpdate();
								sessionObj1.flush();
								// sessionObj1.clear();
								// sessionObj.beginTransaction().commit();
								transaction.commit();
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							} finally {
								sessionObj1.close();
							}

							// q16 = updating restriction level

							Session sessionObj2 = null;
							try {
								sessionObj2 = getSession();
								// sessionObj.beginTransaction();
								Transaction transaction = sessionObj2.beginTransaction();
								String str16 = "update User u set u.restrictionLevel =:restrictionLevel where u.username = :username";
								org.hibernate.Query q16 = sessionObj2.createQuery(str16);
								q16.setParameter("restrictionLevel", 2);
								q16.setParameter("username", username);
								q16.executeUpdate();
								sessionObj2.flush();
								// sessionObj1.clear();
								// sessionObj.beginTransaction().commit();
								transaction.commit();
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							} finally {
								sessionObj2.close();
							}

							// Db.loginMap.get(username).restrictionLevel = 2;
							// Db.loginMap.get(username).attempt = 3;
							return "login";
						}

					}

					// level 2 restriction
					// q17 = getting flagLevel2 from the database for username
					String str17 = "select flagLevel2 from User where username = :username";
					org.hibernate.Query q17 = sessionObj.createQuery(str17);
					q17.setParameter("username", username);
					dbFlagLevel2 = (Integer) q17.uniqueResult();

					// q18 = getting attempt from table
					String str18 = "select attempt from User where username = :username";
					org.hibernate.Query q18 = sessionObj.createQuery(str18);
					q18.setParameter("username", username);
					dbAttempt = (Integer) q18.uniqueResult();

					// q19 = getting restriction level for current user
					String str19 = "select restrictionLevel from User where username = :username";
					org.hibernate.Query q19 = sessionObj.createQuery(str19);
					q19.setParameter("username", username);
					dbRestrictionLevel = (Integer) q19.uniqueResult();

					System.out.println("dbFlagLevel2 :" + dbFlagLevel2);
					System.out.println("dbAttempt :" + dbAttempt);
					System.out.println("dbRestrictionLevel :" + dbRestrictionLevel);

					if ((dbFlagLevel2 != 0) && dbAttempt == 0 && dbRestrictionLevel == 2) {
						java.sql.Time time = new Time(System.currentTimeMillis());
						String cur_timeS = time.toString();
						cur_timeS = cur_timeS.replaceAll(":", "");
						long cur_timeL = Long.parseLong(cur_timeS);

						Session sessionObj1 = null;
						try {
							sessionObj1 = getSession();
							// sessionObj.beginTransaction();
							sessionObj1.beginTransaction();

							// q10 = updating time in milli seconds in ts1 variable
							String str20 = "update User set ts2= :ts2  where username = :username ";
							System.out.println("update ts2 runs");
							org.hibernate.Query q20 = sessionObj1.createQuery(str20);
							q20.setParameter("ts2", cur_timeL);
							q20.setParameter("username", username);
							q20.executeUpdate();

//							sessionObj1.saveOrUpdate(object);

							sessionObj1.flush();
							// sessionObj1.clear();
							// sessionObj.beginTransaction().commit();
							sessionObj1.getTransaction().commit();

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						} finally {
							sessionObj1.close();
						}

						Long temp = cur_timeL + 20;
						dbTs2=cur_timeL;
						System.out.println("level 2 currr " + cur_timeL);
						System.out.println("level 2 stored: " + temp);

						// setting flag 2 value to database
						Session sessionObj2 = null;
						try {
							sessionObj2 = getSession();
							// sessionObj.beginTransaction();
							sessionObj2.beginTransaction();
							String str12 = "update User set flagLevel2 = :flagLevel2  where username = :username";
							org.hibernate.Query q12 = sessionObj2.createQuery(str12);
							q12.setParameter("flagLevel2", 0);
							q12.setParameter("username", username);
							q12.executeUpdate();

							sessionObj2.flush();
							// sessionObj1.clear();
							// sessionObj.beginTransaction().commit();
							sessionObj2.getTransaction().commit();

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						} finally {
							sessionObj2.close();
						}

					}

					// getting current restriction level
					// q21 = getting restriction level from database
					String str21 = "select restrictionLevel from User where username = :username";
					org.hibernate.Query q21 = sessionObj.createQuery(str21);
					q21.setParameter("username", username);
					dbRestrictionLevel = (Integer) q21.uniqueResult();

					if (dbRestrictionLevel == 2) {
						// disable button and wait for 5 min

						// m.addAttribute("current", cur_timeL);
						java.sql.Time time = new Time(System.currentTimeMillis());
						String cur_timeS = time.toString();
						cur_timeS = cur_timeS.replaceAll(":", "");
						long cur_timeL = Long.parseLong(cur_timeS);

						System.out.println("level 2 curr: " + cur_timeL);

						// getting value of ts2 from data base

/*						// q14 = accessing ts2 from data base

						Transaction t = sessionObj.beginTransaction();
						Criteria cr = sessionObj.createCriteria(User.class);
						cr.add(Restrictions.eq("username", username));
						@SuppressWarnings("unchecked")
						List<User> results = cr.list();

						t.commit();

						dbTs2 = results.get(0).getTs2();
						System.out.println(results.get(0).toString());
*/
						Long temp = dbTs2 + 20;

						System.out.println("level  2 dbTs2: " + dbTs2);
						System.out.println("level  2 stored: " + temp);

						if (cur_timeL < temp) {
							String mess = "try after  " + (temp - cur_timeL) + " sec. 2 level ";

							m.addAttribute("mess", mess);
							return "login";
						}
						if (cur_timeL > temp) {

							// updating restrictionLevel
							Session sessionObj2 = null;
							try {
								sessionObj2 = getSession();
								// sessionObj.beginTransaction();
								Transaction transaction = sessionObj2.beginTransaction();
								String str16 = "update User u set u.restrictionLevel =:restrictionLevel where u.username = :username";
								org.hibernate.Query q16 = sessionObj2.createQuery(str16);
								q16.setParameter("restrictionLevel", 3);
								q16.setParameter("username", username);
								q16.executeUpdate();
								sessionObj2.flush();
								// sessionObj1.clear();
								// sessionObj.beginTransaction().commit();
								transaction.commit();
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							} finally {
								sessionObj2.close();
							}

							// String mess = "BLOCKED";
							// m.addAttribute("blocked", mess);
							return "login";
						}

					}
					// level 3 restriction
					// getting restrictionlevel
					String str22 = "select restrictionLevel from User where username = :username";
					org.hibernate.Query q22 = sessionObj.createQuery(str22);
					q22.setParameter("username", username);
					dbRestrictionLevel = (Integer) q22.uniqueResult();

					// getting attempt
					String str23 = "select attempt from User where username = :username";
					org.hibernate.Query q23 = sessionObj.createQuery(str23);
					q23.setParameter("username", username);
					dbAttempt = (Integer) q23.uniqueResult();

					if (dbAttempt == 0 && dbRestrictionLevel == 3) {

						return "index";

					}

					return "index";

				}

			}

		} else {
			// not exist if tries to login
			String mess = "Username Does Not Exist with " + username + " Sign Up First And Try Login Again ";
			m.addAttribute("directLoginNotSignedUp", mess);
			return "index";
		}

		/*
		 * if (Db.loginMap.get(username) != null && Db.loginMap.get(username).attempt !=
		 * 0) { if (Db.loginMap.get(username).password.equals(password)) {
		 * Db.loginMap.get(username).attempt = 3;
		 * Db.loginMap.get(username).restrictionLevel = 1;
		 * 
		 * return "loginS"; } else {
		 * 
		 * Db.loginMap.get(username).attempt--; m.addAttribute("attempt",
		 * Db.loginMap.get(username).attempt); // System.out.println("attempts: "+
		 * totalAttempt); return "loginAttempt"; } } else { // after consuming 3 attempt
		 * // runs only one time after failure attempt if (flagLevel1 &&
		 * Db.loginMap.get(username).attempt == 0 &&
		 * Db.loginMap.get(username).restrictionLevel == 1) { java.sql.Time time = new
		 * Time(System.currentTimeMillis()); String cur_timeS = time.toString();
		 * cur_timeS = cur_timeS.replaceAll(":", ""); long cur_timeL =
		 * Long.parseLong(cur_timeS); long endTime = 300000 + cur_timeL;
		 * Db.loginMap.get(username).ts1 = cur_timeL; System.out.println("currr " +
		 * cur_timeL); System.out.println("stored: " + (Db.loginMap.get(username).ts1 +
		 * 20)); flagLevel1 = false; }
		 * 
		 * if (Db.loginMap.get(username).restrictionLevel == 1) { // disable button and
		 * wait for 5 min
		 * 
		 * // m.addAttribute("current", cur_timeL); java.sql.Time time = new
		 * Time(System.currentTimeMillis()); String cur_timeS = time.toString();
		 * cur_timeS = cur_timeS.replaceAll(":", ""); long cur_timeL =
		 * Long.parseLong(cur_timeS);
		 * 
		 * System.out.println("curr: " + cur_timeL); System.out.println("stored: " +
		 * (Db.loginMap.get(username).ts1 + 20));
		 * 
		 * if (cur_timeL < Db.loginMap.get(username).ts1 + 20) { String mess =
		 * "try after 5  sec"; m.addAttribute("mess", mess); return "login"; } if
		 * (cur_timeL > Db.loginMap.get(username).ts1 + 20) {
		 * Db.loginMap.get(username).restrictionLevel = 2;
		 * Db.loginMap.get(username).attempt = 3; return "login"; }
		 * 
		 * } //level 2 restriction if (flagLevel2 && Db.loginMap.get(username).attempt
		 * == 0 && Db.loginMap.get(username).restrictionLevel == 2) { java.sql.Time time
		 * = new Time(System.currentTimeMillis()); String cur_timeS = time.toString();
		 * cur_timeS = cur_timeS.replaceAll(":", ""); long cur_timeL =
		 * Long.parseLong(cur_timeS);
		 * 
		 * Db.loginMap.get(username).ts2 = cur_timeL;
		 * System.out.println("level 2 currr " + cur_timeL);
		 * System.out.println("level 2 stored: " + (Db.loginMap.get(username).ts2 +
		 * 20)); flagLevel2 = false;
		 * 
		 * } if (Db.loginMap.get(username).restrictionLevel == 2) { // disable button
		 * and wait for 5 min
		 * 
		 * // m.addAttribute("current", cur_timeL); java.sql.Time time = new
		 * Time(System.currentTimeMillis()); String cur_timeS = time.toString();
		 * cur_timeS = cur_timeS.replaceAll(":", ""); long cur_timeL =
		 * Long.parseLong(cur_timeS);
		 * 
		 * System.out.println("level 2 curr: " + cur_timeL);
		 * System.out.println("level  2 stored: " + (Db.loginMap.get(username).ts2 +
		 * 20));
		 * 
		 * if (cur_timeL < Db.loginMap.get(username).ts2 + 20) { String mess =
		 * "try after 10  sec"; m.addAttribute("mess", mess); return "login"; } if
		 * (cur_timeL > Db.loginMap.get(username).ts2 + 20) {
		 * Db.loginMap.get(username).restrictionLevel = 3;
		 * //Db.loginMap.get(username).attempt = 3; String mess = "BLOCKED";
		 * m.addAttribute("blocked", mess); return "login"; }
		 * 
		 * 
		 * } //level 3 restriction if (Db.loginMap.get(username).attempt == 0 &&
		 * Db.loginMap.get(username).restrictionLevel == 3) {
		 * 
		 * return "login";
		 * 
		 * 
		 * }
		 * 
		 * 
		 * return "login";
		 * 
		 * }
		 */

	}

}
