/**
 * Change a regular checkbox to a switch.
 */
koekoek.directive('switchCheckbox', function() {
    return {
	restrict: 'E',
	scope: {
	    onClicked: '&',
	    onModel: '=',
	    switchId: '@',
	    switchCaption: '@'
	},
	replace: true,
	template: function(scope, element) {
	    return '<div>' +
		(element.switchCaption ? '<span class="checkbox_switch_caption">{{switchCaption}}</span><div class="checkbox_switch checkbox_switch_table_header">' : '<div class="checkbox_switch">') +
		'<input type="checkbox" id="{{switchId}}" class="checkbox_switcher" checked="true" data-ng-checked="onModel" data-ng-model="onModel" data-ng-click="onClicked()">' +
		'<label for="{{switchId}}" class="onoffswitch-label">' +
		'<div class="inner"></div>' +
		'<div class="switch"></div>' +
		'</label>' +
		'</div>' +
		'</div>';
	}
    };
});