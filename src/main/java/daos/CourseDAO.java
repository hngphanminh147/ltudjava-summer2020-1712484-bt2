package daos;

import java.util.List;

import org.hibernate.Session;

import hibernatecfg.HibernateUtil;
import pojos.Course;

public class CourseDAO {
	public static List<Course> getCourses() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Course", Course.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
