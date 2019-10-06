package com.example.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.bean.AjiaUser;
import com.example.bean.AjiaUserResult;
import com.example.utils.CookieUtils;
import com.example.utils.GlobalConst;
import com.example.utils.HttpClientUtils;
import com.example.utils.JsonUtils;

public class CheckUserInterceptor implements HandlerInterceptor{

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		String loginUrl = "http://sso.ajstore.com:81/user/toLogin.html";
		if (ticket == null || StringUtils.isEmpty(ticket) || " ".equals(ticket) || "".equals(ticket)) {
			response.sendRedirect(loginUrl);
			return false;
		}
		List<String> cookies = new ArrayList<String>();
		cookies.add(GlobalConst.COOKIE_NAME + "=" + ticket);
		String json = HttpClientUtils.doGet("http://sso.ajstore.com:81/user/checkLoginHttpClient.html", cookies);
		if (json == null || StringUtils.isEmpty(json) || " ".equals(json) || "".equals(json)) {
			response.sendRedirect(loginUrl);
			return false;
		} else {

			AjiaUserResult ajiaUserResult = JsonUtils.jsonToPojo(json, AjiaUserResult.class);
			if (ajiaUserResult.getStatus() == 200) {
				// ���û����������Controller
				
			
				AjiaUser user = ajiaUserResult.getData();
				request.setAttribute("interceptor_user", user);
			} else {
				response.sendRedirect(loginUrl);
				return false;
			}
		}

		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
