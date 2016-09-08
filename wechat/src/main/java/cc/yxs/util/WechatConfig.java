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

    @Value("${wechat.getPageAccessToken.url}")
    private String pageAccessTokenUrl;

    @Value("${wechat.getPageRedirect.url}")
    private String pageRedirectUrl;

    @Value("${wechat.sendTemplate.url}")
    private String sendTemplateUrl;

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getPageAccessTokenUrl() {
        return pageAccessTokenUrl;
    }

    public void setPageAccessTokenUrl(String pageAccessTokenUrl) {
        this.pageAccessTokenUrl = pageAccessTokenUrl;
    }

    public String getPageRedirectUrl() {
        return pageRedirectUrl;
    }

    public void setPageRedirectUrl(String pageRedirectUrl) {
        this.pageRedirectUrl = pageRedirectUrl;
    }

    public String getSendTemplateUrl() {
        return sendTemplateUrl;
    }

    public void setSendTemplateUrl(String sendTemplateUrl) {
        this.sendTemplateUrl = sendTemplateUrl;
    }
}
