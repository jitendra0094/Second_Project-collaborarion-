package com.colleboration.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.colleboration.dao.FriendDao;
import com.colleboration.model.Friend;
import com.colleboration.model.User;


@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> listofSuggestedUsers(String username) {
		Session session= sessionFactory.getCurrentSession();
		SQLQuery query= session.createSQLQuery("Select  * from user where username in"
				+"(Select username from user where username!=? minus" 
				+"(select fromId from freiend where toId=?"
				+"union select toId from friend where fromId=?)"
				+")");
		   query.setString(0, username);
		   query.setString(1, username);
		   query.setString(2, username);
		   query.addEntity(User.class);//to convert records to user Objects
		   List<User> suggestedUsers= query.list();
		return suggestedUsers;
	}

	public void friendRequest(Friend friend) {
		Session session= sessionFactory.getCurrentSession();
		session.save(friend);
	}

	public List<Friend> pandingRequests(String toId) {
		Session session= sessionFactory.getCurrentSession();
		Query query= session.createQuery("from Frriend where toId=? and Status='P'");
		query.setString(0, toId);
		return query.list();
	}

	public void updatePendingRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		if(friend.getStatus()=='A')
			session.update(friend);   //update Friend set status='A' where id=?
		else
			session.delete(friend);   //delete from Friend where id=?
		
		
	}
	
/* List<String> use for SqlQuery for all friend list without all friend property obj...it return string....("gfdgf,gfh,fg")
	List<Friend> use for hibernateQuery for all friend list with friend obj.....return all detail
	*/


	public List<String> listOfFriends(String username) {
		
		Session session=sessionFactory.getCurrentSession();
		SQLQuery sqlQuery1=session.createSQLQuery("select fromId from FriendTable where toId=? and status='A' ")
				.addScalar("fromId", StandardBasicTypes.STRING);
		sqlQuery1.setString(0, username);
		List<String> list1=sqlQuery1.list();
		
		System.out.println("RESULT Of 1st QUERY" +list1);
		
		SQLQuery sqlQuery2=session.createSQLQuery("select toId from FriendTable where fromId=? and status='A' ")
				.addScalar("toId", StandardBasicTypes.STRING);
		sqlQuery2.setString(0, username);
		List<String> list2=sqlQuery2.list();
		System.out.println("RESULT Of 2st QUERY" +list2);
		
		list1.addAll(list2);  //list1=list1 U list2
		System.out.println("RESULT Of list1 + list2 " +list1);
		
		return list1;
	}
}
