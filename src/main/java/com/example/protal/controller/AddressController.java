package com.example.protal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.bean.AjiaResult;
import com.example.bean.AjiaShipping;
import com.example.protal.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService serviceImpl;

	@RequestMapping("/getDefaultAddress")
	public AjiaResult getDefaultAddress() throws Exception {
		AjiaShipping ajiaShipping = serviceImpl.getDefalutAddress(14L);
		AjiaResult ajiaResult = new AjiaResult(200, "", ajiaShipping);
		return ajiaResult;
	}

	@RequestMapping("/setDefault")
	public AjiaResult setDefault(long addId) throws Exception {
		long userId = 14L;
		// AddressServiceImpl serviceImpl = new AddressServiceImpl();
		int row = serviceImpl.setDefault(addId, userId);
		if (row >= 2) {
			AjiaResult ajiaResult = new AjiaResult(200);
			return ajiaResult;
		}
		AjiaResult ajiaResult = new AjiaResult(500);
		return ajiaResult;
	}

	@RequestMapping("/delete")
	public AjiaResult delete(long addId) throws Exception {
		// AddressServiceImpl serviceImpl = new AddressServiceImpl();
		int row = serviceImpl.delete(addId);
		if (row >= 1) {
			AjiaResult ajiaResult = new AjiaResult(200);
			return ajiaResult;
		}

		AjiaResult ajiaResult = new AjiaResult(500);
		return ajiaResult;

	}

	@RequestMapping("/insert")
	public ModelAndView Insert(AjiaShipping ajiaShipping) throws Exception {
		ModelAndView mv = new ModelAndView();
		// AddressServiceImpl serviceImpl = new AddressServiceImpl();
		ajiaShipping.setStatus((byte) 1);
		ajiaShipping.setUserId(14L);
		ajiaShipping.setIsDefault(true);
		serviceImpl.insert(ajiaShipping);
		List<AjiaShipping> list = serviceImpl.selectByUserid(14L);
		mv.addObject("list", list);
		mv.setViewName("addressAdmin");
		return mv;
	}

	// ���ʵ�url��/address/list.html
	@RequestMapping("/list")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		long userId = 14L;

		// AddressServiceImpl addressServiceImpl = new AddressServiceImpl();
		List<AjiaShipping> list = serviceImpl.selectByUserid(userId);

		modelAndView.addObject("list", list);
		modelAndView.setViewName("addressAdmin");
		return modelAndView;
	}
}
