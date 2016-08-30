package cc.yxs.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xusong.yuan
 * @date 16-8-29
 */
@Component
public class WechatConfig {
    @Value("${wechat.getAccessToken.url}")
    private String accessTokenUrl;

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }
}
