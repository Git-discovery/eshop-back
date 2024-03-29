package com.example.protal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bean.AjiaShipping;
import com.example.bean.AjiaShippingExample;
@Mapper
public interface AjiaShippingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    long countByExample(AjiaShippingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByExample(AjiaShippingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByPrimaryKey(Long addId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insert(AjiaShipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insertSelective(AjiaShipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    List<AjiaShipping> selectByExample(AjiaShippingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    AjiaShipping selectByPrimaryKey(Long addId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExampleSelective(@Param("record") AjiaShipping record, @Param("example") AjiaShippingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExample(@Param("record") AjiaShipping record, @Param("example") AjiaShippingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKeySelective(AjiaShipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_shipping
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKey(AjiaShipping record);
}