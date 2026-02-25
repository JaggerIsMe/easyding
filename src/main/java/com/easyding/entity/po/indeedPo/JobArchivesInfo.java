package com.easyding.entity.po.indeedPo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.io.Serializable;


/**
 * job_archives_info表
 */
public class JobArchivesInfo implements Serializable {


	/**
	 * 任务UUID
	 */
	private String jobUuid;

	/**
	 * 任务年代
	 */
	private String age;

	/**
	 * 任务名
	 */
	private String jobName;

	/**
	 * 总运行时长(H)
	 */
	private BigDecimal totalRunTime;

	/**
	 * 总运行次数
	 */
	private Integer totalCounts;

	/**
	 * 总运行成功次数
	 */
	private Integer totalSuccessCounts;

	/**
	 * 总运行失败次数
	 */
	private Integer totalFailCounts;


	public void setJobUuid(String jobUuid){
		this.jobUuid = jobUuid;
	}

	public String getJobUuid(){
		return this.jobUuid;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return this.age;
	}

	public void setJobName(String jobName){
		this.jobName = jobName;
	}

	public String getJobName(){
		return this.jobName;
	}

	public void setTotalRunTime(BigDecimal totalRunTime){
		this.totalRunTime = totalRunTime;
	}

	public BigDecimal getTotalRunTime(){
		return this.totalRunTime;
	}

	public void setTotalCounts(Integer totalCounts){
		this.totalCounts = totalCounts;
	}

	public Integer getTotalCounts(){
		return this.totalCounts;
	}

	public void setTotalSuccessCounts(Integer totalSuccessCounts){
		this.totalSuccessCounts = totalSuccessCounts;
	}

	public Integer getTotalSuccessCounts(){
		return this.totalSuccessCounts;
	}

	public void setTotalFailCounts(Integer totalFailCounts){
		this.totalFailCounts = totalFailCounts;
	}

	public Integer getTotalFailCounts(){
		return this.totalFailCounts;
	}

	@Override
	public String toString() {
		return "JobArchivesInfo{" +
				"jobUuid='" + jobUuid + '\'' +
				", age='" + age + '\'' +
				", jobName='" + jobName + '\'' +
				", totalRunTime=" + totalRunTime +
				", totalCounts=" + totalCounts +
				", totalSuccessCounts=" + totalSuccessCounts +
				", totalFailCounts=" + totalFailCounts +
				'}';
	}
}
