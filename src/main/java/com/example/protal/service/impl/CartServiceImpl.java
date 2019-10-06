package com.example.protal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.bean.AjiaCartItem;
import com.example.bean.AjiaCartItemExample;
import com.example.bean.AjiaCartItemVo;
import com.example.bean.AjiaItem;
import com.example.bean.AjiaItemParamItem;
import com.example.bean.AjiaItemParamItemExample;
import com.example.bean.ajiaitem.AjiaItemParamData;
import com.example.bean.ajiaitem.Params;
import com.example.protal.dao.AjiaCartItemMapper;
import com.example.protal.dao.AjiaItemMapper;
import com.example.protal.dao.AjiaItemParamItemMapper;
import com.example.protal.service.CartService;
import com.example.utils.JsonUtils;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	AjiaCartItemMapper ajiaCartItemMapper;

	@Autowired
	AjiaItemMapper ajiaItemMapper;

	@Autowired
	AjiaItemParamItemMapper ajiaItemParamItemMapper;

	@Override
	public boolean insert(AjiaCartItem ajiaCartItem) throws Exception {

		// where user_id=14 and item_id='10000028' and status=1
		AjiaCartItemExample example = new AjiaCartItemExample();
		AjiaCartItemExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(ajiaCartItem.getUserId());
		criteria.andItemIdEqualTo(ajiaCartItem.getItemId());
		criteria.andStatusEqualTo(ajiaCartItem.getStatus());

		// ��ѯ��Ʒ
		List<AjiaCartItem> list = ajiaCartItemMapper.selectByExample(example);
		int row = 0;
		if (list != null && list.size() >= 1) {
			// ��ѯ�������Ʒ��˵����ǰ��ӹ��������ۼ�
			AjiaCartItem dbAjiaCartItem = list.get(0);
			dbAjiaCartItem.setNum(dbAjiaCartItem.getNum() + ajiaCartItem.getNum());
			// ��Selective��ֻ����ĳЩ��
			row = ajiaCartItemMapper.updateByExampleSelective(dbAjiaCartItem, example);

		} else {

			// û��ѯ������һ����ӵ����ﳵ
			row = ajiaCartItemMapper.insert(ajiaCartItem);
		}

		if (row >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<AjiaCartItemVo> selectAllItemByUserId(long userId) throws Exception {
		List<AjiaCartItemVo> voList = new ArrayList();

		// ��ѯ�û����ﳵ�е�������Ʒ
		// where user_id=14 and status=1
		AjiaCartItemExample example = new AjiaCartItemExample();
		AjiaCartItemExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo(1);
		List<AjiaCartItem> cartItemlist = ajiaCartItemMapper.selectByExample(example);
		for (AjiaCartItem ajiaCartItem : cartItemlist) {
			AjiaCartItemVo vo = new AjiaCartItemVo();
			vo.setAjiaCartItem(ajiaCartItem);
			// ��ѯ��Ʒ�ļ۸�
			long itemId = ajiaCartItem.getItemId();
			AjiaItem ajiaItem = ajiaItemMapper.selectByPrimaryKey(itemId);
			vo.setAjiaItem(ajiaItem);
			// ��ѯ��Ʒ����ɫ�Ȳ���
			AjiaItemParamItemExample paramExample = new AjiaItemParamItemExample();
			AjiaItemParamItemExample.Criteria paramCriteria = paramExample.or();
			paramCriteria.andItemIdEqualTo(itemId);
			List<AjiaItemParamItem> paramItemList = ajiaItemParamItemMapper.selectByExampleWithBLOBs(paramExample);
			if (paramItemList != null && paramItemList.size() >= 1) {
				try {
					AjiaItemParamItem ajiaItemParamItem = paramItemList.get(0);
					// ���Ǹ� json�ַ���
					String paramData = ajiaItemParamItem.getParamData();
					// ��jsonת�� list
					List<AjiaItemParamData> list = JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
					List<Params> paramsList = list.get(0).getParams();
					vo.setParamsList(paramsList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// �� vo�ŵ�voList
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public boolean changeCartNum(AjiaCartItem ajiaCartItem) throws Exception {
		AjiaCartItemExample example = new AjiaCartItemExample();
		AjiaCartItemExample.Criteria criteria = example.or();
		// where user_id=14 and item_id=1 and status=1;
		criteria.andUserIdEqualTo(ajiaCartItem.getUserId());
		criteria.andItemIdEqualTo(ajiaCartItem.getItemId());
		criteria.andStatusEqualTo(1);
		int row = ajiaCartItemMapper.updateByExampleSelective(ajiaCartItem, example);
		if (row >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(long itemId, long userId) throws Exception {
		// where item_id=28 and userid=14 and status=1
		AjiaCartItemExample example = new AjiaCartItemExample();
		AjiaCartItemExample.Criteria criteria = example.or();
		criteria.andItemIdEqualTo(itemId);
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo(1);
		int row = ajiaCartItemMapper.deleteByExample(example);
		if (row >= 1) {
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteItems(List<Long> idList, long userId) throws Exception {
		// update ajia_store.ajia_cart_item set status=2
		// where item_id in(28,32) and user_id=14
		AjiaCartItemExample example = new AjiaCartItemExample();
		AjiaCartItemExample.Criteria criteria = example.or();
		criteria.andItemIdIn(idList);
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo(1);

		AjiaCartItem ajiaCartItem = new AjiaCartItem();
		ajiaCartItem.setStatus(2);
		int row = ajiaCartItemMapper.updateByExampleSelective(ajiaCartItem, example);
		if (row >= 1) {
			return true;
		}

		return false;
	}

}
