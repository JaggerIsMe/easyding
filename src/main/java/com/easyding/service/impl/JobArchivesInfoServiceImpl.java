package com.easyding.service.impl;

import com.aliyun.dingtalknotable_1_0.models.InsertRecordsRequest;
import com.aliyun.dingtalknotable_1_0.models.ListRecordsResponseBody;
import com.aliyun.dingtalknotable_1_0.models.UpdateRecordsRequest;
import com.aliyun.tea.TeaConverter;
import com.easyding.config.AppConfig;
import com.easyding.entity.enums.DateTimePatternEnum;
import com.easyding.entity.enums.IndeedJobStatusEnum;
import com.easyding.entity.enums.PageSize;
import com.easyding.entity.po.indeedPo.JobArchivesInfo;
import com.easyding.entity.po.indeedPo.JobRunHistoryInfo;
import com.easyding.entity.query.JobArchivesInfoQuery;
import com.easyding.entity.query.JobRunHistoryInfoQuery;
import com.easyding.entity.query.SimplePage;
import com.easyding.entity.vo.PaginationResultVO;
import com.easyding.mappers.JobArchivesInfoMapper;
import com.easyding.service.DingService;
import com.easyding.service.JobArchivesInfoService;
import com.easyding.service.JobRunHistoryInfoService;
import com.easyding.utils.DateUtil;
import com.easyding.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 业务接口实现
 */
@Service("jobArchivesInfoService")
public class JobArchivesInfoServiceImpl implements JobArchivesInfoService {

    private static final Logger logger = LoggerFactory.getLogger(JobArchivesInfoServiceImpl.class);

    @Resource
    private JobArchivesInfoMapper<JobArchivesInfo, JobArchivesInfoQuery> jobArchivesInfoMapper;

    @Resource
    private DingService dingService;

    @Resource
    private JobRunHistoryInfoService jobRunHistoryInfoService;

