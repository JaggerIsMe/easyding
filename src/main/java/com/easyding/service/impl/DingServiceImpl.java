package com.easyding.service.impl;

import com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest;
import com.aliyun.dingtalknotable_1_0.models.InsertRecordsRequest;
import com.aliyun.dingtalknotable_1_0.models.ListRecordsResponse;
import com.aliyun.dingtalknotable_1_0.models.ListRecordsResponseBody;
import com.aliyun.dingtalknotable_1_0.models.UpdateRecordsRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenResponse;
import com.aliyun.tea.TeaConverter;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaPair;
import com.aliyun.teautil.models.RuntimeOptions;
import com.easyding.config.AppConfig;
import com.easyding.entity.enums.DateTimePatternEnum;
import com.easyding.entity.po.dingCardPo.ContentData;
import com.easyding.entity.po.dingCardPo.DingCardCallbackData;
import com.easyding.entity.po.dingMsgPo.AtRobotMessage;
import com.easyding.service.AtRobotMessageService;
import com.easyding.service.DingService;
import com.easyding.utils.DateUtil;
import com.easyding.utils.StringTools;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("dingService")
public class DingServiceImpl implements DingService {

    private static final Logger logger = LoggerFactory.getLogger(DingServiceImpl.class);

    @Resource
    private AppConfig appConfig;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private AtRobotMessageService atRobotMessageService;

