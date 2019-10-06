package com.example.enums;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * store-api 通用返回码
 * @author e5426
 *
 */
public enum ResultCodeEnum {
//***************通知信息定义********************
	SUCCESS("0","请求成功"),
//**************消息错误定义********************	
	Request_param_error("1","请求参数错误"),
	Param_length_error("2","参数长度不合法"),
//*****************用户模块定义******************
	Missing_access_token("-1","缺少access_token参数"),
	Invalid_access_token("-2","无效access_token"),
	
	Missing_mobile("-3","缺少mobile参数"),
	Invalid_mobile("-4","无效mobile");
private String code;
	private String msg;
	private String value;
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
	public String getValue() {
		return value;
	}
	private ResultCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	private ResultCodeEnum(String value) {
		this.value = value;
	}
	 /**
     *将该枚举全部转化成json
     * @return
     */
    public static String toJson(){
        JSONArray jsonArray = new JSONArray();
        for (ResultCodeEnum e : ResultCodeEnum.values()) {
            JSONObject object = new JSONObject();
            object.put("code", e.getCode());
            object.put("value",e.getValue());
            jsonArray.add(object);
        }
        return jsonArray.toString();
    }
	
	
	
}
