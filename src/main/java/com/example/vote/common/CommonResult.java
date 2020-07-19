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
    private T data;

    public static <T> CommonResult<T> build(boolean success, int errCode, String msg) {
        return new CommonResult(success, errCode, msg, null);
    }

    public static <T> CommonResult<T> buildWithData(boolean success, int errCode, String msg, T data) {
        return new CommonResult(success, errCode, msg, data);
    }

    public CommonResult() {
    }


    public CommonResult(boolean success, int errCode, String msg, T data) {
        this.success = success;
        this.errCode = errCode;
        this.msg = msg;
        this.data = data;
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

    public T getData() {
        return this.data;
    }


    public void setData(T data) {
        this.data = data;
    }

    public boolean isNotNull() {
        if (ObjectUtils.isEmpty(this) || ObjectUtils.isEmpty(this.getData())) {
            return false;
        }

        if (this.getData() instanceof PageInfo) {
            if (ObjectUtils.isEmpty(((PageInfo) this.getData()).getList())) {
                return false;
            } else {
                if (ObjectUtils.isEmpty(this.getData())) {
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
                ", data=" + data +
                '}';
    }
}
