package com.easyding.service;

import java.util.List;

import com.easyding.entity.query.SmsVerificationCodeQuery;
import com.easyding.entity.po.otherPo.SmsVerificationCode;
import com.easyding.entity.vo.PaginationResultVO;


/**
 * 手机短信验证码表 业务接口
 */
public interface SmsVerificationCodeService {

	/**
	 * 根据条件查询列表
	 */
	List<SmsVerificationCode> findListByParam(SmsVerificationCodeQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(SmsVerificationCodeQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<SmsVerificationCode> findListByPage(SmsVerificationCodeQuery param);

	/**
	 * 新增
	 */
	Integer add(SmsVerificationCode bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<SmsVerificationCode> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<SmsVerificationCode> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(SmsVerificationCode bean,SmsVerificationCodeQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(SmsVerificationCodeQuery param);

	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName查询对象
	 */
	SmsVerificationCode getSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(Long uTimestampId,String phoneNumber,String platformName);


	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName修改
	 */
	Integer updateSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(SmsVerificationCode bean,Long uTimestampId,String phoneNumber,String platformName);


	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName删除
	 */
	Integer deleteSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(Long uTimestampId,String phoneNumber,String platformName);

	/**
	 * 接收新验证码
	 * (使老验证码过期)
	 */
	void receiveNewCode(SmsVerificationCode bean);

}