package com.easyding.entity.po.indeedPo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class IndeedJobRunHistoryDetailResponseBodyData implements Serializable {

    private String gmtModified;
    private Integer distributionType;
    private String modifyByRealName;
    private String workCreateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreated;
    private String departmentId;
    private String botRealName;
    private String createByRealName;
    private String machineName;
    private Integer executeType;
    private String jobUuid;
    private String workCreateByAccount;
    private Boolean isReference;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    private String reasonCode;
    private String jobName;
    private String departmentName;
    private String workCreateByRealName;
    private String modifyBy;
    private String createByAccount;
    private List<IndeedJobRunHistoryDetailResponseBodyDataWorkExecute> workExecutes;
    private String botName;
    private String modifyByAccount;
    private String machineOperateSystem;
    private String accountId;
    private String machineMac;
    private String createBy;
    private String botUuid;
    private String workUuid;
    private String failDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private Boolean isGroup;
    private String reasonCodeStr;
    private Integer status;
    private String machineInternalIp;
    private String machineIp;

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(Integer distributionType) {
        this.distributionType = distributionType;
    }

    public String getModifyByRealName() {
        return modifyByRealName;
    }

    public void setModifyByRealName(String modifyByRealName) {
        this.modifyByRealName = modifyByRealName;
    }

    public String getWorkCreateBy() {
        return workCreateBy;
    }

    public void setWorkCreateBy(String workCreateBy) {
        this.workCreateBy = workCreateBy;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getBotRealName() {
        return botRealName;
    }

    public void setBotRealName(String botRealName) {
        this.botRealName = botRealName;
    }

    public String getCreateByRealName() {
        return createByRealName;
    }

    public void setCreateByRealName(String createByRealName) {
        this.createByRealName = createByRealName;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Integer getExecuteType() {
        return executeType;
    }

    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }

    public String getJobUuid() {
        return jobUuid;
    }

    public void setJobUuid(String jobUuid) {
        this.jobUuid = jobUuid;
    }

    public String getWorkCreateByAccount() {
        return workCreateByAccount;
    }

    public void setWorkCreateByAccount(String workCreateByAccount) {
        this.workCreateByAccount = workCreateByAccount;
    }

    public Boolean getReference() {
        return isReference;
    }

    public void setReference(Boolean reference) {
        isReference = reference;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getWorkCreateByRealName() {
        return workCreateByRealName;
    }

    public void setWorkCreateByRealName(String workCreateByRealName) {
        this.workCreateByRealName = workCreateByRealName;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getCreateByAccount() {
        return createByAccount;
    }

    public void setCreateByAccount(String createByAccount) {
        this.createByAccount = createByAccount;
    }

    public List<IndeedJobRunHistoryDetailResponseBodyDataWorkExecute> getWorkExecutes() {
        return workExecutes;
    }

    public void setWorkExecutes(List<IndeedJobRunHistoryDetailResponseBodyDataWorkExecute> workExecutes) {
        this.workExecutes = workExecutes;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getModifyByAccount() {
        return modifyByAccount;
    }

    public void setModifyByAccount(String modifyByAccount) {
        this.modifyByAccount = modifyByAccount;
    }

    public String getMachineOperateSystem() {
        return machineOperateSystem;
    }

    public void setMachineOperateSystem(String machineOperateSystem) {
        this.machineOperateSystem = machineOperateSystem;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMachineMac() {
        return machineMac;
    }

    public void setMachineMac(String machineMac) {
        this.machineMac = machineMac;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getBotUuid() {
        return botUuid;
    }

    public void setBotUuid(String botUuid) {
        this.botUuid = botUuid;
    }

    public String getWorkUuid() {
        return workUuid;
    }

    public void setWorkUuid(String workUuid) {
        this.workUuid = workUuid;
    }

    public String getFailDescription() {
        return failDescription;
    }

    public void setFailDescription(String failDescription) {
        this.failDescription = failDescription;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getGroup() {
        return isGroup;
    }

    public void setGroup(Boolean group) {
        isGroup = group;
    }

    public String getReasonCodeStr() {
        return reasonCodeStr;
    }

    public void setReasonCodeStr(String reasonCodeStr) {
        this.reasonCodeStr = reasonCodeStr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMachineInternalIp() {
        return machineInternalIp;
    }

    public void setMachineInternalIp(String machineInternalIp) {
        this.machineInternalIp = machineInternalIp;
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    @Override
    public String toString() {
        return "IndeedJobRunHistoryDetailResponseBodyData{" +
                "gmtModified='" + gmtModified + '\'' +
                ", distributionType=" + distributionType +
                ", modifyByRealName='" + modifyByRealName + '\'' +
                ", workCreateBy='" + workCreateBy + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", departmentId='" + departmentId + '\'' +
                ", botRealName='" + botRealName + '\'' +
                ", createByRealName='" + createByRealName + '\'' +
                ", machineName='" + machineName + '\'' +
                ", executeType=" + executeType +
                ", jobUuid='" + jobUuid + '\'' +
                ", workCreateByAccount='" + workCreateByAccount + '\'' +
                ", isReference=" + isReference +
                ", startTime=" + startTime +
                ", reasonCode='" + reasonCode + '\'' +
                ", jobName='" + jobName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", workCreateByRealName='" + workCreateByRealName + '\'' +
                ", modifyBy='" + modifyBy + '\'' +
                ", createByAccount='" + createByAccount + '\'' +
                ", workExecutes=" + workExecutes +
                ", botName='" + botName + '\'' +
                ", modifyByAccount='" + modifyByAccount + '\'' +
                ", machineOperateSystem='" + machineOperateSystem + '\'' +
                ", accountId='" + accountId + '\'' +
                ", machineMac='" + machineMac + '\'' +
                ", createBy='" + createBy + '\'' +
                ", botUuid='" + botUuid + '\'' +
                ", workUuid='" + workUuid + '\'' +
                ", failDescription='" + failDescription + '\'' +
                ", endTime=" + endTime +
                ", isGroup=" + isGroup +
                ", reasonCodeStr='" + reasonCodeStr + '\'' +
                ", status=" + status +
                ", machineInternalIp='" + machineInternalIp + '\'' +
                ", machineIp='" + machineIp + '\'' +
                '}';
    }
}
