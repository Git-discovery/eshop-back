package com.example.protal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.bean.AjiaItem;
import com.example.bean.AjiaItemDesc;
import com.example.bean.AjiaItemParam;
import com.example.bean.AjiaItemParamItem;
import com.example.bean.AjiaResult;
import com.example.bean.ajiaitem.AjiaItemParamData;
import com.example.bean.ajiaitem.Params;
import com.example.protal.service.ItemService;
import com.example.utils.JsonUtils;

@RestController
public class ItemInfoController {
	@Autowired
	ItemService itemService;

	// url /show/10000028.html
	@RequestMapping("/show/{itemId}")
	public ModelAndView showDetail(@PathVariable(name = "itemId") long itemId) throws Exception {
		AjiaItemParamItem ajiaItemParamItem = itemService.getParamDataByItemId(itemId);
		String json = ajiaItemParamItem.getParamData();

		long itemParamId = ajiaItemParamItem.getItemParamId();
		AjiaItemParam typeParam = itemService.selectTypeParamById(itemParamId);
		String typeParamData = typeParam.getParamData();
		List<AjiaItemParamData> typeParamList = JsonUtils.jsonToList(typeParamData, AjiaItemParamData.class);

		List<AjiaItemParamData> list = JsonUtils.jsonToList(json, AjiaItemParamData.class);
		String itemColor = "", itemModel = "";
		for (Params params : list.get(0).getParams()) {
			if ("颜色".equals(params.getKey())) {
				itemColor = params.getValues().get(0);
			}
			if ("型号".equals(params.getKey())) {
				itemModel = params.getValues().get(0);
			}
		}

		AjiaItem item = itemService.SelectItemByItemid(itemId);

		AjiaItemDesc ajiaItemDesc = itemService.selectDescByItemId(itemId);
		String desHtml = ajiaItemDesc.getItemDesc();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", list);
		modelAndView.addObject("item", item);
		modelAndView.addObject("desHtml", desHtml);
		modelAndView.addObject("typeParamList", typeParamList);
		modelAndView.addObject("itemColor", itemColor);
		modelAndView.addObject("itemModel", itemModel);
		modelAndView.addObject("itemParamId", itemParamId);
		modelAndView.setViewName("product_details");
		return modelAndView;
	}

	/**
	 * 
	 * 根据用户选择的参数找对应的商品
	 * @param itemParamId
	 * @param color
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/item/getItem")
	public AjiaResult getItemByParams(long itemParamId, String color, String model) throws Exception {
		long itemId = itemService.selectItemByParams(itemParamId, color, model);
		AjiaResult ajiaResult = new AjiaResult(200, "sucess", itemId);
		return ajiaResult;
	}

}
