package com.colleboration.restcontroller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.colleboration.service.BlogPostService;
import com.colleboration.service.UserService;
import com.colleboration.model.BlogComment;
import com.colleboration.model.BlogPost;
import com.colleboration.model.Error;
import com.colleboration.model.User;

@RestController
public class BlogPostController {

	@Autowired
	BlogPostService blogPostService;
	@Autowired
	UserService userService;


	 public BlogPostController() {
		  System.out.println("BLOGPOSTCONTROLLER INSTANTIATED");
	  }
	 @PostMapping(value="/addblogpost")
		public ResponseEntity<?> addBlogPost(@RequestBody BlogPost blogPost,HttpSession session)
		{
			String username=(String)session.getAttribute("username");
			if(username==null)
			{
				Error error=new Error(5,"Unauthorized access....plaese login...");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			
		// String username="u"; 	used for POSTMAN
		 blogPost.setPostedOn(new Date());
		 User postedBy=userService.getUserByUsername(username);
		 blogPost.setPostedBy(postedBy); //postedBy is an object of type User
		 
		 try {
			blogPostService.addBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		 } catch (Exception e) {
			 Error error=new Error(7,"Unable to save blog post details....");
			 return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR); //exception
			
		 }
		}
	 

		
		@GetMapping(value="/getblogs/{approved}")
		// /getblogs/0 -> blogs waiting for approval
		// /getblogs/1 -> blogs which are approved
		public ResponseEntity<?>getBlogs(@PathVariable int approved,HttpSession session)
		{
			String username=(String)session.getAttribute("username");
			if(username==null)
			{
				Error error=new Error(5,"Unauthorized access....plaese login...");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			List<BlogPost> blogs=blogPostService.getBlogs(approved);
				return new ResponseEntity<List<BlogPost>>(blogs, HttpStatus.OK);
			}
			
			
		@GetMapping(value="/getblogbyid/{id}")
		public ResponseEntity<?>getBlogById(@PathVariable int id,HttpSession session)
		{
			String username=(String)session.getAttribute("username");
			if(username==null)
			{
				Error error=new Error(5,"Unauthorized access....plaese login...");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
		BlogPost blogpost=blogPostService.getBlogById(id);
		return new ResponseEntity<BlogPost>(blogpost, HttpStatus.OK);
		}
		
		
		@PutMapping(value="/updateblog")
		public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session) {
			String username=(String)session.getAttribute("username");
			if(username==null) 
				{
					Error error=new Error(5,"Unauthorized access....");
					return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
				}
			// admin has rejected the blogpost but reason not mentioned
			if(!blogPost.isApproved() && blogPost.getRejectionReason()==null)
				blogPost.setRejectionReason("Not Mentioned");
			blogPostService.updateBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		
		
		
		@PostMapping(value="/addcomment")
		// blogComment : ('commentText' : 'thanks(comment)','blogpost' :('id':387,'blogTitle':'introduction to spring...',...)
		public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session) {
			String username=(String)session.getAttribute("username");
			if(username==null) 
				{
					Error error=new Error(5,"Unauthorized access....");
					return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
				}
			User user=userService.getUserByUsername(username);
			blogComment.setCommentedBy(user);
			blogComment.setCommentedOn(new Date());
			try {
				blogPostService.addBlogComment(blogComment);
				return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
			} catch(Exception e)
			{
				Error error=new Error(7,"Unable to post comments");
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
			
		
		
		@GetMapping(value="/getcomments/{blogPostId}")
		public ResponseEntity<?> getComments(@PathVariable int blogPostId, HttpSession session) {
			String username=(String)session.getAttribute("username");
			if(username==null) 
				{
					Error error=new Error(5,"Unauthorized access....");
					return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
				}
			List<BlogComment> blogComments=blogPostService.getBlogComments(blogPostId);
			return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
		}
		
		

		@GetMapping(value="/test")
		public ResponseEntity<String> testMethod()
		{
			return new ResponseEntity<String>("Test RestController", HttpStatus.OK);
		}
}
