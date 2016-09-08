package cc.yxs.mapper;

import cc.yxs.entity.ReminderCount;
import cc.yxs.entity.WechatReminder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author xusong.yuan
 * @date 16-8-31
 */
@Mapper
public interface WechatReminderMapper {
    @Select("SELECT * FROM wechat_reminder WHERE openid = #{openId} and status = #{status}" )
    List<WechatReminder> getReminderList(@Param("openId") String openId,@Param("status") Integer status);

    @Select("select openid,count(1) as count from wechat_reminder where status=0 GROUP BY openid" )
    List<ReminderCount> getNeedRemindOpenIdList();

    @Select("SELECT * FROM wechat_reminder WHERE openid = #{openId} and status = #{status} ORDER BY  id limit 0,1" )
    WechatReminder getFirstReminderByOpenIdAndStatus(@Param("openId") String openId,@Param("status") Integer status);

    @Insert("insert into wechat_reminder (status, openid, content,reminderdate) values(#{status}, #{openId}, #{content},#{reminderdate})")
    //INSERT INTO loan_quota.wechat_reminder (status, openid, content, date, reminderdate) VALUES (0, 'okFZ5s7bDQXTXg5nEwIutMOlm5Hk', '今天晚上要看电视', '2016-09-01 18:33:35', null);
    int add(WechatReminder reminder);

    @Update("update wechat_reminder set status = #{status} where id = #{id}")
    int updateReminderStatus(@Param("id") Long id,@Param("status") Integer status);
}
