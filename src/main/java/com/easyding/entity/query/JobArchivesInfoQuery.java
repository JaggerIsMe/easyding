package com.easyding.entity.query;

import java.math.BigDecimal;


/**
 * 参数
 */
public class JobArchivesInfoQuery extends BaseParam {


	/**
	 * 任务UUID
	 */
	private String jobUuid;

	private String jobUuidFuzzy;

	/**
	 * 任务年代
	 */
	private String age;

	private String ageFuzzy;

	/**
	 * 任务名
	 */
	private String jobName;

	private String jobNameFuzzy;

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

	public void setJobUuidFuzzy(String jobUuidFuzzy){
		this.jobUuidFuzzy = jobUuidFuzzy;
	}

	public String getJobUuidFuzzy(){
		return this.jobUuidFuzzy;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return this.age;
	}

	public void setAgeFuzzy(String ageFuzzy){
		this.ageFuzzy = ageFuzzy;
	}

	public String getAgeFuzzy(){
		return this.ageFuzzy;
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

}
