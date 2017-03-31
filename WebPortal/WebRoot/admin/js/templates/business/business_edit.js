define(['angular'], function(angular) {
	var businessEdit = angular.module('businessEdit', []);

	businessEdit.controller('businessEditCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$routeParams,$mdDialog) {
		
		$scope.dataList = new Array();
		$scope.article = {
			id:0,
			cate_id:0,
			name:null,
			title:null,
			content:null,
			author:null
		};
		/** 查询出新闻的分类开始 */
		$scope.findList = function() {
			$http({
				method: 'POST',
				url: adminUrl + "articleCategory/getList?type=business"
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
		/** 查询出新闻的分类结束 */
		
		/** 加载新闻数据开始  */
		$scope.findDetailById = function(){
			$http({
				method: 'GET',
				url: adminUrl + "article/getDetail?id="+$routeParams.id
			})
			.success(function(response) {
				if(response.code == "0"){
					$scope.article.id = response.data.id;
					$scope.article.cate_id = response.data.cate_id.toString();
					$scope.article.name = response.data.name;
					$scope.article.title = response.data.title;
					$scope.article.author = response.data.author;
					document.getElementById("content").innerHTML = response.data.content;
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		}
		$scope.findDetailById();
		/** 加载新闻数据结束  */
		/** 更新开始 */
		$scope.updateArticle = function(){
			$scope.article.content = document.getElementById("content").innerHTML;
			
			$http({
				method: 'POST',
				url: adminUrl + "article/update",
				data:$scope.article
			})
			.success(function(response) {
				if(response.code == "0"){
					alert = $mdDialog.alert({
				        title: '新闻更新',
				        textContent: '新闻更新成功',
				        ok: '关闭'
				    });
				    $mdDialog
			        .show( alert )
			        .finally(function() {
			        	$location.path("/business/business_list");
				    });
				}else{
					$mdDialog.show(
						$mdDialog.alert()
						.title('新闻更新')
						.textContent('异常:'+response.msg+"("+response.code+")")
						.ariaLabel('新闻更新')
						.ok('关闭')
					);
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		};
		/** 更新结束 */
		
		$scope.goList = function(){
			$location.path('/business/business_list');
		}
	});
	return businessEdit;
});