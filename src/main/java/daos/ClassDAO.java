package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernatecfg.HibernateUtil;
import pojos.Class;
import pojos.Course;

public class ClassDAO implements ObjectDAOImpl<Class>{
	public List<Class> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Class", Class.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	public Class getByName(String name) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Class where name like '" + name + "'", Class.class).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public Class getByClId(String clId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Class where clId like '" + clId + "'", Class.class).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
