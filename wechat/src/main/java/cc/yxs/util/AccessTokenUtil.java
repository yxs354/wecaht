package cc.yxs.util;

import cc.yxs.entity.Token;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xusong.yuan
 * @date 16-8-26
 */
@Component
public class AccessTokenUtil {
    @Autowired
    private AppConfig appConfig;

    @Autowired
    private WechatConfig wechatConfig;

    private static HttpClientUtils http = HttpClientUtils.build();

    private static final Map<String, Token> tokenCache = new HashMap<>();

    public Token getAppToken(String appId) throws Exception {
        if(appId==null){
            appId = appConfig.getAppId();
        }
        Token token=getTokenFromCache(appId);
        if(token==null){
            String appSecret = appConfig.getAppSecret();
            String url = wechatConfig.getAccessTokenUrl();
            url = String.format(url, appId, appSecret);
            String tokenResult = http.get(url);
            token = JSONObject.parseObject(tokenResult, Token.class);
            tokenCache.put(appId,token);
        }
        return token;
    }
    private Token getTokenFromCache(String appId){
        if(tokenCache.containsKey(appId)){
            Token token=tokenCache.get(appId);
            if((System.currentTimeMillis()-token.getCreateDate().getTime())>(token.getExpires_in()-200)*1000){
                return null;
            }
            return token;
        }
        return null;
    }
}
