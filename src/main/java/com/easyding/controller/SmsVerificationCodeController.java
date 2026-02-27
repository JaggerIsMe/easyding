package com.easyding.controller;

import java.util.List;

import com.easyding.entity.query.SmsVerificationCodeQuery;
import com.easyding.entity.po.otherPo.SmsVerificationCode;
import com.easyding.entity.vo.ResponseVO;
import com.easyding.service.SmsVerificationCodeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 手机短信验证码表 Controller
 */
@RestController("smsVerificationCodeController")
@RequestMapping("/smsVerificationCode")
public class SmsVerificationCodeController extends ABaseController{

	@Resource
	private SmsVerificationCodeService smsVerificationCodeService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(SmsVerificationCodeQuery query){
		return getSuccessResponseVO(smsVerificationCodeService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(SmsVerificationCode bean) {
		smsVerificationCodeService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<SmsVerificationCode> listBean) {
		smsVerificationCodeService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<SmsVerificationCode> listBean) {
		smsVerificationCodeService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName查询对象
	 */
	@RequestMapping("/getSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName")
	public ResponseVO getSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(Long uTimestampId,String phoneNumber,String platformName) {
		return getSuccessResponseVO(smsVerificationCodeService.getSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(uTimestampId,phoneNumber,platformName));
	}

	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName修改对象
	 */
	@RequestMapping("/updateSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName")
	public ResponseVO updateSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(SmsVerificationCode bean,Long uTimestampId,String phoneNumber,String platformName) {
		smsVerificationCodeService.updateSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(bean,uTimestampId,phoneNumber,platformName);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName删除
	 */
	@RequestMapping("/deleteSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName")
	public ResponseVO deleteSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(Long uTimestampId,String phoneNumber,String platformName) {
		smsVerificationCodeService.deleteSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(uTimestampId,phoneNumber,platformName);
		return getSuccessResponseVO(null);
	}

	/**
	 * 新增
	 */
	@RequestMapping("/receiveNewCode")
	public ResponseVO receiveNewCode(@RequestBody SmsVerificationCode bean) {
		smsVerificationCodeService.receiveNewCode(bean);
		return getSuccessResponseVO(null);
	}

}