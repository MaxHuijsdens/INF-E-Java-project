koekoek.directive('dateTimePicker', function() {
    return {
	restrict : 'E',
	scope : {
	    ngModel : '=',
	    text : '@',
	    onTimeSetMethod : '@'
	},
	link : function(scope, element, attrs) {
	    if(scope.ngModel != null){
		scope.dateTime =  moment(scope.ngModel);
		scope.dateTimeFormatted =  moment(scope.ngModel).format("HH:mm DD-MM-YYYY");
	    }else{
		scope.dateTime =  moment();
		scope.dateTimeFormatted =  moment().format("HH:mm DD-MM-YYYY");
		scope.ngModel = moment().format('YYYY-MM-DDTHH:mm');
	    }
	    scope.setTime = function(newDate, oldDate) {
		scope.dateTimeFormatted =  moment(newDate).format("HH:mm DD-MM-YYYY");
		scope.ngModel = moment(newDate).format('YYYY-MM-DDTHH:mm');
		if(scope.onTimeSetMethod || scope.onTimeSetMethod != ''){
		    scope.$parent.$eval(attrs.onTimeSetMethod);
		}				
	    };
	},
	template: '<div class="dropdown">' +
	    '<a class="dropdown-toggle my-toggle-select" id="dLabel" role="button" data-toggle="dropdown" data-target="#" href="">' +
		'<div class="input-group">' +
		    '<span ng-if="text" class="input-group-addon">{{ text }}</span>' + 
			'<input type="text" class="form-control" placeholder="Datum" data-ng-model="dateTimeFormatted">' +
			'<span class="input-group-addon"><i class="fa fa-calendar"></i></span>' +
		'</div>' +
	    '</a>' +
	    '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">' +
		'<datetimepicker data-ng-model="dateTime" data-on-set-time="setTime"></datetimepicker>' +
	    '</ul>' +
	'</div>'
    };
});