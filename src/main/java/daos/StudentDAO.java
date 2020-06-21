package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import constant.Constant;
import hibernatecfg.HibernateUtil;
import pojos.Student;

public class StudentDAO implements ObjectDAOImpl<Student> {
	public void save(Student s) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(s);
			transaction.commit();
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

	public Student getBySId(String sId) {
		Student s = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			s = session.get(Student.class, sId);
		} catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		return s;
	}

	public List<Student> getByClass(String clId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createSQLQuery("call " + Constant.GET_STUDENTS_BY_CLASS_PROC_NAME + "(:MA_LOP)")
					.addEntity(Student.class).setParameter("MA_LOP", clId).list();
		} catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public List<Student> getByClassCourse(String clId, String cID) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session
					.createSQLQuery("call " + Constant.GET_STUDENTS_BY_CLASS_COURSE_PROC_NAME + "(:MA_LOP, :MA_MON)")
					.addEntity(Student.class).setParameter("MA_LOP", clId).setParameter("MA_MON", cID).list();
		} catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
