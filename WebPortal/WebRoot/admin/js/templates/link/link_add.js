define(['angular'], function(angular) {
	var linkAdd = angular.module('linkAdd', []);

	linkAdd.controller('linkAddCtrl', function($scope,$http,$location,$mdDialog) {

		$scope.data = {
			"linkName": null,
			"linkUrl": null,
			"level": null,
			"remark":""
		};
		/*新增分类*/
		$scope.addLink = function() {
			$http({
					method: 'POST',
					url: adminUrl + "link/addLink",
					data: $scope.data
				})
				.success(function(response) {
					if (response.code == "0") {
						alert = $mdDialog.alert({
							title: '链接新增',
							textContent: '链接新增成功',
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
							.title('链接新增')
							.textContent('异常:' + response.msg + "(" + response.code + ")")
							.ariaLabel('链接新增')
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
	return linkAdd;
});