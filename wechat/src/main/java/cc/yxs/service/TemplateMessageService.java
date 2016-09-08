package cc.yxs.service;

import cc.yxs.entity.WechatTemplateMessage;

/**
 * @author xusong.yuan
 * @date 16-9-2
 */
public interface TemplateMessageService {
    void sendTemplate(WechatTemplateMessage template) throws Exception;
}
