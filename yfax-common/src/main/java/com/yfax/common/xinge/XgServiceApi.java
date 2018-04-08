package com.yfax.common.xinge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;
import com.yfax.utils.StrUtil;
import com.tencent.xinge.TagTokenPair;

/**
 * 腾讯信鸽推送服务
 * @author Minbo
 * 
 * 通用返回码
 * 值	含义	可采取措施
	0	调用成功	
	-1	参数错误	检查参数配置
	-2	请求时间戳不在有效期内	检查设备当前时间
	-3	sign校验无效	检查Access ID和Secret Key（注意不是Access Key）
	2	参数错误	检查参数配置
	14	收到非法token，例如iOS终端没能拿到正确的token	Android Token长度为40位iOS Token长度为64位
	15	信鸽逻辑服务器繁忙	稍后重试
	19	操作时序错误。例如进行tag操作前未获取到deviceToken	没有获取到deviceToken的原因：1.没有注册信鸽或者苹果推送2.provisioning profile制作不正确
	20	鉴权错误，可能是由于Access ID和Access Key不匹配	检查Access ID和Access Key
	40	推送的token没有在信鸽中注册	检查token是否注册
	48	推送的账号没有绑定token	检查account和token是否有绑定关系见推送指南：绑定/设置账号见热门问题解答：账号和设备未绑定的解答
	63	标签系统忙	检查标签是否设置成功见推送指南：设置标签
	71	APNS服务器繁忙	苹果服务器繁忙，稍后重试
	73	消息字符数超限	iOS目前是1000字节左右，苹果的额外推送设置如角标，也会占用字节数
	76	请求过于频繁，请稍后再试	全量广播限频为每3秒一次
	78	循环任务参数错误	
	100	APNS证书错误。请重新提交正确的证书	证书格式是pem的，另外，注意区分生产证书、开发证书的区别
 *
 */
public class XgServiceApi {
	
	protected static Logger logger = LoggerFactory.getLogger(XgServiceApi.class);
	
//	private final static long ACCESS_ID = xxx;
	private final static String ACCESS_KEY = "xxx";
//	private final static String SECRET_KEY = "xxx";
	private final static String IMEI_NO = "xxx";
	
	/**
	 * 推送栏通知消息
	 * @param phoneId
	 * @param title
	 * @param msg
	 * @return
	 */
	public static String pushNotify(String phoneId, String title, String msg, long access_id, String secret_key) {
		String result = XingeApp.pushAccountAndroid(access_id, secret_key, title, msg, phoneId).toString();
		return result;
	}
	
	/**
	 * 推送透传消息，非通知栏
	 * @param phoneId
	 * @param title
	 * @param msg
	 * @return
	 */
	public static String pushNotifyByMessage(String phoneId, String title, String msg, long access_id, String secret_key) {
		XingeApp xinge = new XingeApp(access_id, secret_key);
		Message message = new Message();
        message.setTitle(title);
        message.setContent(msg);
        message.setType(Message.TYPE_MESSAGE);
        JSONObject ret = xinge.pushSingleAccount(0, phoneId, message);
		return ret.toString();
	}
	
//    public static void main(String[] args) {
//        demoXingeSimple("");

////        Demo t = new Demo();
//        //Android单推
////        System.out.println(t.demoPushSingleDeviceMessage());
////        System.out.println(t.demoPushSingleDeviceNotification());
////        System.out.println(t.demoPushSingleDeviceNotificationIntent());
////        System.out.println(t.demoPushSingleAccount());
//
//        //iOS推送
////        System.out.println(t.demoPushSingleDeviceMessageIOS());
////        System.out.println(t.demoPushSingleDeviceNotificationIOS());
////        System.out.println(t.demoPushSingleAccountIOS());
////        System.out.println(t.demoPushAccountListIOS());
//
//        //Android批量推送
////        System.out.println(t.demoPushAccountList());
////        System.out.println(t.demoPushAllDevice());
////        System.out.println(t.demoPushTags());
////        System.out.println(t.demoPushAccountListMultiple());
////        System.out.println(t.demoPushDeviceListMultiple());
//
//        //查询接口
////        System.out.println(t.demoQueryPushStatus());
////        System.out.println(t.demoQueryDeviceCount());
////        System.out.println(t.demoQueryTags());
////        System.out.println(t.demoQueryTagTokenNum());
////        System.out.println(t.demoQueryTokenTags());
////        System.out.println(t.demoQueryInfoOfToken());
////        System.out.println(t.demoQueryTokensOfAccount());
//
//        //变更接口
////        System.out.println(t.demoCancelTimingPush());
////        System.out.println(t.demoBatchSetTag());
////        System.out.println(t.demoBatchDelTag());
////        System.out.println(t.demoDeleteTokenOfAccount());
////        System.out.println(t.demoDeleteAllTokensOfAccount());
//    }
	
