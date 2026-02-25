package com.easyding.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easyding.entity.enums.PageSize;
import com.easyding.entity.query.AtRobotMessageQuery;
import com.easyding.entity.po.dingMsgPo.AtRobotMessage;
import com.easyding.entity.vo.PaginationResultVO;
import com.easyding.entity.query.SimplePage;
import com.easyding.mappers.AtRobotMessageMapper;
import com.easyding.service.AtRobotMessageService;
import com.easyding.utils.StringTools;


/**
 * @机器人消息 业务接口实现
 */
@Service("atRobotMessageService")
public class AtRobotMessageServiceImpl implements AtRobotMessageService {

	@Resource
	private AtRobotMessageMapper<AtRobotMessage, AtRobotMessageQuery> atRobotMessageMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<AtRobotMessage> findListByParam(AtRobotMessageQuery param) {
		return this.atRobotMessageMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(AtRobotMessageQuery param) {
		return this.atRobotMessageMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<AtRobotMessage> findListByPage(AtRobotMessageQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<AtRobotMessage> list = this.findListByParam(param);
		PaginationResultVO<AtRobotMessage> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(AtRobotMessage bean) {
		return this.atRobotMessageMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<AtRobotMessage> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.atRobotMessageMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<AtRobotMessage> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.atRobotMessageMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(AtRobotMessage bean, AtRobotMessageQuery param) {
		StringTools.checkParam(param);
		return this.atRobotMessageMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(AtRobotMessageQuery param) {
		StringTools.checkParam(param);
		return this.atRobotMessageMapper.deleteByParam(param);
	}

	/**
	 * 根据MsgIdAndSenderStaffIdAndAtDingtalkId获取对象
	 */
	@Override
	public AtRobotMessage getAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(String msgId, String senderStaffId, String atDingtalkId) {
		return this.atRobotMessageMapper.selectByMsgIdAndSenderStaffIdAndAtDingtalkId(msgId, senderStaffId, atDingtalkId);
	}

	/**
	 * 根据MsgIdAndSenderStaffIdAndAtDingtalkId修改
	 */
	@Override
	public Integer updateAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(AtRobotMessage bean, String msgId, String senderStaffId, String atDingtalkId) {
		return this.atRobotMessageMapper.updateByMsgIdAndSenderStaffIdAndAtDingtalkId(bean, msgId, senderStaffId, atDingtalkId);
	}

	/**
	 * 根据MsgIdAndSenderStaffIdAndAtDingtalkId删除
	 */
	@Override
	public Integer deleteAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(String msgId, String senderStaffId, String atDingtalkId) {
		return this.atRobotMessageMapper.deleteByMsgIdAndSenderStaffIdAndAtDingtalkId(msgId, senderStaffId, atDingtalkId);
	}
}