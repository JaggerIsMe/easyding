package com.easyding.entity.po.dingCardPo;

import java.io.Serializable;

public class ContentData implements Serializable {
    private CardPrivateData cardPrivateData;

    public CardPrivateData getCardPrivateData() {
        return cardPrivateData;
    }

    public void setCardPrivateData(CardPrivateData cardPrivateData) {
        this.cardPrivateData = cardPrivateData;
    }

    @Override
    public String toString() {
        return "ContentData{" +
                "cardPrivateData=" + cardPrivateData +
                '}';
    }
}
