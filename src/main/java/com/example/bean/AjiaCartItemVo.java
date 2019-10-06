package com.example.bean;

import java.util.List;

import com.example.bean.ajiaitem.Params;

/**
 * ���ﳵ�е�һ����Ʒ
 * 
 * @author java
 *
 */
public class AjiaCartItemVo {
	private AjiaCartItem ajiaCartItem;
	private AjiaItem ajiaItem;
	private List<Params> paramsList;

	public AjiaCartItem getAjiaCartItem() {
		return ajiaCartItem;
	}

	public void setAjiaCartItem(AjiaCartItem ajiaCartItem) {
		this.ajiaCartItem = ajiaCartItem;
	}

	public AjiaItem getAjiaItem() {
		return ajiaItem;
	}

	public void setAjiaItem(AjiaItem ajiaItem) {
		this.ajiaItem = ajiaItem;
	}

	public List<Params> getParamsList() {
		return paramsList;
	}

	public void setParamsList(List<Params> paramsList) {
		this.paramsList = paramsList;
	}

}
