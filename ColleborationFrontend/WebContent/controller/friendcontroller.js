/**
 *  FriendController
 */


app.controller('FriendController',function($scope,FriendService,$location) {
	
	function listOfSuggestedUsers(){
	   FriendService.listOfSuggestedUsers().then(function(response){
		   $scope.suggestedUsers=response.data  //List<User>
	   },
	   function(response){
		   if(response.status==401)
			   $location.path('/login')
	   })	
	}   
	
	function pendingRequests(){
		   FriendService.pendingRequests().then(function(response){
			   $scope.pendingRequests=response.data  //List<User>
		   },
		   function(response){
			   if(response.status==401)
				   $location.path('/login')
		   })	
		}  
	
	    
	    	 function listofFriends(){
		    	 FriendService.listofFriends().then(function(response){
		    		alert(response.data)
		    		 $scope.friends=response.data //List<String>
		    	 },
		    	       function(response){
		    		 if(response.status==401)
		    			 $location.path('/login')
		    	 })
		     }
		 
	
	$scope.sendFriendRequest=function(toId){
		FriendService.sendFriendRequest(toId).then(function(response){
			alert('Friend Request has been sent successfully')
			listOfSuggestedUsers()
			$location.path('/getsuggestedusers')
		}, 
		function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.updataPendingRequests=function(request,statusValue){//statusValue=A/D
		//before assingment requests.status is P
		
		console.log(request)
		consolelog(requests.status ) //P
		request.status=statusValue //statusValue A/P
		
		console.log(request.status) //A(accept) /D(delete)
		console.log(request)
		FriendService.updatePandingRequest(request).then(function(response){
			pendingRequests()
			$location.path('/pendingrequests')
		},function(response){
			if(response.status=401)
				$location.path('/login')
		
		})
		
	}
	
	//function call
	listOfSuggestedUsers() //SELECT
	 pendingRequests()                      //SELECT
	
	})