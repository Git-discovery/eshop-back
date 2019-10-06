package com.example.protal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bean.AjiaOrderItem;
import com.example.bean.AjiaOrderItemExample;
@Mapper
public interface AjiaOrderItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    long countByExample(AjiaOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByExample(AjiaOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insert(AjiaOrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insertSelective(AjiaOrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    List<AjiaOrderItem> selectByExample(AjiaOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    AjiaOrderItem selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExampleSelective(@Param("record") AjiaOrderItem record, @Param("example") AjiaOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExample(@Param("record") AjiaOrderItem record, @Param("example") AjiaOrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKeySelective(AjiaOrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_order_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKey(AjiaOrderItem record);
}