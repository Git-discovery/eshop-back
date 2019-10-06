package com.example.user.service;


import com.example.bean.AjiaUser;
public interface UserService {
	/**
	 * 添加用户
	 * @param ajiaUser
	 * @return
	 * @throws Exception
	 */
	public int insert(AjiaUser ajiaUser) throws Exception;

	/**
	 * 根据用户名查找一个用户
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public  AjiaUser selectUserByUsername(String username) throws Exception;

}
