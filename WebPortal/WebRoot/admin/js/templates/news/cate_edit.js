define(['angular'], function(angular) {
	var cateEdit = angular.module('cateEdit', []);

	cateEdit.controller('cateEditCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter, $routeParams) {

		$scope.data = {
			"id":0,
			"cateName": null,
			"cateTitle": null,
			"keyWord": null,
			"level": null,
			"cateFlag": "news",
			"version":null,
			"remark": null
		};

		/** 加载新闻数据开始  */
		$scope.findCateDetailById = function() {
			$http({
					method: 'GET',
					url: adminUrl + "articleCategory/getDetail?id=" + $routeParams.id
				})
				.success(function(response) {
					console.log(response);
					if (response.code == "0") {
						$scope.data.id = response.data.id;
						$scope.data.cateName = response.data.cate_name;
						$scope.data.cateTitle = response.data.cate_title;
						$scope.data.keyWord = response.data.cate_keyword;
						$scope.data.level = response.data.level;
						$scope.data.version = response.data.version;
						$scope.data.remark = response.data.remark;
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		}
		$scope.findCateDetailById();


		/*更新分类数据*/
		$scope.updateCate = function() {
			$http({
					method: 'POST',
					url: adminUrl + "articleCategory/updateCate",
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
	return cateEdit;
});