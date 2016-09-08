package cc.yxs.entity;

/**
 * @author xusong.yuan
 * @date 16-9-2
 */
public class TemplateBean {
    private String value;
    private String color;

    public TemplateBean(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
