package com.colleboration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colleboration.dao.BlogPostDao;
import com.colleboration.dao.UserDao;
import com.colleboration.model.BlogComment;
import com.colleboration.model.BlogPost;

@Service
public class BlogPostServiceImpl implements BlogPostService {

	@Autowired
	private BlogPostDao blogPostDao;
	
	@Autowired
	private UserDao userDao;


	public void addBlogPost(BlogPost blogPost) {
		blogPostDao.addBlogPost(blogPost);

	}

	
	public List<BlogPost> getBlogs(int approved) {
	return blogPostDao.getBlogs(approved);
	}


	
	public BlogPost getBlogById(int id) {
	return  blogPostDao.getBlogById(id);
	}


	
	public void updateBlogPost(BlogPost blogPost) {
	blogPostDao.updateBlogPost(blogPost);	
		
	}


	public void addBlogComment(BlogComment blogComment) {
     blogPostDao.addBlogComment(blogComment);		
	}


	
	public List<BlogComment> getBlogComments(int blogPostId) {
	  return blogPostDao.getBlogComments(blogPostId);
	
	}


}
