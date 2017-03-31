define(['angular'], function(angular) {
	var cateAdd = angular.module('cateAdd', []);

	cateAdd.controller('cateAddCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {

		$scope.data = {
			"cateName": null,
			"cateTitle": null,
			"keyWord": null,
			"level": null,
			"cateFlag": "news",
			"remark":""
		};
		/*新增分类*/
		$scope.addCate = function() {
			console.log($scope.data);
			$http({
					method: 'POST',
					url: adminUrl + "articleCategory/addArticleCategory",
					data: $scope.data
				})
				.success(function(response) {
					if (response.code == 0) {
						$location.url("/news/cate_list");
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		};
	});
	return cateAdd;
});