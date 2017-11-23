/**
 * Friend service
 */


app.factory('FriendService',function($http) {
	var friendService={}
	var BASE_URL="http://localhost:8080/ColleborationMiddleware"
		
		friendService.listOfSuggestedUsers=function(){
		return $http.get(BASE_URL + "/getsuggestedusers")
	   }
	
	friendService.pendingRequests=function(){
		return $http.get(BASE_URL + "/pendingrequests")
		
	}
	
	friendService.updatePendingRequests=function(request){
		return $http.get(BASE_URL + "/updatependingrequests",request)
	}
	
	friendService.sendFriendRequest=function(toId){
		return $http.get(BASE_URL + "/friendrequest/" + toId)
	   }
	
	friendService.listofFriends=function(){
		return $http.get(BASE_URL + "/lisoffriends" )
	   }
	
	
	return friendService;
	})