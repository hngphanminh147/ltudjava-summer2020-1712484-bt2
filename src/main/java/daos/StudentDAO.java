package daos;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernatecfg.HibernateUtil;
import pojos.Student;

public class StudentDAO implements ObjectDAOImpl<Student>{
	public void save(Student s) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(s);
			transaction.commit();;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public List<Student> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Student", Student.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	public Student get(String sId) {
		Student s = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			s = session.get(Student.class, sId);
		} catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		return s;
	}
}
