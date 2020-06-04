package daos;

import java.util.List;

import org.hibernate.Session;

import hibernatecfg.HibernateUtil;
import pojos.Record;
import pojos.Student;

public class RecordDAO  implements ObjectDAOImpl<Record>{
	public List<Record> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Record", Record.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	public List<Record> gets(String sId){
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Record r where r.sID =" + sId, Record.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
