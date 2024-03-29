package com.example.protal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bean.AjiaItemCat;
import com.example.bean.AjiaItemCatExample;
@Mapper
public interface AjiaItemCatMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    long countByExample(AjiaItemCatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByExample(AjiaItemCatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insert(AjiaItemCat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insertSelective(AjiaItemCat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    List<AjiaItemCat> selectByExample(AjiaItemCatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    AjiaItemCat selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExampleSelective(@Param("record") AjiaItemCat record, @Param("example") AjiaItemCatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExample(@Param("record") AjiaItemCat record, @Param("example") AjiaItemCatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKeySelective(AjiaItemCat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item_cat
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKey(AjiaItemCat record);
}