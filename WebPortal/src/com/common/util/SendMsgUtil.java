/**
  * 文件说明
  * @Description:扩展说明
  * @Copyright: 2015 dreamtech.com.cn Inc. All right reserved
  * @Version: V6.0
  */
package com.common.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SendMsgUtil {
	
	/**
	 * 发送短信消息
	  * 方法说明
	 */
	@SuppressWarnings("deprecation")
	public static String sendMsg(String phones,String content){
		//短信接口URL提交地址
		String url = "http://web.1xinxi.cn/smsComputer/pwd.asp?";
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("zh", "13246718812");
		params.put("mm", "3879704B672AD4DBEB5DA8E19F91");
		params.put("dxlbid", "3");
//		params.put("extno", "扩展编号");
		
		//手机号码，多个号码使用英文逗号进行分割
		params.put("hm", phones);
		//将短信内容进行URLEncoder编码
		params.put("nr", URLEncoder.encode(content));
		
		return HttpUtils.post(false, url, params.toString(), null);
	}
	
	/**
	 * 随机生成6位随机验证码
	 */
	public static String createRandomVcode(){
		//验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int)(Math.random() * 9);
		}
		return vcode;
	}
	
	/**
	 * 测试
	  * 方法说明
	 */
	public static void main(String[] args) {
		System.out.println(SendMsgUtil.createRandomVcode());
		System.out.println("&ecb=12".substring(1));
		System.out.println(sendMsg("13246718812", "尊敬的用户，您的验证码为" + SendMsgUtil.createRandomVcode() + "，有效期为60秒，如有疑虑请详询400-069-2886（客服电话）【XXX中心】"));
	}
}
