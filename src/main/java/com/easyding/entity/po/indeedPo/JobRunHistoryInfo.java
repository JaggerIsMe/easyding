package com.easyding.entity.po.indeedPo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.easyding.entity.enums.DateTimePatternEnum;
import com.easyding.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * job_run_history_info表
 */
public class JobRunHistoryInfo implements Serializable {


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
	 * 是否被统计，0未统计，1已统计
	 */
	private Integer addFlag;


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

	public void setAddFlag(Integer addFlag){
		this.addFlag = addFlag;
	}

	public Integer getAddFlag(){
		return this.addFlag;
	}

	@Override
	public String toString() {
		return "JobRunHistoryInfo{" +
				"jobUuid='" + jobUuid + '\'' +
				", workUuid='" + workUuid + '\'' +
				", jobName='" + jobName + '\'' +
				", status=" + status +
				", statusDesc='" + statusDesc + '\'' +
				", deptName='" + deptName + '\'' +
				", accountName='" + accountName + '\'' +
				", realName='" + realName + '\'' +
				", gmtCreated=" + gmtCreated +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", runTime=" + runTime +
				", botUuid='" + botUuid + '\'' +
				", botId='" + botId + '\'' +
				", addFlag=" + addFlag +
				'}';
	}
}
