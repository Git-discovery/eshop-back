package com.example.protal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.AjiaCartItem;
import com.example.bean.AjiaCartItemExample;
import com.example.bean.AjiaCartItemVo;
import com.example.bean.AjiaCartVo;
import com.example.bean.AjiaItem;
import com.example.bean.AjiaItemParamItem;
import com.example.bean.AjiaItemParamItemExample;
import com.example.bean.AjiaOrder;
import com.example.bean.AjiaOrderExample;
import com.example.bean.AjiaOrderItem;
import com.example.bean.AjiaOrderItemExample;
import com.example.bean.AjiaOrderVo;
import com.example.bean.AjiaShipping;
import com.example.bean.ajiaitem.AjiaItemParamData;
import com.example.bean.ajiaitem.Params;
import com.example.protal.dao.AjiaCartItemMapper;
import com.example.protal.dao.AjiaItemMapper;
import com.example.protal.dao.AjiaItemParamItemMapper;
import com.example.protal.dao.AjiaOrderItemMapper;
import com.example.protal.dao.AjiaOrderMapper;
import com.example.protal.dao.AjiaShippingMapper;
import com.example.protal.service.OrderService;
import com.example.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	AjiaCartItemMapper ajiaCartItemMapper;

	@Autowired
	AjiaItemMapper ajiaItemMapper;

	@Autowired
	AjiaItemParamItemMapper ajiaItemParamItemMapper;

	@Autowired
	AjiaShippingMapper ajiaShippingMapper;

	@Autowired
	AjiaOrderMapper ajiaOrderMapper;

	@Autowired
	AjiaOrderItemMapper ajiaOrderItemMapper;

	@Override
	public AjiaCartVo orderConfirm(long userId, List<Long> itemIdList) throws Exception {
		AjiaCartVo ajiaCartVo = new AjiaCartVo();

		// ��ajia_cart_item��ѯ����û���Ʒ
		AjiaCartItemExample cartItemExample = new AjiaCartItemExample();
		AjiaCartItemExample.Criteria criteria = cartItemExample.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andItemIdIn(itemIdList);
		criteria.andStatusEqualTo(1);

		List<AjiaCartItem> list = ajiaCartItemMapper.selectByExample(cartItemExample);
		List<AjiaCartItemVo> itemVoList = new ArrayList<>();
		int total = 0;
		double payment = 0.0;
		for (AjiaCartItem ajiaCartItem : list) {
			AjiaCartItemVo itemVo = new AjiaCartItemVo();
			// �õ�num��Ϣ
			itemVo.setAjiaCartItem(ajiaCartItem);
			long itemId = ajiaCartItem.getItemId();
			// ��ajia_item��ѯ��Ʒ�ļ۸����Ϣ
			AjiaItem ajiaItem = ajiaItemMapper.selectByPrimaryKey(itemId);
			itemVo.setAjiaItem(ajiaItem);
			// ��ajia_item_param_item��ѯ��Ʒ����ɫ����Ϣ

			AjiaItemParamItemExample ParamExample = new AjiaItemParamItemExample();
			AjiaItemParamItemExample.Criteria paramCriteria = ParamExample.or();
			paramCriteria.andItemIdEqualTo(itemId);
			List<AjiaItemParamItem> paramList = ajiaItemParamItemMapper.selectByExampleWithBLOBs(ParamExample);
			if (paramList != null && paramList.size() >= 1) {
				AjiaItemParamItem ajiaItemParamItem = paramList.get(0);
				String paramData = ajiaItemParamItem.getParamData();
				// ��jsonת��list
				List<AjiaItemParamData> paramDataList = JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
				// [{group:����,params:[{��ɫ},{�ͺ�}]},{ͼƬ}]
				List<Params> paramsList = paramDataList.get(0).getParams();
				itemVo.setParamsList(paramsList);
			}
			// itemVo��Ӧ���ǹ��ﳵ��һ����Ʒ
			itemVoList.add(itemVo);
			// ����������
			total = total + ajiaCartItem.getNum();

			// �����ܼ�Ǯ
			payment = payment + (ajiaCartItem.getNum() * ajiaItem.getPrice());
		}
		// �û��Ĺ��ﳵ��һ������ajiaCartVo
		// ������Ʒ����itemVoList
		ajiaCartVo.setItemList(itemVoList);
		ajiaCartVo.setTotal(total);
		ajiaCartVo.setPayment(payment);

		return ajiaCartVo;
	}

	@Override
	public AjiaOrder saveOrder(Long userId, Long addId, List<Long> itemIdList) throws Exception {
		// 1:��ѯ�û���������Ʒ
		AjiaCartVo ajiaCartVo = orderConfirm(userId, itemIdList);
		// 2:�����ܼ�
		double payment = ajiaCartVo.getPayment();
		// 3:���û���ַ
		AjiaShipping ajiaShipping = ajiaShippingMapper.selectByPrimaryKey(addId);
		// 4�����涩��
		AjiaOrder ajiaOrder = new AjiaOrder();
		String orderId = generateid();
		ajiaOrder.setUserId(userId);
		ajiaOrder.setOrderId(orderId);
		ajiaOrder.setPayment(payment);
		ajiaOrder.setAddId(addId);
		ajiaOrder.setShippingName(ajiaShipping.getReceiverName());
		ajiaOrder.setStatus(1);
		ajiaOrder.setCreateTime(new Date());

		ajiaOrderMapper.insert(ajiaOrder);

		// 5:���涩����ÿ����Ʒ

		for (AjiaCartItemVo ajiaCartItemVo : ajiaCartVo.getItemList()) {
			String id = generateid();
			AjiaOrderItem orderItem = new AjiaOrderItem();
			orderItem.setId(id);
			
			//��һ����Ʒ����ɹ����ڶ�����Ʒ����ʧ�ܣ���Ϊ����һ����
			//Ϊ�˲������񣬹�����������ͬ����
			//orderItem.setId("1");
			orderItem.setItemId("" + ajiaCartItemVo.getAjiaItem().getId());
			orderItem.setOrderId(orderId);
			orderItem.setNum(ajiaCartItemVo.getAjiaCartItem().getNum());
			orderItem.setPrice(ajiaCartItemVo.getAjiaItem().getPrice());
			orderItem.setTitle(ajiaCartItemVo.getAjiaItem().getTitle());
			orderItem.setTotalFee(ajiaCartItemVo.getAjiaCartItem().getNum() * ajiaCartItemVo.getAjiaItem().getPrice());
			ajiaOrderItemMapper.insert(orderItem);
		}
		return ajiaOrder;
	}

	/**
	 * ����id
	 * 
	 * @return
	 */
	public String generateid() {
		Random random = new Random();
		String randomString = "" + (random.nextInt(9000000) + 1000000);
		return System.currentTimeMillis() + randomString;
	}

	/**
	 * 
	 * @param userid
	 * @param status
	 * @param currentPage
	 *            ��ǰҳ
	 * @param pageSize
	 *            ÿҳ��ʾ����
	 * @return pageInfo������ҳ
	 * @throws Exception
	 */
	@Override
	public PageInfo<AjiaOrderVo> findByUserIdAndStatus(long userid, int status, int currentPage, int pageSize)
			throws Exception {
		// ��ҳ��һ�������õ�ǰҳ��ÿҳ��ʾ������
		PageHelper.startPage(currentPage, pageSize);
		PageHelper.orderBy("create_time desc");

		AjiaOrderExample orderExample = new AjiaOrderExample();
		AjiaOrderExample.Criteria criteria = orderExample.or();
		criteria.andUserIdEqualTo(userid);

		// 9��ɾ������
		criteria.andStatusNotEqualTo(9);

		// ����״̬��ѯ����
		if (status != 0) {
			criteria.andStatusEqualTo(status);
		}
		// orderExample.setOrderByClause("create_time desc");
		List<AjiaOrder> orderList = ajiaOrderMapper.selectByExample(orderExample);
		List<AjiaOrderVo> voList = new ArrayList<>();

		// ��ҳ�ڶ���������PageInfo
		PageInfo<AjiaOrderVo> pageinfoList = new PageInfo(orderList);

		for (AjiaOrder ajiaOrder : orderList) {
			AjiaOrderVo ajiaOrderVo = new AjiaOrderVo();
			voList.add(ajiaOrderVo);

			ajiaOrderVo.setAjiaOrder(ajiaOrder);
			// ��ѯÿ��������������Ʒ
			String orderId = ajiaOrder.getOrderId();

			AjiaOrderItemExample itemExample = new AjiaOrderItemExample();
			AjiaOrderItemExample.Criteria itemCriteria = itemExample.or();
			itemCriteria.andOrderIdEqualTo(orderId);
			List<AjiaOrderItem> itemList = ajiaOrderItemMapper.selectByExample(itemExample);
			// �����е���Ʒ�ŵ�vo
			ajiaOrderVo.setAjiaOrderItemList(itemList);

			for (AjiaOrderItem ajiaOrderItem : itemList) {

				String itemId = ajiaOrderItem.getItemId();
				// ��ѯÿ����Ʒ�Ĳ���

				AjiaItemParamItemExample ParamExample = new AjiaItemParamItemExample();
				AjiaItemParamItemExample.Criteria paramCriteria = ParamExample.or();
				paramCriteria.andItemIdEqualTo(Long.parseLong(itemId));
				// text���ͱ�����WithBLOBs
				List<AjiaItemParamItem> paramItemList = ajiaItemParamItemMapper.selectByExampleWithBLOBs(ParamExample);
				// [{,params:[]},{}]
				String paramData = paramItemList.get(0).getParamData();
				try {
					List<AjiaItemParamData> paramDataList = JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
					List<Params> paramsList = paramDataList.get(0).getParams();
					ajiaOrderItem.setParamsList(paramsList);
				} catch (Exception e) {
					ajiaOrderItem.setParamsList(new ArrayList<>());
				}

			}
		}
		// ��ҳ�����������õ�ǰҳ��ʾ������
		pageinfoList.setList(voList);
		System.out.println(voList.size());
		return pageinfoList;
	}

	@Override
	public AjiaOrder findByOrderId(String orderId) throws Exception {
		return ajiaOrderMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public void updateStatusByOrderId(String orderId, int status) throws Exception {

		AjiaOrder ajiaOrder = new AjiaOrder();
		ajiaOrder.setOrderId(orderId);
		ajiaOrder.setStatus(status);
		// update ajiaOrder set status=8 where orderId=28;
		ajiaOrderMapper.updateByPrimaryKeySelective(ajiaOrder);
	}

	@Override
	public AjiaOrderVo findOrderDetailByOrderid(String orderId) throws Exception {
		// ��ѯһ������

		AjiaOrder ajiaOrder = ajiaOrderMapper.selectByPrimaryKey(orderId);

		AjiaOrderVo ajiaOrderVo = new AjiaOrderVo();

		ajiaOrderVo.setAjiaOrder(ajiaOrder);
		// ��ѯ������������Ʒ

		AjiaOrderItemExample itemExample = new AjiaOrderItemExample();
		AjiaOrderItemExample.Criteria itemCriteria = itemExample.or();
		itemCriteria.andOrderIdEqualTo(orderId);
		List<AjiaOrderItem> itemList = ajiaOrderItemMapper.selectByExample(itemExample);
		// �����е���Ʒ�ŵ�vo
		ajiaOrderVo.setAjiaOrderItemList(itemList);

		for (AjiaOrderItem ajiaOrderItem : itemList) {

			String itemId = ajiaOrderItem.getItemId();
			// ��ѯÿ����Ʒ�Ĳ���

			AjiaItemParamItemExample ParamExample = new AjiaItemParamItemExample();
			AjiaItemParamItemExample.Criteria paramCriteria = ParamExample.or();
			paramCriteria.andItemIdEqualTo(Long.parseLong(itemId));
			// text���ͱ�����WithBLOBs
			List<AjiaItemParamItem> paramItemList = ajiaItemParamItemMapper.selectByExampleWithBLOBs(ParamExample);
			// [{,params:[]},{}]
			String paramData = paramItemList.get(0).getParamData();
			try {
				List<AjiaItemParamData> paramDataList = JsonUtils.jsonToList(paramData, AjiaItemParamData.class);
				List<Params> paramsList = paramDataList.get(0).getParams();
				ajiaOrderItem.setParamsList(paramsList);
			} catch (Exception e) {
				ajiaOrderItem.setParamsList(new ArrayList<>());
			}

		}

		return ajiaOrderVo;
	}

}
