koekoek.controller('FooterController', [ '$scope', '$http', function($scope, $http) {

    $http.get("/application").success(function(data){
	$scope.version = data;
    });
} ]);
