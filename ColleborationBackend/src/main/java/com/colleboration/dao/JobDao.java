package com.colleboration.dao;

import java.util.List;

import com.colleboration.model.Job;



public interface JobDao {

void addjob(Job job);
	
	
	List<Job>getAllJobs();
	
    Job getJob (int jobId);
}
