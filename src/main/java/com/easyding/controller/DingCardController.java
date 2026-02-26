package com.easyding.controller;

import com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest;
import com.easyding.entity.po.dingCardPo.DingCardCallbackData;
import com.easyding.entity.vo.ResponseVO;
import com.easyding.service.DingService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;


@RestController("dingCardController")
@RequestMapping("/dingCard")
public class DingCardController extends ABaseController {

    @Resource
    private DingService dingService;

    /**
     * 获取accessToken
     */
    @RequestMapping("/getAccessToken")
    public ResponseVO getAccessToken() {
        return getSuccessResponseVO(dingService.getAccessToken());
    }

    /**
     * 发送卡片(群聊)
     *
     * @param openConversationId
     * @param cardTemplateId
     * @param cardData
     */
    @RequestMapping("/sendCard2Group")
    public ResponseVO sendCard2Group(@RequestHeader("openConversationId") String openConversationId, @RequestHeader("cardTemplateId") String cardTemplateId, @RequestBody CreateAndDeliverRequest.CreateAndDeliverRequestCardData cardData) {
        dingService.sendCard2Group(openConversationId, cardTemplateId, cardData);
        return getSuccessResponseVO(null);
    }

    /**
     * 发送卡片(私聊用户)
     *
     * @param userId
     * @param cardTemplateId
     * @param cardData
     */
    @RequestMapping("/sendCard2User")
    public ResponseVO sendCard2User(@RequestHeader("userId") String userId, @RequestHeader("cardTemplateId") String cardTemplateId, @RequestBody CreateAndDeliverRequest.CreateAndDeliverRequestCardData cardData) {
        dingService.sendCard2User(userId, cardTemplateId, cardData);
        return getSuccessResponseVO(null);
    }

    /**
     * 更新卡片
     *
     * @param dingCardCallbackData
     */
    @RequestMapping("/updateCard")
    public ResponseVO updateCard(@RequestBody DingCardCallbackData dingCardCallbackData) {
        dingService.updateCard(dingCardCallbackData);
        return getSuccessResponseVO(null);
    }

}