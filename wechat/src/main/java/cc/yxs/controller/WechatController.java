package cc.yxs.controller;

import cc.yxs.WechatApplication;
import cc.yxs.entity.InputMessage;
import cc.yxs.service.AccessTokenService;
import cc.yxs.service.MessageService;
import cc.yxs.util.AppConfig;
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

public class WechatController {

    @Autowired private AccessTokenService tokenService;

    @Value("${app.gen}") private String appGen;

    @RequestMapping(value = "/token", method = RequestMethod.GET) public Object getToken(String gen) throws Exception {
        if (gen.equals(appGen)) {
            return tokenService.getAccessToken();
        }
        return null;
    }

    @RequestMapping(value = "/wechat", method = RequestMethod.GET) public Object get(String signature, String timestamp, String nonce, String echostr) throws Exception {
        List<String> params = new ArrayList<>();
        params.add(tokenService.getAppToken());
        params.add(timestamp);
        params.add(nonce);
        //1. 将token、timestamp、nonce三个参数进行字典序排序
        Collections.sort(params, new Comparator<String>() {
            @Override public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        //2. 将三个参数字符串拼接成一个字符串进行sha1加密
        String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
        if (temp.equals(signature)) {
            return echostr;
        }
        return null;
    }

    @RequestMapping(value = "/wechat", method = RequestMethod.POST) public Object post(HttpServletRequest request) throws Exception {
        ServletInputStream in = request.getInputStream();
        String requestString = IOUtils.toString(in, "utf8");
        System.out.println(requestString);
        //将POST流转换为XStream对象
        XStream xs = new XStream(new DomDriver());
        //将指定节点下的xml节点数据映射为对象
        xs.alias("xml", InputMessage.class);
        //将xml内容转换为InputMessage对象
        InputMessage inputMsg = (InputMessage) xs.fromXML(requestString);
        // 取得消息类型
        String msgType = inputMsg.getMsgType();


        System.out.println("开发者微信号：" + inputMsg.getToUserName());
        System.out.println("发送方帐号：" + inputMsg.getFromUserName());
        System.out.println("消息创建时间：" + inputMsg.getCreateTime());
        System.out.println("消息内容：" + inputMsg.getContent());
        System.out.println("消息类型：" + msgType);
        System.out.println("消息Id：" + inputMsg.getMsgId());

        MessageService messageService = null;
        try{
            messageService=WechatApplication.getContext().getBean(msgType.toLowerCase() + "MessageServiceImpl", MessageService.class);
        }catch (Exception e){
        }
        if (messageService != null) {
            String response = messageService.execute(inputMsg);
            if (response != null) {
                XStream xstream = new XStream(new XppDriver() {
                    @Override public HierarchicalStreamWriter createWriter(Writer out) {
                        return new PrettyPrintWriter(out) {
                            @Override protected void writeText(QuickWriter writer, String text) {
                                if (!text.startsWith("<![CDATA[")) {
                                    text = "<![CDATA[" + text + "]]>";
                                }
                                writer.write(text);
                            }
                        };
                    }
                });
                //创建文本发送消息对象
                TextOutputMessage outputMsg = new TextOutputMessage();
                outputMsg.setContent(response);
                setOutputMsgInfo(outputMsg, inputMsg);
                //设置对象转换的XML根节点为xml
                xstream.alias("xml", outputMsg.getClass());
                //将对象转换为XML字符串
                String xml = xstream.toXML(outputMsg);
                return xml;
            }

        }
        return "";
    }

    private static void setOutputMsgInfo(OutputMessage oms, InputMessage msg) throws Exception {
        // 设置发送信息
        Class<?> outMsg = oms.getClass().getSuperclass();
        Field CreateTime = outMsg.getDeclaredField("CreateTime");
        Field ToUserName = outMsg.getDeclaredField("ToUserName");
        Field FromUserName = outMsg.getDeclaredField("FromUserName");

        ToUserName.setAccessible(true);
        CreateTime.setAccessible(true);
        FromUserName.setAccessible(true);

        CreateTime.set(oms, new Date().getTime());
        ToUserName.set(oms, msg.getFromUserName());
        FromUserName.set(oms, msg.getToUserName());
    }
}
