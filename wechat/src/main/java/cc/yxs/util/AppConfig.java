package cc.yxs.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xusong.yuan
 * @date 16-8-29
 */
@Component
public class AppConfig {
    @Value("${wechat.app.appId}")
    private String appId;

    @Value("${wechat.app.appSecret}")
    private String appSecret;

    @Value("${wechat.app.appToken}")
    private String appToken;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }
}
