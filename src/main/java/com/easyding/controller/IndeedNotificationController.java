package com.easyding.controller;

import com.easyding.entity.vo.ResponseVO;
import com.easyding.service.IndeedNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController("indeedNotificationController")
@RequestMapping("/indeedNotification")
public class IndeedNotificationController extends ABaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndeedNotificationController.class);

    @Resource
    private IndeedNotificationService indeedNotificationService;

    @RequestMapping("/jobExecuteResult")
    public ResponseVO jobExecuteResult(@RequestHeader("openConversationID") String openConversationID) {
        indeedNotificationService.sendJobExecuteResultNotification(openConversationID);
        return getSuccessResponseVO(null);
    }

}