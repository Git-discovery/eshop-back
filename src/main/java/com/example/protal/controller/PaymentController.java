package com.example.protal.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bean.AjiaOrder;
import com.example.payment.utils.PaymentUtil;
import com.example.protal.service.OrderService;

@Controller
@RequestMapping("/yibao")
public class PaymentController {
	@Autowired
	private OrderService orderService;
	
	//accountId,keyValue是商家在易宝网站上注册后，易宝提供的，
	
	//商家的id
	private String accountID="10001126856";
	//用来加密
	private String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
	
	@RequestMapping("/checkPayment")
	public String checkPayment(String hmac, String p1_MerId, String r0_Cmd, String r1_Code, String r2_TrxId,
			String r3_Amt, String r4_Cur, String r5_Pid, String r6_Order, String r7_Uid, String r8_MP, String r9_BType,
			String keyValue, HttpServletResponse resp, Model model)throws Exception{
		//做hmac校验   验证hmac
		boolean ok=PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		if(!ok){
			return "pay_fail";
		}
		if(r9_BType.equals("1")){
			//验证订单的状态 是否为已付款  转发到付款成功页面即可
			AjiaOrder order = orderService.findByOrderId(r6_Order);
			if( order.getStatus() == 2){// 状态已经改变
				model.addAttribute("order", order);
			}else{
				//orderService.updateOrderStatus(r6_Order, 2);
				model.addAttribute("order", order);
			}
			return "pay_success";
		}else{
			//修改订单信息,返回信息给易宝
			//orderService.updateOrderStatus(r6_Order, 2);
			PrintWriter out = resp.getWriter();
			out.print("success");
			out.close();
			return "";
		}
	}

	
	//给易宝发数据
	@RequestMapping("/paymentSend")
	public String paymentSend(String orderId, String bankId, Model model) throws Exception{
		//整理易宝支付所需要的所有请求参数
		String message="";
		if (StringUtils.isEmpty(orderId) || StringUtils.isEmpty(bankId) ) {
			message = "您的订单状态好像有点问题哦！";
			model.addAttribute("message", message);
			return "pay_fail";
		}
		
		AjiaOrder order = orderService.findByOrderId(orderId);
		if(order == null || order.getStatus()!=1){
			message = "您的订单状态好像有点问题哦！";
			model.addAttribute("message", message);
			return "pay_fail";
		}
		
		// 测试商户：商户接收支付成功数据的地址
		String accountCallbackURL = "http://payment.ajstore.com/yibao/checkPayment.html";
		String businessType = "Buy"; // 业务类型。Buy为在线支付
		String currency = "CNY"; // 交易币种。CNY为人民币
		String productDesc = ""; // 商品描述
		String productCategory = ""; // 商品种类
		String productID = ""; // 商品ID
		String addressFlag = ""; // 送货地址。0为不需要，1为需要
		String accountMoreInfo = ""; // 商户扩展信息
		String pr_NeedResponse = "0"; // 应答机制
		String payment = order.getPayment()+""; //订单价格
		//测试数据payment=0.01
		payment = 0.01+"";
		String md5hmac = PaymentUtil.buildHmac(businessType, accountID, orderId, payment, currency, productID,
				productCategory, productDesc, accountCallbackURL, addressFlag, accountMoreInfo, bankId,
				pr_NeedResponse, keyValue);
		model.addAttribute("businessType", businessType);
		model.addAttribute("accountID", accountID);
		model.addAttribute("orderID", orderId);
		model.addAttribute("amount", payment);
		model.addAttribute("currency", currency);
		model.addAttribute("productID", productID);
		model.addAttribute("productCategory", productCategory);
		model.addAttribute("productDesc", productDesc);
		model.addAttribute("accountCallbackURL", accountCallbackURL);
		model.addAttribute("addressFlag", addressFlag);
		model.addAttribute("accountMoreInfo", accountMoreInfo);
		model.addAttribute("accountBankID", bankId);
		model.addAttribute("needResponse", pr_NeedResponse);
		model.addAttribute("md5hmac", md5hmac);
		//进度条
		//是个转发，相当于modelandView.setViewName("connection")
		return "connection";
	}
	
}






