package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernatecfg.HibernateUtil;
import pojos.Class;
import pojos.Student;

public class ClassDAO implements ObjectDAOImpl<Class>{
	public List<pojos.Class> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Class", Class.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
