package com.easyding.controller;

import com.easyding.entity.vo.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("indeedNotificationController")
@RequestMapping("/indeedNotification")
public class IndeedNotificationController extends ABaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndeedNotificationController.class);

    @RequestMapping("/jobExecuteResult")
    public ResponseVO jobExecuteResult(@RequestHeader("openConversationID") String openConversationID) {
        logger.info("openConversationID : " + openConversationID);
        return getSuccessResponseVO(null);
    }

}