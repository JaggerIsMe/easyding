package com.easyding.service.impl;

import com.aliyun.dingtalkcard_1_0.models.CreateAndDeliverRequest;
import com.aliyun.dingtalknotable_1_0.models.ListRecordsRequest;
import com.aliyun.dingtalknotable_1_0.models.ListRecordsResponseBody;
import com.easyding.config.AppConfig;
import com.easyding.entity.enums.DateTimePatternEnum;
import com.easyding.entity.enums.IndeedJobRunResultNotificationFlagEnum;
import com.easyding.entity.enums.IndeedJobStatusEnum;
import com.easyding.entity.enums.PageSize;
import com.easyding.entity.po.indeedPo.IndeedJobRunHistoryDetailResponseBodyData;
import com.easyding.entity.po.indeedPo.IndeedJobRunHistoryResponseBody;
import com.easyding.entity.po.indeedPo.JobRunResultNotificationInfo;
import com.easyding.entity.query.JobRunResultNotificationInfoQuery;
import com.easyding.entity.query.SimplePage;
import com.easyding.entity.vo.PaginationResultVO;
import com.easyding.mappers.JobRunResultNotificationInfoMapper;
import com.easyding.service.DingService;
import com.easyding.service.JobRunHistoryInfoService;
import com.easyding.service.JobRunResultNotificationInfoService;
import com.easyding.utils.DateUtil;
import com.easyding.utils.StringTools;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


/**
 * 任务执行结果通知表 业务接口实现
 */
@Service("jobRunResultNotificationInfoService")
public class JobRunResultNotificationInfoServiceImpl implements JobRunResultNotificationInfoService {

    private static final Logger logger = LoggerFactory.getLogger(JobRunResultNotificationInfoServiceImpl.class);

    @Resource
    private JobRunResultNotificationInfoMapper<JobRunResultNotificationInfo, JobRunResultNotificationInfoQuery> jobRunResultNotificationInfoMapper;

    @Resource
    private DingService dingService;

