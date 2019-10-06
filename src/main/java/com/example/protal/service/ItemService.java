package com.example.protal.service;

import java.util.List;


import com.example.bean.AjiaItem;
import com.example.bean.AjiaItemDesc;
import com.example.bean.AjiaItemParam;
import com.example.bean.AjiaItemParamItem;
public interface ItemService {
	
	public List<AjiaItem> findAll() throws Exception;
	
	/**
	 * 根据参数找到商品
	 * @param itemParamId
	 * @param color
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long selectItemByParams(long itemParamId,String color,String model) throws Exception;
	/**
	 * 取商品类型的参数
	 * @param itemParamId
	 * @return
	 * @throws Exception
	 *
	 */
	public  AjiaItemParam selectTypeParamById(long itemParamId) throws Exception;
	
	public AjiaItemDesc selectDescByItemId(long itemId) throws Exception;
	
	
	public AjiaItem SelectItemByItemid(long itemId) throws Exception;
	
	public AjiaItemParamItem getParamDataByItemId(long itemId) throws Exception;

}
