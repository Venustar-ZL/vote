package com.example.vote.dao;

import com.example.vote.entity.OptionsInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: OptionDao
 * @Description: 选项数据持久类
 * @Date : 2020-07-23 13:50
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Repository
@Mapper
public interface OptionsDao {

    @Insert("insert into options_info (options_uuid, options_description, options_num, vote_uuid) values (#{optionsUuid}, #{optionsDescription}, #{optionsNum}, #{voteUuid})")
    @Results( id = "options1", value =
            {
                    @Result(column = "options_uuid", property = "optionsUuid"),
                    @Result(column = "options_description", property = "optionsDescription"),
                    @Result(column = "options_num", property = "optionsNum"),
                    @Result(column = "vote_uuid", property = "voteUuid")
            }
    )
    public void addOption(@Param("optionsUuid") String optionsUuid, @Param("optionsDescription") String optionsDescription, @Param("optionsNum") Integer optionsNum, @Param("voteUuid") String voteUuid);

    @Select("select options_uuid, options_description, options_num from options_info where vote_uuid = #{voteUuid}")
    @Results( id = "options2", value =
            {
                    @Result(column = "options_uuid", property = "optionsUuid"),
                    @Result(column = "options_description", property = "optionsDescription"),
                    @Result(column = "options_num", property = "optionsNum"),
                    @Result(column = "vote_uuid", property = "voteUuid")
            }
    )
    public List<OptionsInfo> getOptions(@Param("voteUuid") String voteUuid);

    @Delete("delete from options_info where vote_uuid = #{voteUuid}")
    @Results( id = "options3", value =
            {
                    @Result(column = "options_uuid", property = "optionsUuid"),
                    @Result(column = "options_description", property = "optionsDescription"),
                    @Result(column = "options_num", property = "optionsNum"),
                    @Result(column = "vote_uuid", property = "voteUuid")
            }
    )
    public void deleteOptions(@Param("voteUuid") String voteUuid);

    @Update("update options_info set options_num = options_num + 1 where options_uuid = #{optionsUuid}")
    @Results( id = "options4", value =
            {
                    @Result(column = "options_uuid", property = "optionsUuid"),
                    @Result(column = "options_description", property = "optionsDescription"),
                    @Result(column = "options_num", property = "optionsNum"),
                    @Result(column = "vote_uuid", property = "voteUuid")
            }
    )
    public void updateOptionsById(@Param("optionsUuid") String optionsUuid);

    @Update("update options_info set options_description = #{optionsDescription} where options_uuid = #{optionsUuid}")
    public void updateOptionsByDescription(@Param("optionsUuid") String optionsUuid, @Param("optionsDescription") String optionsDescription);
}
