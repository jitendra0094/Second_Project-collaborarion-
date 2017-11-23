package com.colleboration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colleboration.dao.JobDao;
import com.colleboration.model.Job;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobDao jobDao;

	public void addJob(Job job) {
		jobDao.addjob(job);

	}
	
	public List<Job> getAllJobs() {
		return jobDao.getAllJobs();
		}


		public Job getJob(int jobId) {
		return jobDao.getJob(jobId);
			
		}


}