    /**
     * 使用 Token 初始化账号Client
     *
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dingtalkcard_1_0.Client createCardClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkcard_1_0.Client(config);
    }

    public static com.aliyun.dingtalkoauth2_1_0.Client createTokenClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }

    public static com.aliyun.dingtalknotable_1_0.Client createNoTableClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalknotable_1_0.Client(config);
    }

    private static String generateOutTrackId(String sendTo) {
        LocalDateTime sendTime = LocalDateTime.now();
        Date sendTimeDate = Date.from(sendTime.atZone(ZoneId.systemDefault()).toInstant());
        return String.format("%s-%s", DateUtil.format(sendTimeDate, DateTimePatternEnum.YYYYMMDDHHMMSS.getPattern()), sendTo);
    }

    /**
     * 获取accessToken
     */
    @Override
    public String getAccessToken() {

        try {
            com.aliyun.dingtalkoauth2_1_0.Client client = DingServiceImpl.createTokenClient();

            com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest getAccessTokenRequest = new com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest()
                    .setAppKey(appConfig.getDingAppKey())
                    .setAppSecret(appConfig.getDingAppSecret());
            GetAccessTokenResponse accessTokenResponse = client.getAccessToken(getAccessTokenRequest);
            return accessTokenResponse.getBody().getAccessToken();

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }

        }
        return null;

    }

    /**
     * 发送卡片(群聊)
     *
     * @param openConversationId
     * @param cardTemplateId
     * @param cardData
     */
    @Override
    public void sendCard2Group(String openConversationId, String cardTemplateId, CreateAndDeliverRequest.CreateAndDeliverRequestCardData cardData) {

        try {

            com.aliyun.dingtalkcard_1_0.Client client = DingServiceImpl.createCardClient();
            com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverHeaders createAndDeliverHeaders = new com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverHeaders();
            createAndDeliverHeaders.xAcsDingtalkAccessToken = this.getAccessToken();
//            java.util.Map<String, String> imGroupOpenDeliverModelAtUserIds = TeaConverter.buildMap(
//                    new TeaPair("key", "example_user_name")
//            );
//            java.util.Map<String, String> imGroupOpenDeliverModelExtension = TeaConverter.buildMap(
//                    new TeaPair("key", "example_ext_value")
//            );
            com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImGroupOpenDeliverModel imGroupOpenDeliverModel = new com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImGroupOpenDeliverModel()
//                    .setAtUserIds(imGroupOpenDeliverModelAtUserIds)
//                    .setRecipients(java.util.Arrays.asList(
//                            "example_user_id"
//                    ))
//                    .setExtension(imGroupOpenDeliverModelExtension)
                    .setRobotCode(appConfig.getDingRobotCode());

            java.util.Map<String, String> imGroupOpenSpaceModelLastMessageI18n = TeaConverter.buildMap(
                    new TeaPair("ZH_CN", "RPA机器人卡片消息")
            );
            com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImGroupOpenSpaceModelNotification imGroupOpenSpaceModelNotification = new com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImGroupOpenSpaceModelNotification()
                    .setAlertContent("喵~你收到了一条RPA机器人卡片消息")
                    .setNotificationOff(false);
            com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImGroupOpenSpaceModel imGroupOpenSpaceModel = new com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImGroupOpenSpaceModel()
                    .setSupportForward(false)
                    .setLastMessageI18n(imGroupOpenSpaceModelLastMessageI18n)
                    .setNotification(imGroupOpenSpaceModelNotification);

            com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest createAndDeliverRequest = new com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest()
                    .setCardTemplateId(cardTemplateId)
                    .setOutTrackId(DingServiceImpl.generateOutTrackId(openConversationId))
                    .setCallbackType("HTTP")
                    .setCallbackRouteKey(appConfig.getDingCardCallbackRouteKey())
                    .setCardData(cardData)
//                    .setUserIdType(1)
//                    .setCardAtUserIds(java.util.Arrays.asList())
//                    .setPrivateData(privateData)
                    .setImGroupOpenSpaceModel(imGroupOpenSpaceModel)
                    .setOpenSpaceId(String.format("dtv1.card//im_group.%s", openConversationId))
                    .setImGroupOpenDeliverModel(imGroupOpenDeliverModel);

            client.createAndDeliverWithOptions(createAndDeliverRequest, createAndDeliverHeaders, new com.aliyun.teautil.models.RuntimeOptions());

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
        }
    }

    /**
     * 发送卡片(私聊用户)
     *
     * @param userId
     * @param cardTemplateId
     * @param cardData
     */
    @Override
    public void sendCard2User(String userId, String cardTemplateId, CreateAndDeliverRequest.CreateAndDeliverRequestCardData cardData) {

        try {

            com.aliyun.dingtalkcard_1_0.Client client = DingServiceImpl.createCardClient();
            com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverHeaders createAndDeliverHeaders = new com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverHeaders();
            createAndDeliverHeaders.xAcsDingtalkAccessToken = this.getAccessToken();
//            java.util.Map<String, String> imRobotOpenDeliverModelExtension = TeaConverter.buildMap(
//                    new TeaPair("key", "example_ext_value")
//            );
            com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImRobotOpenDeliverModel imRobotOpenDeliverModel = new com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImRobotOpenDeliverModel()
                    .setSpaceType("IM_ROBOT")
//                    .setExtension(imRobotOpenDeliverModelExtension)
                    .setRobotCode(appConfig.getDingRobotCode());

            java.util.Map<String, String> imRobotOpenSpaceModelLastMessageI18n = TeaConverter.buildMap(
                    new TeaPair("ZH_CN", "RPA机器人卡片消息")
            );
            com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImRobotOpenSpaceModelNotification imRobotOpenSpaceModelNotification = new com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImRobotOpenSpaceModelNotification()
                    .setAlertContent("喵~你收到了一条RPA机器人卡片消息")
                    .setNotificationOff(false);
            com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImRobotOpenSpaceModel imRobotOpenSpaceModel = new com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest.CreateAndDeliverRequestImRobotOpenSpaceModel()
                    .setSupportForward(false)
                    .setLastMessageI18n(imRobotOpenSpaceModelLastMessageI18n)
                    .setNotification(imRobotOpenSpaceModelNotification);

            com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest createAndDeliverRequest = new com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest()
                    .setCardTemplateId(cardTemplateId)
                    .setOutTrackId(DingServiceImpl.generateOutTrackId(userId))
                    .setCallbackType("HTTP")
                    .setCallbackRouteKey(appConfig.getDingCardCallbackRouteKey())
                    .setCardData(cardData)
//                    .setPrivateData(privateData)
                    .setImRobotOpenSpaceModel(imRobotOpenSpaceModel)
                    .setOpenSpaceId(String.format("dtv1.card//im_robot.%s", userId))
                    .setImRobotOpenDeliverModel(imRobotOpenDeliverModel);

            client.createAndDeliverWithOptions(createAndDeliverRequest, createAndDeliverHeaders, new com.aliyun.teautil.models.RuntimeOptions());

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
        }

    }

    /**
     * 更新卡片
     *
     * @param dingCardCallbackData
     */
    @Override
    public void updateCard(DingCardCallbackData dingCardCallbackData) {
        /**
         * dingCardCallbackData示例：
         * {
         *     "type": "actionCallback",
         *     "outTrackId": "202601210021",
         *     "corpId": "dingf9e2225096a9207535c2f4657eb6378f",
         *     "userId": "01054707111933827299",
         *     "content": "{\"cardPrivateData\":{\"actionIds\":[\"agree\"],\"params\":{\"jobUUID\":\"44fbf32eb6228ec0c603a92c73193329\",\"jobParams\":\"{'inputParam':{'input':'深圳天气'}}\",\"title\":\"服务器已处理回调请求\",\"status\":\"agree\"}}}"
         * }
         */
        try {
            com.aliyun.dingtalkcard_1_0.Client client = DingServiceImpl.createCardClient();
            com.aliyun.dingtalkcard_1_0.models.UpdateCardHeaders updateCardHeaders = new com.aliyun.dingtalkcard_1_0.models.UpdateCardHeaders();
            updateCardHeaders.xAcsDingtalkAccessToken = getAccessToken();
            com.aliyun.dingtalkcard_1_0.models.UpdateCardRequest.UpdateCardRequestCardUpdateOptions cardUpdateOptions = new com.aliyun.dingtalkcard_1_0.models.UpdateCardRequest.UpdateCardRequestCardUpdateOptions()
                    .setUpdateCardDataByKey(true)
                    .setUpdatePrivateDataByKey(false);
//            java.util.Map<String, String> privateDataValueKeyCardParamMap = TeaConverter.buildMap(
//                    new TeaPair("key", "example-value")
//            );
//            com.aliyun.dingtalkcard_1_0.models.PrivateDataValue privateDataValueKey = new com.aliyun.dingtalkcard_1_0.models.PrivateDataValue()
//                    .setCardParamMap(privateDataValueKeyCardParamMap);
//            java.util.Map<String, com.aliyun.dingtalkcard_1_0.models.PrivateDataValue> privateData = TeaConverter.buildMap(
//                    new TeaPair("privateDataValueKey", privateDataValueKey)
//            );
            ContentData contentData = objectMapper.readValue(
                    dingCardCallbackData.getContent(),
                    ContentData.class
            );
            Map<String, String> params = contentData.getCardPrivateData().getParams();
            List<TeaPair> teaPairsList = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                teaPairsList.add(new TeaPair(entry.getKey(), entry.getValue()));
            }
            TeaPair[] teaPairsArray = teaPairsList.stream().toArray(TeaPair[]::new);
            java.util.Map<String, String> cardDataCardParamMap = TeaConverter.buildMap(
                    teaPairsArray
            );
            com.aliyun.dingtalkcard_1_0.models.UpdateCardRequest.UpdateCardRequestCardData cardData = new com.aliyun.dingtalkcard_1_0.models.UpdateCardRequest.UpdateCardRequestCardData()
                    .setCardParamMap(cardDataCardParamMap);
            com.aliyun.dingtalkcard_1_0.models.UpdateCardRequest updateCardRequest = new com.aliyun.dingtalkcard_1_0.models.UpdateCardRequest()
                    .setOutTrackId(dingCardCallbackData.getOutTrackId()) // 设置outTrackID
                    .setCardData(cardData)
//                    .setPrivateData(privateData)
                    .setCardUpdateOptions(cardUpdateOptions)
                    .setUserIdType(1);
            // 更新卡片
            client.updateCardWithOptions(updateCardRequest, updateCardHeaders, new com.aliyun.teautil.models.RuntimeOptions());

            // 执行流程
            // 判断是否有回传JobUUID，有则调用实在API执行任务
            if (params != null && params.containsKey("jobUUID")) {
                String jobUUID = params.get("jobUUID");
                String jobParamsStr = params.get("jobParams");
                if (jobParamsStr == null) {
                    jobParamsStr = "{'inputParam':{}}";
                }
//                commandJobV1(jobUUID, jobParamsStr);
                commandJobV2(jobUUID, jobParamsStr);
            }

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
        }
    }

    /**
     * 触发实在RPA任务_V1
     *
     * @param jobUUID
     * @param jobParamsStr
     */
    @Override
    public void commandJobV1(String jobUUID, String jobParamsStr) {
        // 1. 创建 OkHttpClient 实例
        OkHttpClient client = new OkHttpClient();

        // 2. 构建请求体
        RequestBody body = RequestBody.create(jobParamsStr, MediaType.parse("application/json; charset=utf-8"));

        // 3. 构建请求
        Request request = new Request.Builder()
                .url(String.format("%s/v1/job/%s/1", appConfig.getIndeedApiUrl(), jobUUID))
                .put(body)  // PUT 方法
                .addHeader("appKey", appConfig.getIndeedAppKey())
                .addHeader("appSecret", appConfig.getIndeedAppSecret())
                .build();

        // 4. 发送请求并处理响应
        try {
            Response response = client.newCall(request).execute();
            response.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 触发实在RPA任务_V2
     *
     * @param jobUUID
     * @param jobParamsStr
     */
    @Override
    public void commandJobV2(String jobUUID, String jobParamsStr) {
        // 1. 创建 OkHttpClient 实例
        OkHttpClient client = new OkHttpClient();

        // 2. 构建请求体
        String bodyStr = "{\"jobUuid\":\"" + jobUUID + "\",\"operation\":1," + StringTools.removeFirstAndLastChar(jobParamsStr) + "}";
        RequestBody body = RequestBody.create(bodyStr, MediaType.parse("application/json; charset=utf-8"));

        // 3. 构建请求
        Request request = new Request.Builder()
                .url(String.format("%s/v1/job/%s/1", appConfig.getIndeedApiUrl(), jobUUID))
                .put(body)  // PUT 方法
                .addHeader("appKey", appConfig.getIndeedAppKey())
                .addHeader("appSecret", appConfig.getIndeedAppSecret())
                .build();

        // 4. 发送请求并处理响应
        try {
            Response response = client.newCall(request).execute();
            response.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 添加表格数据
     *
     * @param baseId
     * @param sheetId
     */
    @Override
    public void addRecords(String baseId, String sheetId, List<InsertRecordsRequest.InsertRecordsRequestRecords> records) {

        try {
            com.aliyun.dingtalknotable_1_0.Client client = DingServiceImpl.createNoTableClient();
            com.aliyun.dingtalknotable_1_0.models.InsertRecordsHeaders insertRecordsHeaders = new com.aliyun.dingtalknotable_1_0.models.InsertRecordsHeaders();
            insertRecordsHeaders.xAcsDingtalkAccessToken = getAccessToken();
            com.aliyun.dingtalknotable_1_0.models.InsertRecordsRequest insertRecordsRequest = new com.aliyun.dingtalknotable_1_0.models.InsertRecordsRequest()
                    .setOperatorId(appConfig.getDingUnionId())
                    .setRecords(records);
            client.insertRecordsWithOptions(baseId, sheetId, insertRecordsRequest, insertRecordsHeaders, new com.aliyun.teautil.models.RuntimeOptions());

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
        }
    }

    /**
     * 获取钉钉表格数据
     * 获取所有记录列表
     *
     * @param baseId
     * @param sheetId
     * @return
     */
    @Override
    public List<ListRecordsResponseBody.ListRecordsResponseBodyRecords> listAllRecords(String baseId, String sheetId) {

        try {
            com.aliyun.dingtalknotable_1_0.Client client = DingServiceImpl.createNoTableClient();
            com.aliyun.dingtalknotable_1_0.models.ListRecordsHeaders listRecordsHeaders = new com.aliyun.dingtalknotable_1_0.models.ListRecordsHeaders();
            listRecordsHeaders.xAcsDingtalkAccessToken = getAccessToken();
            com.aliyun.dingtalknotable_1_0.models.ListRecordsRequest listRecordsRequest = new com.aliyun.dingtalknotable_1_0.models.ListRecordsRequest()
                    .setOperatorId(appConfig.getDingUnionId())
                    .setMaxResults(100)
                    .setNextToken(null);

            ListRecordsResponse listRecordsResponse = client.listRecordsWithOptions(baseId, sheetId, listRecordsRequest, listRecordsHeaders, new RuntimeOptions());

            List<ListRecordsResponseBody.ListRecordsResponseBodyRecords> records = listRecordsResponse.body.records;

            Boolean hasMore = listRecordsResponse.body.hasMore;
            String nextToken = listRecordsResponse.body.nextToken;
            // 添加最大循环次数限制，防止无限循环
            int maxIterations = 500;
            int currentIteration = 0;
            while (hasMore != null && hasMore && !StringTools.isEmpty(nextToken)) {
                // 防止无限循环的安全检查
                if (currentIteration >= maxIterations) {
                    break;
                }
                listRecordsRequest.setNextToken(nextToken);
                ListRecordsResponse listRestRecordsResponse = client.listRecordsWithOptions(baseId, sheetId, listRecordsRequest, listRecordsHeaders, new RuntimeOptions());
                if (listRestRecordsResponse != null && listRestRecordsResponse.body != null) {
                    if (listRestRecordsResponse.body.records != null) {
                        records.addAll(listRestRecordsResponse.body.records);
                    }
                    hasMore = listRestRecordsResponse.body.hasMore;
                    nextToken = listRestRecordsResponse.body.nextToken;
                } else {
                    // 如果响应为空，跳出循环
                    break;
                }
                currentIteration++;
            }
            return records;

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
            return null;
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
            return null;
        }
    }

    /**
     * 更新表格数据
     *
     * @param baseId
     * @param sheetId
     * @param records0
     */
    @Override
    public void updateRecord(String baseId, String sheetId, UpdateRecordsRequest.UpdateRecordsRequestRecords records0) {

        try {
            com.aliyun.dingtalknotable_1_0.Client client = DingServiceImpl.createNoTableClient();
            com.aliyun.dingtalknotable_1_0.models.UpdateRecordsHeaders updateRecordsHeaders = new com.aliyun.dingtalknotable_1_0.models.UpdateRecordsHeaders();
            updateRecordsHeaders.xAcsDingtalkAccessToken = getAccessToken();
//            Map<String, Map<String, Object>> records0Fields = TeaConverter.buildMap(
//                    new TeaPair("key", new HashMap<>())
//            );
//            com.aliyun.dingtalknotable_1_0.models.UpdateRecordsRequest.UpdateRecordsRequestRecords records0 = new com.aliyun.dingtalknotable_1_0.models.UpdateRecordsRequest.UpdateRecordsRequestRecords()
//                    .setId(recordId)
//                    .setFields((Map<String, Map<String, Object>>) records0Fields);
            com.aliyun.dingtalknotable_1_0.models.UpdateRecordsRequest updateRecordsRequest = new com.aliyun.dingtalknotable_1_0.models.UpdateRecordsRequest()
                    .setOperatorId(appConfig.getDingUnionId())
                    .setRecords(java.util.Arrays.asList(
                            records0
                    ));
            client.updateRecordsWithOptions(baseId, sheetId, updateRecordsRequest, updateRecordsHeaders, new com.aliyun.teautil.models.RuntimeOptions());

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                logger.error(err.message);
            }
        }
    }

    /**
     * 保存钉钉群@机器人消息
     *
     * @param timestamp
     * @param sign
     * @param requestBody
     */
    @Override
    public void saveAtRobotMessage(String timestamp, String sign, Map<String, Object> requestBody) {
        try {
            Long timestampLong = Long.parseLong(timestamp);
            String appSecret = appConfig.getDingAppSecret();
            String stringToSign = timestampLong + "\n" + appSecret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String signReal = new String(Base64.encodeBase64(signData));

            if (sign.equals(signReal)) {
                String msgId = (String) requestBody.get("msgId");
                String senderStaffId = (String) requestBody.get("senderStaffId");
                String conversationId = (String) requestBody.get("conversationId");
                String robotCode = (String) requestBody.get("robotCode");
                String chatbotUserId = (String) requestBody.get("chatbotUserId");
                String senderId = (String) requestBody.get("senderId");
                Integer conversationType = Integer.valueOf(requestBody.get("conversationType").toString());
                String conversationTitle = requestBody.get("conversationTitle") == null ? null : (String) requestBody.get("conversationTitle");
                Long createAt = Long.parseLong(requestBody.get("createAt").toString());
                String msgType = (String) requestBody.get("msgtype");

                String msgJson = objectMapper.writeValueAsString(requestBody);

                List<Map<String, Object>> atUsers = requestBody.get("atUsers") == null ? new ArrayList<Map<String, Object>>() : (List<Map<String, Object>>) requestBody.get("atUsers");
                if (atUsers != null && !atUsers.isEmpty()) {
                    atUsers.forEach(atUser -> {
                        String atDingtalkId = (String) atUser.get("dingtalkId");
                        String atStaffId = atUser.get("staffId") == null ? null : (String) atUser.get("staffId");
                        String atUnionId = atUser.get("unionId") == null ? null : (String) atUser.get("unionId");

                        AtRobotMessage atRobotMessage = new AtRobotMessage();
                        atRobotMessage.setMsgId(msgId);
                        atRobotMessage.setSenderStaffId(senderStaffId);
                        atRobotMessage.setConversationId(conversationId);
                        atRobotMessage.setRobotCode(robotCode);
                        atRobotMessage.setChatbotUserId(chatbotUserId);
                        atRobotMessage.setSenderId(senderId);
                        atRobotMessage.setConversationType(conversationType);
                        atRobotMessage.setConversationTitle(conversationTitle);
                        atRobotMessage.setCreateAt(createAt);
                        atRobotMessage.setMsgType(msgType);
                        atRobotMessage.setConsumeFlag(0);

                        atRobotMessage.setAtDingtalkId(atDingtalkId);
                        atRobotMessage.setAtStaffId(atStaffId);
                        atRobotMessage.setAtUnionId(atUnionId);

                        atRobotMessage.setMsgJson(msgJson);

                        atRobotMessageService.add(atRobotMessage);
                    });
                } else {
                    AtRobotMessage atRobotMessage = new AtRobotMessage();
                    atRobotMessage.setMsgId(msgId);
                    atRobotMessage.setSenderStaffId(senderStaffId);
                    atRobotMessage.setConversationId(conversationId);
                    atRobotMessage.setRobotCode(robotCode);
                    atRobotMessage.setChatbotUserId(chatbotUserId);
                    atRobotMessage.setSenderId(senderId);
                    atRobotMessage.setConversationType(conversationType);
                    atRobotMessage.setConversationTitle(conversationTitle);
                    atRobotMessage.setCreateAt(createAt);
                    atRobotMessage.setMsgType(msgType);
                    atRobotMessage.setConsumeFlag(0);

                    // 将机器人的 加密机器人ID(chatbotUserId)设置成加密的被@用户的id，当两个ID相同时，则说明被@的是机器人自己或是人机私聊消息
                    atRobotMessage.setAtDingtalkId(chatbotUserId);

                    atRobotMessage.setMsgJson(msgJson);

                    atRobotMessageService.add(atRobotMessage);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
