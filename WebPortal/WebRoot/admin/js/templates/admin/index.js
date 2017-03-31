define(['angular',
		'js/templates/admin/list',
		'js/templates/admin/add',
		'js/templates/admin/edit'], function(angular) {
	var adminIndex = angular.module('adminIndex',['adminList','adminAdd','adminEdit'])
	.config(function($routeProvider) {
		$routeProvider.when('/admin/list', {
			templateUrl: 'templates/admin/list.html'
		}).when('/admin/add', {
			templateUrl: 'templates/admin/add.html'
		}).when('/admin/edit', {
			templateUrl: 'templates/admin/edit.html'
		});
	});
	
	angular._LoadModule('adminIndex');
	return adminIndex;
});