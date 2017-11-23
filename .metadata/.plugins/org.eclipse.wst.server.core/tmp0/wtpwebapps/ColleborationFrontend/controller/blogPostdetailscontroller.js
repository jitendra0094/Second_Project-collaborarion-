/**
 *  BlogPostDetailController
 *  getblogbyid/317
 *  getblogbyid/:id
 *  
 *  $routeParam:id => 317
 *  
 *  two statements
 */


app.controller('BlogPostDetailController',function($scope,$location,BlogPostService,$routeParams) {
	
	$scope.isRejected=false
	$scope.isLiked=false;
	var id=$routeParams.id
	/*alert('blogpostdetailcontroller instantiated')*/
    BlogPostService.getBlogPostById(id).then(function(response) {
    	$scope.blogPost=response.data //BlogPost [select * from blogpost where id=?]
    },function(response) {
    	if(response.status==401)
    		$location.path('/login')
    })	
    
    /*
     *  update approved/rejectionReason in blogpost table
     *  update blogpost set approved=1 where id=?
     *  (or)
     *  update blogpost set rejectionreason=? where id=?
     */
    
    $scope.updateBlogPost=function(){
		console.log("APPROVED/REJECTED" + $scope.blogPost.approved)
		BlogPostService.updateBlogPost($scope.blogPost).then(function(response) {
			$location.path('/getblogs')
		}, function(response) {
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
		})
	}
    
	$scope.updateLikes=function(){
		$scope.isLiked=!$scope.isLiked;
		if($scope.isLiked){
			$scope.blogPost.likes=$scope.blogPost.likes + 1 //1st click
		}
		else{
			$scope.blogPost.likes=$scope.blogPost.likes - 1 //2nd click
		}
		//update blogPost set likes=? where id=?
		BlogPostService.updateBlogPost($scope.blogPost).then(function(response) {
			console.log(response.data)
		}, function(response){
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	
	
	$scope.showRejectionTxt=function(val){
		$scope.isRejected=val    //true/false
	}
	

	
	
	$scope.addComment=function(){
	 console.log($scope.blogComment) //commenttext property in blogcomment
	  //value dor FK blogpost_id in blogcomment
	 $scope.blogComment.blogPost=$scope.blogPost  //blogPost property in blogComment
	 console.log($scope.blogComment)
	 BlogPostService.addComment($scope.blogComment).then(function(response) {
	 console.log(response.data)	
	  $scope.blogComment.commentText=''
		 getBlogComments();
	 }, function(response) {
		 if(response.status==401)
			 $location.path('/login')
		else
			$location.path('/getblogbyid/'+id) //blogdetails.html
			
	 })
		
	}
	
	
	function getBlogComments() { //select blogcomments for particular blogpost
		BlogPostService.getBlogComments(id).then(function(response) { // id is blogpost id
        $scope.blogComments=response.data //list of blogcomments for id
		}, function(response) {
			if(response.status==401)
				$location.path('/login')
		})
		
	}
	
	getBlogComments()
	
	
    
})