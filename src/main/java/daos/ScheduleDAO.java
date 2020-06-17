package daos;

import java.util.List;

import org.hibernate.Session;

import hibernatecfg.HibernateUtil;
import pojos.Student;
import pojos.Class;
import pojos.Schedule;

public class ScheduleDAO implements ObjectDAOImpl<Schedule> {
	public List<Schedule> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Schedule", Schedule.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	public List<Schedule> getByClId(String clId){
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Schedule where clID like '" + clId + "'", Schedule.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
