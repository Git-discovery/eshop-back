package com.example.user.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.bean.AjiaResult;
import com.example.bean.AjiaUser;
import com.example.enums.ResultCodeEnum;
import com.example.exception.StoreException;
import com.example.user.service.UserService;
import com.example.utils.BlankUtil;
import com.example.utils.CookieUtils;
import com.example.utils.GlobalConst;
import com.example.utils.JedisUtils;
import com.example.utils.JsonUtils;
import com.example.utils.MD5Encrypt;
import com.example.utils.PhoneFormatCheckUtils;
import com.example.utils.ValidateUtil;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	JedisUtils jedisUtils;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/checkLoginJsonP")
 	public void checkLoginJsonP(String jsonpCallback, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws Exception {
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		AjiaResult ajiaResult = null;
		if (ticket == null || "".equals(ticket)) {
			ajiaResult = new AjiaResult(500);
		} else {
			AjiaUser ajiaUser = (AjiaUser) session.getAttribute(ticket);
			if (ajiaUser != null) {
				ajiaResult = new AjiaResult(200, "验证成功", ajiaUser.getUsername());

			} else {
				ajiaResult = new AjiaResult(500);
			}
		}
		String json = JsonUtils.objectToJson(ajiaResult);
		String text = jsonpCallback + "(" + json + ")";
		PrintWriter out = response.getWriter();
		out.println(text);
		out.close();
	}

	@RequestMapping("/checkLogin")
	@CrossOrigin
	public AjiaResult checkLogin(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		if (ticket == null || "".equals(ticket)) {
			AjiaResult ajiaResult = new AjiaResult(500);
			return ajiaResult;
		}
		 AjiaUser ajiaUser=(AjiaUser)session.getAttribute(ticket);
		if (ajiaUser != null) {
			AjiaResult ajiaResult = new AjiaResult(200, "验证成功", ajiaUser.getUsername());
			return ajiaResult;
		}
		AjiaResult ajiaResult = new AjiaResult(500);
		return ajiaResult;	
	}

	@RequestMapping("/checkLoginHttpClient")
	public AjiaResult checkLoginHttpClient(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		if (ticket == null || "".equals(ticket)) {
			AjiaResult ajiaResult = new AjiaResult(500);
			return ajiaResult;
		}
		Jedis jedis = jedisUtils.getJedis();
		String userJson = jedis.hget(ticket, "user");
		AjiaUser ajiaUser = JsonUtils.jsonToPojo(userJson, AjiaUser.class);
		jedisUtils.returnJedis(jedis);
		if (ajiaUser != null) {
			ajiaUser.setPassword("");
			AjiaResult ajiaResult = new AjiaResult(200, "�û��Ѿ���¼��", ajiaUser);
			return ajiaResult;
		}
		AjiaResult ajiaResult = new AjiaResult(500);
		return ajiaResult;
	}

	
	@RequestMapping("/login")
	public AjiaResult Login(String username, String password, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		AjiaResult ajiaResult = null;
		AjiaUser ajiaUser = userService.selectUserByUsername(username);
		password = MD5Encrypt.MD5Encode(password);
		if (ajiaUser != null && ajiaUser.getPassword().equals(password)) {
			ajiaResult = new AjiaResult(200, "登陆成功", username);
			String uuid = UUID.randomUUID().toString();
			 session.setAttribute(uuid,ajiaUser);
			CookieUtils.setCookie(request, response, GlobalConst.COOKIE_NAME, uuid + "");
		} else if (ajiaUser != null && !ajiaUser.getPassword().equals(password)) {

			ajiaResult = new AjiaResult(500, "密码不正确");
		} else {
			ajiaResult = new AjiaResult(500, "账号或密码错误");

		}

		return ajiaResult;
	}

	@RequestMapping("/register")
	public AjiaResult register(AjiaUser user) throws Exception {
		//参数校验
		if(BlankUtil.isBlank(user.getUsername())) {
			log.debug("用户名为空"+ResultCodeEnum.Request_param_error);
			throw new StoreException(ResultCodeEnum.Request_param_error);
		}else {
			int uNameLength = user.getUsername().length();
			if(!(uNameLength>=6&&uNameLength<=9)) {
				log.debug("用户名长度不合法"+ResultCodeEnum.Request_param_error);
				throw new StoreException(ResultCodeEnum.Request_param_error);
			}
		}
		if(BlankUtil.isBlank(user.getPassword())) {
			log.debug("密码为空"+ResultCodeEnum.Request_param_error);
			throw new StoreException(ResultCodeEnum.Request_param_error);
		}else {
			if(!ValidateUtil.rexCheckPassword(user.getPassword())) {
				log.debug("密码格式错误"+ResultCodeEnum.Request_param_error);
				throw new StoreException(ResultCodeEnum.Request_param_error);
			}
		}
		if(BlankUtil.isBlank(user.getEmail())) {
			log.debug("邮箱为空"+ResultCodeEnum.Request_param_error);
			throw new StoreException(ResultCodeEnum.Request_param_error);
		}else {
			if(!ValidateUtil.rexCheckEmail(user.getEmail())) {
				log.debug("邮箱格式错误"+ResultCodeEnum.Request_param_error);
				throw new StoreException(ResultCodeEnum.Request_param_error);
			}
		}
		if(BlankUtil.isBlank(user.getPhone())) {
			log.debug("手机号为空"+ResultCodeEnum.Request_param_error);
			throw new StoreException(ResultCodeEnum.Request_param_error);
		}
		if(!PhoneFormatCheckUtils.isPhoneLegal(user.getPhone())) {
			log.debug("手机号格式错误"+ResultCodeEnum.Request_param_error);
			throw new StoreException(ResultCodeEnum.Request_param_error);
		}
		AjiaUser dbUser = userService.selectUserByUsername(user.getUsername());
		if (dbUser == null) {
			user.setCreated(new Date());
			String password = user.getPassword();
			String md5Password = MD5Encrypt.MD5Encode(password);
			user.setPassword(md5Password);
			int row = userService.insert(user);
			return new AjiaResult(200,"请求成功","注册成功");
		}
		return new AjiaResult(200,"请求成功","该用户已存在");
	}

	@RequestMapping("/toRegister")
	public ModelAndView toRegister() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping("/checkUsername")
	public AjiaResult checkUserName(String username) throws Exception {
		AjiaUser ajiaUser = userService.selectUserByUsername(username);
		if (ajiaUser != null) {
			AjiaResult ajiaResult = new AjiaResult(200, "成功");
			return ajiaResult;
		}
		AjiaResult ajiaResult = new AjiaResult(200, "成功");
		return ajiaResult;
	}
	@RequestMapping("/test")
	public String getTest() {
		return "测试";
	}
}
