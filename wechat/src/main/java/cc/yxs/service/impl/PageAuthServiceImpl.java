package cc.yxs.service.impl;

import cc.yxs.entity.PageToken;
import cc.yxs.service.PageAuthService;
import cc.yxs.util.AppConfig;
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
public class PageAuthServiceImpl implements PageAuthService {

    private static HttpClientUtils http = HttpClientUtils.build();

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private WechatConfig wechatConfig;

    @Override
    public String getOpenIdByPageCode(String code) {
        try{
            String url=String.format(wechatConfig.getPageAccessTokenUrl(),appConfig.getAppId(),appConfig.getAppSecret(),code);
            String resultString=http.get(url);
            PageToken pageToken= JSONObject.parseObject(resultString, PageToken.class);
            return pageToken.getOpenid();
        }catch (Exception e){
            System.out.println("error get openId from code "+e.getMessage());
            return null;
        }

    }
}
