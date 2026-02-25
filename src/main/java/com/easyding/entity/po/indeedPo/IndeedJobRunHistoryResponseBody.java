package com.easyding.entity.po.indeedPo;

import java.io.Serializable;

public class IndeedJobRunHistoryResponseBody<T> implements Serializable {

    private Integer code;
    private String msg;
    private IndeedJobRunHistoryResponseBodyPageData<T> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public IndeedJobRunHistoryResponseBodyPageData<T> getData() {
        return data;
    }

    public void setData(IndeedJobRunHistoryResponseBodyPageData<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IndeedResponseBody{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
