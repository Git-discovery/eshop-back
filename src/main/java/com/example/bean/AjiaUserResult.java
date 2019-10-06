package com.example.bean;

public class AjiaUserResult {
	//json转成对象，类的属性，属性类型必须与json一致
	private int status;
	private String msg;
	private AjiaUser data;
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
	public AjiaUser getData() {
		return data;
	}
	public void setData(AjiaUser data) {
		this.data = data;
	}
	

}
