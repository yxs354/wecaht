package cc.yxs.service;

/**
 * @author xusong.yuan
 * @date 16-8-29
 */
public interface AccessTokenService {
    String getAccessToken(String appId) throws Exception;
    String getAccessToken() throws Exception;
    String getAppToken(String appId) throws Exception;
    String getAppToken() throws Exception;
}
