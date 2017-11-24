package com.colleboration.dao;

import java.util.List;

import com.colleboration.model.BlogComment;
import com.colleboration.model.BlogPost;

public interface BlogPostDao {
	

	void addBlogPost(BlogPost blogPost);
	List<BlogPost> getBlogs(int approved); //0/1
	
	BlogPost getBlogById(int id);
	
	void updateBlogPost(BlogPost blogPost);
	
	void addBlogComment(BlogComment blogComment);
	
	List<BlogComment> getBlogComments(int blogPostId);

}
