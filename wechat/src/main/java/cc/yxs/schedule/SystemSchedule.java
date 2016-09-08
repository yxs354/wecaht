package cc.yxs.schedule;

import cc.yxs.entity.ReminderTemplate;
import cc.yxs.entity.TemplateBean;
import cc.yxs.service.ReminderService;
import cc.yxs.service.TemplateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author xusong.yuan
 * @date 16-9-2
 */
@Component
@Configurable
@EnableScheduling
public class SystemSchedule {
    @Autowired
    private ReminderService reminderService;

    @Scheduled(cron = "0 0/5 * * * * ")
    public void reconnectDb(){
        reminderService.getReminderList("111");
    }

}
