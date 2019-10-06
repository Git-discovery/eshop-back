package com.example.protal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.bean.AjiaItem;
import com.example.bean.Item;
import com.example.protal.service.ItemService;
import com.example.protal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	@Qualifier("solrClient")
	private SolrClient solrClient;


	@Autowired
	private ItemService itemService;
	@Override
	public void insert() throws Exception {

		List<AjiaItem> mysqlItemList=itemService.findAll();
		List<Item> solrItemList=new ArrayList();
		
	   for (AjiaItem ajiaItem:mysqlItemList)
	   {
		   Item solrItem=new Item();
		   solrItem.setId(ajiaItem.getId()+"");
		   solrItem.setBrand(ajiaItem.getBrand());
		   solrItem.setModel(ajiaItem.getModel());
		   solrItem.setTitle(ajiaItem.getTitle());
		   solrItem.setImage(ajiaItem.getImage());
		   solrItem.setPrice(ajiaItem.getPrice());
		   solrItemList.add(solrItem);
	   }
	   solrClient.addBeans(solrItemList);
	   solrClient.commit();
	}
	@Override
	public List<Item> search(String key) throws Exception {
		List<Item> itemList=null;
		SolrQuery solrQuery=new SolrQuery();
		solrQuery.set("rows", 20000);
		solrQuery.setQuery(key);
		
		QueryResponse queryResponse=solrClient.query(solrQuery);
		itemList=queryResponse.getBeans(Item.class);
		return itemList;
	}

}
