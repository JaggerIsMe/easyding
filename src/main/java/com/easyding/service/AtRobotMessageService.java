package com.easyding.service;

import java.util.List;

import com.easyding.entity.query.AtRobotMessageQuery;
import com.easyding.entity.po.dingMsgPo.AtRobotMessage;
import com.easyding.entity.vo.PaginationResultVO;


/**
 * @机器人消息 业务接口
 */
public interface AtRobotMessageService {

	/**
	 * 根据条件查询列表
	 */
	List<AtRobotMessage> findListByParam(AtRobotMessageQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(AtRobotMessageQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<AtRobotMessage> findListByPage(AtRobotMessageQuery param);

	/**
	 * 新增
	 */
	Integer add(AtRobotMessage bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<AtRobotMessage> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<AtRobotMessage> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(AtRobotMessage bean,AtRobotMessageQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(AtRobotMessageQuery param);

	/**
	 * 根据MsgIdAndSenderStaffIdAndAtDingtalkId查询对象
	 */
	AtRobotMessage getAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(String msgId,String senderStaffId,String atDingtalkId);


	/**
	 * 根据MsgIdAndSenderStaffIdAndAtDingtalkId修改
	 */
	Integer updateAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(AtRobotMessage bean,String msgId,String senderStaffId,String atDingtalkId);


	/**
	 * 根据MsgIdAndSenderStaffIdAndAtDingtalkId删除
	 */
	Integer deleteAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(String msgId,String senderStaffId,String atDingtalkId);

}