package cc.yxs.controller;

import cc.yxs.entity.InputMessage;
import cc.yxs.service.AccessTokenService;
import cc.yxs.service.ReminderService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping(value = "/reminder/list",method = RequestMethod.GET)
    public Object get(String signature,String timestamp,String nonce,String echostr) throws Exception{
        return reminderService.getReminderList("123");
    }

}
