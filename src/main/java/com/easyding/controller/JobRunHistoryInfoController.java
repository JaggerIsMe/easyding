package com.easyding.controller;

import com.easyding.entity.po.indeedPo.JobRunHistoryInfo;
import com.easyding.entity.query.JobRunHistoryInfoQuery;
import com.easyding.entity.vo.ResponseVO;
import com.easyding.service.JobRunHistoryInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * API调用任务执行日志表 Controller
 */
@RestController("jobRunHistoryInfoController")
@RequestMapping("/jobRunHistoryInfo")
public class JobRunHistoryInfoController extends ABaseController{

	@Resource
	private JobRunHistoryInfoService jobRunHistoryInfoService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(JobRunHistoryInfoQuery query){
		return getSuccessResponseVO(jobRunHistoryInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(JobRunHistoryInfo bean) {
		jobRunHistoryInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<JobRunHistoryInfo> listBean) {
		jobRunHistoryInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<JobRunHistoryInfo> listBean) {
		jobRunHistoryInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据JobUuidAndWorkUuid查询对象
	 */
	@RequestMapping("/getJobRunHistoryInfoByJobUuidAndWorkUuid")
	public ResponseVO getJobRunHistoryInfoByJobUuidAndWorkUuid(String jobUuid,String workUuid) {
		return getSuccessResponseVO(jobRunHistoryInfoService.getJobRunHistoryInfoByJobUuidAndWorkUuid(jobUuid,workUuid));
	}

	/**
	 * 根据JobUuidAndWorkUuid修改对象
	 */
	@RequestMapping("/updateJobRunHistoryInfoByJobUuidAndWorkUuid")
	public ResponseVO updateJobRunHistoryInfoByJobUuidAndWorkUuid(JobRunHistoryInfo bean,String jobUuid,String workUuid) {
		jobRunHistoryInfoService.updateJobRunHistoryInfoByJobUuidAndWorkUuid(bean,jobUuid,workUuid);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据JobUuidAndWorkUuid删除
	 */
	@RequestMapping("/deleteJobRunHistoryInfoByJobUuidAndWorkUuid")
	public ResponseVO deleteJobRunHistoryInfoByJobUuidAndWorkUuid(String jobUuid,String workUuid) {
		jobRunHistoryInfoService.deleteJobRunHistoryInfoByJobUuidAndWorkUuid(jobUuid,workUuid);
		return getSuccessResponseVO(null);
	}

	/**
	 * 加载任务执行日志
	 * 获取新的运行记录
	 */
	@RequestMapping("/loadJobRunHistory")
	public ResponseVO loadJobRunHistory() {
		jobRunHistoryInfoService.loadJobRunHistory();
		return getSuccessResponseVO(null);
	}

	/**
	 * 加载任务执行日志--指定日期
	 * 获取新的运行记录
	 *
	 * @param startDateStr
	 * @param endDateStr
	 * startDateStr: 2026-02-09 00:00:00, endDateStr: 2026-02-09 23:59:59
	 */
	@RequestMapping("/loadJobRunHistorySpecificDate")
	public ResponseVO loadJobRunHistorySpecificDate(@RequestParam("startDateStr") String startDateStr, @RequestParam("endDateStr") String endDateStr) {
		jobRunHistoryInfoService.loadJobRunHistorySpecificDate(startDateStr, endDateStr);
		return getSuccessResponseVO(null);
	}

	/**
	 * 重新加载运行记录
	 * 更新运行状态为1或者2的旧记录
	 */
	@RequestMapping("/reloadJobRunHistory")
	public ResponseVO reloadJobRunHistory() {
		jobRunHistoryInfoService.reloadJobRunHistory();
		return getSuccessResponseVO(null);
	}
}