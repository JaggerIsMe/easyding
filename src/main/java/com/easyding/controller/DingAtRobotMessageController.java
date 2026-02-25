package com.easyding.controller;

import com.easyding.entity.po.dingMsgPo.AtRobotMessage;
import com.easyding.entity.query.AtRobotMessageQuery;
import com.easyding.entity.vo.ResponseVO;
import com.easyding.service.AtRobotMessageService;
import com.easyding.service.DingService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.Map;


@RestController("dingAtRobotMessageController")
@RequestMapping("/dingAtRobotMessage")
public class DingAtRobotMessageController extends ABaseController {

    public static final Logger logger = LoggerFactory.getLogger(DingAtRobotMessageController.class);

    @Resource
    private DingService dingService;

    @Resource
    private AtRobotMessageService atRobotMessageService;

    /**
     * 根据条件分页查询
     */
    @RequestMapping("/loadDataList")
    public ResponseVO loadDataList(AtRobotMessageQuery query){
        return getSuccessResponseVO(atRobotMessageService.findListByPage(query));
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public ResponseVO add(AtRobotMessage bean) {
        atRobotMessageService.add(bean);
        return getSuccessResponseVO(null);
    }

    /**
     * 批量新增
     */
    @RequestMapping("/addBatch")
    public ResponseVO addBatch(@RequestBody List<AtRobotMessage> listBean) {
        atRobotMessageService.addBatch(listBean);
        return getSuccessResponseVO(null);
    }

    /**
     * 批量新增/修改
     */
    @RequestMapping("/addOrUpdateBatch")
    public ResponseVO addOrUpdateBatch(@RequestBody List<AtRobotMessage> listBean) {
        atRobotMessageService.addBatch(listBean);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据MsgIdAndSenderStaffIdAndAtDingtalkId查询对象
     */
    @RequestMapping("/getAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId")
    public ResponseVO getAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(String msgId,String senderStaffId,String atDingtalkId) {
        return getSuccessResponseVO(atRobotMessageService.getAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(msgId,senderStaffId,atDingtalkId));
    }

    /**
     * 根据MsgIdAndSenderStaffIdAndAtDingtalkId修改对象
     */
    @RequestMapping("/updateAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId")
    public ResponseVO updateAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(AtRobotMessage bean,String msgId,String senderStaffId,String atDingtalkId) {
        atRobotMessageService.updateAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(bean,msgId,senderStaffId,atDingtalkId);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据MsgIdAndSenderStaffIdAndAtDingtalkId删除
     */
    @RequestMapping("/deleteAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId")
    public ResponseVO deleteAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(String msgId,String senderStaffId,String atDingtalkId) {
        atRobotMessageService.deleteAtRobotMessageByMsgIdAndSenderStaffIdAndAtDingtalkId(msgId,senderStaffId,atDingtalkId);
        return getSuccessResponseVO(null);
    }

    /**
     * 获取accessToken
     */
    @RequestMapping("/getAccessToken")
    public ResponseVO getAccessToken() {
        return getSuccessResponseVO(dingService.getAccessToken());
    }

    /**
     * 保存钉钉群@机器人消息
     *
     * @param timestamp
     * @param sign
     * @param requestBody
     */
    @RequestMapping("/saveAtRobotMessage")
    public ResponseVO saveAtRobotMessage(@RequestHeader("timestamp") String timestamp, @RequestHeader("sign") String sign, @RequestBody Map<String, Object> requestBody) {
        dingService.saveAtRobotMessage(timestamp, sign, requestBody);
        return getSuccessResponseVO(null);
    }

}