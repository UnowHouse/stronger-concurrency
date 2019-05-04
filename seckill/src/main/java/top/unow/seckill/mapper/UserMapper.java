package top.unow.seckill.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.unow.seckill.bean.User;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.mapper
 *  @文件名:   UserMapper
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-04 21:06
 *  @描述：    TODO
 */
@Mapper
public interface UserMapper {

    @Select("select * from sk_user where id = #{id}")
    public User getById(@Param("id") long id);

    @Update("update sk_user set password = #{password} where id = #{id}")
    public void update(User toBeUpdate);
}

