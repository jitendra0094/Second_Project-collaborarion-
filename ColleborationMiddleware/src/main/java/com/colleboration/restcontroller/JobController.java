package com.colleboration.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.colleboration.dao.JobDao;
import com.colleboration.model.Job;
import com.colleboration.model.Error;
import com.colleboration.model.User;
import com.colleboration.service.JobService;
import com.colleboration.service.UserService;

@RestController
public class JobController {
	

	@Autowired
	private JobDao jobDao;
	@Autowired
	private JobService jobService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addjob",method=RequestMethod.POST)
	public ResponseEntity<?> addJobs(@RequestBody Job job,HttpSession session){
		String username= (String)session.getAttribute("username");
		if(username==null){
			Error error= new Error(5,"Unauthorised accrss");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		User user= userService.getUserByUsername(username);
		if(!user.getRole().equals("ADMIN")){// user roll is not Admin
			Error error = new Error (6,"Access Denied");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
		}
		try{
			jobService.addJob(job);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}catch(Exception e){
			Error error =new Error(7,"Unable to insert job details");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	

	 @GetMapping(value="/getalljobs")
	public ResponseEntity<?> getAllJobs(HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobService.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	 
	 
	 @GetMapping(value="getjob/{jobId}")
	 public ResponseEntity<?> getJob(@PathVariable int jobId, HttpSession session)
		{
			String username=(String)session.getAttribute("username");
			if(username==null)
			{
				Error error=new Error(5,"Unauthorized access");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
		Job job=jobService.getJob(jobId);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	 	 
		} 
	 

}
