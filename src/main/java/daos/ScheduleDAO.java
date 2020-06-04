package daos;

import java.util.List;

import org.hibernate.Session;

import hibernatecfg.HibernateUtil;
import pojos.Student;
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
}
