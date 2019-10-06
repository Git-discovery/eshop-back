package com.example.exception;

public class MobileIsNullException extends StoreException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3711827261322957180L;

	@Override
	public String getMessage() {
		return "手机号为空";
	}

}
