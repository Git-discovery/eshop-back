package com.example.protal.service;

import java.util.List;


import com.example.bean.AjiaCartItem;
import com.example.bean.AjiaCartItemVo;

public interface CartService {
	
	
	public boolean deleteItems(List<Long> idList,long userId) throws Exception;
	
	
	public boolean delete(long itemId,long userId) throws Exception;
	
	
	public boolean changeCartNum(AjiaCartItem ajiaCartItem) throws Exception;
	
public List<AjiaCartItemVo> selectAllItemByUserId(long userId) throws Exception;
	
	public boolean insert(AjiaCartItem ajiaCartItem) throws Exception;
}
