koekoek.factory('loginService', function($http, $rootScope, $location, messageService) {
	var instance = {
		user : {}
	};

	instance.login = function(username, password, success) {
		if (username && password) {
			instance.setAuthentication ({name : username, roles : []});
			success();
		} else if (!username) {
			messageService.addError('U dient een gebruikersnaam op te geven!');
		} else if (!password) {
			messageService.addError('U dient een wachtwoord op te geven!');
		}
	};

	instance.logout = function(success) {
			instance.user = {};
			success();
	};

	instance.verify = function(success) {
		var authentication = {name : 'admin', roles : []};
		instance.setAuthentication(authentication);
		success({authenticated: true});
	};

	instance.isAuthenticated = function() {
		return instance.user.name && instance.user.name !== '';
	};

	instance.hasRole = function(roleName) {
		var authorized = false;
		if (instance.isAuthenticated()) {
			authorized = instance.user.roles.indexOf('ROLE_' + roleName) !== -1;
		}
		return authorized;
	};

	instance.setAuthentication = function(authentication) {
		instance.user = {
		    name : authentication.name,
		    roles : authentication.roles
		};
	};

	return instance;
});
