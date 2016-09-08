package cc.yxs.schedule;

import cc.yxs.entity.ReminderCount;
import cc.yxs.entity.ReminderTemplate;
import cc.yxs.entity.TemplateBean;
import cc.yxs.entity.WechatReminder;
import cc.yxs.service.ReminderService;
import cc.yxs.service.TemplateMessageService;
import cc.yxs.util.AppConfig;
import cc.yxs.util.WechatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author xusong.yuan
 * @date 16-9-2
 */
@Component
@Configurable
@EnableScheduling
public class ReminderSchedule {
    @Autowired
    private ReminderService reminderService;

    @Autowired
    private TemplateMessageService templateMessageService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private WechatConfig wechatConfig;



    @Scheduled(cron = "0 0 8,22 * * * ")
//    @Scheduled(cron = "0 * * * * * ")
    public void sendReminder() throws Exception {
        List<ReminderCount> needReminderList = reminderService.getReminderCountList();
        if (needReminderList != null && needReminderList.size() > 0) {
            for (ReminderCount reminderCount : needReminderList) {
                WechatReminder reminder = reminderService.getFirstReminder(reminderCount.getOpenId());
                sendTemplate(reminder.getOpenId(), reminder.getContent(), reminderCount.getCount());
            }
        }

    }

    private void sendTemplate(String openId,String sample,Integer count) throws Exception{
        ReminderTemplate reminderTemplate = new ReminderTemplate();
        reminderTemplate.setTouser(openId);
        reminderTemplate.setTemplate_id(appConfig.getReminderTemplateId());
        String url=String.format(wechatConfig.getPageRedirectUrl(),appConfig.getAppId(),appConfig.getReminderListPageUrl(),"snsapi_base","wx");
        reminderTemplate.setUrl(url);
        ReminderTemplate.ReminderTemplateData data=new ReminderTemplate.ReminderTemplateData();
        TemplateBean desc = new TemplateBean(sample, "blue");
        data.setDesc(desc);
        TemplateBean num = new TemplateBean(String.valueOf(count), "red");
        data.setNum(num);
        reminderTemplate.setData(data);
        templateMessageService.sendTemplate(reminderTemplate);
    }
}
