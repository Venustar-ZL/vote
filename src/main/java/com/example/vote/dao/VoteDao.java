package com.example.vote.dao;

import com.example.vote.entity.VoteInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: VoteDao
 * @Description: 投票数据持久类
 * @Date : 2020-07-22 16:37
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Mapper
@Repository
public interface VoteDao {

    /**
     * 获取该用户的投票列表
     * @param userUuid
     * @return
     */
    @Select("select vote_uuid, vote_title, vote_description, deadline, is_anonymous, vote_type, user_uuid, vote_num from vote_info where user_uuid = #{userUuid}")
    @Results( id = "vote1", value =
            {
                    @Result(column = "vote_uuid", property = "voteUuid"),
                    @Result(column = "vote_title", property = "voteTitle"),
                    @Result(column = "vote_description", property = "voteDescription"),
                    @Result(column = "deadline", property = "deadline"),
                    @Result(column = "is_anonymous", property = "isAnonymous"),
                    @Result(column = "vote_type", property = "voteType"),
                    @Result(column = "user_uuid", property = "userUuid"),
                    @Result(column = "vote_num", property = "voteNum")
            }
    )
    public List<VoteInfo> getAllVotes(@Param("userUuid") String userUuid);

    @Select("select vote_uuid, vote_title, vote_description, deadline, is_anonymous, vote_type, user_uuid, vote_num, user_name from vote_info")
    @Results( id = "vote3", value =
            {
                    @Result(column = "vote_uuid", property = "voteUuid"),
                    @Result(column = "vote_title", property = "voteTitle"),
                    @Result(column = "vote_description", property = "voteDescription"),
                    @Result(column = "deadline", property = "deadline"),
                    @Result(column = "is_anonymous", property = "isAnonymous"),
                    @Result(column = "vote_type", property = "voteType"),
                    @Result(column = "user_uuid", property = "userUuid"),
                    @Result(column = "vote_num", property = "voteNum"),
                    @Result(column = "user_name", property = "userName")
            }
    )
    public List<VoteInfo> getAllVotesWithoutId();

    /**
     * 添加投票
     * @param voteUuid
     * @param voteTitle
     * @param voteDescription
     * @param deadline
     * @param isAnonymous
     * @param voteType
     * @param userUuid
     * @param voteNum
     */
    @Insert("insert into vote_info (vote_uuid, vote_title, vote_description, deadline, is_anonymous, vote_type, user_uuid, vote_num, user_name) values (#{voteUuid}, #{voteTitle}, #{voteDescription}, #{deadline}, #{isAnonymous}, #{voteType}, #{userUuid}, #{voteNum}, #{userName})")
    @Results( id = "vote2", value =
            {
                    @Result(column = "vote_uuid", property = "voteUuid"),
                    @Result(column = "vote_title", property = "voteTitle"),
                    @Result(column = "vote_description", property = "voteDescription"),
                    @Result(column = "deadline", property = "deadline"),
                    @Result(column = "is_anonymous", property = "isAnonymous"),
                    @Result(column = "vote_type", property = "voteType"),
                    @Result(column = "user_uuid", property = "userUuid"),
                    @Result(column = "vote_num", property = "voteNum"),
                    @Result(column = "user_name", property = "userName")
            }
    )
    public void insertVote(@Param("voteUuid") String voteUuid, @Param("voteTitle") String voteTitle, @Param("voteDescription") String voteDescription, @Param("deadline") String deadline, @Param("isAnonymous") Integer isAnonymous, @Param("voteType") Integer voteType, @Param("userUuid") String userUuid, @Param("voteNum") Integer voteNum, @Param("userName") String userName);

    /**
     * 选择该投票事件对应的选项
     * @param voteUuid
     * @return
     */
    @Select("select vote_uuid, vote_title, vote_description, deadline, is_anonymous, vote_type, user_uuid, vote_num from vote_info where vote_uuid = #{voteUuid}")
    @Results( id = "vote4", value =
            {
                    @Result(column = "vote_uuid", property = "voteUuid"),
                    @Result(column = "vote_title", property = "voteTitle"),
                    @Result(column = "vote_description", property = "voteDescription"),
                    @Result(column = "deadline", property = "deadline"),
                    @Result(column = "is_anonymous", property = "isAnonymous"),
                    @Result(column = "vote_type", property = "voteType"),
                    @Result(column = "user_uuid", property = "userUuid"),
                    @Result(column = "vote_num", property = "voteNum")
            }
    )
    public VoteInfo getVoteInfo(@Param("voteUuid") String voteUuid);

    @Delete("delete from vote_info where vote_uuid = #{voteUuid}")
    @Results( id = "vote5", value =
            {
                    @Result(column = "vote_uuid", property = "voteUuid"),
                    @Result(column = "vote_title", property = "voteTitle"),
                    @Result(column = "vote_description", property = "voteDescription"),
                    @Result(column = "deadline", property = "deadline"),
                    @Result(column = "is_anonymous", property = "isAnonymous"),
                    @Result(column = "vote_type", property = "voteType"),
                    @Result(column = "user_uuid", property = "userUuid"),
                    @Result(column = "vote_num", property = "voteNum")
            }
    )
    public void deleteVOte(@Param("voteUuid") String voteUuid);

    @Update("update vote_info set vote_num = vote_num + 1 where vote_uuid = #{voteUuid}")
    @Results( id = "vote6", value =
            {
                    @Result(column = "vote_uuid", property = "voteUuid"),
                    @Result(column = "vote_title", property = "voteTitle"),
                    @Result(column = "vote_description", property = "voteDescription"),
                    @Result(column = "deadline", property = "deadline"),
                    @Result(column = "is_anonymous", property = "isAnonymous"),
                    @Result(column = "vote_type", property = "voteType"),
                    @Result(column = "user_uuid", property = "userUuid"),
                    @Result(column = "vote_num", property = "voteNum")
            }
    )
    public void addNum(@Param("voteUuid") String voteUuid);

    @Update("update vote_info set vote_title = #{voteTitle}, vote_description = #{voteDescription} where vote_uuid = #{voteUuid}")
    @Results( id = "vote7", value =
            {
                    @Result(column = "vote_uuid", property = "voteUuid"),
                    @Result(column = "vote_title", property = "voteTitle"),
                    @Result(column = "vote_description", property = "voteDescription"),
                    @Result(column = "deadline", property = "deadline"),
                    @Result(column = "is_anonymous", property = "isAnonymous"),
                    @Result(column = "vote_type", property = "voteType"),
                    @Result(column = "user_uuid", property = "userUuid"),
                    @Result(column = "vote_num", property = "voteNum")
            }
    )
    public void updateVote(@Param("voteUuid") String voteUuid, @Param("voteTitle") String voteTitle, @Param("voteDescription") String voteDescription);
}
