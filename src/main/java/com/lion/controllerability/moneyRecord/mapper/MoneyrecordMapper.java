package com.lion.controllerability.moneyRecord.mapper;

import com.lion.controllerability.moneyRecord.data.Moneyrecord;
import com.lion.controllerability.moneyRecord.data.MoneyrecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MoneyrecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    int countByExample(MoneyrecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    int deleteByExample(MoneyrecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    int deleteByPrimaryKey(Long recordid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    int insert(Moneyrecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    int insertSelective(Moneyrecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    List<Moneyrecord> selectByExample(MoneyrecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    Moneyrecord selectByPrimaryKey(Long recordid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    int updateByExampleSelective(@Param("record") Moneyrecord record, @Param("example") MoneyrecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    int updateByExample(@Param("record") Moneyrecord record, @Param("example") MoneyrecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    int updateByPrimaryKeySelective(Moneyrecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MoneyRecord
     *
     * @mbggenerated Tue Mar 10 18:21:56 CST 2020
     */
    int updateByPrimaryKey(Moneyrecord record);

    List<Moneyrecord> selectAllByPositionId(Long positionId);
}