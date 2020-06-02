package daos;

import java.util.List;

import org.hibernate.Session;

import hibernatecfg.HibernateUtil;
import pojos.Reexamine;
import pojos.Student;

public class ReexamineDAO implements ObjectDAOImpl<Reexamine>{
	public List<Reexamine> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Reexamine", Reexamine.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
