package cc.yxs.entity;

import java.util.Date;

/**
 * @author xusong.yuan
 * @date 16-8-26
 */
public class Token {
    private String access_token;
    private int expires_in;
    private Date createDate=new Date();

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
