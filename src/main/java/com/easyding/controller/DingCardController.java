package com.easyding.controller;

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