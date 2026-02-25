package com.easyding.entity.po.dingMsgPo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * @机器人消息
 */
public class AtRobotMessage implements Serializable {


	/**
	 * 消息ID
	 */
	private String msgId;

	/**
	 * 发送者userid
	 */
	private String senderStaffId;

	/**
	 * 加密的被@用户的id
	 */
	private String atDingtalkId;

	/**
	 * 被@用户的userId
	 */
	private String atStaffId;

	/**
	 * 被@的用户unionid
	 */
	private String atUnionId;

	/**
	 * 会话id
	 */
	private String conversationId;

	/**
	 * 机器人编码
	 */
	private String robotCode;

	/**
	 * 加密的机器人ID
	 */
	private String chatbotUserId;

	/**
	 * 加密的发送者ID
	 */
	private String senderId;

	/**
	 * 1：单聊  2：群聊
	 */
	private Integer conversationType;

	/**
	 * 群聊时才有的会话标题
	 */
	private String conversationTitle;

	/**
	 * 消息的时间戳，单位毫秒
	 */
	private Long createAt;

	/**
	 * 消息类型，文本text、语音audio、图片picture、文件file、视频file、富文本richText
	 */
	private String msgType;

	/**
	 * 原消息请求体JSON
	 */
	private String msgJson;

	/**
	 * 0：未消费，1：已消费
	 */
	private Integer consumeFlag;


	public void setMsgId(String msgId){
		this.msgId = msgId;
	}

	public String getMsgId(){
		return this.msgId;
	}

	public void setSenderStaffId(String senderStaffId){
		this.senderStaffId = senderStaffId;
	}

	public String getSenderStaffId(){
		return this.senderStaffId;
	}

	public void setAtDingtalkId(String atDingtalkId){
		this.atDingtalkId = atDingtalkId;
	}

	public String getAtDingtalkId(){
		return this.atDingtalkId;
	}

	public void setAtStaffId(String atStaffId){
		this.atStaffId = atStaffId;
	}

	public String getAtStaffId(){
		return this.atStaffId;
	}

	public void setAtUnionId(String atUnionId){
		this.atUnionId = atUnionId;
	}

	public String getAtUnionId(){
		return this.atUnionId;
	}

	public void setConversationId(String conversationId){
		this.conversationId = conversationId;
	}

	public String getConversationId(){
		return this.conversationId;
	}

	public void setRobotCode(String robotCode){
		this.robotCode = robotCode;
	}

	public String getRobotCode(){
		return this.robotCode;
	}

	public void setChatbotUserId(String chatbotUserId){
		this.chatbotUserId = chatbotUserId;
	}

	public String getChatbotUserId(){
		return this.chatbotUserId;
	}

	public void setSenderId(String senderId){
		this.senderId = senderId;
	}

	public String getSenderId(){
		return this.senderId;
	}

	public void setConversationType(Integer conversationType){
		this.conversationType = conversationType;
	}

	public Integer getConversationType(){
		return this.conversationType;
	}

	public void setConversationTitle(String conversationTitle){
		this.conversationTitle = conversationTitle;
	}

	public String getConversationTitle(){
		return this.conversationTitle;
	}

	public void setCreateAt(Long createAt){
		this.createAt = createAt;
	}

	public Long getCreateAt(){
		return this.createAt;
	}

	public void setMsgType(String msgType){
		this.msgType = msgType;
	}

	public String getMsgType(){
		return this.msgType;
	}

	public void setMsgJson(String msgJson){
		this.msgJson = msgJson;
	}

	public String getMsgJson(){
		return this.msgJson;
	}

	public void setConsumeFlag(Integer consumeFlag){
		this.consumeFlag = consumeFlag;
	}

	public Integer getConsumeFlag(){
		return this.consumeFlag;
	}

	@Override
	public String toString() {
		return "AtRobotMessage{" +
				"msgId='" + msgId + '\'' +
				", senderStaffId='" + senderStaffId + '\'' +
				", atDingtalkId='" + atDingtalkId + '\'' +
				", atStaffId='" + atStaffId + '\'' +
				", atUnionId='" + atUnionId + '\'' +
				", conversationId='" + conversationId + '\'' +
				", robotCode='" + robotCode + '\'' +
				", chatbotUserId='" + chatbotUserId + '\'' +
				", senderId='" + senderId + '\'' +
				", conversationType=" + conversationType +
				", conversationTitle='" + conversationTitle + '\'' +
				", createAt=" + createAt +
				", msgType='" + msgType + '\'' +
				", msgJson='" + msgJson + '\'' +
				", consumeFlag=" + consumeFlag +
				'}';
	}
}
