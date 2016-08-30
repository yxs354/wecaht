package cc.yxs.service.impl;

import cc.yxs.service.AccessTokenService;
import cc.yxs.util.AccessTokenUtil;
import cc.yxs.util.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xusong.yuan
 * @date 16-8-29
 */
@Service
public class TokenServiceImpl implements AccessTokenService {
    @Autowired
    private AccessTokenUtil tokenUtil;

    @Autowired
    private AppConfig appConfig;


    @Override
    public String getAccessToken(String appId) throws Exception{
        return tokenUtil.getAppToken(appId).getAccess_token();
    }

    @Override public String getAccessToken() throws Exception {
        return getAccessToken(null);
    }

    @Override public String getAppToken(String appId) throws Exception {
        return null;
    }

    @Override public String getAppToken() throws Exception {
        return appConfig.getAppToken();
    }
}
