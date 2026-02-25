package com.easyding.entity.query;

import java.util.Date;


/**
 * API调用任务执行日志表参数
 */
public class JobRunHistoryInfoQuery extends BaseParam {


	/**
	 * 任务UUID
	 */
	private String jobUuid;

	private String jobUuidFuzzy;

	/**
	 * 执行UUID
	 */
	private String workUuid;

	private String workUuidFuzzy;

	/**
	 * 任务名称
	 */
	private String jobName;

	private String jobNameFuzzy;

	/**
	 * 状态码，1运行中，2待运行，3运行失败，4已停止，5运行成功
	 */
	private Integer status;

	/**
	 * 状态描述
	 */
	private String statusDesc;

	private String statusDescFuzzy;

	/**
	 * 部门名称
	 */
	private String deptName;

	private String deptNameFuzzy;

	/**
	 * 账户名称
	 */
	private String accountName;

	private String accountNameFuzzy;

	/**
	 * 真实姓名
	 */
	private String realName;

	private String realNameFuzzy;

	/**
	 * 创建时间
	 */
	private String gmtCreated;

	private String gmtCreatedStart;

	private String gmtCreatedEnd;

	/**
	 * 开始时间
	 */
	private String startTime;

	private String startTimeStart;

	private String startTimeEnd;

	/**
	 * 结束时间
	 */
	private String endTime;

	private String endTimeStart;

	private String endTimeEnd;

	/**
	 * 运行时长(秒)
	 */
	private Integer runTime;

	/**
	 * 机器人UUID
	 */
	private String botUuid;

	private String botUuidFuzzy;

	/**
	 * 机器人ID
	 */
	private String botId;

	private String botIdFuzzy;

	/**
	 * 是否被统计，0未统计，1已统计
	 */
	private Integer addFlag;


	public void setJobUuid(String jobUuid){
		this.jobUuid = jobUuid;
	}

	public String getJobUuid(){
		return this.jobUuid;
	}

	public void setJobUuidFuzzy(String jobUuidFuzzy){
		this.jobUuidFuzzy = jobUuidFuzzy;
	}

	public String getJobUuidFuzzy(){
		return this.jobUuidFuzzy;
	}

	public void setWorkUuid(String workUuid){
		this.workUuid = workUuid;
	}

	public String getWorkUuid(){
		return this.workUuid;
	}

	public void setWorkUuidFuzzy(String workUuidFuzzy){
		this.workUuidFuzzy = workUuidFuzzy;
	}

	public String getWorkUuidFuzzy(){
		return this.workUuidFuzzy;
	}

	public void setJobName(String jobName){
		this.jobName = jobName;
	}

	public String getJobName(){
		return this.jobName;
	}

	public void setJobNameFuzzy(String jobNameFuzzy){
		this.jobNameFuzzy = jobNameFuzzy;
	}

	public String getJobNameFuzzy(){
		return this.jobNameFuzzy;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setStatusDesc(String statusDesc){
		this.statusDesc = statusDesc;
	}

	public String getStatusDesc(){
		return this.statusDesc;
	}

	public void setStatusDescFuzzy(String statusDescFuzzy){
		this.statusDescFuzzy = statusDescFuzzy;
	}

	public String getStatusDescFuzzy(){
		return this.statusDescFuzzy;
	}

	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	public String getDeptName(){
		return this.deptName;
	}

	public void setDeptNameFuzzy(String deptNameFuzzy){
		this.deptNameFuzzy = deptNameFuzzy;
	}

	public String getDeptNameFuzzy(){
		return this.deptNameFuzzy;
	}

	public void setAccountName(String accountName){
		this.accountName = accountName;
	}

	public String getAccountName(){
		return this.accountName;
	}

	public void setAccountNameFuzzy(String accountNameFuzzy){
		this.accountNameFuzzy = accountNameFuzzy;
	}

	public String getAccountNameFuzzy(){
		return this.accountNameFuzzy;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	public String getRealName(){
		return this.realName;
	}

	public void setRealNameFuzzy(String realNameFuzzy){
		this.realNameFuzzy = realNameFuzzy;
	}

	public String getRealNameFuzzy(){
		return this.realNameFuzzy;
	}

	public void setGmtCreated(String gmtCreated){
		this.gmtCreated = gmtCreated;
	}

	public String getGmtCreated(){
		return this.gmtCreated;
	}

	public void setGmtCreatedStart(String gmtCreatedStart){
		this.gmtCreatedStart = gmtCreatedStart;
	}

	public String getGmtCreatedStart(){
		return this.gmtCreatedStart;
	}
	public void setGmtCreatedEnd(String gmtCreatedEnd){
		this.gmtCreatedEnd = gmtCreatedEnd;
	}

	public String getGmtCreatedEnd(){
		return this.gmtCreatedEnd;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public String getStartTime(){
		return this.startTime;
	}

	public void setStartTimeStart(String startTimeStart){
		this.startTimeStart = startTimeStart;
	}

	public String getStartTimeStart(){
		return this.startTimeStart;
	}
	public void setStartTimeEnd(String startTimeEnd){
		this.startTimeEnd = startTimeEnd;
	}

	public String getStartTimeEnd(){
		return this.startTimeEnd;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	public String getEndTime(){
		return this.endTime;
	}

	public void setEndTimeStart(String endTimeStart){
		this.endTimeStart = endTimeStart;
	}

	public String getEndTimeStart(){
		return this.endTimeStart;
	}
	public void setEndTimeEnd(String endTimeEnd){
		this.endTimeEnd = endTimeEnd;
	}

	public String getEndTimeEnd(){
		return this.endTimeEnd;
	}

	public void setRunTime(Integer runTime){
		this.runTime = runTime;
	}

	public Integer getRunTime(){
		return this.runTime;
	}

	public void setBotUuid(String botUuid){
		this.botUuid = botUuid;
	}

	public String getBotUuid(){
		return this.botUuid;
	}

	public void setBotUuidFuzzy(String botUuidFuzzy){
		this.botUuidFuzzy = botUuidFuzzy;
	}

	public String getBotUuidFuzzy(){
		return this.botUuidFuzzy;
	}

	public void setBotId(String botId){
		this.botId = botId;
	}

	public String getBotId(){
		return this.botId;
	}

	public void setBotIdFuzzy(String botIdFuzzy){
		this.botIdFuzzy = botIdFuzzy;
	}

	public String getBotIdFuzzy(){
		return this.botIdFuzzy;
	}

	public void setAddFlag(Integer addFlag){
		this.addFlag = addFlag;
	}

	public Integer getAddFlag(){
		return this.addFlag;
	}

}
