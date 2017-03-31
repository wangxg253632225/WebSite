define(['angular'], function(angular) {
	var adminEdit = angular.module('adminEdit', []);

	adminEdit.controller('adminEditCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter, $routeParams,$mdDialog) {

		$scope.data = {
			"id":null,
			"username":null,
			"password":null,
			"remark":"",
			"version":null
		};

		/** 加载新闻数据开始  */
		$scope.findCateDetailById = function() {
			$http({
					method: 'GET',
					url: adminUrl + "user/getDetail?id=" + $routeParams.id
				})
				.success(function(response) {
					console.log(response);
					if (response.code == "0") {
						$scope.data.id = response.data.id;
						$scope.data.username = response.data.username;
						$scope.data.password = response.data.password;
						if(response.data.remark != null){
							$scope.data.remark = response.data.remark;
						}
						$scope.data.version = response.data.version;
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		}
		$scope.findCateDetailById();


		/*更新用户数据*/
		$scope.updateAdmin = function() {
			$http({
					method: 'POST',
					url: adminUrl + "user/updateUser",
					data: $scope.data
				})
				.success(function(response) {
					if (response.code == 0) {
						alert = $mdDialog.alert({
							title: '用户更新',
							textContent: '用户更新成功',
							ok: '关闭'
						});
						$mdDialog
							.show(alert)
							.finally(function() {
								$location.path("/admin/list");
							});
					} else {
						$mdDialog.show(
							$mdDialog.alert()
							.title('用户更新')
							.textContent('异常:' + response.msg + "(" + response.code + ")")
							.ariaLabel('用户更新失败')
							.ok('关闭')
						);
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		};

	});
	return adminEdit;
});