    @Resource
    private AppConfig appConfig;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private JobRunHistoryInfoService jobRunHistoryInfoService;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<JobRunResultNotificationInfo> findListByParam(JobRunResultNotificationInfoQuery param) {
        return this.jobRunResultNotificationInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(JobRunResultNotificationInfoQuery param) {
        return this.jobRunResultNotificationInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<JobRunResultNotificationInfo> findListByPage(JobRunResultNotificationInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<JobRunResultNotificationInfo> list = this.findListByParam(param);
        PaginationResultVO<JobRunResultNotificationInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(JobRunResultNotificationInfo bean) {
        return this.jobRunResultNotificationInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<JobRunResultNotificationInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.jobRunResultNotificationInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<JobRunResultNotificationInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.jobRunResultNotificationInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(JobRunResultNotificationInfo bean, JobRunResultNotificationInfoQuery param) {
        StringTools.checkParam(param);
        return this.jobRunResultNotificationInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(JobRunResultNotificationInfoQuery param) {
        StringTools.checkParam(param);
        return this.jobRunResultNotificationInfoMapper.deleteByParam(param);
    }

    /**
     * 根据JobUuidAndWorkUuid获取对象
     */
    @Override
    public JobRunResultNotificationInfo getJobRunResultNotificationInfoByJobUuidAndWorkUuid(String jobUuid, String workUuid) {
        return this.jobRunResultNotificationInfoMapper.selectByJobUuidAndWorkUuid(jobUuid, workUuid);
    }

    /**
     * 根据JobUuidAndWorkUuid修改
     */
    @Override
    public Integer updateJobRunResultNotificationInfoByJobUuidAndWorkUuid(JobRunResultNotificationInfo bean, String jobUuid, String workUuid) {
        return this.jobRunResultNotificationInfoMapper.updateByJobUuidAndWorkUuid(bean, jobUuid, workUuid);
    }

    /**
     * 根据JobUuidAndWorkUuid删除
     */
    @Override
    public Integer deleteJobRunResultNotificationInfoByJobUuidAndWorkUuid(String jobUuid, String workUuid) {
        return this.jobRunResultNotificationInfoMapper.deleteByJobUuidAndWorkUuid(jobUuid, workUuid);
    }

    /**
     * 只获取执行成功和失败的任务运行记录
     *
     * @param startDateStr
     * @param endDateStr   startDateStr: 2026-02-09 00:00:00, endDateStr: 2026-02-09 23:59:59
     * @return
     */
    @Override
    public List<JobRunResultNotificationInfo> getJobRunResultSuccessAndFail(String startDateStr, String endDateStr) {

        // 1. 创建 OkHttpClient 实例
        OkHttpClient client = new OkHttpClient();
        // 2. 构建请求体
        String jobParamsStr = "{\"startTime\":\"" + startDateStr + "\",\"endTime\":\"" + endDateStr + "\",\"current\":1,\"size\":500}";
        RequestBody body = RequestBody.create(jobParamsStr, MediaType.parse("application/json; charset=utf-8"));
        // 3. 构建请求
        Request request = new Request.Builder()
                .url(String.format("%s/work-execute/list", appConfig.getIndeedApiUrl()))
                .post(body)
                .addHeader("appKey", appConfig.getIndeedAppKey())
                .addHeader("appSecret", appConfig.getIndeedAppSecret())
                .build();
        // 4. 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {

            IndeedJobRunHistoryResponseBody<JobRunResultNotificationInfo> infoIndeedJobRunHistoryResponseBody = objectMapper.readValue(
                    response.body().string(),
                    new TypeReference<IndeedJobRunHistoryResponseBody<JobRunResultNotificationInfo>>() {
                    });

            List<JobRunResultNotificationInfo> records = infoIndeedJobRunHistoryResponseBody.getData().getRecords();
            records.forEach(record -> {
                record.setNotificationFlag(0);
            });

            // 只保留运行成功和失败的记录
            records.removeIf(record -> !record.getStatus().equals(IndeedJobStatusEnum.SUCCESS.getCode()) && !record.getStatus().equals(IndeedJobStatusEnum.FAILED.getCode()));

            return records;

        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }

    }

    /**
     * 发送任务执行结果通知(群)
     *
     * @param unionID
     * @param openConversationID
     */
    @Override
    public void sendJobExecuteResultNotification(String unionID, String openConversationID) {

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取指定小时前的时间
        LocalDateTime before = now.minusHours(appConfig.getNotificationAheadHours());
        String endTimeStr = DateUtil.format(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
        String startTimeStr = DateUtil.format(Date.from(before.atZone(ZoneId.systemDefault()).toInstant()), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
        List<JobRunResultNotificationInfo> jobRunResultSuccessAndFailList = this.getJobRunResultSuccessAndFail(startTimeStr, endTimeStr);

        jobRunResultSuccessAndFailList.forEach(jobRunResult -> {
            /**
             * 检查钉钉多维表 档案室 unionID是否匹配
             * 构建filter
             * {
             *     "combination": "and",
             *     "conditions": [
             *         {
             *             "field": "jobUUID",
             *             "operator": "equal",
             *             "value": [
             *                 "44fbf32eb6228ec0c603a92c73193329"
             *             ]
             *         },
             *         {
             *             "field": "开发人员",
             *             "operator": "contain",
             *             "value": [
             *                 {
             *                     "unionId": "H2mNA5ScfH2VegzCmE80ywiEiE"
             *                 }
             *             ]
             *         }
             *     ]
             * }
             */
            ListRecordsRequest.ListRecordsRequestFilter filter = new ListRecordsRequest.ListRecordsRequestFilter();
            filter.setCombination("and");
            filter.setConditions(new ArrayList<ListRecordsRequest.ListRecordsRequestFilterConditions>() {{
                add(new ListRecordsRequest.ListRecordsRequestFilterConditions()
                        .setField("jobUUID")
                        .setOperator("equal")
                        .setValue(Collections.singletonList(jobRunResult.getJobUuid())));
                add(new ListRecordsRequest.ListRecordsRequestFilterConditions()
                        .setField("开发人员")
                        .setOperator("contain")
                        .setValue(Collections.singletonList(Collections.singletonMap("unionId", unionID))));
            }});

            List<ListRecordsResponseBody.ListRecordsResponseBodyRecords> records = dingService.listAllRecordsByFilter(appConfig.getDingMpBaseId(), appConfig.getDingMpSheetId(), filter);
            // 如果运行记录和unionId匹配 则发送通知
            if (records != null && records.size() == 1) {
                // 检查数据库job_run_result_notification_info表中是否存在该记录且已经发送过结果通知
                JobRunResultNotificationInfoQuery jobRunResultNotificationInfoQuery = new JobRunResultNotificationInfoQuery();
                jobRunResultNotificationInfoQuery.setJobUuid(jobRunResult.getJobUuid());
                jobRunResultNotificationInfoQuery.setWorkUuid(jobRunResult.getWorkUuid());
                jobRunResultNotificationInfoQuery.setNotificationFlag(IndeedJobRunResultNotificationFlagEnum.NOTIFIED.getCode());
                // 如果存在该记录且已通知 则跳过处理
                if (this.findCountByParam(jobRunResultNotificationInfoQuery) == 0) {
                    // 如果不存在该记录(或存在但未通知) 则新增(或更新)
                    // 执行成功的 发送成功通知
                    if (jobRunResult.getStatus().equals(IndeedJobStatusEnum.SUCCESS.getCode())) {
                        // 构建成功的卡片内容cardData
                        CreateAndDeliverRequest.CreateAndDeliverRequestCardData cardData = new CreateAndDeliverRequest.CreateAndDeliverRequestCardData();
                        cardData.setCardParamMap(new HashMap<String, String>() {{
                            put("jobName", jobRunResult.getJobName());
                            put("accountName", jobRunResult.getAccountName());
                            put("startTime", DateUtil.format(jobRunResult.getStartTime(), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
                            put("endTime", DateUtil.format(jobRunResult.getEndTime(), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
                            put("runTime", String.valueOf(jobRunResult.getRunTime() / 60));
                        }});
                        dingService.sendCard2Group(openConversationID, appConfig.getDingSuccessCardTemplateId(), cardData);
                    }
                    // 执行失败的 发送失败通知
                    if (jobRunResult.getStatus().equals(IndeedJobStatusEnum.FAILED.getCode())) {
                        // 查询失败原因
                        IndeedJobRunHistoryDetailResponseBodyData jobRunHistoryDetail = jobRunHistoryInfoService.getJobRunHistoryDetailByWorkUUID(jobRunResult.getWorkUuid());
                        jobRunResult.setFailDescription(jobRunHistoryDetail.getFailDescription());
                        // 构建失败的卡片内容cardData
                        CreateAndDeliverRequest.CreateAndDeliverRequestCardData cardData = new CreateAndDeliverRequest.CreateAndDeliverRequestCardData();
                        cardData.setCardParamMap(new HashMap<String, String>() {{
                            put("jobName", jobRunResult.getJobName());
                            put("failDescription", jobRunResult.getFailDescription());
                            put("accountName", jobRunResult.getAccountName());
                            put("startTime", DateUtil.format(jobRunResult.getStartTime(), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
                            put("endTime", DateUtil.format(jobRunResult.getEndTime(), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
                            put("runTime", String.valueOf(jobRunResult.getRunTime() / 60));
                            put("jobUUID", jobRunResult.getJobUuid());
                        }});
                        dingService.sendCard2Group(openConversationID, appConfig.getDingFailCardTemplateId(), cardData);
                    }
                    // 数据库操作
                    jobRunResult.setNotificationFlag(IndeedJobRunResultNotificationFlagEnum.NOTIFIED.getCode());
                    this.addOrUpdateBatch(Collections.singletonList(jobRunResult));
                }
            }

        });

    }

}