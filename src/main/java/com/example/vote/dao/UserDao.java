package com.example.vote.dao;

import com.example.vote.entity.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: UserDao
 * @Description: 用户数据持久类
 * @Date : 2020-07-19 12:42
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Mapper
@Repository
public interface UserDao {

    /**
     * 添加用户
     * @param userName
     * @param password
     * @param userUuid
     */
    @Insert({"insert into user_info (user_name, password, user_uuid) values (#{userName}, #{password}, #{userUuid})"})
    @Results(id="user1", value= {
                    @Result(column = "user_name", property = "userName"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "user_uuid", property = "userUuid")
            }
    )
    public void addUser(@Param("userName") String userName, @Param("password") String password, @Param("userUuid") String userUuid);

    /**
     * 根据用户姓名查询用户信息
     * @param userName
     * @return
     */
    @Select({"select user_uuid, user_name, password from user_info where user_name = #{userName}"})
    @Results(id="user2", value= {
            @Result(column = "user_name", property = "userName"),
            @Result(column = "password", property = "password"),
            @Result(column = "user_uuid", property = "userUuid")
    }
    )
    public UserInfo getUserInfo(@Param("userName") String userName);

    /**
     * 获取所有的用户信息
     * @return
     */
    @Select({"select user_uuid, user_name, password from user_info"})
    @Results(id="user3", value= {
            @Result(column = "user_name", property = "userName"),
            @Result(column = "password", property = "password"),
            @Result(column = "user_uuid", property = "userUuid")
    }
    )
    public List<UserInfo> getAllUserInfo();

    @Select({"select user_name from user_info where user_uuid = #{userUuid}"})
    @Results(id="user4", value= {
            @Result(column = "user_name", property = "userName"),
            @Result(column = "password", property = "password"),
            @Result(column = "user_uuid", property = "userUuid")
    }
    )
    public String getUserName(@Param("userUuid") String userUuid);

}
