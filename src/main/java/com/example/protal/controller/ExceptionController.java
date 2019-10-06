package com.example.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bean.AjiaShipping;

@Controller
@RequestMapping("/e")
public class ExceptionController {

	@RequestMapping("/e1.html")
	public String createException1() throws Exception
	{
		AjiaShipping ajiaShipping=new AjiaShipping();
		ajiaShipping.setUserId(null);
		
		//ת��
		return "index";
	}
	@RequestMapping("/e2.html")
	public String createException2() throws Exception
	{
		AjiaShipping ajiaShipping=new AjiaShipping();
		ajiaShipping.setUserId(14L);
		
		//ת��
		return "index";
	}
	@RequestMapping("/e3.html")
	public String createSystemException3()throws Exception{
		
		String string=null;
		string.toCharArray();
		return "index";
	}
}
