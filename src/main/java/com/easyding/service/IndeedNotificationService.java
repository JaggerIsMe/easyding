package com.easyding.service;

public interface IndeedNotificationService {

    /**
     * 发送任务执行结果通知
     *
     * @param openConversationID
     */
    void sendJobExecuteResultNotification(String openConversationID);

}
