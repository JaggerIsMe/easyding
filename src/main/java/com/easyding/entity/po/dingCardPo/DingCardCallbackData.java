package com.easyding.entity.po.dingCardPo;

import java.io.Serializable;

public class DingCardCallbackData implements Serializable {

    private String type;
    private String outTrackId;
    private String corpId;
    private String userId;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutTrackId() {
        return outTrackId;
    }

    public void setOutTrackId(String outTrackId) {
        this.outTrackId = outTrackId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DingCardCallbackData{" +
                "type='" + type + '\'' +
                ", outTrackId='" + outTrackId + '\'' +
                ", corpId='" + corpId + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
