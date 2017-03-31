define(['angular'], function(angular) {
	var adminAdd = angular.module('adminAdd', []);

	adminAdd.controller('adminAddCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter, $mdDialog) {

		$scope.data = {
			"username": null,
			"password": null,
			"remark":""
		};
		$scope.alert = null;

		$scope.addAdmin = function() {
			$http({
					method: 'POST',
					url: adminUrl + "user/addUser",
					data: $scope.data
				})
				.success(function(response) {
					if (response.code == "0") {
						alert = $mdDialog.alert({
							title: '管理员新增',
							textContent: '管理员新增成功',
							ok: '关闭'
						});
						$mdDialog.show(alert).finally(function() {
							$location.path("/admin/list");
						});
					} else {
						$mdDialog.show(
							$mdDialog.alert()
							.title('管理员新增')
							.textContent('异常:' + response.msg + "(" + response.code + ")")
							.ariaLabel('管理员新增')
							.ok('关闭'));
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		};
	});

	return adminAdd;
});