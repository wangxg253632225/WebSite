define(['angular'], function(angular) {
	var newsCateList = angular.module('newsCateList', []);

	newsCateList.controller('newsCateListCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {

		$scope.dataList = new Array();
		$scope.findList = function() {
			$http({
				method: 'POST',
				url: adminUrl + "articleCategory/getList?type=news"
			})
			.success(function(response) {
				console.log(response);
				if (response.code == "0") {
					$scope.dataList = response.data;
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		};
		$scope.findList();
		
		$scope.addCate = function(){
			$location.path('/news/cate_add');
		}
		
		/*编辑分类*/
		$scope.goEdit = function(id){
			console.log(id);
			$location.url('/news/cate_edit?id='+id);
		}

	});
	return newsCateList;
});