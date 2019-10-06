package com.example.protal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.bean.AjiaCartItem;
import com.example.bean.AjiaCartItemVo;
import com.example.bean.AjiaResult;
import com.example.protal.service.CartService;


@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	/**
	 * 
	 * @param itemId
	 *            28,32,40
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteItems")
	public ModelAndView deleteItems(String itemIds) throws Exception {
		String[] idArray = itemIds.split(",");
		List<Long> list = new ArrayList<Long>();
		for (String id : idArray) {
			list.add(Long.parseLong(id));
		}
		 cartService.deleteItems(list, 14L);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/cart/toCart.html");
		return modelAndView;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(long itemId) throws Exception {
		boolean isSuccess = cartService.delete(itemId, 14L);
		// boolean isSuccess=false;
		ModelAndView modelAndView = new ModelAndView();
		if (isSuccess) {
			modelAndView.setViewName("redirect:/cart/toCart.html");
		} else {

			modelAndView.addObject("errorInfo", "ɾ��ʧ��");
			List<AjiaCartItemVo> voList = cartService.selectAllItemByUserId(14L);
			modelAndView.addObject("voList", voList);
			modelAndView.setViewName("cart");
		}
		return modelAndView;
	}

	@RequestMapping("/changeCartNum")
	public AjiaResult changeCartNum(long itemId, int num) throws Exception {
		AjiaCartItem ajiaCartItem = new AjiaCartItem();
		ajiaCartItem.setUserId(14L);
		ajiaCartItem.setItemId(itemId);
		ajiaCartItem.setNum(num);
		ajiaCartItem.setStatus(1);

		boolean isSuccess = cartService.changeCartNum(ajiaCartItem);
		if (isSuccess) {
			return new AjiaResult(200);
		}
		return new AjiaResult(500);
	}

	@RequestMapping("/toCart")
	public ModelAndView toCart(HttpServletRequest request) throws Exception {
		String user=(String) request.getAttribute("user");
		
		ModelAndView modelAndView = new ModelAndView();
		long userId = 14L;
		List<AjiaCartItemVo> voList = cartService.selectAllItemByUserId(userId);

		modelAndView.addObject("voList", voList);
		modelAndView.setViewName("cart");
		return modelAndView;
	}

	@RequestMapping("/insert")
	public AjiaResult insert(long itemId, int num) throws Exception {

		AjiaCartItem ajiaCartItem = new AjiaCartItem();
		ajiaCartItem.setItemId(itemId);
		ajiaCartItem.setNum(num);
		ajiaCartItem.setStatus(1);
		long userId = 14L;
		ajiaCartItem.setUserId(userId);
		ajiaCartItem.setCreated(new Date());
		ajiaCartItem.setUpdated(new Date());

		boolean isSuccess = cartService.insert(ajiaCartItem);
		if (isSuccess) {
			return new AjiaResult(200);
		}
		return new AjiaResult(500);

	}
}
