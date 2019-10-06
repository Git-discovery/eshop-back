package com.example.protal.service;

import java.util.List;


import com.example.bean.AjiaCartVo;
import com.example.bean.AjiaOrder;
import com.example.bean.AjiaOrderVo;
import com.github.pagehelper.PageInfo;
public interface OrderService {
	
	public AjiaOrderVo findOrderDetailByOrderid(String orderId) throws Exception;
	
	public void updateStatusByOrderId(String orderId,int status) throws Exception;
	
	public AjiaOrder findByOrderId(String orderId) throws Exception;
	
	public PageInfo<AjiaOrderVo> findByUserIdAndStatus(long userid,int status,int currentPage,int pageSize) throws Exception;
	
	
	public AjiaOrder saveOrder(Long userId,Long addId,List<Long> itemIdList) throws Exception;
	
public AjiaCartVo orderConfirm(long userId,List<Long> itemIdList) throws Exception;
}
