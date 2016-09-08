package cc.yxs.service.impl;

import cc.yxs.entity.ReminderCount;
import cc.yxs.entity.WechatReminder;
import cc.yxs.mapper.WechatReminderMapper;
import cc.yxs.service.MessageService;
import cc.yxs.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xusong.yuan
 * @date 16-8-31
 */
@Service
public class ReminderServiceImpl implements ReminderService{
    @Autowired
    private WechatReminderMapper mapper;

    @Override
    public List<WechatReminder> getReminderList(String openId) {
        return mapper.getReminderList(openId,0);
    }

    @Override
    public WechatReminder addReminder(WechatReminder reminder) {
        int i=mapper.add(reminder);
        return reminder;
    }

    @Override
    public void setReminderStatus(Long id, Integer status) {
        mapper.updateReminderStatus(id,status);
    }

    @Override
    public List<ReminderCount> getReminderCountList() {
        return mapper.getNeedRemindOpenIdList();
    }

    @Override
    public WechatReminder getFirstReminder(String openId) {
        return mapper.getFirstReminderByOpenIdAndStatus(openId,0);
    }
}
