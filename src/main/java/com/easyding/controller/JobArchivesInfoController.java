package com.easyding.controller;

import java.util.List;

import com.easyding.entity.query.JobArchivesInfoQuery;
import com.easyding.entity.po.indeedPo.JobArchivesInfo;
import com.easyding.entity.vo.ResponseVO;
import com.easyding.service.JobArchivesInfoService;
import com.easyding.task.IndeedJobArchivesTask;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  Controller
 */
@RestController("jobArchivesInfoController")
@RequestMapping("/jobArchivesInfo")
public class JobArchivesInfoController extends ABaseController{

	@Resource
	private JobArchivesInfoService jobArchivesInfoService;

	@Resource
	private IndeedJobArchivesTask indeedJobArchivesTask;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(JobArchivesInfoQuery query){
		return getSuccessResponseVO(jobArchivesInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(JobArchivesInfo bean) {
		jobArchivesInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<JobArchivesInfo> listBean) {
		jobArchivesInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<JobArchivesInfo> listBean) {
		jobArchivesInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据JobUuidAndAge查询对象
	 */
	@RequestMapping("/getJobArchivesInfoByJobUuidAndAge")
	public ResponseVO getJobArchivesInfoByJobUuidAndAge(String jobUuid,String age) {
		return getSuccessResponseVO(jobArchivesInfoService.getJobArchivesInfoByJobUuidAndAge(jobUuid,age));
	}

	/**
	 * 根据JobUuidAndAge修改对象
	 */
	@RequestMapping("/updateJobArchivesInfoByJobUuidAndAge")
	public ResponseVO updateJobArchivesInfoByJobUuidAndAge(JobArchivesInfo bean,String jobUuid,String age) {
		jobArchivesInfoService.updateJobArchivesInfoByJobUuidAndAge(bean,jobUuid,age);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据JobUuidAndAge删除
	 */
	@RequestMapping("/deleteJobArchivesInfoByJobUuidAndAge")
	public ResponseVO deleteJobArchivesInfoByJobUuidAndAge(String jobUuid,String age) {
		jobArchivesInfoService.deleteJobArchivesInfoByJobUuidAndAge(jobUuid,age);
		return getSuccessResponseVO(null);
	}

	/**
	 * 老年代升级到新生代
	 */
	@RequestMapping("/growJobArchivesAge")
	public ResponseVO growJobArchivesAge() {
		jobArchivesInfoService.growJobArchivesAge();
		return getSuccessResponseVO(null);
	}

	/**
	 * 手动触发执行定时任务
	 */
	@RequestMapping("/executeIndeedJobArchivesTask")
	public ResponseVO executeIndeedJobArchivesTask() {
		indeedJobArchivesTask.execute();
		return getSuccessResponseVO(null);
	}
}