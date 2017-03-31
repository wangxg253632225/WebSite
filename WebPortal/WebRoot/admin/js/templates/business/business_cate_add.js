define(['angular'], function(angular) {
	var businessCateAdd = angular.module('businessCateAdd', []);

	businessCateAdd.controller('businessCateAddCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {

		$scope.data = {
			"cateName": null,
			"cateTitle": null,
			"keyWord": null,
			"level": null,
			"cateFlag": "business",
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
						$location.url("/business/business_cate_list");
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		};
	});
	return businessCateAdd;
});