package com.example.exception;
/**
 * 用户异常处理
 * @author ssp
 * @date 2018年7月29日
 */
public class UserIdIsNullException extends StoreException{
	@Override
	public String getMessage() {
		return "用户id为空异常";
	}

}
