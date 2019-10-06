package com.example.exception;

import com.example.enums.ResultCodeEnum;

/**
 * 统一异常处理
 * @author ssp
 * @date 2018年7月29日
 */
public class StoreException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4069796356774929148L;
	private ResultCodeEnum resultCode;
	
	

	public StoreException() {
		super();
	}

	public StoreException(ResultCodeEnum resultCode) {
		super(resultCode.getMsg());
        this.resultCode = resultCode;
	}

	public ResultCodeEnum getResultCode() {
		return resultCode;
	}
	

}
