package com.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.Address;
import com.entity.User;

public class Manager {
	private static Logger logger = LogManager.getLogger(Manager.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// persist();
		// merge();
		refresh2();
		// merge2();
	}

	private static void persist() {
		EntityManagerFactory emFactory = null;
		EntityManager em = null;
		try {
			emFactory = Persistence.createEntityManagerFactory("jpa");
			em = emFactory.createEntityManager();
			em.getTransaction().begin();

			User user = new User();
			user.setName("BO");
			em.persist(user);
			// 提交后name的值？
			user.setName("SUN");
			em.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			// 打印异常栈详细信息
			logger.error("持久化异常！", e);
		} finally {
			// 关闭EntityManager
			if (em != null && em.isOpen()) {
				em.close();
			}
			// 关闭EntityManagerFactory
			if (emFactory != null && emFactory.isOpen()) {
				emFactory.close();
			}
		}
	}

	/**
	 * 实现：创建User对象，关闭em，开启em2，持久化对象 对象状态？是否可以完成持久化？
	 */
	private static void persist1() {
		EntityManagerFactory emFactory = null;
		EntityManager em = null;
		try {
			emFactory = Persistence.createEntityManagerFactory("jpa");
			em = emFactory.createEntityManager();
			em.getTransaction().begin();

			User user = new User();
			user.setName("BO");
			em.getTransaction().commit();
			em.close();

			EntityManager em2 = emFactory.createEntityManager();
			em2.getTransaction().begin();
			em2.persist(user);
			em2.getTransaction().commit();
			em2.close();
		} catch (Exception e) {
			// TODO: handle exception
			// 打印异常栈详细信息
			logger.error("持久化异常！", e);
		} finally {
			// 关闭EntityManager
			if (em != null && em.isOpen()) {
				em.close();
			}
			// 关闭EntityManagerFactory
			if (emFactory != null && emFactory.isOpen()) {
				emFactory.close();
			}
		}
	}

	/**
	 * 实现：使用find()方法加载对象，更新对象属性，同步 修改后使用什么方法同步更新？
	 */
	private static void find() {
		EntityManagerFactory emFactory = null;
		EntityManager em = null;
		try {
			emFactory = Persistence.createEntityManagerFactory("jpa");
			em = emFactory.createEntityManager();
			em.getTransaction().begin();

			User user = em.find(User.class, 1);
			user.setName("SUN");
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			// 打印异常栈详细信息
			logger.error("持久化异常！", e);
		} finally {
			// 关闭EntityManager
			if (em != null && em.isOpen()) {
				em.close();
			}
			// 关闭EntityManagerFactory
			if (emFactory != null && emFactory.isOpen()) {
				emFactory.close();
			}
		}
	}

	/**
	 * 实现：加载user对象，更新对象属性，使用refresh()方法同步对象 对象状态？对象属性值？
	 */
	private static void refresh() {
		EntityManagerFactory emFactory = null;
		EntityManager em = null;
		try {
			emFactory = Persistence.createEntityManagerFactory("jpa");
			em = emFactory.createEntityManager();
			em.getTransaction().begin();

			User user = em.find(User.class, 1);
			user.setName("SUN");
			em.refresh(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			// 打印异常栈详细信息
			logger.error("持久化异常！", e);
		} finally {
			// 关闭EntityManager
			if (em != null && em.isOpen()) {
				em.close();
			}
			// 关闭EntityManagerFactory
			if (emFactory != null && emFactory.isOpen()) {
				emFactory.close();
			}
		}
	}

	/**
	 * 实现：创建User对象，打印现象对象创建时间 新建状态转为持久化状态对象，如何在同一em事务中获取由数据库生成的数据信息？
	 * 
	 */
	private static void refresh2() {
		EntityManagerFactory emFactory = null;
		EntityManager em = null;
		try {
			emFactory = Persistence.createEntityManagerFactory("jpa");
			em = emFactory.createEntityManager();
			em.getTransaction().begin();

			User user = new User();
			user.setName("BO");
			em.persist(user);
			System.out.println(user.getInsertTime());
			em.refresh(user);
			System.out.println(user.getInsertTime());

			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			// 打印异常栈详细信息
			logger.error("持久化异常！", e);
		} finally {
			// 关闭EntityManager
			if (em != null && em.isOpen()) {
				em.close();
			}
			// 关闭EntityManagerFactory
			if (emFactory != null && emFactory.isOpen()) {
				emFactory.close();
			}
		}
	}

	/**
	 * 实现：加载user对象，关闭session至对象为脱管状态，更新对象，开启session2，合并更新 脱管状态对象是否可以继续更新？如何同步更新？
	 * 
	 */
	private static void merge() {
		EntityManagerFactory emFactory = null;
		EntityManager em = null;
		try {
			emFactory = Persistence.createEntityManagerFactory("jpa");
			em = emFactory.createEntityManager();
			em.getTransaction().begin();
			User user = em.find(User.class, 1);
			em.getTransaction().commit();
			user.setName("ZHANG");
			em.close();

			EntityManager em2 = emFactory.createEntityManager();
			em2.getTransaction().begin();
			em2.merge(user);
			em2.getTransaction().commit();
			em2.close();

		} catch (Exception e) {
			// TODO: handle exception
			// 打印异常栈详细信息
			logger.error("持久化异常！", e);
		} finally {
			// 关闭EntityManager
			if (em != null && em.isOpen()) {
				em.close();
			}
			// 关闭EntityManagerFactory
			if (emFactory != null && emFactory.isOpen()) {
				emFactory.close();
			}
		}
	}

	/**
	 * 实现：前端传入封装了id及更新name属性的User对象，将新name属性同步 User对象为脱管状态？使用什么方法更新？
	 * 
	 */
	private static void merge2() {
		User user = new User();
		user.setId(1);
		user.setName("LIU");

		EntityManagerFactory emFactory = null;
		EntityManager em = null;
		try {
			emFactory = Persistence.createEntityManagerFactory("jpa");
			em = emFactory.createEntityManager();
			em.getTransaction().begin();

			User user2 = em.find(User.class, user.getId());
			user2.setName(user.getName());

			em.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			// 打印异常栈详细信息
			logger.error("持久化异常！", e);
		} finally {
			// 关闭EntityManager
			if (em != null && em.isOpen()) {
				em.close();
			}
			// 关闭EntityManagerFactory
			if (emFactory != null && emFactory.isOpen()) {
				emFactory.close();
			}
		}
	}

}
