package com.colleboration.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.colleboration.dao.BlogPostDao;
import com.colleboration.model.BlogComment;
import com.colleboration.model.BlogPost;

@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao {


	@Autowired
	SessionFactory sessionFactory;
	
	public BlogPostDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	


	public void addBlogPost(BlogPost blogPost) {
	Session session=sessionFactory.getCurrentSession();
	session.save(blogPost);	
	}

	
	public List<BlogPost> getBlogs(int approved) {
	Session session=sessionFactory.getCurrentSession();
	// if approved=0, select* from blogPost where approved=0; blogs waiting for approval
	// if approved=1, select* from blogPost where approved=1; blogs which are approved
	
	String queryStr="";
	if(approved==1)
		queryStr="from BlogPost where approved=" +approved;
	else
		queryStr="from BlogPost where rejectionReason is null and approved=" +approved;
	
	Query query =session.createQuery(queryStr);	
	return query.list();
	}



	public BlogPost getBlogById(int id) {
	Session session=sessionFactory.getCurrentSession();
	BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		return blogPost;
	}



	public void updateBlogPost(BlogPost blogPost) {
	Session session=sessionFactory.getCurrentSession();
	session.update(blogPost); //update approved
	}



	public void addBlogComment(BlogComment blogComment) {
	Session session=sessionFactory.getCurrentSession();
	System.out.println("blogComment");
	session.save(blogComment);
	System.out.println("blogComment1");

		
	}


	public List<BlogComment> getBlogComments(int blogPostId) {
		Session session=sessionFactory.getCurrentSession();
		System.out.println("comments");
		Query query=session.createQuery("from BlogComment where blogPost.id=" + blogPostId);
		System.out.println("comments1");
		return query.list();
		
	}

}
