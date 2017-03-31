define(['angular'], function(angular) {
	var login = angular.module('login', []);
	login.controller('loginCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$mdDialog,publicmodel,$routeParams) {
		$rootScope.loginMod = true;
		// 清空已登录用户数据
		publicFunction.localClear("user");
		sessionStorage.clear();
//		$http.defaults.headers.common["X-Auth-Token"] = null;
		
		// 模拟数据开始
		$scope.user = {
				text: '登录',
				username: 'dddd',
				password: 'aaa',
				tenants: [],
				disabled: false,
				select: null,
				rememberMe: false
			}
		$scope.userLogin = function(){
		// 登录开始
			//$scope.user.text = '正在登录…';
			$http({
				method: 'POST',
				url: adminUrl + 'user/login',
				data: {
					"username": $scope.user.username,
					"password": $scope.user.password
				}
			}).success(function(response) {
				console.log(response);
//				$scope.content=headers('x-auth-token');
//				console.log($scope.content);
//				if($scope.content!=''&&$scope.content!=null){
//					$cookies.put('JSESSION', $scope.content);
//				}
				if(response.code == "0") {
					console.log("登录成功");
//					sessionStorage(sessionStorage)
					sessionStorage.LoginTime = (new Date().getTime() / 1000).toFixed(0);
					sessionStorage.User = angular.toJson(response);
					
					// 登录数据保存到本地
					publicFunction.localSet(response,"user");
					console.log(sessionStorage.user);
					$scope.toDefault();
					// 登录数据保存到本地结束
				} else {
//					$scope.toDefault();
					//$scope.user.text = '出错了：' + data.msg;
					$mdDialog.show($mdDialog.alert().title('提交结果').textContent(response.mes+ '(' + response.code + ')').ariaLabel('提交结果').ok('关闭'));

				}
			})
			.error(function(data, status, headers, config) {
				console.log("登录失败");
			});
			// 登录结束
		}

		$scope.toDefault = function() {
			$scope.backurl = $location.$$path;
			console.log($routeParams.url);
			console.log(document.referrer);
			console.log($scope.backurl);
			if(angular.equals($scope.backurl,"/login")) {
				console.log(111);
				$location.url('/index');
			} else {
				console.log(2222);
				$rootScope.goBack();
			}
		}
	});
	angular._LoadModule('login');
	return login;
});