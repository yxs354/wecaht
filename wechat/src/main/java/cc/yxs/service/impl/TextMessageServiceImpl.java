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
public class TextMessageServiceImpl extends BaseMessageService implements MessageService {
    @Autowired
    private ReminderService reminderService;

    @Override
    public String execute(InputMessage inputMsg) {
        String input=inputMsg.getContent();
        if(input!=null){
            input=input.trim();
            if(input.length()>0){
                if("help".equals(input)){
                    return "发送文本或者语音消息备忘待办事项,点击待办列表查看并设置已办,待办提醒即将上线";
                }else{
                    WechatReminder reminder=new WechatReminder();
                    reminder.setStatus(0);
                    reminder.setContent(inputMsg.getContent());
                    reminder.setOpenId(inputMsg.getFromUserName());
                    reminderService.addReminder(reminder);
                    return getReminderResonponse(inputMsg.getContent());
                }
            }
        }
        return "";
    }
}
