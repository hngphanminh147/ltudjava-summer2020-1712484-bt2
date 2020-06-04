package hibernatecfg;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static String dbname = "sm";
	private static String connectionUser = "root";
	private static String connectionPassword = "1111";

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
//			sessionFactory = new Configuration().configure().buildSessionFactory();
				Configuration configuration = new Configuration();
				
				Properties properties = new Properties();
				properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//				properties.put(Environment.URL, "jdbc:mysql://localhost:3306/" + dbname
//						+ "?autoReconnect&amp;useUnicode=true&amp;characterEncoding=UTF-8");
				properties.put(Environment.URL, "jdbc:mysql://localhost:3306/" + dbname);
				properties.put(Environment.USER, connectionUser);
				properties.put(Environment.PASS, connectionPassword);
				properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				properties.put(Environment.SHOW_SQL, "true");
				properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//				properties.put(Environment.HBM2DDL_AUTO, "create-drop");
				configuration.setProperties(properties);
				
				configuration.addAnnotatedClass(pojos.Class.class);
				configuration.addAnnotatedClass(pojos.ClassCourseDetail.class);
				configuration.addAnnotatedClass(pojos.Course.class);
				configuration.addAnnotatedClass(pojos.Record.class);
				configuration.addAnnotatedClass(pojos.Record.class);
				configuration.addAnnotatedClass(pojos.Reexamination.class);
				configuration.addAnnotatedClass(pojos.Student.class);
				configuration.addAnnotatedClass(pojos.Schedule.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				ex.printStackTrace();
				throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory;
	}
}