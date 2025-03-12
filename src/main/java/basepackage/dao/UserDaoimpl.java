package basepackage.dao;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import basepackage.model.User;

@Repository
public class UserDaoimpl implements UserDao {

	
	@Autowired
	private SessionFactory  sessionFactory;
	
	

	@Override
	public void saveUser(User user) {
	
		try {
		
		Session session= sessionFactory.openSession();
		session.save(user);
		} catch(HibernateException e) {
			
			e.printStackTrace();
		}
	}

}