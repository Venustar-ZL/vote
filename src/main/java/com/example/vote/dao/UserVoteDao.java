package com.example.vote.dao;

import com.example.vote.entity.VoteUuidVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @ClassName: UserVoteDao
 * @Description: 用户投票持久类
 * @Date : 2020-07-23 22:51
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Repository
@Mapper
public interface UserVoteDao {

    @Select("select vote_uuid from user_vote_info where user_uuid = #{userUuid}")
    @Results( id = "userVote1", value =
            {
                    @Result(column = "vote_uuid", property = "voteUuid"),
                    @Result(column = "user_uuid", property = "userUuid"),
                    @Result(column = "user_name", property = "userName")
            }
    )
    public List<String> getVoteUuids(@Param("userUuid") String userUuid);

    @Insert("insert into user_vote_info (user_uuid, vote_uuid, user_name) values (#{userUuid}, #{voteUuid}, #{userName})")
    @Results( id = "userVote2", value =
            {
                    @Result(column = "vote_uuid", property = "voteUuid"),
                    @Result(column = "user_uuid", property = "userUuid"),
                    @Result(column = "user_name", property = "userName")
            }
    )
    public void addVoteUuids(@Param("userUuid") String userUuid, @Param("voteUuid") String voteUuid, @Param("userName") String userName);

}
