package com.common;


/**
 * 错误码
 * @author LeeJohn
 *
 */
public enum ErrorCodeEnum {

	SUCCEED_CODE("0", "成功"),
    
	ERR_NOT_LOGIN("-1", "用户未登录"),
	ERR_LOGIN_ERROR("1001", "用户不存在或密码错误"),
	ERR_SERVICE("2999", "业务异常"),
    ERR_UNKNOW_ERROR("9999", "系统繁忙");//未知异常

	private String errorcode;
	
	private String errordesc;

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrordesc() {
		return errordesc;
	}

	public void setErrordesc(String errordesc) {
		this.errordesc = errordesc;
	}

	ErrorCodeEnum(String errorcode, String errordesc) {
		this.errorcode = errorcode;
		this.errordesc = errordesc;
	}

	public static String getDesc(String errorCode) {
		for (ErrorCodeEnum bussErrorCode : ErrorCodeEnum.values()) {
			if (bussErrorCode.errorcode.equals(errorCode)) {
				return bussErrorCode.errordesc;
			}
		}
		return errorCode;
	}

}
