koekoek.factory('messageService', function($timeout) {
	var instance = {
		messages : []
	};

	instance.addMessage = function(type, texts, duration, callback) {
		
		if (angular.isString(texts)) {
			texts = [texts];
		}
		var message = {
		    type: type,
		    subMessages: texts.splice(1),
		    title: texts[0],
		    callback: callback
		};
		instance.messages.push(message);

		message.remove = function() {
			var index = instance.messages.indexOf(this);
			instance.messages.splice(index, 1);
		};

		message.click = function() {
			if (this.callback) {
				this.callback();
			}
		};

		$timeout(function() {
			message.remove();
		}, duration * 700);

	};

	instance.addInfo = function(texts, callback) {
		instance.addMessage('info', texts, 5, callback);
	};

	instance.addSuccess = function(texts, callback) {
		instance.addMessage('success', texts, 5, callback);
	};

	instance.addWarning = function(texts, callback) {
		instance.addMessage('warning', texts, 7, callback);
	};

	instance.addError = function(texts, callback) {
		instance.addMessage('danger', texts, 10, callback);
	};

	instance.clear = function() {
		instance.messages = [];
	};

	return instance;
});
