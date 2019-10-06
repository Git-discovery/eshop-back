package com.example.protal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.bean.Item;
import com.example.protal.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping("/search")
	public ModelAndView searchItem3(String key) throws Exception
	{
		System.out.println("------------search");
		byte[] data=key.getBytes("ISO-8859-1");
		key=new String(data, "utf-8");
		
		List<Item> itemList=searchService.search(key);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("itemList", itemList);
		modelAndView.setViewName("search");
		return modelAndView;
	}

	@RequestMapping("/insert")
	public void insert() throws Exception
	{
		searchService.insert();
	}
}
