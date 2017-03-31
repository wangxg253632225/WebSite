define(['angular'], function(angular) {
	var linkList = angular.module('linkList', []);

	linkList.controller('linkListCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$mdDialog) {

		$scope.dataList = new Array();
		$scope.findList = function() {
			$http({
					method: 'POST',
					url: adminUrl + "link/getList"
				})
				.success(function(response) {
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

		/** 新增友情链接*/
		$scope.addLink = function() {
			$location.path('/link/link_add');
		}

		/** 编辑友情链接*/
		$scope.goEdit = function(id) {
			$location.url('/link/link_edit?id=' + id);
		}


		/** 弹出删除提示框*/
		$scope.goDel = function(id,name) {
			var confirm = $mdDialog.confirm()
		          .title("链接删除")
		          .textContent("您,确定要删除《"+name+"》该链接吗 ？")
		          .ok('确定')
		          .cancel('取消');
		    $mdDialog.show(confirm).then(function() {
		    	$scope.deleteById(id);
		    }, function() {
		      	$scope.status = 'cancel';
		    });
		};

		/** 删除友情链接*/
		$scope.deleteById = function(id) {
			$http({
					method: 'GET',
					url: adminUrl + "link/del?id=" + id
				})
				.success(function(response) {
					if (response.code == "0") {
						$scope.findList();
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		}


	});
	return linkList;
});