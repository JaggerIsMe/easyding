package com.easyding.controller;

import com.aliyun.dingtalknotable_1_0.models.InsertRecordsRequest;
import com.aliyun.dingtalknotable_1_0.models.ListRecordsResponseBody;
import com.aliyun.dingtalknotable_1_0.models.UpdateRecordsRequest;
import com.easyding.entity.vo.ResponseVO;
import com.easyding.service.DingService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController("dingNoTableController")
@RequestMapping("/dingNoTable")
public class DingNoTableController extends ABaseController {

    @Resource
    private DingService dingService;

    /**
     * 获取accessToken
     */
    @RequestMapping("/getAccessToken")
    public ResponseVO getAccessToken() {
        return getSuccessResponseVO(dingService.getAccessToken());
    }

    /**
     * 添加表格数据
     *
     * @param baseId
     * @param sheetId
     */
    @RequestMapping("/addRecords")
    public ResponseVO addRecords(@RequestParam("baseId") String baseId, @RequestParam("sheetId") String sheetId, @RequestBody List<InsertRecordsRequest.InsertRecordsRequestRecords> records) {
        dingService.addRecords(baseId, sheetId, records);
        return getSuccessResponseVO(null);
    }

    /**
     * 获取钉钉表格数据
     * 获取所有记录列表
     *
     * @param baseId
     * @param sheetId
     * @return
     */
    @RequestMapping("/listAllRecords")
    public ResponseVO listAllRecords(@RequestParam("baseId") String baseId, @RequestParam("sheetId") String sheetId) {
        List<ListRecordsResponseBody.ListRecordsResponseBodyRecords> records = dingService.listAllRecords(baseId, sheetId);
        return getSuccessResponseVO(records);
    }

    /**
     * 更新表格数据
     *
     * @param baseId
     * @param sheetId
     * @param records0
     */
    @RequestMapping("/updateRecord")
    public ResponseVO updateRecord(@RequestParam("baseId") String baseId, @RequestParam("sheetId") String sheetId, @RequestBody UpdateRecordsRequest.UpdateRecordsRequestRecords records0) {
        dingService.updateRecord(baseId, sheetId, records0);
        return getSuccessResponseVO(null);
    }

}