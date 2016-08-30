package cc.yxs.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xusong.yuan
 * @date 16-8-26
 */
@XmlRootElement
public class User {
    private String username;
    private Long id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
