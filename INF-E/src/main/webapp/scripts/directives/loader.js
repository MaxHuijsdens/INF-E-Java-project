koekoek.directive('loading', function () {
    return {
        restrict: 'E',
        replace:true,
        template: '<div class="loading">Loading data....     <i class="fa fa-spin fa-2x fa-spinner"/></div>',
        link: function (scope, element, attr) {
            scope.$watch('loading', function (val) {
                if (val)
                    $(element).show();
                else
                    $(element).hide();
            });
        }
    }
})