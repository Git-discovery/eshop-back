package com.example.protal.service;

import java.util.List;


import com.example.bean.Item;
public interface SearchService {
	
	
	public List<Item> search(String key) throws Exception;

	public void insert() throws Exception;
}
