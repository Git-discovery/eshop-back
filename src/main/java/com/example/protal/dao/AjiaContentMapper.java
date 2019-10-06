package com.example.protal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bean.AjiaContent;
import com.example.bean.AjiaContentExample;
@Mapper
public interface AjiaContentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    long countByExample(AjiaContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByExample(AjiaContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insert(AjiaContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insertSelective(AjiaContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    List<AjiaContent> selectByExampleWithBLOBs(AjiaContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    List<AjiaContent> selectByExample(AjiaContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    AjiaContent selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExampleSelective(@Param("record") AjiaContent record, @Param("example") AjiaContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") AjiaContent record, @Param("example") AjiaContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExample(@Param("record") AjiaContent record, @Param("example") AjiaContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKeySelective(AjiaContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(AjiaContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_content
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKey(AjiaContent record);
}