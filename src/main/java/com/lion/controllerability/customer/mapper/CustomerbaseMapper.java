package com.lion.controllerability.customer.mapper;

import com.lion.controllerability.customer.data.Customerbase;
import com.lion.controllerability.customer.data.CustomerbaseExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CustomerbaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    int countByExample(CustomerbaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    int deleteByExample(CustomerbaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    int deleteByPrimaryKey(Long customerld);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    int insert(Customerbase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    int insertSelective(Customerbase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    List<Customerbase> selectByExample(CustomerbaseExample example);
    List<Customerbase> selectAllCustomer();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    Customerbase selectByPrimaryKey(Long customerld);
    Customerbase isBind(@Param("openId") String openId);

    Customerbase selectByMobile(String mobile);

    Customerbase getInfo(Long customerld);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    int updateByExampleSelective(@Param("record") Customerbase record, @Param("example") CustomerbaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    int updateByExample(@Param("record") Customerbase record, @Param("example") CustomerbaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    int updateByPrimaryKeySelective(Customerbase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CustomerBase
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    int updateByPrimaryKey(Customerbase record);
}