    @Resource
    private AppConfig appConfig;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<JobArchivesInfo> findListByParam(JobArchivesInfoQuery param) {
        return this.jobArchivesInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(JobArchivesInfoQuery param) {
        return this.jobArchivesInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<JobArchivesInfo> findListByPage(JobArchivesInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<JobArchivesInfo> list = this.findListByParam(param);
        PaginationResultVO<JobArchivesInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(JobArchivesInfo bean) {
        return this.jobArchivesInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<JobArchivesInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.jobArchivesInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<JobArchivesInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.jobArchivesInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(JobArchivesInfo bean, JobArchivesInfoQuery param) {
        StringTools.checkParam(param);
        return this.jobArchivesInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(JobArchivesInfoQuery param) {
        StringTools.checkParam(param);
        return this.jobArchivesInfoMapper.deleteByParam(param);
    }

    /**
     * 根据JobUuidAndAge获取对象
     */
    @Override
    public JobArchivesInfo getJobArchivesInfoByJobUuidAndAge(String jobUuid, String age) {
        return this.jobArchivesInfoMapper.selectByJobUuidAndAge(jobUuid, age);
    }

    /**
     * 根据JobUuidAndAge修改
     */
    @Override
    public Integer updateJobArchivesInfoByJobUuidAndAge(JobArchivesInfo bean, String jobUuid, String age) {
        return this.jobArchivesInfoMapper.updateByJobUuidAndAge(bean, jobUuid, age);
    }

    /**
     * 根据JobUuidAndAge删除
     */
    @Override
    public Integer deleteJobArchivesInfoByJobUuidAndAge(String jobUuid, String age) {
        return this.jobArchivesInfoMapper.deleteByJobUuidAndAge(jobUuid, age);
    }

    /**
     * 老年代升级到新生代
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void growJobArchivesAge() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate beforeYesterday = today.minusDays(2);
        Date yesterdayDate = Date.from(yesterday.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date beforeYesterdayDate = Date.from(beforeYesterday.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String youngAge = DateUtil.format(yesterdayDate, DateTimePatternEnum.YYYYMMDD.getPattern());
        String oldAge = DateUtil.format(beforeYesterdayDate, DateTimePatternEnum.YYYYMMDD.getPattern());

        // 读取钉钉表格--档案室记录
        List<ListRecordsResponseBody.ListRecordsResponseBodyRecords> listRecordsResponseBodyRecords = dingService.listAllRecords(appConfig.getDingMpBaseId(), appConfig.getDingMpSheetId());
        listRecordsResponseBodyRecords.forEach(record -> {
            String jobUuid = (String) record.fields.get("jobUUID");
            String jobName = (String) record.fields.get("任务名");
            // 创建新生代
            JobArchivesInfo newJobArchivesInfo = new JobArchivesInfo();
            newJobArchivesInfo.setJobUuid(jobUuid);
            newJobArchivesInfo.setAge(youngAge);
            newJobArchivesInfo.setJobName(jobName);
            // 查找老年代
            JobArchivesInfo oldJobArchivesInfo = this.getJobArchivesInfoByJobUuidAndAge(jobUuid, oldAge);
            if (oldJobArchivesInfo != null) {
                // 找到老年代，则复制老年代数据到新生代
                newJobArchivesInfo.setTotalRunTime(oldJobArchivesInfo.getTotalRunTime());
                newJobArchivesInfo.setTotalCounts(oldJobArchivesInfo.getTotalCounts());
                newJobArchivesInfo.setTotalSuccessCounts(oldJobArchivesInfo.getTotalSuccessCounts());
                newJobArchivesInfo.setTotalFailCounts(oldJobArchivesInfo.getTotalFailCounts());
            } else {
                // 找不到老年代，则初始化一个新生代
                newJobArchivesInfo.setTotalRunTime(BigDecimal.valueOf(0));
                newJobArchivesInfo.setTotalCounts(0);
                newJobArchivesInfo.setTotalSuccessCounts(0);
                newJobArchivesInfo.setTotalFailCounts(0);
            }
            // 更新档案记录
            JobRunHistoryInfoQuery queryTotalFailedCounts = new JobRunHistoryInfoQuery();
            queryTotalFailedCounts.setJobUuid(jobUuid);
            queryTotalFailedCounts.setStatus(IndeedJobStatusEnum.FAILED.getCode());
            queryTotalFailedCounts.setAddFlag(0);
            JobRunHistoryInfoQuery queryTotalStoppedCounts = new JobRunHistoryInfoQuery();
            queryTotalStoppedCounts.setJobUuid(jobUuid);
            queryTotalStoppedCounts.setStatus(IndeedJobStatusEnum.STOPPED.getCode());
            queryTotalStoppedCounts.setAddFlag(0);
            JobRunHistoryInfoQuery queryTotalSuccessCounts = new JobRunHistoryInfoQuery();
            queryTotalSuccessCounts.setJobUuid(jobUuid);
            queryTotalSuccessCounts.setStatus(IndeedJobStatusEnum.SUCCESS.getCode());
            queryTotalSuccessCounts.setAddFlag(0);
            int totalFailedCounts = jobRunHistoryInfoService.findCountByParam(queryTotalFailedCounts);
            int totalStoppedCounts = jobRunHistoryInfoService.findCountByParam(queryTotalStoppedCounts);
            int totalSuccessCounts = jobRunHistoryInfoService.findCountByParam(queryTotalSuccessCounts);
            newJobArchivesInfo.setTotalCounts(newJobArchivesInfo.getTotalCounts() + totalFailedCounts + totalStoppedCounts + totalSuccessCounts);
            newJobArchivesInfo.setTotalSuccessCounts(newJobArchivesInfo.getTotalSuccessCounts() + totalSuccessCounts);
            newJobArchivesInfo.setTotalFailCounts(newJobArchivesInfo.getTotalFailCounts() + totalFailedCounts);

            AtomicInteger totalRunTimeInSecond = new AtomicInteger(0);
            JobRunHistoryInfoQuery notAddJobRunHistoryQuery = new JobRunHistoryInfoQuery();
            notAddJobRunHistoryQuery.setJobUuid(jobUuid);
            notAddJobRunHistoryQuery.setAddFlag(0);
            List<JobRunHistoryInfo> listJobRunHistoryInfo = jobRunHistoryInfoService.findListByParam(notAddJobRunHistoryQuery);
            listJobRunHistoryInfo.forEach(jobRunHistoryInfo -> {
                if (jobRunHistoryInfo.getStatus() != 1 && jobRunHistoryInfo.getStatus() != 2 && jobRunHistoryInfo.getRunTime() != null) {
                    totalRunTimeInSecond.addAndGet(jobRunHistoryInfo.getRunTime());
                    jobRunHistoryInfo.setAddFlag(1);
                    // 更新数据库
                    jobRunHistoryInfoService.updateJobRunHistoryInfoByJobUuidAndWorkUuid(jobRunHistoryInfo, jobRunHistoryInfo.getJobUuid(), jobRunHistoryInfo.getWorkUuid());
                    // 钉钉表格新增记录
                    InsertRecordsRequest.InsertRecordsRequestRecords newRecords0 = new InsertRecordsRequest.InsertRecordsRequestRecords();
                    Map<String, Object> newRecords0Fields = TeaConverter.buildMap();
                    newRecords0Fields.put("jobUUID", jobRunHistoryInfo.getJobUuid());
                    newRecords0Fields.put("workUUID", jobRunHistoryInfo.getWorkUuid());
                    newRecords0Fields.put("运行状态", jobRunHistoryInfo.getStatus());
                    newRecords0Fields.put("任务创建时间", DateUtil.format(jobRunHistoryInfo.getGmtCreated(), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
                    newRecords0Fields.put("任务开始运行时间", DateUtil.format(jobRunHistoryInfo.getStartTime(), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
                    newRecords0Fields.put("任务结束运行时间", DateUtil.format(jobRunHistoryInfo.getEndTime(), DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
                    newRecords0Fields.put("运行时长(秒)", jobRunHistoryInfo.getRunTime());
                    newRecords0Fields.put("botUUID", jobRunHistoryInfo.getBotUuid());
                    newRecords0.setFields(newRecords0Fields);
                    dingService.addRecords(appConfig.getDingMpBaseId(), appConfig.getDingHsSheetId(), Collections.singletonList(newRecords0));
                }
            });
            BigDecimal totalRunTimeInHour = BigDecimal.valueOf(totalRunTimeInSecond.get() / 3600.0D);
            newJobArchivesInfo.setTotalRunTime(newJobArchivesInfo.getTotalRunTime().add(totalRunTimeInHour));
            // 数据库新增
            this.add(newJobArchivesInfo);

            // 更新钉钉表格数据
            UpdateRecordsRequest.UpdateRecordsRequestRecords records0 = new UpdateRecordsRequest.UpdateRecordsRequestRecords();
            records0.setId(record.id);
            Map<String, Object> records0Fields = TeaConverter.buildMap();
            records0Fields.put("累计运行时长(H)", newJobArchivesInfo.getTotalRunTime().floatValue());
            records0Fields.put("总运行次数", newJobArchivesInfo.getTotalCounts());
            records0Fields.put("运行成功次数", newJobArchivesInfo.getTotalSuccessCounts());
            records0Fields.put("运行失败次数", newJobArchivesInfo.getTotalFailCounts());
            records0Fields.put("年代", youngAge);
            records0.setFields(records0Fields);
            dingService.updateRecord(appConfig.getDingMpBaseId(), appConfig.getDingMpSheetId(), records0);
        });
    }
}