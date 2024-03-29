package com.example.protal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bean.AjiaItem;
import com.example.bean.AjiaItemExample;
@Mapper
public interface AjiaItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    long countByExample(AjiaItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByExample(AjiaItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insert(AjiaItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insertSelective(AjiaItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    List<AjiaItem> selectByExample(AjiaItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    AjiaItem selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExampleSelective(@Param("record") AjiaItem record, @Param("example") AjiaItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExample(@Param("record") AjiaItem record, @Param("example") AjiaItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKeySelective(AjiaItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_item
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKey(AjiaItem record);
}