define(['angular'], function(angular) {
	var adminList = angular.module('adminList', []);
	
	adminList.run(function($http){
        $http.defaults.headers.common["sessionToken"] = sessionStorage.User.data;
        $http.defaults.headers.common["Content-Type"] = "application/json";
    });
	
	adminList.controller('adminListCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$mdDialog) {

		console.log(sessionStorage);
		console.log(sessionStorage.User);
		console.log(JSON.parse(sessionStorage.User).data);
		$scope.pageSizes = [2, 5, 10, 20];
		//变量  
		$scope.pageNum = 1;
		$scope.totalRow = 0;
		$scope.pageSize = 5;
		$scope.totalPage = 0;
		$scope.firstPage = true;
		$scope.lastPage = true;
		$scope.pages = []; 

		$scope.dataList = new Array();
		$scope.findList = function() {
			$http({
					method: 'POST',
					url: adminUrl + "user/getList",
					data: {
						"pageNum": $scope.pageNum,
						"pageSize": $scope.pageSize,
						"sessionToken":JSON.parse(sessionStorage.User).data
					}
				})
				.success(function(response) {
					console.log(response);
					if (response.code == "0") {
						$scope.dataList = response.data.list;
						$scope.totalRow = response.data.totalRow;
						$scope.totalPage = response.data.totalPage;
						$scope.firstPage = response.data.firstPage;
						$scope.lastPage = response.data.lastPage;
						reloadPno();
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		};
		$scope.findList();

		//加载选中页
		$scope.load_page = function(page) {
			$scope.pageNum = page;
			$scope.findList();
		};

		//初始化页码  
		var reloadPno = function() {
			$scope.pages = calculateIndexes($scope.pageNum, $scope.totalPage, 5);
		};

		//分页算法  
		var calculateIndexes = function(current, totalPage, displayLength) {
			var indexes = [];
			var number = Math.floor(displayLength / 2);
			if (totalPage <= displayLength) {
				for (var i = 1; i <= totalPage; i++) {
					indexes.push(i);
				}
			} else {
				if (current - 1 <= number) {
					for (var i = 1; i <= displayLength; i++) {
						indexes.push(i);
					}
				} else if (current - 1 > number) {
					if (totalPage - current >= number) {
						var start = current - number;
						var end = current + number;
						for (var i = start; i <= end; i++) {
							indexes.push(i);
						}
					} else {
						var start = totalPage - displayLength + 1;
						for (var i = start; i <= totalPage; i++) {
							indexes.push(i);
						}
					}
				}
			}
			return indexes;
		};

		$scope.$watch('pageSize', function(newSize, oldSize, c) {
			$scope.pageSize = newSize;
			$scope.pageNum = 1;
			$scope.findList();
		});

		/** 新增管理员*/
		$scope.addAdmin = function() {
			$location.path("/admin/add");
		}

		/** 编辑管理员*/
		$scope.goEdit = function(id) {
			$location.url('/admin/edit?id=' + id);
		}

		/** 删除管理员*/
		$scope.goDel = function(id,username) {
			var confirm = $mdDialog.confirm()
		          .title("用户删除")
		          .textContent("您,确定要删除《"+username+"》该用户吗 ？")
		          .ok('确定')
		          .cancel('取消');
		    $mdDialog.show(confirm).then(function() {
		    	$scope.deleteById(id);
		    }, function() {
		      	$scope.status = 'cancel';
		    });
		}
		
		/** 删除友情链接*/
		$scope.deleteById = function(id) {
			$http({
					method: 'GET',
					url: adminUrl + "user/delUserById?id=" + id
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


	return adminList;
});