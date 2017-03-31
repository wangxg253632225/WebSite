define(['angular',
		'js/templates/link/link_list',
		'js/templates/link/link_add',
		'js/templates/link/link_edit'], function(angular) {
	var linkIndex = angular.module('linkIndex',['linkList','linkAdd','linkEdit'])
	.config(function($routeProvider) {
		$routeProvider.when('/link/link_list', {
			templateUrl: 'templates/link/link_list.html'
		}).when('/link/link_add', {
			templateUrl: 'templates/link/link_add.html'
		}).when('/link/link_edit', {
			templateUrl: 'templates/link/link_edit.html'
		});
	});
	
	angular._LoadModule('linkIndex');
	return linkIndex;
});