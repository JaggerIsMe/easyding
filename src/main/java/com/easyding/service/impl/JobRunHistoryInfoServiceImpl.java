package com.easyding.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.easyding.config.AppConfig;
import com.easyding.entity.enums.DateTimePatternEnum;
import com.easyding.entity.enums.IndeedJobStatusEnum;
import com.easyding.entity.po.indeedPo.IndeedJobRunHistoryDetailResponseBody;
import com.easyding.entity.po.indeedPo.IndeedJobRunHistoryDetailResponseBodyData;
import com.easyding.entity.po.indeedPo.IndeedJobRunHistoryResponseBody;
import com.easyding.utils.DateUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.easyding.entity.enums.PageSize;
import com.easyding.entity.query.JobRunHistoryInfoQuery;
import com.easyding.entity.po.indeedPo.JobRunHistoryInfo;
import com.easyding.entity.vo.PaginationResultVO;
import com.easyding.entity.query.SimplePage;
import com.easyding.mappers.JobRunHistoryInfoMapper;
import com.easyding.service.JobRunHistoryInfoService;
import com.easyding.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 * API调用任务执行日志表 业务接口实现
 */
@Service("jobRunHistoryInfoService")
public class JobRunHistoryInfoServiceImpl implements JobRunHistoryInfoService {

    private static final Logger logger = LoggerFactory.getLogger(JobRunHistoryInfoServiceImpl.class);

    @Resource
    private JobRunHistoryInfoMapper<JobRunHistoryInfo, JobRunHistoryInfoQuery> jobRunHistoryInfoMapper;

