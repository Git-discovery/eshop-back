package com.example.bean;

import java.util.List;

/**
 * �û���һ������
 * @author java
 *
 */
public class AjiaOrderVo {

	//������ţ��ɽ�ʱ�����Ϣ
	private AjiaOrder  ajiaOrder;
	//������������Ʒ
	private List<AjiaOrderItem> ajiaOrderItemList;
	public AjiaOrder getAjiaOrder() {
		return ajiaOrder;
	}
	public void setAjiaOrder(AjiaOrder ajiaOrder) {
		this.ajiaOrder = ajiaOrder;
	}
	public List<AjiaOrderItem> getAjiaOrderItemList() {
		return ajiaOrderItemList;
	}
	public void setAjiaOrderItemList(List<AjiaOrderItem> ajiaOrderItemList) {
		this.ajiaOrderItemList = ajiaOrderItemList;
	}
	
}
