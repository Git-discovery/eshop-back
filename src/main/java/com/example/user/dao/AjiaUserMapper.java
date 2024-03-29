package com.example.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bean.AjiaUser;
import com.example.bean.AjiaUserExample;
@Mapper
public interface AjiaUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    long countByExample(AjiaUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByExample(AjiaUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insert(AjiaUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int insertSelective(AjiaUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    List<AjiaUser> selectByExample(AjiaUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    AjiaUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExampleSelective(@Param("record") AjiaUser record, @Param("example") AjiaUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByExample(@Param("record") AjiaUser record, @Param("example") AjiaUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKeySelective(AjiaUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ajia_user
     *
     * @mbg.generated Wed Jan 31 17:22:15 CST 2018
     */
    int updateByPrimaryKey(AjiaUser record);
}