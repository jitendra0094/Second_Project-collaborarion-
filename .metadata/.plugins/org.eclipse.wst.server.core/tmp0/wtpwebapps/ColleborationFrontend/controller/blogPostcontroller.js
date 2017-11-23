/**
 * BlogPost Controller
 * 
 */


app.controller('BlogPostController',function($scope,BlogPostService,$location) {
	
	$scope.addBlogPost=function() {
		BlogPostService.addBlogPost($scope.blog).then(function(response) {
			alert('BlogPost added successfully and waiting for approval....')
		    $location.path('/getblogs')
		 
		},function(response) {//response.status [401/500]
		$scope.error=response.data.message //Error(code,message)
		if(response.status==401)  //401
			$location.path('/login')
			else//500
			$location.path('/addblogpost')	
		})
		
	}
	
	/*
	 * List of blogs which are approved
	 */
	
	function blogsApproved(){
		
	BlogPostService.blogsAapproved().then(function(response) {
		$scope.listOfBlogsApproved=response.data //List<blogPost> approved(1)
	}, function(response) {
		if(responser.status==401)
			$location.path('/login')
	})

}
	
	/*
	 * List of blogs waiting for approval
	 */
	
	function blogsWaitingForApproval(){
		BlogPostService.blogsWaitingForApproval().then(function(response){
			$scope.listOfBlogsWaitingForApproval=response.data //List<blogPost> waiting for approval(0)
    	}, function(response){
    		if(response.status==401)
    			$location.path('/login')
    	})
	}
	
	blogsApproved()
	blogsWaitingForApproval()
	
})