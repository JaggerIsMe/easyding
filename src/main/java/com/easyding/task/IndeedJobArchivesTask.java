package com.easyding.task;


import com.easyding.service.JobArchivesInfoService;
import com.easyding.service.JobRunHistoryInfoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class IndeedJobArchivesTask {

    @Resource
    private JobArchivesInfoService jobArchivesInfoService;

    @Resource
    private JobRunHistoryInfoService jobRunHistoryInfoService;

    @Scheduled(cron = "${task.cron.expression}", zone = "Asia/Shanghai")
    public void execute() {
        // reload
        jobRunHistoryInfoService.reloadJobRunHistory();
        // load
        jobRunHistoryInfoService.loadJobRunHistory();
        // grow age
        jobArchivesInfoService.growJobArchivesAge();
    }

}
