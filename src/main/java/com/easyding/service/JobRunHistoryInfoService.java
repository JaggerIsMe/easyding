package com.easyding.service;

import java.util.List;

import com.easyding.entity.query.JobRunHistoryInfoQuery;
import com.easyding.entity.po.indeedPo.JobRunHistoryInfo;
import com.easyding.entity.vo.PaginationResultVO;


/**
 * API调用任务执行日志表 业务接口
 */
public interface JobRunHistoryInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<JobRunHistoryInfo> findListByParam(JobRunHistoryInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(JobRunHistoryInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<JobRunHistoryInfo> findListByPage(JobRunHistoryInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(JobRunHistoryInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<JobRunHistoryInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<JobRunHistoryInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(JobRunHistoryInfo bean,JobRunHistoryInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(JobRunHistoryInfoQuery param);

	/**
	 * 根据JobUuidAndWorkUuid查询对象
	 */
	JobRunHistoryInfo getJobRunHistoryInfoByJobUuidAndWorkUuid(String jobUuid,String workUuid);


	/**
	 * 根据JobUuidAndWorkUuid修改
	 */
	Integer updateJobRunHistoryInfoByJobUuidAndWorkUuid(JobRunHistoryInfo bean,String jobUuid,String workUuid);


	/**
	 * 根据JobUuidAndWorkUuid删除
	 */
	Integer deleteJobRunHistoryInfoByJobUuidAndWorkUuid(String jobUuid,String workUuid);

	/**
	 * 加载任务执行日志
	 * 获取新的运行记录
	 */
	void loadJobRunHistory();

	/**
	 * 加载任务执行日志--指定日期
	 * 获取新的运行记录
	 *
	 * @param startDateStr
	 * @param endDateStr
	 * startDateStr: 2026-02-09 00:00:00, endDateStr: 2026-02-09 23:59:59
	 */
	void loadJobRunHistorySpecificDate(String startDateStr, String endDateStr);

	/**
	 * 重新加载运行记录
	 * 更新运行状态为1或者2的旧记录
	 */
	void reloadJobRunHistory();

}