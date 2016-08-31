package cc.yxs.mapper;

import cc.yxs.entity.WechatReminder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xusong.yuan
 * @date 16-8-31
 */
@Mapper
public interface WechatReminderMapper {
    @Select("SELECT * FROM wechat_reminder WHERE openid = #{openId}")
    List<WechatReminder> getReminderList(@Param("openId") String openId);
}
