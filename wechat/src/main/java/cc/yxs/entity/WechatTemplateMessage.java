package cc.yxs.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * @author xusong.yuan
 * @date 16-9-2
 */
public class WechatTemplateMessage<T> {
    private String touser;
    private String template_id;
    private String url;
    private T data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
