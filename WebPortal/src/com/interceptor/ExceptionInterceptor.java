package com.interceptor;

import com.common.ErrorCodeEnum;
import com.common.result.JsonResult;
import com.exception.ServiceException;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.ActionException;
import com.jfinal.core.Controller;

public class ExceptionInterceptor implements Interceptor {

	/**
	 * 全局拦截器,用于捕获异常,对异常进行操作
	 * @param inv
     */
	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
	    try {
	      inv.invoke();
	    } catch (ServiceException se) {
	    	controller.renderJson(new JsonResult(ErrorCodeEnum.ERR_SERVICE.getErrorcode(),se.getMessage()));
	    } catch (Exception e) {
			if( e instanceof ActionException && ((ActionException) e).getErrorCode() == 404 ){//404异常，用于方法中只允许GET或POST时的异常
				controller.render(((ActionException) e).getErrorRender());
				return ;
			}
			e.printStackTrace();
	    	controller.renderJson(new JsonResult(ErrorCodeEnum.ERR_UNKNOW_ERROR));
		}
	}

}
