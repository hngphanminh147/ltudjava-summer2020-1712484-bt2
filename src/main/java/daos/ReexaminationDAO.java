package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
	
	public void save(Reexamination r) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(r);
			transaction.commit();
		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
			exception.printStackTrace();
		}
	}
	
	public void update(String clID, String cID, String sID, int type, int expectScore, String reason) {
		
	}
}
