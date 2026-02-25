package com.easyding.service;

import com.aliyun.dingtalknotable_1_0.models.InsertRecordsRequest;
import com.aliyun.dingtalknotable_1_0.models.ListRecordsResponseBody;
import com.aliyun.dingtalknotable_1_0.models.UpdateRecordsRequest;
import com.easyding.entity.po.dingCardPo.DingCardCallbackData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

public interface DingService {

    /**
     * 获取accessToken
     */
    String getAccessToken();

    /**
     * 更新卡片
     *
     * @param dingCardCallbackData
     */
    void updateCard(DingCardCallbackData dingCardCallbackData);

    /**
     * 触发实在RPA任务_V1
     *
     * @param jobUUID
     * @param jobParamsStr
     */
    void commandJobV1(String jobUUID, String jobParamsStr);

    /**
     * 触发实在RPA任务_V2
     *
     * @param jobUUID
     * @param jobParamsStr
     */
    void commandJobV2(String jobUUID, String jobParamsStr);

    /**
     * 添加表格数据
     *
     * @param baseId
     * @param sheetId
     */
    void addRecords(String baseId, String sheetId, List<InsertRecordsRequest.InsertRecordsRequestRecords> records);

    /**
     * 获取钉钉表格数据
     * 获取所有记录列表
     *
     * @param baseId
     * @param sheetId
     * @return
     */
    List<ListRecordsResponseBody.ListRecordsResponseBodyRecords> listAllRecords(String baseId, String sheetId);

    /**
     * 更新表格数据
     *
     * @param baseId
     * @param sheetId
     * @param records0
     */
    void updateRecord(String baseId, String sheetId, UpdateRecordsRequest.UpdateRecordsRequestRecords records0);

    /**
     * 保存钉钉群@机器人消息
     *
     * @param timestamp
     * @param sign
     * @param requestBody
     */
    void saveAtRobotMessage(String timestamp, String sign, Map<String, Object> requestBody);

}
