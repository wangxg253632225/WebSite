define(['angular'
		,'js/templates/business/business_cate_list'
		,'js/templates/business/business_cate_add'
		,'js/templates/business/business_cate_edit'
		,'js/templates/business/business_list'
		,'js/templates/business/business_add'
		,'js/templates/business/business_edit'], function(angular) {
	var newsIndex = angular.module('businessIndex',['businessCateList'
		,'businessCateAdd'
		,'businessCateEdit'
		,'businessList'
		,'businessAdd'
		,'businessEdit'])
	.config(function($routeProvider) {
		$routeProvider.when('/business/business_cate_list', {
			templateUrl: 'templates/business/business_cate_list.html'
		}).when('/business/business_list', {
			templateUrl: 'templates/business/business_list.html'
		}).when('/business/business_add', {
			templateUrl: 'templates/business/business_add.html'
		}).when('/business/business_edit', {
			templateUrl: 'templates/business/business_edit.html'
		}).when('/business/business_cate_add', {
			templateUrl: 'templates/business/business_cate_add.html'
		}).when('/business/business_cate_edit', {
			templateUrl: 'templates/business/business_cate_edit.html'
		});
	});
	
	angular._LoadModule('businessIndex');
	return newsIndex;
});