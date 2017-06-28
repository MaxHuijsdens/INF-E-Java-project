koekoek.controller('jsonTestController', function($scope, $http, $routeParams, $location) {
	$scope.persistentDataResponse = "";
	$scope.dateUntil;
	
	$scope.onTimeSet = function(newDate, oldDate){
		console.log("timeset " + newDate);
	};
	$scope.views = [ {
        label : 'Info',
            value : 'info'
        },
        {
            label : 'Metrieken',
            value : 'metrics'
        }
    ];

	$scope.measurements = [ {
		  label : 'Servercheck',
	        value : 'servcheck'
       },
       {
      	  label : 'N2',
            value : 'n2'
     }
  ];
	$scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();

	  $scope.clear = function () {
	    $scope.dt = null;
	  };

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	  };

	  $scope.initDate = new Date('2016-15-20');
	  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	  $scope.format = $scope.formats[0];
	$scope.sendPersistentData = function() {
		$http({
	    url: 'persistentdata',
	    method: 'POST',
	    data: JSON.stringify({
	    	  "serverData": {
	    		    "hostName": "vps1111",
	    		    "hostNameLong": "vps34638.public.cloudvps.com",
	    		    "hostName42": "lolol test",
	    		    "timeOfMeasurement": "20140826-1510",
	    		    "packagesCanUpdated": 0,
	    		    "packagesSecurity": 0,
	    		    "restartNeeded": true,
	    		    "diskSize": 79,
	    		    "ufwStatus": "not installed",
	    		    "scriptErrorOccured": false,
	    		    "use42mirror": false,
	    		    "newRelic": ""
	    		  },
	    		  "versionsData": {
	    		    "ubuntuVersion": "13.05.8",
	    		    "puppetVersion": "2.7.11 puppetmaster.42.nl",
	    		    "javaVersion": "1.8.0_45",
	    		    "postgresVersion": "10.2.13",
	    		    "mysqlVersion": "14.14 Distrib 5.5.37 ",
	    		    "mongodbVersion": "",
	    		    "apache2Version": "3.0.2",
	    		    "tomcatVersion": "6.0.32 7.0.52 6.0.32",
	    		    "crowdVersion": "2.7.0",
	    		    "confluenceVersion": "",
	    		    "jiraVersion": "",
	    		    "stashVersion": "",
	    		    "bambooVersion": "",
	    		    "fisheyeVersion": "",
	    		    "certValidPeriod": "2012-2015",
	    		    "servercheckVersion": "2.1.1"
	    		  },
	    		  "usersArray": [
	    		    "ailbert",
	    		    "backup",
	    		    "basv",
	    		    "bin",
	    		    "crowd",
	    		    "daemon",
	    		    "danny",
	    		    "edwin",
	    		    "games",
	    		    "gerard",
	    		    "gnats",
	    		    "irc",
	    		    "jeroen",
	    		    "landscape",
	    		    "libuuid",
	    		    "list",
	    		    "lp",
	    		    "mail",
	    		    "man",
	    		    "messagebus",
	    		    "michiel",
	    		    "mysql",
	    		    "n2",
	    		    "news",
	    		    "nobody",
	    		    "ntp",
	    		    "postgres",
	    		    "proxy",
	    		    "puppet",
	    		    "richard",
	    		    "root",
	    		    "servercheck",
	    		    "sshd",
	    		    "sync",
	    		    "sys",
	    		    "syslog",
	    		    "thijs",
	    		    "uucp",
	    		    "voliere",
	    		    "www-data"
	    		  ],
	    		  "sudoUsersArray": [
	    		    "ailbert",
	    		    "michiel",
	    		    "danny",
	    		    "edwin",
	    		    "thijs",
	    		    "gerard",
	    		    "richard"
	    		  ],
	    		  "iptablesRules": [
	    		    "A-icmp-0.0.0.0/0-",
	    		    "A-all-0.0.0.0/0-",
	    		    "A-all-0.0.0.0/0-",
	    		    "A-tcp-213.126.14.2-",
	    		    "A-tcp-90.145.68.26-",
	    		    "A-tcp-84.53.109.99-",
	    		    "A-tcp-93.191.131.139-",
	    		    "A-tcp-37.34.54.120-",
	    		    "A-tcp-178.18.83.54-",
	    		    "A-tcp-79.170.89.58-",
	    		    "A-tcp-79.170.89.59-",
	    		    "A-tcp-37.230.96.193-",
	    		    "A-tcp-0.0.0.0/0-",
	    		    "A-tcp-0.0.0.0/0-",
	    		    "A-tcp-141.138.199.215-",
	    		    "A-tcp-213.126.14.2-",
	    		    "A-tcp-90.145.68.26-",
	    		    "D-all-0.0.0.0/0-"
	    		  ]
	    		}),
		}).success(function(data, status, headers, config) {
			$scope.persistentDataResponse = "Status:" + status + ". URL: /" +  config.url;
		}).
		error(function(data, status, headers, config) {
			$scope.persistentDataResponse = "Status:" + status + " " +  config;
		});
	};
	
	$scope.sendServerMeasurement = function() {
		$http({
	    url: 'servermeasurement',
	    method: 'POST',
	    data: JSON.stringify({"versionsData":{"servercheckVersion":"2.1.0"},
	    	"serverData":{"hostName":"vps1111","hostNameLong":"vps34638.public.cloudvps.com"},
	    	"measurementData":{"timeOfMeasurement":"20140826-1523","load":0.04,"cpuUsage":55,"diskUsage":13,
	    		"inodeUsage":3,"networkIn":0.001,"networkOut":3,"diskIO":13.38,"roundtripTime":8.924,
	    		"ioWait":0.02,"ram":1033,"swap":4046,"totalMem":5079,"processesRunning":102,"uptime":"120d,20:34h","timeDiff":0}}),
		}).success(function(data, status, headers, config) {
			$scope.serverDataResponse = "Status:" + status + ". URL: /" +  config.url;
		}).
		error(function(data, status, headers, config) {
			$scope.serverDataResponse = "Status:" + status + " " +  config;
		});
	};

});