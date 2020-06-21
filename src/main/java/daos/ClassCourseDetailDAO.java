package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernatecfg.HibernateUtil;
import pojos.ClassCourseDetail;

public class ClassCourseDetailDAO implements ObjectDAOImpl<ClassCourseDetail> {
	public List<ClassCourseDetail> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from ClassCourseDetail", ClassCourseDetail.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public ClassCourseDetail getByClassCourseSID(String clID, String cID, String sID) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from ClassCourseDetail c where c.clID = '" + clID + "' and c.cID = '" + cID + "' and c.sID = '" + sID + "'", ClassCourseDetail.class).getSingleResult();
		} catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public void delete(String clID, String cID, String sID) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
//			session.createSQLQuery("delete from ClassCourseDetail where clID = :clID and cID = :cID and sID = :sID")
//					.setParameter("clID", clID).setParameter("cID", cID).setParameter("sID", sID);
//			session.delete(c);
			ClassCourseDetail c = getByClassCourseSID(clID, cID, sID);
			session.delete(c);
			transaction.commit();
		} catch (Exception exception) {
			exception.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			// TODO: handle exception
		}
	}
}
