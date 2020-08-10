package com.example.vote.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @ClassName: CommonResult
 * @Description: 通用返回值
 * @Date : 2020-07-19 15:55
 * @Author: ZhangLei
 * Version: 1.0
 **/
@ApiModel(description = "通用返回值")
@JsonPropertyOrder({"success", "errCode", "msg", "data"})
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = -1;
    @ApiModelProperty(value = "返回操作是否成功")
    private boolean success;
    @ApiModelProperty(value = "状态码")
    private int errCode;
    @ApiModelProperty(value = "提示信息")
    private String msg;
    @ApiModelProperty(value = "返回json数据体")
    private T res;

    public static <T> CommonResult<T> build(boolean success, int errCode, String msg) {
        return new CommonResult(success, errCode, msg, null);
    }

    public static <T> CommonResult<T> buildWithData(boolean success, int errCode, String msg, T res) {
        return new CommonResult(success, errCode, msg, res);
    }

    public CommonResult() {
    }


    public CommonResult(boolean success, int errCode, String msg, T res) {
        this.success = success;
        this.errCode = errCode;
        this.msg = msg;
        this.res = res;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return this.res;
    }


    public void setData(T result) {
        this.res = res;
    }

    public boolean isNotNull() {
        if (ObjectUtils.isEmpty(this) || ObjectUtils.isEmpty(this.getResult())) {
            return false;
        }

        if (this.getResult() instanceof PageInfo) {
            if (ObjectUtils.isEmpty(((PageInfo) this.getResult()).getList())) {
                return false;
            } else {
                if (ObjectUtils.isEmpty(this.getResult())) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "success=" + success +
                ", errCode=" + errCode +
                ", msg='" + msg + '\'' +
                ", res=" + res +
                '}';
    }
}