    @Resource
    private AppConfig appConfig;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<JobRunHistoryInfo> findListByParam(JobRunHistoryInfoQuery param) {
        return this.jobRunHistoryInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(JobRunHistoryInfoQuery param) {
        return this.jobRunHistoryInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<JobRunHistoryInfo> findListByPage(JobRunHistoryInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<JobRunHistoryInfo> list = this.findListByParam(param);
        PaginationResultVO<JobRunHistoryInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(JobRunHistoryInfo bean) {
        return this.jobRunHistoryInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<JobRunHistoryInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.jobRunHistoryInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<JobRunHistoryInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.jobRunHistoryInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(JobRunHistoryInfo bean, JobRunHistoryInfoQuery param) {
        StringTools.checkParam(param);
        return this.jobRunHistoryInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(JobRunHistoryInfoQuery param) {
        StringTools.checkParam(param);
        return this.jobRunHistoryInfoMapper.deleteByParam(param);
    }

    /**
     * 根据JobUuidAndWorkUuid获取对象
     */
    @Override
    public JobRunHistoryInfo getJobRunHistoryInfoByJobUuidAndWorkUuid(String jobUuid, String workUuid) {
        return this.jobRunHistoryInfoMapper.selectByJobUuidAndWorkUuid(jobUuid, workUuid);
    }

    /**
     * 根据JobUuidAndWorkUuid修改
     */
    @Override
    public Integer updateJobRunHistoryInfoByJobUuidAndWorkUuid(JobRunHistoryInfo bean, String jobUuid, String workUuid) {
        return this.jobRunHistoryInfoMapper.updateByJobUuidAndWorkUuid(bean, jobUuid, workUuid);
    }

    /**
     * 根据JobUuidAndWorkUuid删除
     */
    @Override
    public Integer deleteJobRunHistoryInfoByJobUuidAndWorkUuid(String jobUuid, String workUuid) {
        return this.jobRunHistoryInfoMapper.deleteByJobUuidAndWorkUuid(jobUuid, workUuid);
    }

    /**
     * 加载任务执行日志
     * 获取新的运行记录
     */
    @Override
    public void loadJobRunHistory() {
        // 获取昨天日期
        LocalDate yesterday = LocalDate.now().minusDays(1);
        // 昨天0点
        LocalDateTime yesterdayStart = yesterday.atTime(LocalTime.MIN);
        // 昨天24点（即今天0点）
        LocalDateTime yesterdayEnd = yesterday.atTime(LocalTime.MAX);
        // 转换为Date
        Date startDate = Date.from(yesterdayStart.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(yesterdayEnd.atZone(ZoneId.systemDefault()).toInstant());

        String startDateStr = DateUtil.format(startDate, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
        String endDateStr = DateUtil.format(endDate, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());

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

            IndeedJobRunHistoryResponseBody<JobRunHistoryInfo> infoIndeedJobRunHistoryResponseBody = objectMapper.readValue(
                    response.body().string(),
                    new TypeReference<IndeedJobRunHistoryResponseBody<JobRunHistoryInfo>>() {
                    });

            List<JobRunHistoryInfo> records = infoIndeedJobRunHistoryResponseBody.getData().getRecords();
            records.forEach(record -> {
                record.setAddFlag(0);
            });

            this.addBatch(records);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 加载任务执行日志--指定日期
     * 获取新的运行记录
     *
     * @param startDateStr
     * @param endDateStr
     * startDateStr: 2026-02-09 00:00:00, endDateStr: 2026-02-09 23:59:59
     */
    @Override
    public void loadJobRunHistorySpecificDate(String startDateStr, String endDateStr) {

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

            IndeedJobRunHistoryResponseBody<JobRunHistoryInfo> infoIndeedJobRunHistoryResponseBody = objectMapper.readValue(
                    response.body().string(),
                    new TypeReference<IndeedJobRunHistoryResponseBody<JobRunHistoryInfo>>() {
                    });

            List<JobRunHistoryInfo> records = infoIndeedJobRunHistoryResponseBody.getData().getRecords();
            records.forEach(record -> {
                record.setAddFlag(0);
            });

            this.addBatch(records);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 重新加载运行记录
     * 更新运行状态为1或者2的旧记录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reloadJobRunHistory() {
        // 获取所有状态为1或者2的运行记录，并更新记录
        JobRunHistoryInfoQuery query1Status = new JobRunHistoryInfoQuery();
        query1Status.setStatus(1);
        JobRunHistoryInfoQuery query2Status = new JobRunHistoryInfoQuery();
        query2Status.setStatus(2);
        List<JobRunHistoryInfo> queryList = this.findListByParam(query1Status);
        queryList.addAll(this.findListByParam(query2Status));

        queryList.forEach(record -> {

            // 1. 创建 OkHttpClient 实例
            OkHttpClient client = new OkHttpClient();
            // 2. 构建请求体
            String jobParamsStr = "{\"workUuids\":[\"" + record.getWorkUuid() + "\"]}";
            RequestBody body = RequestBody.create(jobParamsStr, MediaType.parse("application/json; charset=utf-8"));
            // 3. 构建请求
            Request request = new Request.Builder()
                    .url(String.format("%s/v1/work-list", appConfig.getIndeedApiUrl()))
                    .post(body)
                    .addHeader("appKey", appConfig.getIndeedAppKey())
                    .addHeader("appSecret", appConfig.getIndeedAppSecret())
                    .build();
            // 4. 发送请求并处理响应
            try (Response response = client.newCall(request).execute()) {

                IndeedJobRunHistoryDetailResponseBody indeedJobRunHistoryDetailResponseBody = objectMapper.readValue(
                        response.body().string(),
                        new TypeReference<IndeedJobRunHistoryDetailResponseBody>() {
                        });

                List<IndeedJobRunHistoryDetailResponseBodyData> dataList = indeedJobRunHistoryDetailResponseBody.getData();
                if (dataList == null || dataList.isEmpty()) {
                    this.deleteJobRunHistoryInfoByJobUuidAndWorkUuid(record.getJobUuid(), record.getWorkUuid());
                    return;
                }
                IndeedJobRunHistoryDetailResponseBodyData JobRunHistoryDetail = indeedJobRunHistoryDetailResponseBody.getData().get(0);
                // 设置更新信息
                JobRunHistoryInfo updateJobRunHistoryInfo = new JobRunHistoryInfo();
                updateJobRunHistoryInfo.setStatus(JobRunHistoryDetail.getStatus());
                updateJobRunHistoryInfo.setStatusDesc(IndeedJobStatusEnum.getDescriptionByCode(JobRunHistoryDetail.getStatus()));
                updateJobRunHistoryInfo.setStartTime(JobRunHistoryDetail.getStartTime());
                updateJobRunHistoryInfo.setEndTime(JobRunHistoryDetail.getEndTime());
                updateJobRunHistoryInfo.setBotUuid(JobRunHistoryDetail.getWorkExecutes().get(0).getBotUuid());
                updateJobRunHistoryInfo.setBotId(JobRunHistoryDetail.getWorkExecutes().get(0).getBotId());

                // 计算运行时间
                if (!updateJobRunHistoryInfo.getStatus().equals(IndeedJobStatusEnum.RUNNING.getCode())
                        && !updateJobRunHistoryInfo.getStatus().equals(IndeedJobStatusEnum.PENDING.getCode())
                        && updateJobRunHistoryInfo.getStartTime() != null
                        && updateJobRunHistoryInfo.getEndTime() != null) {
                    Integer runTime = (int) ((updateJobRunHistoryInfo.getEndTime().getTime() - updateJobRunHistoryInfo.getStartTime().getTime()) / 1000);
                    updateJobRunHistoryInfo.setRunTime(runTime);
                }

                this.updateJobRunHistoryInfoByJobUuidAndWorkUuid(updateJobRunHistoryInfo, record.getJobUuid(), record.getWorkUuid());

            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        });
    }
}