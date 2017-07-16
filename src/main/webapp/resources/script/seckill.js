/**
 * 存放主要交互逻辑js代码
 * javascript 模块化
 * seckill.detail.init{(params)}
 */

var seckill={
	//封装秒杀相关ajax的url
	URL:{
		
	},
	//详情页秒杀逻辑
	detail:{
		//详情页初始化
		init:function(params){
			//用户手机验证和登陆,计时交互
			//规划交互流程
			//在cookie中查找手机号
			var killPhone=$.cookie('killPhone');
			
			var startTime = params['startTime'];
	        var endTime = params['endTime'];
	        var seckillId = params['seckillId'];
		}
		
	}
}