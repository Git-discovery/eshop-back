package com.example.protal.service;

import java.util.List;


import com.example.bean.AjiaShipping;
public interface AddressService {
	
	public AjiaShipping getDefalutAddress(long userId) throws Exception;
	
	
	public int setDefault(long addId,long userId) throws Exception;
	
	
	public int delete(long addId) throws Exception;
	
	
public int insert(AjiaShipping ajiaShipping) throws Exception;


public List<AjiaShipping> selectByUserid(long userId) throws Exception;
}
