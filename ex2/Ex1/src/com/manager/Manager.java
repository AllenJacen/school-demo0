package com.manager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.entity.Address;
import com.entity.User;

public class Manager {
	private static Logger logger = LogManager.getLogger(Manager.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		persist();
	}
	private static void persist() {
		EntityManagerFactory emfactory= null;
		EntityManager em = null;
		try {
			emfactory = Persistence.createEntityManagerFactory("jpa");
			em = emfactory.createEntityManager();
			em.getTransaction().begin();
			
			User user = new User();
			user.setUserName("WANG");
			em.persist(user);
			user.setUserName("SUN");
			
			em.getTransaction().commit();
		}catch(Exception e) {
			// TODO: handle exception
						// 打印异常栈详细信息
			logger.error("持久化异常！", e);
		}finally {
			if(em.isOpen() && em != null) {
				em.close();
			}
			if(emfactory != null && emfactory.isOpen() ) {
				emfactory.close();
			}
		}
	}
}
