package com.easyding.service;

import java.util.List;

import com.easyding.entity.query.JobRunResultNotificationInfoQuery;
import com.easyding.entity.po.indeedPo.JobRunResultNotificationInfo;
import com.easyding.entity.vo.PaginationResultVO;


/**
 * 任务执行结果通知表 业务接口
 */
public interface JobRunResultNotificationInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<JobRunResultNotificationInfo> findListByParam(JobRunResultNotificationInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(JobRunResultNotificationInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<JobRunResultNotificationInfo> findListByPage(JobRunResultNotificationInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(JobRunResultNotificationInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<JobRunResultNotificationInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<JobRunResultNotificationInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(JobRunResultNotificationInfo bean,JobRunResultNotificationInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(JobRunResultNotificationInfoQuery param);

	/**
	 * 根据JobUuidAndWorkUuid查询对象
	 */
	JobRunResultNotificationInfo getJobRunResultNotificationInfoByJobUuidAndWorkUuid(String jobUuid,String workUuid);


	/**
	 * 根据JobUuidAndWorkUuid修改
	 */
	Integer updateJobRunResultNotificationInfoByJobUuidAndWorkUuid(JobRunResultNotificationInfo bean,String jobUuid,String workUuid);


	/**
	 * 根据JobUuidAndWorkUuid删除
	 */
	Integer deleteJobRunResultNotificationInfoByJobUuidAndWorkUuid(String jobUuid,String workUuid);

	/**
	 * 只获取执行成功和失败的任务运行记录
	 *
	 * @param startDateStr
	 * @param endDateStr
	 * startDateStr: 2026-02-09 00:00:00, endDateStr: 2026-02-09 23:59:59
	 * @return
	 */
	List<JobRunResultNotificationInfo> getJobRunResultSuccessAndFail(String startDateStr, String endDateStr);

	/**
	 * 发送任务执行结果通知(群)
	 *
	 * @param openConversationID
	 */
	void sendJobExecuteResultNotification(String openConversationID);

}