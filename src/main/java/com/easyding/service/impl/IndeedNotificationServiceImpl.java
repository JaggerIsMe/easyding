package com.easyding.service.impl;

import com.easyding.service.IndeedNotificationService;
import org.springframework.stereotype.Service;

@Service("indeedNotificationService")
public class IndeedNotificationServiceImpl implements IndeedNotificationService {

    /**
     * 发送任务执行结果通知
     *
     * @param openConversationID
     */
    @Override
    public void sendJobExecuteResultNotification(String openConversationID) {

    }
}
