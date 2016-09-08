package cc.yxs.service.impl;

import cc.yxs.entity.InputMessage;
import cc.yxs.entity.WechatReminder;
import cc.yxs.service.MessageService;
import cc.yxs.service.ReminderService;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xusong.yuan
 * @date 16-9-2
 */
@Service
public class EventMessageServiceImpl extends BaseMessageService implements MessageService {

    @Override
    public String execute(InputMessage inputMsg) {
        String eventType=inputMsg.getEvent();
        if("subscribe".equals(eventType)){
            return "欢迎关注,不定期更新各种小功能.您的数据可能随时被清,您也可能随时会被踢,不用谢!发送help查看帮助";
        }
        return "";
    }
}
