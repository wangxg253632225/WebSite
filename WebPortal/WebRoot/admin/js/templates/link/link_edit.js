define(['angular'], function(angular) {
	var linkEdit = angular.module('linkEdit', []);

	linkEdit.controller('linkEditCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$routeParams,$mdDialog) {

		$scope.data = {
			"id":0,
			"linkName": null,
			"linkUrl": null,
			"level": null,
			"remark": null,
			"version":null,
		};

		/** 加载新闻数据开始  */
		$scope.findCateDetailById = function() {
			$http({
					method: 'GET',
					url: adminUrl + "link/getDetail?id=" + $routeParams.id
				})
				.success(function(response) {
					if (response.code == "0") {
						$scope.data.id = response.data.id;
						$scope.data.linkName = response.data.link_name;
						$scope.data.linkUrl = response.data.link_url;
						$scope.data.level = response.data.level;
						$scope.data.remark = response.data.remark;
						$scope.data.version = response.data.version;
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		}
		$scope.findCateDetailById();


		/*更新分类数据*/
		$scope.updateLink = function() {
			console.log($scope.data);
			$http({
					method: 'POST',
					url: adminUrl + "link/updateLink",
					data: $scope.data
				})
				.success(function(response) {
					if (response.code == 0) {
						alert = $mdDialog.alert({
							title: '链接更新',
							textContent: '链接更新成功',
							ok: '关闭'
						});
						$mdDialog
							.show(alert)
							.finally(function() {
								$location.path("/link/link_list");
							});
					} else {
						$mdDialog.show(
							$mdDialog.alert()
							.title('链接更新')
							.textContent('异常:' + response.msg + "(" + response.code + ")")
							.ariaLabel('链接更新失败')
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
	return linkEdit;
});