package cc.yxs.entity;

/**
 * @author xusong.yuan
 * @date 16-9-2
 */
public class ReminderTemplate extends WechatTemplateMessage<ReminderTemplate.ReminderTemplateData>{

    public static class ReminderTemplateData{
        private TemplateBean desc;
        private TemplateBean num;

        public TemplateBean getDesc() {
            return desc;
        }

        public void setDesc(TemplateBean desc) {
            this.desc = desc;
        }

        public TemplateBean getNum() {
            return num;
        }

        public void setNum(TemplateBean num) {
            this.num = num;
        }
    }

}
