/**
 *  BlogPost service
 */


app.factory('BlogPostService',function($http) {
	var blogPostService={}
	var BASE_URL="http://localhost:8080/ColleborationMiddleware"
		blogPostService.addBlogPost=function(blogPost) {
		return $http.post(BASE_URL + "/addblogpost",blogPost)
	}
	
	blogPostService.blogsWaitingForApproval=function(){
		return $http.get(BASE_URL + "/getblogs/"+0)   //select * from blogpost where approved=0
	}
	
	blogPostService.blogsAapproved=function(){
		return $http.get(BASE_URL + "/getblogs/"+1)    //select * from blogpost where approved=1
	}
	
	blogPostService.getBlogPostById=function(id) {
		return $http.get(BASE_URL + "/getblogbyid/"+id)
	}
	
	/*
	 * to update approved property and rejection reason(approve/reject)
	 * id,blogTitle,blogContent.postedBy,postedOn,approved,rejectionReason
	 */
	blogPostService.updateBlogPost=function(blogPost){
		return $http.put(BASE_URL + "/updateblog", blogPost)
	}
	
	blogPostService.addComment=function(blogComment){
		console.log(blogComment)
		return $http.post(BASE_URL + "/addcomment", blogComment)
	}
	
	blogPostService.getBlogComments=function(blogPostId){
		
		return $http.get(BASE_URL + "/getcomments/" + blogPostId)
	}
	
	
	return blogPostService;
})