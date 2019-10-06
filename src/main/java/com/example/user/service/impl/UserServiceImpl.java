package com.example.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.AjiaUser;
import com.example.bean.AjiaUserExample;
import com.example.user.dao.AjiaUserMapper;
import com.example.user.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	AjiaUserMapper ajiaUserMapper;

	@Override
	public AjiaUser selectUserByUsername(String username) throws Exception {
		// from ajia_user where username='username'
		AjiaUserExample example = new AjiaUserExample();
		AjiaUserExample.Criteria criteria = example.or();
		criteria.andUsernameEqualTo(username);

		List<AjiaUser> list = ajiaUserMapper.selectByExample(example);
		if (list != null && list.size() >= 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int insert(AjiaUser ajiaUser) throws Exception {
		int row = ajiaUserMapper.insert(ajiaUser);
		return row;
	}

}
