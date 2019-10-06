package com.example.bean;

import java.util.List;

/**
 * ���ﳵ
 * 
 * @author java
 *
 */
public class AjiaCartVo {

	// �ж����Ʒ
	List<AjiaCartItemVo> itemList;
	// ������Ʒ����
	int total;
	// �ܼ�Ǯ
	double payment;

	public List<AjiaCartItemVo> getItemList() {
		return itemList;
	}

	public void setItemList(List<AjiaCartItemVo> itemList) {
		this.itemList = itemList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

}
