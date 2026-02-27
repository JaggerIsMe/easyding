package com.easyding.entity.po.indeedPo;

import com.easyding.entity.enums.DateTimePatternEnum;
import com.easyding.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 任务执行结果通知表
 */
public class JobRunResultNotificationInfo implements Serializable {


	/**
	 * 任务UUID
	 */
	private String jobUuid;

	/**
	 * 执行UUID
	 */
	private String workUuid;

	/**
	 * 任务名称
	 */
	private String jobName;

	/**
	 * 状态码，1运行中，2待运行，3运行失败，4已停止，5运行成功
	 */
	private Integer status;

	/**
	 * 状态描述
	 */
	private String statusDesc;

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 账户名称
	 */
	private String accountName;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreated;

	/**
	 * 开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	/**
	 * 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	/**
	 * 运行时长(秒)
	 */
	private Integer runTime;

	/**
	 * 机器人UUID
	 */
	private String botUuid;

	/**
	 * 机器人ID
	 */
	private String botId;

	/**
	 * 报错信息
	 */
	private String failDescription;

	/**
	 * 是否发送通知，0未通知，1已通知
	 */
	private Integer notificationFlag;


	public void setJobUuid(String jobUuid){
		this.jobUuid = jobUuid;
	}

	public String getJobUuid(){
		return this.jobUuid;
	}

	public void setWorkUuid(String workUuid){
		this.workUuid = workUuid;
	}

	public String getWorkUuid(){
		return this.workUuid;
	}

	public void setJobName(String jobName){
		this.jobName = jobName;
	}

	public String getJobName(){
		return this.jobName;
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

	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	public String getDeptName(){
		return this.deptName;
	}

	public void setAccountName(String accountName){
		this.accountName = accountName;
	}

	public String getAccountName(){
		return this.accountName;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	public String getRealName(){
		return this.realName;
	}

	public void setGmtCreated(Date gmtCreated){
		this.gmtCreated = gmtCreated;
	}

	public Date getGmtCreated(){
		return this.gmtCreated;
	}

	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}

	public Date getStartTime(){
		return this.startTime;
	}

	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}

	public Date getEndTime(){
		return this.endTime;
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

	public void setBotId(String botId){
		this.botId = botId;
	}

	public String getBotId(){
		return this.botId;
	}

	public void setFailDescription(String failDescription){
		this.failDescription = failDescription;
	}

	public String getFailDescription(){
		return this.failDescription;
	}

	public void setNotificationFlag(Integer notificationFlag){
		this.notificationFlag = notificationFlag;
	}

	public Integer getNotificationFlag(){
		return this.notificationFlag;
	}

	@Override
	public String toString (){
		return "任务UUID:"+(jobUuid == null ? "空" : jobUuid)+"，执行UUID:"+(workUuid == null ? "空" : workUuid)+"，任务名称:"+(jobName == null ? "空" : jobName)+"，状态码，1运行中，2待运行，3运行失败，4已停止，5运行成功:"+(status == null ? "空" : status)+"，状态描述:"+(statusDesc == null ? "空" : statusDesc)+"，部门名称:"+(deptName == null ? "空" : deptName)+"，账户名称:"+(accountName == null ? "空" : accountName)+"，真实姓名:"+(realName == null ? "空" : realName)+"，创建时间:"+(gmtCreated == null ? "空" : DateUtil.format(gmtCreated, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，开始时间:"+(startTime == null ? "空" : DateUtil.format(startTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，结束时间:"+(endTime == null ? "空" : DateUtil.format(endTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，运行时长(秒):"+(runTime == null ? "空" : runTime)+"，机器人UUID:"+(botUuid == null ? "空" : botUuid)+"，机器人ID:"+(botId == null ? "空" : botId)+"，报错信息:"+(failDescription == null ? "空" : failDescription)+"，是否发送通知，0未通知，1已通知:"+(notificationFlag == null ? "空" : notificationFlag);
	}
}
