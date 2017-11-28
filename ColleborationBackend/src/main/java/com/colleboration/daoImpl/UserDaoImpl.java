package com.colleboration.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.colleboration.dao.UserDao;
import com.colleboration.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {


	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDaoImpl(SessionFactory sessionFactory) 
	{
		this.sessionFactory=sessionFactory;
	}
	
	/*
	 * username must be unique and not null
	 * email must be unique and not null
	 * email is null/username is null - it is an exception
	 */
	
	
	
@Transactional
	public boolean registerUser(User user) {
			Session session = sessionFactory.getCurrentSession();	
			try 
			{
			session.save(user);	
		  System.out.println("Insert User Table");
		return true;	
			}
		catch(Exception e)
		{
		System.out.println(e.getMessage());	
		return false;
		}

	}

      public boolean isUsernameValid(String username){
    	Session  session = sessionFactory.getCurrentSession();
    	User user =(User)session.get(User.class, username);
    	if(user==null)//no row selected-unique
    		return true;
    	else{
    		return false;// not null-1 row selected - duplicate
    	}
      }

      public boolean isEmailValid(String email){
    	  Session session =sessionFactory.getCurrentSession();
    	  Query query = session.createQuery("from user where email=?");
    	  query.setString(0, email);
    	  User user =(User)query.uniqueResult();
    	  if(user==null)//email is unique
      		return true;
      	else{
      		return false;// email is duplicate
      	}
      }


  	public User login(User user) {
  	    Session session=sessionFactory.getCurrentSession();
  	    System.out.println("0");
  	    Query query=session.createQuery("from User where username=? and password=?");
  	    System.out.println("1");
  	    query.setString(0, user.getUsername());
  	    System.out.println("2");
  	    query.setString(1, user.getPassword());
  	    System.out.println("3");
  	    user=(User) query.uniqueResult();
  	    System.out.println("4");
  	    return user;
  	}

  	
  	public void update(User user) {
  		Session session=sessionFactory.getCurrentSession();
  		session.update(user); //update set online=1, firstname=..lastname=... where username=? will same...only changing in online status
  		
  	}

  	
  	public User getUserByUsername(String username) {
  		Session session=sessionFactory.getCurrentSession();
  		 User user =(User) session.get(User.class, username);
  		  return user;
  	
  	}

  	
  	public boolean isUpdatedEmailValid(String email, String username) {
  		Session session=sessionFactory.getCurrentSession();
  		Query query=session.createQuery("from User where email=? and username!=?");
  		query.setString(0, email);
  		query.setString(1, username);
  		User user =(User)query.uniqueResult();
  		if(user==null)
  			return true;
  		else
  		    return false;
  	}

}
