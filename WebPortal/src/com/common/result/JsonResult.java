package com.common.result;



import com.common.ErrorCodeEnum;

import java.io.Serializable;


/**
 * <p>function:返回终端的数据结构
 * <p>User: LeeJohn 
 * <p>Date: 2015-02-06
 * <p>Version: 1.0
 */
public class JsonResult implements Serializable {
	private static final long serialVersionUID = -7906626823989989372L;
	
	private String mes;
	private String num;
	private String code;
	private Object data;	
	private String version;
	
	public JsonResult() {
		super();
	}

	public JsonResult(String code) {
		super();
		this.code = code;
	}
	
	public JsonResult(String code, String mes) {
		super();
		this.code = code;
		this.mes = mes ;
	}
	
	public JsonResult(ErrorCodeEnum errorCode) {
		super();
		this.code = errorCode.getErrorcode();
		this.mes = errorCode.getErrordesc() ;
	}
	
	public JsonResult(String mes, String num, String code, Object data, String version) {
		super();
		this.mes = mes;
		this.num = num;
		this.code = code;
		this.data = data;
		this.version = version;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
}

