'use strict';

(function(win) {
	//配置baseUrl
	var baseUrl = document.getElementById('main').getAttribute('data-baseurl');

	/*
	 * 文件依赖
	 */
	var config = {
		baseUrl: baseUrl, //依赖相对路径
		paths: {
			angular: 'libs/Angular.min',
			ngAnimate: 'libs/angular-animate.min',
			ngAria: 'libs/angular-aria.min',
			ngMaterial: 'libs/angular-material.min',
			ngRoute: 'libs/angular-route.min',
			ngMessages: 'libs/angular-messages.min',
			ngPrint: 'libs/angularPrint',
			ngLocale: 'libs/angular-locale_zh-cn',
			cTri:'js/view/app'
		},
		shim: { //引入没有使用requirejs模块写法的类库。例如underscore这个类库，本来会有一个全局变量'_'。这里shim等于快速定义一个模块，把原来的全局变量'_'封装在局部，并导出为一个exports，变成跟普通requirejs模块一样
			angular: {
				exports: 'angular'
			},
			ngAnimate: {
				deps: ['angular'],
				exports: 'ngAnimate'
			},
			ngPrint : {
				deps: ['angular'],
				exports: 'ngPrint'
			},
			ngMaterial: {
				deps: ['angular', 'ngAnimate', 'ngAria'],
				exports: 'ngMaterial'
			},
			ngAria: {
				deps: ['angular'],
				exports: 'ngAria'
			},
			ngLocale: {
				deps: ['angular'],
				exports: 'ngLocale'
			},
			ngMessages: {
				deps: ['angular'],
				exports: 'ngMessages'
			},
			ngRoute: {
				deps: ['angular'],
				exports: 'ngRoute'
			},
			cTri: {
				deps: ['angular'],
				exports: 'c.tri'
			}
		}
	};

	require.config(config);

	require(['angular', 'js/config/app'], function(angular) {
		angular.bootstrap(document, ['webapp']);
	});

})(window);
