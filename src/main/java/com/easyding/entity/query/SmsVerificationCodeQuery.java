package com.easyding.entity.query;



/**
 * 手机短信验证码表参数
 */
public class SmsVerificationCodeQuery extends BaseParam {


	/**
	 * 毫秒级时间戳作唯一ID
	 */
	private Long uTimestampId;

	/**
	 * 手机号
	 */
	private String phoneNumber;

	private String phoneNumberFuzzy;

	/**
	 * 平台名称
	 */
	private String platformName;

	private String platformNameFuzzy;

	/**
	 * 验证码
	 */
	private String verificationCode;

	private String verificationCodeFuzzy;

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

	public void setPhoneNumberFuzzy(String phoneNumberFuzzy){
		this.phoneNumberFuzzy = phoneNumberFuzzy;
	}

	public String getPhoneNumberFuzzy(){
		return this.phoneNumberFuzzy;
	}

	public void setPlatformName(String platformName){
		this.platformName = platformName;
	}

	public String getPlatformName(){
		return this.platformName;
	}

	public void setPlatformNameFuzzy(String platformNameFuzzy){
		this.platformNameFuzzy = platformNameFuzzy;
	}

	public String getPlatformNameFuzzy(){
		return this.platformNameFuzzy;
	}

	public void setVerificationCode(String verificationCode){
		this.verificationCode = verificationCode;
	}

	public String getVerificationCode(){
		return this.verificationCode;
	}

	public void setVerificationCodeFuzzy(String verificationCodeFuzzy){
		this.verificationCodeFuzzy = verificationCodeFuzzy;
	}

	public String getVerificationCodeFuzzy(){
		return this.verificationCodeFuzzy;
	}

	public void setExpiredFlag(Integer expiredFlag){
		this.expiredFlag = expiredFlag;
	}

	public Integer getExpiredFlag(){
		return this.expiredFlag;
	}

}