    /**
     * 调用简易接口
     */
    public static String demoXingeSimple(String phoneId) {
    		if (StrUtil.null2Str(phoneId).equals("")) {
    			phoneId = IMEI_NO;
		}
    		String result = "";
        // 易用接口
//        System.out.println(XingeApp.pushTokenAndroid(ACCESS_ID, SECRET_KEY, "消息标题", "消息内容", "token"));
//    		result = XingeApp.pushAccountAndroid(ACCESS_ID, SECRET_KEY, "这是消息标题", "这是消息内容", phoneId).toString();
//    		logger.info(result);
//        System.out.println(XingeApp.pushAllAndroid(000, "secretKey", "test", "测试"));
//        System.out.println(XingeApp.pushTagAndroid(000, "secretKey", "test", "测试", "tag"));

//        System.out.println(XingeApp.pushTokenIos(000L, "secretKey", "测试", "token", XingeApp.IOSENV_DEV));
//        System.out.println(XingeApp.pushAccountIos(000L, "secretKey", "测试", "account", XingeApp.IOSENV_DEV));
//        System.out.println(XingeApp.pushAllIos(000L, "secretKey", "测试", XingeApp.IOSENV_DEV));
//        System.out.println(XingeApp.pushTagIos(000L, "secretKey", "测试", "tag", XingeApp.IOSENV_DEV));
        
        return result;
    }

    //单个设备下发透传消息
    protected JSONObject demoPushSingleDeviceMessage() {
        Message message = new Message();
        message.setTitle("title");
        message.setContent("content");
        message.setType(Message.TYPE_MESSAGE);
        message.setExpireTime(86400);
        JSONObject ret = xinge.pushSingleDevice("token", message);

        return ret;
    }

    //单个设备下发通知消息
    protected JSONObject demoPushSingleDeviceNotification() {
        JSONObject ret = xinge.pushSingleDevice("token", message1);
        return ret;
    }

    //单个设备下发通知Intent
    //setIntent()的内容需要使用intent.toUri(Intent.URI_INTENT_SCHEME)方法来得到序列化后的Intent(自定义参数也包含在Intent内）
    //终端收到后可通过intent.parseUri()来反序列化得到Intent
    protected JSONObject demoPushSingleDeviceNotificationIntent() {
        JSONObject ret = xinge.pushSingleDevice("token", message2);
        return ret;
    }

    //单个设备静默通知(iOS7以上)
    protected JSONObject demoPushSingleDeviceMessageIOS() {
        MessageIOS remoteMessageIOS = new MessageIOS();
        remoteMessageIOS.setType(MessageIOS.TYPE_REMOTE_NOTIFICATION);
        return xinge.pushSingleDevice("token", messageIOS, XingeApp.IOSENV_DEV);
    }

    //单个设备下发通知消息iOS
    protected JSONObject demoPushSingleDeviceNotificationIOS() {
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        messageIOS.addAcceptTime(acceptTime1);
        Map<String, Object> custom = new HashMap<String, Object>();
        custom.put("key1", "value1");
        custom.put("key2", 2);
        messageIOS.setCustom(custom);

        JSONObject ret = xinge.pushSingleDevice("token", messageIOS, XingeApp.IOSENV_DEV);
        return ret;
    }

    //下发单个账号
    protected JSONObject demoPushSingleAccount() {
        Message message = new Message();
        message.setExpireTime(86400);
        message.setTitle("title");
        message.setContent("content");
        message.setType(Message.TYPE_MESSAGE);
        JSONObject ret = xinge.pushSingleAccount(0, "joelliu", message);
        return ret;
    }

