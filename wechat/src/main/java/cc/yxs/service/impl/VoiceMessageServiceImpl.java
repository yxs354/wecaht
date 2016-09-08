package cc.yxs.service.impl;

import cc.yxs.entity.InputMessage;
import cc.yxs.entity.WechatReminder;
import cc.yxs.service.MessageService;
import cc.yxs.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xusong.yuan
 * @date 16-9-2
 */
@Service
public class VoiceMessageServiceImpl extends BaseMessageService implements MessageService {
    @Autowired
    private ReminderService reminderService;

    @Override
    public String execute(InputMessage inputMsg) {
        WechatReminder reminder=new WechatReminder();
        reminder.setStatus(0);
        reminder.setContent(inputMsg.getRecognition());
        reminder.setOpenId(inputMsg.getFromUserName());
        reminderService.addReminder(reminder);
        return getReminderResonponse(inputMsg.getRecognition());
    }
}
