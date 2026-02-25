package com.easyding.entity.query;



/**
 * @机器人消息参数
 */
public class AtRobotMessageQuery extends BaseParam {


	/**
	 * 消息ID
	 */
	private String msgId;

	private String msgIdFuzzy;

	/**
	 * 发送者userid
	 */
	private String senderStaffId;

	private String senderStaffIdFuzzy;

	/**
	 * 加密的被@用户的id
	 */
	private String atDingtalkId;

	private String atDingtalkIdFuzzy;

	/**
	 * 被@用户的userId
	 */
	private String atStaffId;

	private String atStaffIdFuzzy;

	/**
	 * 被@的用户unionid
	 */
	private String atUnionId;

	private String atUnionIdFuzzy;

	/**
	 * 会话id
	 */
	private String conversationId;

	private String conversationIdFuzzy;

	/**
	 * 机器人编码
	 */
	private String robotCode;

	private String robotCodeFuzzy;

	/**
	 * 加密的机器人ID
	 */
	private String chatbotUserId;

	private String chatbotUserIdFuzzy;

	/**
	 * 加密的发送者ID
	 */
	private String senderId;

	private String senderIdFuzzy;

	/**
	 * 1：单聊  2：群聊
	 */
	private Integer conversationType;

	/**
	 * 群聊时才有的会话标题
	 */
	private String conversationTitle;

	private String conversationTitleFuzzy;

	/**
	 * 消息的时间戳，单位毫秒
	 */
	private Long createAt;

	/**
	 * 消息类型，文本text、语音audio、图片picture、文件file、视频file、富文本richText
	 */
	private String msgType;

	private String msgTypeFuzzy;

	/**
	 * 原消息请求体JSON
	 */
	private String msgJson;

	private String msgJsonFuzzy;

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

	public void setMsgIdFuzzy(String msgIdFuzzy){
		this.msgIdFuzzy = msgIdFuzzy;
	}

	public String getMsgIdFuzzy(){
		return this.msgIdFuzzy;
	}

	public void setSenderStaffId(String senderStaffId){
		this.senderStaffId = senderStaffId;
	}

	public String getSenderStaffId(){
		return this.senderStaffId;
	}

	public void setSenderStaffIdFuzzy(String senderStaffIdFuzzy){
		this.senderStaffIdFuzzy = senderStaffIdFuzzy;
	}

	public String getSenderStaffIdFuzzy(){
		return this.senderStaffIdFuzzy;
	}

	public void setAtDingtalkId(String atDingtalkId){
		this.atDingtalkId = atDingtalkId;
	}

	public String getAtDingtalkId(){
		return this.atDingtalkId;
	}

	public void setAtDingtalkIdFuzzy(String atDingtalkIdFuzzy){
		this.atDingtalkIdFuzzy = atDingtalkIdFuzzy;
	}

	public String getAtDingtalkIdFuzzy(){
		return this.atDingtalkIdFuzzy;
	}

	public void setAtStaffId(String atStaffId){
		this.atStaffId = atStaffId;
	}

	public String getAtStaffId(){
		return this.atStaffId;
	}

	public void setAtStaffIdFuzzy(String atStaffIdFuzzy){
		this.atStaffIdFuzzy = atStaffIdFuzzy;
	}

	public String getAtStaffIdFuzzy(){
		return this.atStaffIdFuzzy;
	}

	public void setAtUnionId(String atUnionId){
		this.atUnionId = atUnionId;
	}

	public String getAtUnionId(){
		return this.atUnionId;
	}

	public void setAtUnionIdFuzzy(String atUnionIdFuzzy){
		this.atUnionIdFuzzy = atUnionIdFuzzy;
	}

	public String getAtUnionIdFuzzy(){
		return this.atUnionIdFuzzy;
	}

	public void setConversationId(String conversationId){
		this.conversationId = conversationId;
	}

	public String getConversationId(){
		return this.conversationId;
	}

	public void setConversationIdFuzzy(String conversationIdFuzzy){
		this.conversationIdFuzzy = conversationIdFuzzy;
	}

	public String getConversationIdFuzzy(){
		return this.conversationIdFuzzy;
	}

	public void setRobotCode(String robotCode){
		this.robotCode = robotCode;
	}

	public String getRobotCode(){
		return this.robotCode;
	}

	public void setRobotCodeFuzzy(String robotCodeFuzzy){
		this.robotCodeFuzzy = robotCodeFuzzy;
	}

	public String getRobotCodeFuzzy(){
		return this.robotCodeFuzzy;
	}

	public void setChatbotUserId(String chatbotUserId){
		this.chatbotUserId = chatbotUserId;
	}

	public String getChatbotUserId(){
		return this.chatbotUserId;
	}

	public void setChatbotUserIdFuzzy(String chatbotUserIdFuzzy){
		this.chatbotUserIdFuzzy = chatbotUserIdFuzzy;
	}

	public String getChatbotUserIdFuzzy(){
		return this.chatbotUserIdFuzzy;
	}

	public void setSenderId(String senderId){
		this.senderId = senderId;
	}

	public String getSenderId(){
		return this.senderId;
	}

	public void setSenderIdFuzzy(String senderIdFuzzy){
		this.senderIdFuzzy = senderIdFuzzy;
	}

	public String getSenderIdFuzzy(){
		return this.senderIdFuzzy;
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

	public void setConversationTitleFuzzy(String conversationTitleFuzzy){
		this.conversationTitleFuzzy = conversationTitleFuzzy;
	}

	public String getConversationTitleFuzzy(){
		return this.conversationTitleFuzzy;
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

	public void setMsgTypeFuzzy(String msgTypeFuzzy){
		this.msgTypeFuzzy = msgTypeFuzzy;
	}

	public String getMsgTypeFuzzy(){
		return this.msgTypeFuzzy;
	}

	public void setMsgJson(String msgJson){
		this.msgJson = msgJson;
	}

	public String getMsgJson(){
		return this.msgJson;
	}

	public void setMsgJsonFuzzy(String msgJsonFuzzy){
		this.msgJsonFuzzy = msgJsonFuzzy;
	}

	public String getMsgJsonFuzzy(){
		return this.msgJsonFuzzy;
	}

	public void setConsumeFlag(Integer consumeFlag){
		this.consumeFlag = consumeFlag;
	}

	public Integer getConsumeFlag(){
		return this.consumeFlag;
	}

}
