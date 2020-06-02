package daos;

import java.util.List;

import org.hibernate.Session;

import hibernatecfg.HibernateUtil;
import pojos.ClassCourseDetail;

public class ClassCourseDetailDAO implements ObjectDAOImpl<ClassCourseDetail>{
	public List<ClassCourseDetail> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from ClassCourseDetail", ClassCourseDetail.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
