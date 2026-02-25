package com.easyding.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @机器人消息 数据库操作接口
 */
public interface AtRobotMessageMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据MsgIdAndSenderStaffIdAndAtDingtalkId更新
	 */
	 Integer updateByMsgIdAndSenderStaffIdAndAtDingtalkId(@Param("bean") T t,@Param("msgId") String msgId,@Param("senderStaffId") String senderStaffId,@Param("atDingtalkId") String atDingtalkId);


	/**
	 * 根据MsgIdAndSenderStaffIdAndAtDingtalkId删除
	 */
	 Integer deleteByMsgIdAndSenderStaffIdAndAtDingtalkId(@Param("msgId") String msgId,@Param("senderStaffId") String senderStaffId,@Param("atDingtalkId") String atDingtalkId);


	/**
	 * 根据MsgIdAndSenderStaffIdAndAtDingtalkId获取对象
	 */
	 T selectByMsgIdAndSenderStaffIdAndAtDingtalkId(@Param("msgId") String msgId,@Param("senderStaffId") String senderStaffId,@Param("atDingtalkId") String atDingtalkId);


}
