package com.easyding.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 手机短信验证码表 数据库操作接口
 */
public interface SmsVerificationCodeMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName更新
	 */
	 Integer updateByuTimestampIdAndPhoneNumberAndPlatformName(@Param("bean") T t,@Param("uTimestampId") Long uTimestampId,@Param("phoneNumber") String phoneNumber,@Param("platformName") String platformName);


	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName删除
	 */
	 Integer deleteByuTimestampIdAndPhoneNumberAndPlatformName(@Param("uTimestampId") Long uTimestampId,@Param("phoneNumber") String phoneNumber,@Param("platformName") String platformName);


	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName获取对象
	 */
	 T selectByuTimestampIdAndPhoneNumberAndPlatformName(@Param("uTimestampId") Long uTimestampId,@Param("phoneNumber") String phoneNumber,@Param("platformName") String platformName);


}
