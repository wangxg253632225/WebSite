define(['angular'],function(angular){
var server= angular.module('server', []);
server.factory('publicmodel',function(){
		return {
			Share : {
				selectMode : false,
				fromUrl : '',
				toUrl : '',
				data : '',
				data1 : '',
				data2 : ''
			},
			Passengers : [],
			breadCrumb : [],
			TemplateModel :{
				JieKuan : {
					module_type: "LM",
                    order_type: "PERSONPAY"
				},
				HuanKuan : {
					module_type: "RP",
                    order_type: "PERSONREPAY"
				}
			}
		};
	});
	
	return server;
});