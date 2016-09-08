package cc.yxs.service.impl;

import cc.yxs.WechatScopeEnum;
import cc.yxs.util.AppConfig;
import cc.yxs.util.WechatConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xusong.yuan
 * @date 16-9-2
 */
public class BaseMessageService {
    @Autowired
    private AppConfig appConfig;

    @Autowired
    private WechatConfig wechatConfig;

    protected String getReminderResonponse(String response) {
        String redirectUrl=String.format(wechatConfig.getPageRedirectUrl(),appConfig.getAppId(),appConfig.getReminderListPageUrl(), WechatScopeEnum.snsapi_base.name(),"");
        return "你的待办(" + response+ ")已记录，点击<a href='"+redirectUrl+"'> 待办列表 </a>查看,谢谢使用！";
    }
}
