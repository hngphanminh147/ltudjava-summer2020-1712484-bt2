package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernatecfg.HibernateUtil;
import pojos.Record;
import pojos.Student;

public class RecordDAO implements ObjectDAOImpl<Record> {
	public List<Record> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Record", Record.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public List<Record> getBySId(String sId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Record r where r.sID =" + sId, Record.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public List<Record> getByClassCourse(String clID, String cID) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Record r where r.clID = :clID and r.cID = :cID", Record.class)
					.setParameter("clID", clID).setParameter("cID", cID).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public Record getBySIdClassCourse(String sID, String clID, String cID) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session
					.createQuery("from Record r where r.clID = :clID and r.cID = :cID and r.sID = :sID", Record.class)
					.setParameter("clID", clID).setParameter("cID", cID).setParameter("sID", sID).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public void update(String clID,String cID,String sID,int midR,int finalR,int otherR,double grade) {
		Transaction transaction = null;
		try  (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			Record r = getBySIdClassCourse(sID, clID, cID);
			r.setMidTerm(midR);
			r.setFinalTerm(finalR);
			r.setOther(otherR);
			r.setGrade(grade);
			
			session.saveOrUpdate(r);
			transaction.commit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
