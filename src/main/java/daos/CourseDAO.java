package daos;

import java.util.List;

import org.hibernate.Session;

import constant.Constant;
import hibernatecfg.HibernateUtil;
import pojos.Class;
import pojos.Course;
import pojos.Student;

public class CourseDAO implements ObjectDAOImpl<Course> {
	public List<Course> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Course", Course.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public Course getByName(String name) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Course where name like '" + name + "'", Course.class).getSingleResult();
		} catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public Course getByCId(String cId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Course where cId like '" + cId + "'", Course.class).getSingleResult();
		} catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	public List<Course> getByClId(String clId){
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createSQLQuery("call " + Constant.GET_COURSES_BY_CLASS_PROC_NAME + "(:MA_LOP)")
					.addEntity(Course.class).setParameter("MA_LOP", clId).list();
		} catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
