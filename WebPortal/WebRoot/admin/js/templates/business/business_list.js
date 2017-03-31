define(['angular'], function(angular) {
	var businessList = angular.module('businessList', []);

	businessList.controller('businessListCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$mdDialog) {
		
		$scope.selected = new Array();
		$scope.pageSizes = [2,5,10,20];
		//变量  
		$scope.pageNum = 1;
		$scope.totalRow = 0;
		$scope.pageSize = 5;
		$scope.totalPage = 0;
		$scope.firstPage = true;
		$scope.lastPage = true;
		$scope.pages = [];
		//选项框
		$scope.totalFlag = false;
		$scope.dataList = new Array();
		
		$scope.findList = function() {
			
			$scope.selected = new Array();
			$scope.totalFlag = false;
			
			$http({
				method: 'POST',
				url: adminUrl + "article/getList?type=business",
				data:{
					pageNum:$scope.pageNum,
					pageSize:$scope.pageSize
				}
			})
			.success(function(response) {
				if (response.code == "0") {
					$scope.dataList = response.data.list;
					angular.forEach($scope.dataList,function(item){
						item.flag = false;
					});
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
			if(totalPage <= displayLength){
				for (var i = 1; i <= totalPage; i++) {
					indexes.push(i);
				}
			}else{
				if(current - 1 <= number){
					for (var i = 1; i <= displayLength; i++) {
						indexes.push(i);
					}
				}else if(current - 1 > number){
					if(totalPage - current >= number){
						var start= current - number;
						var end = current + number;
						for (var i = start; i <= end; i++) {
							indexes.push(i);
						}
					}else{
						var start= totalPage - displayLength+1;
						for (var i = start; i <= totalPage; i++) {
							indexes.push(i);
						}
					}
				}
			}
			return indexes;
		};
		
		$scope.$watch('pageSize',function(newSize,oldSize,c){
			$scope.pageSize = newSize;
			$scope.pageNum = 1;
			$scope.findList();
		});
		
		$scope.addArticle = function(){
			$location.path('/business/business_add');
		}
		
		$scope.goEdit = function(id){
			$location.url('/business/business_edit?id='+id);
		}
		
		//弹出删除选择框
    	$scope.confirmDelete = function(id,name){
		    var confirm = $mdDialog.confirm()
		          .title("新闻删除")
		          .textContent("您确定要删除《"+name+"》该文章吗？")
		          .ok('确定')
		          .cancel('取消');
		    $mdDialog.show(confirm).then(function() {
		    	$scope.deleteById(id);
		    }, function() {
		      	$scope.status = 'cancel';
		    });
		};
		
		$scope.deleteById = function(id){
			$http({
				method: 'POST',
				url: adminUrl + "article/delete?id="+id
			})
			.success(function(response) {
				if (response.code == "0") {
					alert = $mdDialog.alert({
				        title: '新闻删除',
				        textContent: '新闻删除成功',
				        ok: '关闭'
				    });
				    $mdDialog
			        .show( alert )
			        .finally(function() {
			        	$scope.findList();
				    });
				}else{
					$mdDialog.show(
						$mdDialog.alert()
						.title('新闻删除')
						.textContent('异常:'+response.msg+"("+response.code+")")
						.ariaLabel('新闻删除')
						.ok('关闭')
					);
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		}
		
		
		$scope.deleteByIdsConfirm = function(){
			if($scope.selected.length <= 0){
				$mdDialog.show(
					$mdDialog.alert()
					.title('新闻删除')
					.textContent('异常:请选择您要删除的数据!')
					.ariaLabel('新闻删除')
					.ok('关闭')
				);
				return;
			}
			
			var confirm = $mdDialog.confirm()
		          .title("新闻删除")
		          .textContent("您确定要删除选中的文章吗？")
		          .ok('确定')
		          .cancel('取消');
		    $mdDialog.show(confirm).then(function() {
		    	$scope.deleteByIds();
		    }, function() {
		      	$scope.status = 'cancel';
		    });
		}
		
		$scope.deleteByIds = function(){
			var length = $scope.selected.length;
			var ids = "";
			for(var i=0;i<length;i++){
				ids += $scope.selected[i].id +",";
			}
			ids = ids.substr(0,ids.length-1);
			
			$http({
				method: 'POST',
				url: adminUrl + "article/deleteIds",
				data:{
					"ids":ids
				}
			})
			.success(function(response) {
				if (response.code == "0") {
					alert = $mdDialog.alert({
				        title: '新闻删除',
				        textContent: '新闻删除成功',
				        ok: '关闭'
				    });
				    $mdDialog
			        .show( alert )
			        .finally(function() {
			        	$scope.findList();
				    });
				}else{
					$mdDialog.show(
						$mdDialog.alert()
						.title('新闻删除')
						.textContent('异常:'+response.msg+"("+response.code+")")
						.ariaLabel('新闻删除')
						.ok('关闭')
					);
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
			
		}
		
		$scope.selectTotal = function(newFlag){
			if(newFlag){
				angular.forEach($scope.dataList,function(item){
					item.flag = true;
					$scope.selected.push(item);
				});
			}else{
				angular.forEach($scope.dataList,function(item){
					item.flag = false;
					$scope.selected = new Array();
				});
			}
		};
		
		$scope.selectCheck = function(obj){
			var length = $scope.selected.length;
			if(obj.flag){
				var flag = false;
				for(var i=0;i<length;i++){
					if(obj.id == $scope.selected[i].id){
						flag = true;
						break;
					}
				}
				if(!flag){
					$scope.selected.push(obj);
				}
				if($scope.selected.length == $scope.dataList.length){
					$scope.totalFlag = true;
				}
			}else{
				var idx = -1;
				for(var i=0;i<length;i++){
					if(obj.id == $scope.selected[i].id){
						idx = i;
						break;
					}
				}
				if (idx > -1) $scope.selected.splice(idx, 1);
				if($scope.selected.length != $scope.dataList.length){
					$scope.totalFlag = false;
				}
			}
		}

	});
	return businessList;
});