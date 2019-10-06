package com.example.bean;

public class AjiaResult {
	/**
	 * 200:�ɹ� 500:����
	 */
	private int status;
	/**
	 * ���������ϸ����Ϣ
	 */
	private String msg;

	/**
	 * ���������������������
	 */
	private Object data;
	
	
	

	public AjiaResult(int status) {
		super();
		this.status = status;
	}	
	public AjiaResult(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	public AjiaResult(int status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
