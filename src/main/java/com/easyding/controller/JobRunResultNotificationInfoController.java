package com.easyding.controller;

import java.util.List;

import com.easyding.entity.query.JobRunResultNotificationInfoQuery;
import com.easyding.entity.po.indeedPo.JobRunResultNotificationInfo;
import com.easyding.entity.vo.ResponseVO;
import com.easyding.service.JobRunResultNotificationInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 任务执行结果通知表 Controller
 */
@RestController("jobRunResultNotificationInfoController")
@RequestMapping("/jobRunResultNotificationInfo")
public class JobRunResultNotificationInfoController extends ABaseController{

	@Resource
	private JobRunResultNotificationInfoService jobRunResultNotificationInfoService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(JobRunResultNotificationInfoQuery query){
		return getSuccessResponseVO(jobRunResultNotificationInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(JobRunResultNotificationInfo bean) {
		jobRunResultNotificationInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<JobRunResultNotificationInfo> listBean) {
		jobRunResultNotificationInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<JobRunResultNotificationInfo> listBean) {
		jobRunResultNotificationInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据JobUuidAndWorkUuid查询对象
	 */
	@RequestMapping("/getJobRunResultNotificationInfoByJobUuidAndWorkUuid")
	public ResponseVO getJobRunResultNotificationInfoByJobUuidAndWorkUuid(String jobUuid,String workUuid) {
		return getSuccessResponseVO(jobRunResultNotificationInfoService.getJobRunResultNotificationInfoByJobUuidAndWorkUuid(jobUuid,workUuid));
	}

	/**
	 * 根据JobUuidAndWorkUuid修改对象
	 */
	@RequestMapping("/updateJobRunResultNotificationInfoByJobUuidAndWorkUuid")
	public ResponseVO updateJobRunResultNotificationInfoByJobUuidAndWorkUuid(JobRunResultNotificationInfo bean,String jobUuid,String workUuid) {
		jobRunResultNotificationInfoService.updateJobRunResultNotificationInfoByJobUuidAndWorkUuid(bean,jobUuid,workUuid);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据JobUuidAndWorkUuid删除
	 */
	@RequestMapping("/deleteJobRunResultNotificationInfoByJobUuidAndWorkUuid")
	public ResponseVO deleteJobRunResultNotificationInfoByJobUuidAndWorkUuid(String jobUuid,String workUuid) {
		jobRunResultNotificationInfoService.deleteJobRunResultNotificationInfoByJobUuidAndWorkUuid(jobUuid,workUuid);
		return getSuccessResponseVO(null);
	}

	/**
	 * 发送任务执行结果通知(群)
	 *
	 * @param openConversationID
	 */
	@RequestMapping("/sendJobExecuteResultNotification")
	public ResponseVO sendJobExecuteResultNotification(@RequestHeader("openConversationID") String openConversationID) {
		jobRunResultNotificationInfoService.sendJobExecuteResultNotification(openConversationID);
		return getSuccessResponseVO(null);
	}

}