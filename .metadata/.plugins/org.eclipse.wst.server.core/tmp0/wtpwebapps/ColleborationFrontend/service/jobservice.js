/**
 *  Job service
 */


app.factory('JobService',function($http) {
	var JobService={}
	var BASE_URL="http://localhost:8080/ColleborationMiddleware"
		
		JobService.addJob=function(job) {
		return $http.post(BASE_URL + "/addjob",job)
	}
	
	JobService.getAllJobs=function() {
		return $http.get(BASE_URL + "/getalljobs")
	}
	
	JobService.getJobDetails=function(jobId) {
		return $http.get(BASE_URL + "/getjob/"+jobId)
	}
	
	
return JobService;	
}
)