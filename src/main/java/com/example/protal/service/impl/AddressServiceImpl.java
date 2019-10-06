package com.example.protal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.bean.AjiaShipping;
import com.example.bean.AjiaShippingExample;
import com.example.protal.dao.AjiaShippingMapper;
import com.example.protal.service.AddressService;
@Component
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AjiaShippingMapper mapper;

	@Override
	public int insert(AjiaShipping ajiaShipping) throws Exception {
		// InputStream inputStream =
		// Resources.getResourceAsStream("mybatis-config.xml");
		// SqlSessionFactory factory = new
		// SqlSessionFactoryBuilder().build(inputStream);
		// SqlSession sqlSession = factory.openSession();
		// AjiaShippingMapper mapper =
		// sqlSession.getMapper(AjiaShippingMapper.class);
		int row = mapper.insert(ajiaShipping);
		// sqlSession.commit();
		// sqlSession.close();
		return row;
	}

	@Override
	public List<AjiaShipping> selectByUserid(long userId) throws Exception {

		// InputStream inputStream =
		// Resources.getResourceAsStream("mybatis-config.xml");
		// SqlSessionFactory factory = new
		// SqlSessionFactoryBuilder().build(inputStream);
		// SqlSession sqlSession = factory.openSession();
		//
		// AjiaShippingMapper mapper =
		// sqlSession.getMapper(AjiaShippingMapper.class);
		AjiaShippingExample example = new AjiaShippingExample();
		// where user_id=14 and status=1 order by add_id desc
		example.setOrderByClause("add_id desc");
		AjiaShippingExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo((byte) 1);

		// mapper.selectByPrimaryKey(addId)
		return mapper.selectByExample(example);

	}

	@Override
	public int delete(long addId) throws Exception {
		// InputStream inputStream =
		// Resources.getResourceAsStream("mybatis-config.xml");
		// SqlSessionFactory factory = new
		// SqlSessionFactoryBuilder().build(inputStream);
		// SqlSession sqlSession = factory.openSession();
		// AjiaShippingMapper mapper =
		// sqlSession.getMapper(AjiaShippingMapper.class);
		int row = mapper.deleteByPrimaryKey(addId);
		// sqlSession.commit();
		// sqlSession.close();
		return row;
	}

	@Override
	public int setDefault(long addId, long userId) throws Exception {

		// InputStream
		// inputStream=Resources.getResourceAsStream("mybatis-config.xml");
		// SqlSessionFactory factory=new
		// SqlSessionFactoryBuilder().build(inputStream);
		// SqlSession sqlSession=factory.openSession();
		// AjiaShippingMapper
		// mapper=sqlSession.getMapper(AjiaShippingMapper.class);
		// ���û������е�ַ��is_default���ó�0,0��ʾ����Ĭ�ϵ�
		// where user_id=14
		// where����example�е�criteria����������
		AjiaShippingExample example = new AjiaShippingExample();
		AjiaShippingExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(userId);

		AjiaShipping ajiaShipping = new AjiaShipping();
		// false�൱��mysql�е�0
		ajiaShipping.setIsDefault(false);
		// ������mapper.updateByExample(ajiaShipping, example);
		int row = mapper.updateByExampleSelective(ajiaShipping, example);

		// ��ĳ����ַ��is_default���ó�1,1��ʾ��Ĭ�ϵ�
		// where user_id=14 and add_id=7
		// ��һ��and add_id=7
		criteria.andAddIdEqualTo(addId);
		// true�൱��mysql�е�1,���ó�Ĭ�ϵ�
		ajiaShipping.setIsDefault(true);
		// Ӱ�����ҵ���ۼӵ�
		row = row + mapper.updateByExampleSelective(ajiaShipping, example);
		// sqlSession.commit();
		// sqlSession.close();
		return row;
	}

	@Override
	public AjiaShipping getDefalutAddress(long userId) throws Exception {
		AjiaShippingExample example = new AjiaShippingExample();
		AjiaShippingExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andIsDefaultEqualTo(true);
		List<AjiaShipping> list = mapper.selectByExample(example);
		if (list != null && list.size() >= 1) {
			return list.get(0);
		}
		return null;
	}

}
