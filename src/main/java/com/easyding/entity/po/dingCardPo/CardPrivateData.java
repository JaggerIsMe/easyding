package com.easyding.entity.po.dingCardPo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CardPrivateData implements Serializable {

    private List<String> actionIds;
    private Map<String, String> params;

    public List<String> getActionIds() {
        return actionIds;
    }

    public void setActionIds(List<String> actionIds) {
        this.actionIds = actionIds;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "CardPrivateData{" +
                "actionIds=" + actionIds +
                ", params=" + params +
                '}';
    }
}
