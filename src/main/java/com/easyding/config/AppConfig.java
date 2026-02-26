package com.easyding.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("appConfig")
public class AppConfig {

    @Value("${ding.appKey:}")
    private String dingAppKey;

    @Value("${ding.appSecret:}")
    private String dingAppSecret;

    @Value("${ding.robotCode:}")
    private String dingRobotCode;

    @Value("${ding.unionId}")
    private String dingUnionId;

    @Value("${ding.userId}")
    private String dingUserId;

    @Value("${ding.cardCallbackRouteKey}")
    private String dingCardCallbackRouteKey;

    @Value("${ding.mpBaseId}")
    private String dingMpBaseId;

    @Value("${ding.mpSheetId}")
    private String dingMpSheetId;

    @Value("${ding.hsSheetId}")
    private String dingHsSheetId;

    @Value("${indeed.commandJobAPIUrl}")
    private String indeedApiUrl;

    @Value("${indeed.appKey}")
    private String indeedAppKey;

    @Value("${indeed.appSecret}")
    private String indeedAppSecret;

    public String getDingAppKey() {
        return dingAppKey;
    }

    public String getDingAppSecret() {
        return dingAppSecret;
    }

    public String getDingRobotCode() {
        return dingRobotCode;
    }

    public String getDingUnionId() {
        return dingUnionId;
    }

    public String getDingUserId() {
        return dingUserId;
    }

    public String getDingCardCallbackRouteKey() {
        return dingCardCallbackRouteKey;
    }

    public String getDingMpBaseId() {
        return dingMpBaseId;
    }

    public String getDingMpSheetId() {
        return dingMpSheetId;
    }

    public String getDingHsSheetId() {
        return dingHsSheetId;
    }

    public String getIndeedApiUrl() {
        return indeedApiUrl;
    }

    public String getIndeedAppKey() {
        return indeedAppKey;
    }

    public String getIndeedAppSecret() {
        return indeedAppSecret;
    }
}
