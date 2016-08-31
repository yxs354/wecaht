package cc.yxs.service;

import cc.yxs.entity.WechatReminder;

import java.util.List;

/**
 * @author xusong.yuan
 * @date 16-8-31
 */
public interface ReminderService {
    List<WechatReminder> getReminderList(String openId);
}