    //下发多个账号
    protected JSONObject demoPushAccountList() {
        Message message = new Message();
        message.setExpireTime(86400);
        message.setTitle("title");
        message.setContent("content");
        message.setType(Message.TYPE_MESSAGE);
        List<String> accountList = new ArrayList<String>();
        accountList.add("joelliu");
        JSONObject ret = xinge.pushAccountList(0, accountList, message);
        return ret;
    }

    //下发IOS单个账号
    protected JSONObject demoPushSingleAccountIOS() {
        MessageIOS message = new MessageIOS();
        message.setExpireTime(86400);
        message.setAlert("ios test");
        message.setBadge(1);
        message.setSound("beep.wav");
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        message.addAcceptTime(acceptTime1);
        Map<String, Object> custom = new HashMap<String, Object>();
        custom.put("key1", "value1");
        custom.put("key2", 2);
        message.setCustom(custom);

        JSONObject ret = xinge.pushSingleAccount(0, "joelliu", messageIOS, XingeApp.IOSENV_DEV);
        return ret;
    }

    //下发IOS多个账号
    protected JSONObject demoPushAccountListIOS() {
        List<String> accountList = new ArrayList<String>();
        accountList.add("joelliu");
        JSONObject ret = xinge.pushAccountList(0, accountList, messageIOS, XingeApp.IOSENV_DEV);
        return ret;
    }

    //下发所有设备
    protected JSONObject demoPushAllDevice() {
        JSONObject ret = xinge.pushAllDevice(0, message1);
        return ret;
    }

    //下发标签选中设备
    protected JSONObject demoPushTags() {
        List<String> tagList = new ArrayList<String>();
        tagList.add("joelliu");
        tagList.add("phone");
        JSONObject ret = xinge.pushTags(0, tagList, "OR", message1);
        return ret;
    }

    //大批量下发给帐号 // iOS 请构造MessageIOS消息
    protected JSONObject demoPushAccountListMultiple() {
        Message message = new Message();
        message.setExpireTime(86400);
        message.setTitle("title");
        message.setContent("content");
        message.setType(Message.TYPE_NOTIFICATION);

        JSONObject ret = xinge.createMultipush(message);
        if (ret.getInt("ret_code") != 0)
            return ret;
        else {
            JSONObject result = new JSONObject();

            List<String> accountList1 = new ArrayList<String>();
            accountList1.add("joelliu1");
            accountList1.add("joelliu2");
            // ...
            //result.append("all", xinge.pushAccountListMultiple(ret.getJSONObject("result").getLong("push_id"), accountList1));

            List<String> accountList2 = new ArrayList<String>();
            accountList2.add("joelliu3");
            accountList2.add("joelliu4");
            // ...
            //result.append("all", xinge.pushAccountListMultiple(ret.getJSONObject("result").getLong("push_id"), accountList2));
            return result;
        }
    }

    //大批量下发给设备 // iOS 请构造MessageIOS消息
    protected JSONObject demoPushDeviceListMultiple() {
        Message message = new Message();
        message.setExpireTime(86400);
        message.setTitle("title");
        message.setContent("content");
        message.setType(Message.TYPE_NOTIFICATION);

        JSONObject ret = xinge.createMultipush(message);
        if (ret.getInt("ret_code") != 0)
            return ret;
        else {
            JSONObject result = new JSONObject();

            List<String> deviceList1 = new ArrayList<String>();
            deviceList1.add("joelliu1");
            deviceList1.add("joelliu2");
            // ...
            result.append("all", xinge.pushDeviceListMultiple(ret.getJSONObject("result").getLong("push_id"), deviceList1));

            List<String> deviceList2 = new ArrayList<String>();
            deviceList2.add("joelliu3");
            deviceList2.add("joelliu4");
            // ...
            result.append("all", xinge.pushDeviceListMultiple(ret.getJSONObject("result").getLong("push_id"), deviceList2));
            return result;
        }
    }

    //查询消息推送状态
    protected JSONObject demoQueryPushStatus() {
        List<String> pushIdList = new ArrayList<String>();
        pushIdList.add("390");
        pushIdList.add("389");
        JSONObject ret = xinge.queryPushStatus(pushIdList);
        return ret;
    }

    //查询设备数量
    protected JSONObject demoQueryDeviceCount() {
        JSONObject ret = xinge.queryDeviceCount();
        return ret;
    }

