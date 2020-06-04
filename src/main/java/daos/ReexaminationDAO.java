package daos;

import java.util.List;

import org.hibernate.Session;

import hibernatecfg.HibernateUtil;
import pojos.Reexamination;
import pojos.Student;

public class ReexaminationDAO implements ObjectDAOImpl<Reexamination>{
	public List<Reexamination> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Reexamination", Reexamination.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
