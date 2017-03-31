console.log("进入index....")
define(['angular'], function(angular) {
	var index = angular.module('index',[]);
	
	index.controller('indexCtrl',function($scope){
		console.log("进入controller方法里面....");
	});
	
	angular._LoadModule('index');
	return index;
});
