package com.easyding.entity.po.otherPo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 手机短信验证码表
 */
public class SmsVerificationCode implements Serializable {


	/**
	 * 毫秒级时间戳作唯一ID
	 */
	private Long uTimestampId;

	/**
	 * 手机号
	 */
	private String phoneNumber;

	/**
	 * 平台名称
	 */
	private String platformName;

	/**
	 * 验证码
	 */
	private String verificationCode;

	/**
	 * 过期标识，0：未过期，1：已过期
	 */
	private Integer expiredFlag;


	public void setuTimestampId(Long uTimestampId){
		this.uTimestampId = uTimestampId;
	}

	public Long getuTimestampId(){
		return this.uTimestampId;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return this.phoneNumber;
	}

	public void setPlatformName(String platformName){
		this.platformName = platformName;
	}

	public String getPlatformName(){
		return this.platformName;
	}

	public void setVerificationCode(String verificationCode){
		this.verificationCode = verificationCode;
	}

	public String getVerificationCode(){
		return this.verificationCode;
	}

	public void setExpiredFlag(Integer expiredFlag){
		this.expiredFlag = expiredFlag;
	}

	public Integer getExpiredFlag(){
		return this.expiredFlag;
	}

	@Override
	public String toString() {
		return "SmsVerificationCode{" +
				"uTimestampId=" + uTimestampId +
				", phoneNumber='" + phoneNumber + '\'' +
				", platformName='" + platformName + '\'' +
				", verificationCode='" + verificationCode + '\'' +
				", expiredFlag=" + expiredFlag +
				'}';
	}
}