    //查询标签
    protected JSONObject demoQueryTags() {
        JSONObject ret = xinge.queryTags();
        return ret;
    }

    //查询某个tag下token的数量
    protected JSONObject demoQueryTagTokenNum() {
        JSONObject ret = xinge.queryTagTokenNum("tag");
        return ret;
    }

    //查询某个token的标签
    protected JSONObject demoQueryTokenTags() {
        JSONObject ret = xinge.queryTokenTags("token");
        return ret;
    }

    //取消定时任务
    protected JSONObject demoCancelTimingPush() {
        JSONObject ret = xinge.cancelTimingPush("32");
        return ret;
    }

    // 设置标签
    protected JSONObject demoBatchSetTag() {
        List<TagTokenPair> pairs = new ArrayList<TagTokenPair>();

        // 切记把这里的示例tag和示例token修改为你的真实tag和真实token
        pairs.add(new TagTokenPair("tag1", "token00000000000000000000000000000000001"));
        pairs.add(new TagTokenPair("tag2", "token00000000000000000000000000000000001"));

        JSONObject ret = xinge.BatchSetTag(pairs);
        return ret;
    }

    // 删除标签
    protected JSONObject demoBatchDelTag() {
        List<TagTokenPair> pairs = new ArrayList<TagTokenPair>();

        // 切记把这里的示例tag和示例token修改为你的真实tag和真实token
        pairs.add(new TagTokenPair("tag1", "token00000000000000000000000000000000001"));
        pairs.add(new TagTokenPair("tag2", "token00000000000000000000000000000000001"));

        JSONObject ret = xinge.BatchDelTag(pairs);

        return ret;
    }

    //查询某个token的信息
    protected JSONObject demoQueryInfoOfToken() {
        JSONObject ret = xinge.queryInfoOfToken("token");
        return ret;
    }

    //查询某个account绑定的token
    protected JSONObject demoQueryTokensOfAccount() {
        JSONObject ret = xinge.queryTokensOfAccount("nickName");
        return ret;
    }

    //删除某个account绑定的token
    protected JSONObject demoDeleteTokenOfAccount() {
        JSONObject ret = xinge.deleteTokenOfAccount("nickName", "token");
        return ret;
    }

    //删除某个account绑定的所有token
    protected JSONObject demoDeleteAllTokensOfAccount() {
        JSONObject ret = xinge.deleteAllTokensOfAccount("nickName");
        return ret;
    }

    protected void buildMesssges() {
        message1 = new Message();
        message1.setType(Message.TYPE_NOTIFICATION);
        Style style = new Style(1);
        style = new Style(3, 1, 0, 1, 0);
        ClickAction action = new ClickAction();
        action.setActionType(ClickAction.TYPE_URL);
        action.setUrl("http://xg.qq.com");
        Map<String, Object> custom = new HashMap<String, Object>();
        custom.put("key1", "value1");
        custom.put("key2", 2);
        message1.setTitle("title");
        message1.setContent("大小");
        message1.setStyle(style);
        message1.setAction(action);
        message1.setCustom(custom);
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        message1.addAcceptTime(acceptTime1);

        message2 = new Message();
        message2.setType(Message.TYPE_NOTIFICATION);
        message2.setTitle("title");
        message2.setContent("通知点击执行Intent测试");
        style = new Style(1);
        action = new ClickAction();
        action.setActionType(ClickAction.TYPE_INTENT);
        action.setIntent("intent:10086#Intent;scheme=tel;action=android.intent.action.DIAL;S.key=value;end");
        message2.setStyle(style);
        message2.setAction(action);

        messageIOS = new MessageIOS();
        messageIOS.setType(MessageIOS.TYPE_APNS_NOTIFICATION);
        messageIOS.setExpireTime(86400);
        messageIOS.setAlert("ios test");
        messageIOS.setBadge(1);
        messageIOS.setCategory("INVITE_CATEGORY");
        messageIOS.setSound("beep.wav");
    }

    public XgServiceApi() {
        XingeApp xinge = new XingeApp(000, "secret_key");
        buildMesssges();
    }

    private XingeApp xinge;
    private Message message1;
    private Message message2;
    private MessageIOS messageIOS;
}
