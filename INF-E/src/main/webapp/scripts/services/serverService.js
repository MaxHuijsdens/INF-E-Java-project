koekoek.service('serverService', function($http){	
    
    this.getServer = function(id){
    	return $http.get('server/' + id + '/');
    };
    
    this.updateServer = function(server){
    	return $http.post('server/update/' + server.id, angular.toJson(server));
    };
    
	this.getServerList = function(){
		return $http.get('server/serverList');
	};
	
	this.getServerVersionList = function(){
		return $http.get('server/serverVersionsList');
	};
	
	this.getServerMeasurementList = function(){
		return $http.get('measurement/getLatest');
	};
	
	this.getServerListBySla = function(slaId){
		return $http.get('server/serverList/slaId/' + slaId);
	};
	
	this.getServerVersionListBySla = function(slaId){
		return $http.get('server/serverVersionsList/slaId/' + slaId);
	};
	
	this.getServerMeasurementListBySla = function(slaId){
		return $http.get('measurement/getLatest/slaId/' + slaId);
	};
	
	
	this.getServerListByHostName = function(hostName){
		return $http.get('server/serversByHost/' + hostName);
	};
	
	this.getServerVersions = function(serverId, showVersionHistory){
		return $http.get('server/serverVersions/' + serverId + '/showVersionHistory/' + showVersionHistory);
	};
	
	this.getServerUsers = function(serverId, showKnownUsers){
		return $http.get('server/serverUsers/' + serverId + '/showKnownUsers/' + showKnownUsers);
	};
	
	this.getServerUserChange = function(serverId, showKnownUsers){
		return $http.get('server/serverUserChange/' + serverId + '/showKnownUsers/' + showKnownUsers);
	};
	
	this.saveNote = function(note){
		if(!note.id){
			return $http.post("note", angular.toJson(note));
		}else{
			return $http.post("note/" + note.id, angular.toJson(note));
		}
		
	};
	
	this.getNoteList = function(){
		return $http.get("note/noteList");
	};
	
	this.getServerNotes = function(serverId){
		return $http.get("note/noteListByServer/" + serverId);
	};
	
	this.deleteNote = function(noteId){
		return $http.post("note/delete/" +  noteId);
	};
	
	this.getServerIptables = function(serverId, showKnownIptables){
		return $http.get("server/serverIptables/" + serverId + '/showKnownIptables/' + showKnownIptables);
	};
	
});