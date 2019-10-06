package com.example.bean;

import org.apache.solr.client.solrj.beans.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//把Item的对象添加到solr中
//从solr中搜索出来的数据是Item


//忽略未知的字段
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

	//配置的是对象属性id和solr的item_id列的映射关系，
	//mybatis是在xml中配置的
    //item_id是solr中的field
	@Field("item_id")
    private String id;
    
    @Field("brand")
    private String brand;
    
    @Field("model")
    private String model;

    @Field("title")
    private String title;

    @Field("sellpoint")
    private String sellPoint;

    @Field("price")
    private Double price;

    @Field("image")
    private String image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

    

}
