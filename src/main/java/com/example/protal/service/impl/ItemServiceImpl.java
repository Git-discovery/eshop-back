package com.example.protal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.AjiaItem;
import com.example.bean.AjiaItemDesc;
import com.example.bean.AjiaItemParam;
import com.example.bean.AjiaItemParamItem;
import com.example.bean.AjiaItemParamItemExample;
import com.example.bean.ajiaitem.AjiaItemParamData;
import com.example.bean.ajiaitem.Params;
import com.example.protal.dao.AjiaItemDescMapper;
import com.example.protal.dao.AjiaItemMapper;
import com.example.protal.dao.AjiaItemParamItemMapper;
import com.example.protal.dao.AjiaItemParamMapper;
import com.example.protal.service.ItemService;
import com.example.utils.JsonUtils;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	AjiaItemParamItemMapper ajiaItemParamItemMapper;

	@Autowired
	AjiaItemMapper ajiaItemMapper;

	@Autowired
	AjiaItemDescMapper ajiaItemDescMapper;

	@Autowired
	AjiaItemParamMapper ajiaItemParamMapper;

	@Override
	public AjiaItemParamItem getParamDataByItemId(long itemId) throws Exception {
		// where item_id=10000028
		AjiaItemParamItemExample example = new AjiaItemParamItemExample();
		AjiaItemParamItemExample.Criteria criteria = example.or();
		criteria.andItemIdEqualTo(itemId);

		// ��example���ص���list
		// ���mysql��һ�е�����������text,������selectByExampleWithBLOBs��������selectByExample
		List<AjiaItemParamItem> list = ajiaItemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() >= 1) {
			AjiaItemParamItem ajiaItemParamItem = list.get(0);
			return ajiaItemParamItem;
		}
		return null;
	}

	@Override
	public AjiaItem SelectItemByItemid(long itemId) throws Exception {
		return ajiaItemMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public AjiaItemDesc selectDescByItemId(long itemId) throws Exception {
		return ajiaItemDescMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public AjiaItemParam selectTypeParamById(long itemParamId) throws Exception {
		// TODO Auto-generated method stub
		return ajiaItemParamMapper.selectByPrimaryKey(itemParamId);
	}

	@Override
	public long selectItemByParams(long itemParamId, String color, String model) throws Exception {
		// ��ѯ��ͬ���͵�������Ʒ
		AjiaItemParamItemExample example = new AjiaItemParamItemExample();
		AjiaItemParamItemExample.Criteria criteria = example.or();
		criteria.andItemParamIdEqualTo(itemParamId);
//���ݿ������һ����text��������WithBLOBs
		List<AjiaItemParamItem> list = ajiaItemParamItemMapper.selectByExampleWithBLOBs(example);

		// �ж��Ǹ���Ʒ����ɫ���ͺŵ����û�ѡ���

		for (AjiaItemParamItem ajiaItemParamItem : list) {
			// �����ɫ���isEquals[0]=true
			// ����ͺ����isEquals[1]=true

			boolean[] isEquals = new boolean[2];

			// ȡ����Ʒ�Ĳ�������
			String json = ajiaItemParamItem.getParamData();
			// �� jsonת��list
			try {
				List<AjiaItemParamData> paramList = JsonUtils.jsonToList(json, AjiaItemParamData.class);
				// ��list��ȡ����Ʒ����ɫ���ͺ����Ƚ�
				for (Params param : paramList.get(0).getParams()) {
					// �����������ɫ
					if ("��ɫ".equals(param.getKey())) {
						// �ж���ɫ�ǲ����û�ѡ�����ɫ
						if (color.equals(param.getValues().get(0))) {
							isEquals[0] = true;
						}
					}

					if ("�ͺ�".equals(param.getKey())) {
						if (model.equals(param.getValues().get(0))) {
							isEquals[1] = true;
						}
					}
					// ��isEquals������ֵ����true,�ҵ�����Ʒ
					if (isEquals[0] == true && isEquals[1] == true) {
						return ajiaItemParamItem.getItemId();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	@Override
	public List<AjiaItem> findAll() throws Exception {
		// TODO Auto-generated method stub
		return ajiaItemMapper.selectByExample(null);
	}

}
