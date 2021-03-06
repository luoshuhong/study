package com.zhe800.quartz.model.mapper;

import com.zhe800.quartz.model.pojo.TbQuartzTask;
import com.zhe800.quartz.model.pojo.TbQuartzTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface TbQuartzTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    int countByExample(TbQuartzTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    int deleteByExample(TbQuartzTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    @Delete({
        "delete from tb_quartz_task",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer taskId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    @Insert({
        "insert into tb_quartz_task (task_name, task_group, ",
        "task_des, task_url, ",
        "task_express, task_key, ",
        "task_status, create_time, ",
        "last_update_time)",
        "values (#{taskName,jdbcType=VARCHAR}, #{taskGroup,jdbcType=VARCHAR}, ",
        "#{taskDes,jdbcType=VARCHAR}, #{taskUrl,jdbcType=VARCHAR}, ",
        "#{taskExpress,jdbcType=VARCHAR}, #{taskKey,jdbcType=VARCHAR}, ",
        "#{taskStatus,jdbcType=TINYINT}, #{createTime,jdbcType=TIMESTAMP,typeHandler=com.tuan800.mybatis.typehandler.JodaDateTime2TimestampTypeHandler}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP,typeHandler=com.tuan800.mybatis.typehandler.JodaDateTime2TimestampTypeHandler})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="taskId", before=false, resultType=Integer.class)
    int insert(TbQuartzTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    int insertSelective(TbQuartzTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    List<TbQuartzTask> selectByExample(TbQuartzTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    @Select({
        "select",
        "task_id, task_name, task_group, task_des, task_url, task_express, task_key, ",
        "task_status, create_time, last_update_time",
        "from tb_quartz_task",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    TbQuartzTask selectByPrimaryKey(Integer taskId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    int updateByExampleSelective(@Param("record") TbQuartzTask record, @Param("example") TbQuartzTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    int updateByExample(@Param("record") TbQuartzTask record, @Param("example") TbQuartzTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    int updateByPrimaryKeySelective(TbQuartzTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_quartz_task
     *
     * @mbggenerated Thu Apr 02 11:06:17 CST 2015
     */
    @Update({
        "update tb_quartz_task",
        "set task_name = #{taskName,jdbcType=VARCHAR},",
          "task_group = #{taskGroup,jdbcType=VARCHAR},",
          "task_des = #{taskDes,jdbcType=VARCHAR},",
          "task_url = #{taskUrl,jdbcType=VARCHAR},",
          "task_express = #{taskExpress,jdbcType=VARCHAR},",
          "task_key = #{taskKey,jdbcType=VARCHAR},",
          "task_status = #{taskStatus,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP,typeHandler=com.tuan800.mybatis.typehandler.JodaDateTime2TimestampTypeHandler},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP,typeHandler=com.tuan800.mybatis.typehandler.JodaDateTime2TimestampTypeHandler}",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TbQuartzTask record);
}