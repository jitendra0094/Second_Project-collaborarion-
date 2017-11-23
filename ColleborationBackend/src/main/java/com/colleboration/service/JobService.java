package com.colleboration.service;

import java.util.List;

import com.colleboration.model.Job;



public interface JobService {
	 void addJob(Job job);
	    

		List<Job> getAllJobs();

		Job getJob(int jobId);
	

}
