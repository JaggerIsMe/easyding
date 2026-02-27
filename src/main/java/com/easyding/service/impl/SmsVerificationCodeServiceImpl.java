package com.easyding.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.easyding.entity.enums.PageSize;
import com.easyding.entity.query.SmsVerificationCodeQuery;
import com.easyding.entity.po.otherPo.SmsVerificationCode;
import com.easyding.entity.vo.PaginationResultVO;
import com.easyding.entity.query.SimplePage;
import com.easyding.mappers.SmsVerificationCodeMapper;
import com.easyding.service.SmsVerificationCodeService;
import com.easyding.utils.StringTools;


/**
 * 手机短信验证码表 业务接口实现
 */
@Service("smsVerificationCodeService")
public class SmsVerificationCodeServiceImpl implements SmsVerificationCodeService {

	private static final Logger logger = LoggerFactory.getLogger(SmsVerificationCodeServiceImpl.class);

	@Resource
	private SmsVerificationCodeMapper<SmsVerificationCode, SmsVerificationCodeQuery> smsVerificationCodeMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<SmsVerificationCode> findListByParam(SmsVerificationCodeQuery param) {
		return this.smsVerificationCodeMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(SmsVerificationCodeQuery param) {
		return this.smsVerificationCodeMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<SmsVerificationCode> findListByPage(SmsVerificationCodeQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<SmsVerificationCode> list = this.findListByParam(param);
		PaginationResultVO<SmsVerificationCode> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(SmsVerificationCode bean) {
		return this.smsVerificationCodeMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<SmsVerificationCode> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.smsVerificationCodeMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<SmsVerificationCode> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.smsVerificationCodeMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(SmsVerificationCode bean, SmsVerificationCodeQuery param) {
		StringTools.checkParam(param);
		return this.smsVerificationCodeMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(SmsVerificationCodeQuery param) {
		StringTools.checkParam(param);
		return this.smsVerificationCodeMapper.deleteByParam(param);
	}

	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName获取对象
	 */
	@Override
	public SmsVerificationCode getSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(Long uTimestampId, String phoneNumber, String platformName) {
		return this.smsVerificationCodeMapper.selectByuTimestampIdAndPhoneNumberAndPlatformName(uTimestampId, phoneNumber, platformName);
	}

	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName修改
	 */
	@Override
	public Integer updateSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(SmsVerificationCode bean, Long uTimestampId, String phoneNumber, String platformName) {
		return this.smsVerificationCodeMapper.updateByuTimestampIdAndPhoneNumberAndPlatformName(bean, uTimestampId, phoneNumber, platformName);
	}

	/**
	 * 根据uTimestampIdAndPhoneNumberAndPlatformName删除
	 */
	@Override
	public Integer deleteSmsVerificationCodeByuTimestampIdAndPhoneNumberAndPlatformName(Long uTimestampId, String phoneNumber, String platformName) {
		return this.smsVerificationCodeMapper.deleteByuTimestampIdAndPhoneNumberAndPlatformName(uTimestampId, phoneNumber, platformName);
	}

	/**
	 * 接收新验证码
	 * (使老验证码过期)
	 *
	 * @param bean
	 */
	@Override
	public void receiveNewCode(SmsVerificationCode bean) {
		// 使老验证码过期
		SmsVerificationCodeQuery param = new SmsVerificationCodeQuery();
		param.setPhoneNumber(bean.getPhoneNumber());
		param.setPlatformName(bean.getPlatformName());
		param.setExpiredFlag(0);
		SmsVerificationCode updateBean = new SmsVerificationCode();
		updateBean.setExpiredFlag(1);
		this.updateByParam(updateBean, param);

		// 添加新验证码
		bean.setuTimestampId(System.currentTimeMillis());
		bean.setExpiredFlag(0);
		this.add(bean);
	}

}