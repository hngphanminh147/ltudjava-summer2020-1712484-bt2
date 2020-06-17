package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernatecfg.HibernateUtil;
import pojos.Member;

public class MemberDAO implements ObjectDAOImpl<Member> {

	@Override
	public List<Member> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Member", Member.class).list();
		} catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public Member getByUsername(String username) {
		Member m = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			m = session.get(Member.class, username);
		} catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		return m;
	}

	public void updatePassword(String username, String newPassword) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
					
//			Member newMem = new Member(username, newPassword);
//			session.persist(newMem);
//			session.evict(newMem);
//			session.update(newMem);
			
			Member mem = new MemberDAO().getByUsername(username);
			mem.setPassword(newPassword);
			session.saveOrUpdate(mem);
			transaction.commit();
		} catch (Exception exception) {

			exception.printStackTrace();
			// TODO: handle exception
		}
	}
}
