package com.easyding.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.easyding.config.AppConfig;
import com.easyding.entity.enums.DateTimePatternEnum;
import com.easyding.entity.enums.IndeedJobStatusEnum;
import com.easyding.entity.po.indeedPo.IndeedJobRunHistoryResponseBody;
import com.easyding.service.JobRunHistoryInfoService;
import com.easyding.utils.DateUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.easyding.entity.enums.PageSize;
import com.easyding.entity.query.JobRunResultNotificationInfoQuery;
import com.easyding.entity.po.indeedPo.JobRunResultNotificationInfo;
import com.easyding.entity.vo.PaginationResultVO;
import com.easyding.entity.query.SimplePage;
import com.easyding.mappers.JobRunResultNotificationInfoMapper;
import com.easyding.service.JobRunResultNotificationInfoService;
import com.easyding.utils.StringTools;


/**
 * 任务执行结果通知表 业务接口实现
 */
@Service("jobRunResultNotificationInfoService")
public class JobRunResultNotificationInfoServiceImpl implements JobRunResultNotificationInfoService {

    private static final Logger logger = LoggerFactory.getLogger(JobRunResultNotificationInfoServiceImpl.class);

    @Resource
    private JobRunResultNotificationInfoMapper<JobRunResultNotificationInfo, JobRunResultNotificationInfoQuery> jobRunResultNotificationInfoMapper;

    @Resource
    private JobRunHistoryInfoService jobRunHistoryInfoService;

    @Resource
    private AppConfig appConfig;

    @Resource
    private ObjectMapper objectMapper;

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
     * @param endDateStr
     * startDateStr: 2026-02-09 00:00:00, endDateStr: 2026-02-09 23:59:59
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
     * @param openConversationID
     */
    @Override
    public void sendJobExecuteResultNotification(String openConversationID) {

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取48小时前的时间
        LocalDateTime before = now.minusHours(48);

        String endTimeStr = DateUtil.format(Date.from(before.atZone(ZoneId.systemDefault()).toInstant()), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
        String startTimeStr = DateUtil.format(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
        logger.info("开始时间：" + startTimeStr + " 结束时间：" + endTimeStr);


    }

}