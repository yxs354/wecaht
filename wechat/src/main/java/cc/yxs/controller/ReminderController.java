package cc.yxs.controller;

import cc.yxs.WechatApplication;
import cc.yxs.entity.InputMessage;
import cc.yxs.entity.ReminderTemplate;
import cc.yxs.entity.TemplateBean;
import cc.yxs.entity.WechatReminder;
import cc.yxs.service.AccessTokenService;
import cc.yxs.service.MessageService;
import cc.yxs.service.PageAuthService;
import cc.yxs.service.ReminderService;
import cc.yxs.service.TemplateMessageService;
import cc.yxs.util.MsgType;
import cc.yxs.util.OutputMessage;
import cc.yxs.util.SHA1;
import cc.yxs.util.TextOutputMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author xusong.yuan
 * @date 16-8-26
 */
@RestController

public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private PageAuthService pageAuthService;

    @Autowired
    private TemplateMessageService templateMessageService;

    @RequestMapping(value = "/reminder/list",method = RequestMethod.GET)
    public Object get(String code) throws Exception{
        String openId=pageAuthService.getOpenIdByPageCode(code);
        if (openId != null) {
            return reminderService.getReminderList(openId);
        }
        return null;
//        return reminderService.getReminderList("okFZ5s7bDQXTXg5nEwIutMOlm5Hk");
    }

    @RequestMapping(value = "/reminder/{id}/{status}",method = RequestMethod.GET)
    public void geaddt(@PathVariable Long id,@PathVariable Integer status) throws Exception{
        reminderService.setReminderStatus(id,status);
    }

    @RequestMapping(value = "/reminder/test", method = RequestMethod.GET)
    public void test() throws Exception {
        ReminderTemplate reminderTemplate = new ReminderTemplate();
        reminderTemplate.setTouser("okFZ5s7bDQXTXg5nEwIutMOlm5Hk");
        reminderTemplate.setTemplate_id("2HJaaQ992ThdyNDtlhCC1WAqf-mMKlgi9PfdNBL-qX4");
        reminderTemplate.setUrl(
            "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdd7c381794777c14&redirect_uri=http%3a%2f%2f114.55.253.30%2fpages%2fdashboard%2findex.html&response_type=code&scope=snsapi_base&state=wx#wechat_redirect");
        ReminderTemplate.ReminderTemplateData data=new ReminderTemplate.ReminderTemplateData();

        TemplateBean desc = new TemplateBean("药不能停", "blue");
        data.setDesc(desc);
        TemplateBean num = new TemplateBean("3", "red");
        data.setNum(num);
        reminderTemplate.setData(data);
        templateMessageService.sendTemplate(reminderTemplate);
    }

}
