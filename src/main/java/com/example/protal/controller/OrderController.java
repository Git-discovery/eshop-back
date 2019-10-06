package com.example.protal.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.bean.AjiaCartVo;
import com.example.bean.AjiaOrder;
import com.example.bean.AjiaOrderVo;
import com.example.protal.service.OrderService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	
	@Value("${PAGESIZE}")   
	private int pageSize;
@RequestMapping("/orderInfo")
	public ModelAndView orderInfo(String orderId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		AjiaOrderVo ajiaOrderVo = orderService.findOrderDetailByOrderid(orderId);
		modelAndView.addObject("ajiaOrderVo", ajiaOrderVo);
		modelAndView.setViewName("orderInfo");
		return modelAndView;
	}

	@RequestMapping("/deleteOrder")
	public ModelAndView delete(String orderId, String status, int currentPage) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		AjiaOrder ajiaOrder = orderService.findByOrderId(orderId);
		if (ajiaOrder == null) {
			modelAndView.setViewName("redirect:/order/toMyOrder.html");

		} else if (ajiaOrder.getUserId() == 14L) {
			orderService.updateStatusByOrderId(orderId, 9);
			String url = "/order/toMyOrder.html?status=" + status + "&currentPage=" + currentPage;
			modelAndView.setViewName("redirect:" + url);
		} else {
			modelAndView.setViewName("redirect:/order/toMyOrder.html");
		}

		return modelAndView;

	}

	@RequestMapping("/cancelOrder")
	public ModelAndView cancelOrder(@RequestParam(defaultValue = "1") int currentPage, String orderId,
			@RequestParam(defaultValue = "all") String status) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		AjiaOrder ajiaOrder = orderService.findByOrderId(orderId);
		long currentUserid = 14L;

		int intStatus = 0;
		switch (status) {
		case "all":
			intStatus = 0;
			break;
		case "waitPay":
			intStatus = 1;
			break;
		}
		if (ajiaOrder == null) {
			modelAndView.setViewName("redirect:/order/toMyOrder.html");
		} else if (ajiaOrder.getUserId() != currentUserid) {
			modelAndView.setViewName("redirect:/order/toMyOrder.html");

		} else {
			orderService.updateStatusByOrderId(orderId, 8);
			modelAndView.addObject("status", status);

			PageInfo<AjiaOrderVo> pageInfo = orderService.findByUserIdAndStatus(currentUserid, intStatus, currentPage,
					pageSize);
			modelAndView.addObject("voList", pageInfo.getList());
			modelAndView.addObject("pageInfo", pageInfo);
			modelAndView.setViewName("myOrder");

		}
		return modelAndView;
	}

	@RequestMapping("/toMyOrder")
	public ModelAndView toMyOrder(@RequestParam(defaultValue = "1") int currentPage,
			@RequestParam(defaultValue = "all") String status) throws Exception {
		// status=all,waitPay,waitReceive,watiAssess
		// 0, 1 , 5 , 6
		int dbStatus = 0;
		switch (status) {
		case "all":
			dbStatus = 0;
			break;
		case "waitPay":
			dbStatus = 1;
			break;
		case "waitReceive":
			dbStatus = 5;
			break;
		case "waitAssess":
			dbStatus = 6;
			break;
		}
		PageInfo<AjiaOrderVo> pageInfo = orderService.findByUserIdAndStatus(14L, dbStatus, currentPage, pageSize);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("myOrder");
		List<AjiaOrderVo> voList = pageInfo.getList();
		modelAndView.addObject("voList", voList);

		modelAndView.addObject("status", status);

		modelAndView.addObject("pageInfo", pageInfo);
		return modelAndView;
	}
	
	@RequestMapping("/OrderToPayment")
	public ModelAndView toPayment(String orderId) throws Exception
	{
		ModelAndView modelAndView=new ModelAndView();
		
		AjiaOrder ajiaOrder=orderService.findByOrderId(orderId);
		modelAndView.addObject("ajiaOrder", ajiaOrder);
		modelAndView.setViewName("payment");
		return modelAndView;
	}

	@RequestMapping("/toPayment")
	public ModelAndView saveOrder(Long addId, Long[] itemIdArray) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Long userId = 14L;
		List<Long> itemIdList = Arrays.asList(itemIdArray);

		AjiaOrder ajiaOrder = orderService.saveOrder(userId, addId, itemIdList);
		modelAndView.addObject("ajiaOrder", ajiaOrder);
		modelAndView.setViewName("payment");
		return modelAndView;
	}

	@RequestMapping("/orderConfirm")
	public ModelAndView orderConfirm(String itemIds) throws Exception {
		String[] idArray = itemIds.split(",");
		ArrayList<Long> itemIdList = new ArrayList<>();
		for (String id : idArray) {
			itemIdList.add(Long.parseLong(id));
		}
		ModelAndView modelAndView = new ModelAndView();
		AjiaCartVo ajiaCartVo = orderService.orderConfirm(14L, itemIdList);
		modelAndView.addObject("ajiaCartVo", ajiaCartVo);
		modelAndView.setViewName("orderConfirm");
		;
		return modelAndView;
	}
}
