package cc.yxs.service.impl;

import cc.yxs.entity.WechatTemplateMessage;
import cc.yxs.service.TemplateMessageService;
import cc.yxs.util.HttpClientUtils;
import cc.yxs.util.WechatConfig;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xusong.yuan
 * @date 16-9-2
 */
@Service
public class TemplateServiceImpl implements TemplateMessageService{

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private WechatConfig wechatConfig;


    @Override
    public void sendTemplate(WechatTemplateMessage template) throws Exception{
        String accessToken=tokenService.getAccessToken();
        String url=String.format(wechatConfig.getSendTemplateUrl(),accessToken);
        String body= JSONObject.toJSONString(template);
        String response=HttpClientUtils.build().post(url,body);
    }
}
