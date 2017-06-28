var koekoek = angular.module('koekoek', [ 'ui.bootstrap', 'ngRoute', 'ngCookies', 'timer', 'ui.bootstrap.datetimepicker',
        'ngSwitchToggle' ]);

koekoek.config([ '$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {

	$httpProvider.defaults.withCredentials = true;

	$routeProvider.when('/', {
	    templateUrl : 'views/pages/login.html',
	    controller : 'loginController'
	}).when('/activitystream', {
	    templateUrl : 'views/pages/activityStream.html',
	    controller : 'activityStreamController'
	}).when('/servers', {
	    templateUrl : 'views/pages/serversOverview.html',
	    controller : 'serversOverviewController'
	}).when('/compare', {
	    templateUrl : 'views/pages/compareChart.html',
	    controller : 'chartPageController'
	}).when('/info/:serverId', {
	    templateUrl : 'views/pages/serverInfo.html',
	    controller : 'serverInfoController'
	}).when('/json', {
	    templateUrl : 'views/pages/jsonTestPage.html',
	    controller : 'jsonTestController'
	}).when('/customers', {
	    templateUrl : 'views/pages/customerSla.html',
	    controller : 'customerSlaController'
	}).when('/suppliers', {
	    templateUrl : 'views/pages/supplier.html',
	    controller : 'supplierController'
	}).when('/manage', {
	    templateUrl : 'views/pages/manage.html',
	    controller : 'manageController'
	}).when('/404', {
		templateUrl : 'views/pages/404.html'
	}).when('/403', {
		templateUrl : 'views/pages/403.html'
	}).otherwise({
		redirectTo : '/404'
	});
} ]);

koekoek.config(function() {
	Highcharts.setOptions({
	    global : {
		    useUTC : false
	    },
	    credits : {
		    enabled : false
	    }
	});
	moment.lang('nl');
});

koekoek.run(function($rootScope, $location, $http, messageService, loginService) {

	$rootScope.messageService = messageService;
	$rootScope.loginService = loginService;

	loginService.verify(function(authentication) {
		if (authentication.authenticated !== true) {
			$location.path('/');
		} else if ($location.path() === '/') {
			$location.path('/activitystream');
		}

		$rootScope.$on('$routeChangeStart', function(scope, next, current) {
			var roleName = next.$$route.role;
			if (roleName && !loginService.hasRole(roleName)) {
				messageService.addWarning("Cannot access page because you don't have the '" + roleName + "' role.");
				$location.path('/403');
			}
		});
	});

});

moment.lang('nl', {
    months : "januari_februari_maart_april_mei_juni_juli_augustus_september_oktober_november_december".split("_"),
    monthsShort : "jan_feb_mrt_apr_mei_jun_jul_aug_sep_okt_nov_dec".split("_"),
    weekdays : "zondag_maandag_dinsdag_woensdag_donderdag_vrijdag_zaterdag".split("_"),
    weekdaysShort : "zo._ma._di._wo._do._vr._za.".split("_"),
    weekdaysMin : "Zo_Ma_Di_Wo_Do_Vr_Za".split("_"),
    longDateFormat : {
        LT : "HH:mm",
        L : "DD-MM-YYYY",
        LL : "D MMMM YYYY",
        LLL : "D MMMM YYYY LT",
        LLLL : "dddd D MMMM YYYY LT"
    },
    calendar : {
        sameDay : '[vandaag om] LT',
        nextDay : '[morgen om] LT',
        nextWeek : 'dddd [om] LT',
        lastDay : '[gisteren om] LT',
        lastWeek : '[afgelopen] dddd [om] LT',
        sameElse : 'L'
    },
    relativeTime : {
        future : "over %s",
        past : "%s geleden",
        s : "een paar seconden",
        m : "één minuut",
        mm : "%d minuten",
        h : "één uur",
        hh : "%d uur",
        d : "één dag",
        dd : "%d dagen",
        M : "één maand",
        MM : "%d maanden",
        y : "één jaar",
        yy : "%d jaar"
    },
    ordinal : function(number) {
	    return number + ((number === 1 || number === 8 || number >= 20) ? 'ste' : 'de');
    },
    week : {
        dow : 1, // Monday is the first day of the week.
        doy : 4  // The week that contains Jan 4th is the first week of the year.
    }
});
