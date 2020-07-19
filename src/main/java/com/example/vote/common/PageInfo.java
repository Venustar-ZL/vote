package com.example.vote.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PageInfo
 * @Description: 分页信息
 * @Date : 2020-07-19 15:57
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "分页信息")
public class PageInfo<T> implements Serializable {

    @ApiModelProperty(value = "总数")
    private Integer total;

    @ApiModelProperty(value = "查询集合信息")
    private List<T> list;

    public static <T> PageInfo<T> buildWithData(Integer total, List<T> data) {
        return new PageInfo(total, data);
    }

}
