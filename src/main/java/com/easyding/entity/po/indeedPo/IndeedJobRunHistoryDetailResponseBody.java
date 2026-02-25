package com.easyding.entity.po.indeedPo;

import java.io.Serializable;
import java.util.List;

public class IndeedJobRunHistoryDetailResponseBody implements Serializable {

    private Integer code;
    private String msg;
    private List<IndeedJobRunHistoryDetailResponseBodyData> data;

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

    public List<IndeedJobRunHistoryDetailResponseBodyData> getData() {
        return data;
    }

    public void setData(List<IndeedJobRunHistoryDetailResponseBodyData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IndeedJobRunHistoryDetailResponseBody{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
