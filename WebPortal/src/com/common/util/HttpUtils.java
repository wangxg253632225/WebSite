package com.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HTTP请求相关
 * @author 董华健
 */
@SuppressWarnings("deprecation")
public class HttpUtils {

	private static Logger log = Logger.getLogger(HttpUtils.class);
	public static final int COOKIE_MAX_AGE = 7*24 * 3600;;
	/**
	 * 进行HttpClient get连接
	 * @param isHttps 是否ssl链接
	 * @param url
	 * @return
	 */
	public static String get(boolean isHttps, String url) {
		CloseableHttpClient httpClient = null;
		try {
			if(!isHttps){
				httpClient = HttpClients.createDefault();
			}else{
				httpClient = createSSLInsecureClient();
			}
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 获取状态行
				//System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String out = EntityUtils.toString(entity, ToolString.encoding);
					return out;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(null != httpClient){
					httpClient.close();
				}
			} catch (IOException e) {
				log.error("httpClient.close()异常");
			}
		}
		return null;
	}

	/**
	 * 进行HttpClient post连接
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post( String url, String data) {
		return post(false, url,  data, null);
	}


	public static String post(boolean isHttps, String url, String data, String contentType) {
		CloseableHttpClient httpClient = null;
		try {
			if(!isHttps){
				httpClient = HttpClients.createDefault();
			}else{
				httpClient = createSSLInsecureClient();
			}
			HttpPost httpPost = new HttpPost(url);
			//(name, value);.addRequestHeader("Content-Type","text/html;charset=UTF-8");
			//httpPost.getParams().setParameter(HttpMethod.HTTP_CONTENT_CHARSET, "UTF-8");

			if(null != data){
				StringEntity stringEntity = new StringEntity(data, ToolString.encoding);
				stringEntity.setContentEncoding(ToolString.encoding);
				if (null != contentType) {
					stringEntity.setContentType(contentType);
				}else{
					stringEntity.setContentType("application/json");
				}
				httpPost.setEntity(stringEntity);
			}

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();// 设置请求和传输超时时间
			httpPost.setConfig(requestConfig);

			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String out = EntityUtils.toString(entity, ToolString.encoding);
					return out;
				}
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.error("连接超时：" + url);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("IO异常:" + url);
		} finally {
			try {
				if(null != httpClient){
					httpClient.close();
				}
			} catch (IOException e) {
				log.error("httpClient.close()异常");
			}
		}
		return null;
	}

	/**
	 * HTTPS访问对象，信任所有证书
	 * @return
	 */
	public static CloseableHttpClient createSSLInsecureClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				//信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return  HttpClients.createDefault();
	}

	/**
	 * 原生方式请求
	 * @param isHttps 是否https
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return
	 */
	public static String httpRequest(boolean isHttps, String requestUrl, String requestMethod, String outputStr) {
		HttpURLConnection conn = null;

		OutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		PrintWriter printWriter = null;

		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		try {
			URL url = new URL(requestUrl);
			conn = (HttpURLConnection) url.openConnection();
			if(isHttps){
				HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
				// 创建SSLContext对象，并使用我们指定的信任管理器初始化
				TrustManager[] tm = { new X509TrustManager() {
					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
						// 检查客户端证书
					}
					public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
						// 检查服务器端证书
					}
					public X509Certificate[] getAcceptedIssuers() {
						// 返回受信任的X509证书数组
						return null;
					}
				} };
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new java.security.SecureRandom());
				SSLSocketFactory ssf = sslContext.getSocketFactory();// 从上述SSLContext对象中得到SSLSocketFactory对象
				httpsConn.setSSLSocketFactory(ssf);
				conn = httpsConn;
			}

			// 超时设置，防止 网络异常的情况下，可能会导致程序僵死而不继续往下执行
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);

			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			conn.setDoOutput(true);

			// 设置是否从httpUrlConnection读入，默认情况下是true;
			conn.setDoInput(true);

			// Post 请求不能使用缓存
			conn.setUseCaches(false);

			// 设定传送的内容类型是可序列化的java对象
			// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

			// 设置请求方式（GET/POST），默认是GET
			conn.setRequestMethod(requestMethod);

			// 连接，上面对urlConn的所有配置必须要在connect之前完成，
			conn.connect();

			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				outputStream = conn.getOutputStream();
				outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
				printWriter = new PrintWriter(outputStreamWriter);
				printWriter.write(outputStr);
				printWriter.flush();
				printWriter.close();
			}

			// 从输入流读取返回内容
			inputStream = conn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, ToolString.encoding);
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuilder buffer = new StringBuilder();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str).append("\n");
			}

			return buffer.toString();
		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
			return null;

		} catch (Exception e) {
			log.error("https请求异常：{}", e);
			return null;

		} finally { // 释放资源
			if(null != outputStream){
				try {
					outputStream.close();
				} catch (IOException e) {
					log.error("outputStream.close()异常", e);
				}
				outputStream = null;
			}

			if(null != outputStreamWriter){
				try {
					outputStreamWriter.close();
				} catch (IOException e) {
					log.error("outputStreamWriter.close()异常", e);
				}
				outputStreamWriter = null;
			}

			if(null != printWriter){
				printWriter.close();
				printWriter = null;
			}

			if(null != bufferedReader){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					log.error("bufferedReader.close()异常", e);
				}
				bufferedReader = null;
			}

			if(null != inputStreamReader){
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					log.error("inputStreamReader.close()异常", e);
				}
				inputStreamReader = null;
			}

			if(null != inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error("inputStream.close()异常", e);
				}
				inputStream = null;
			}

			if(null != conn){
				conn.disconnect();
				conn = null;
			}
		}
	}

	public static String sendHttpClientPost(String path,
											Map<String, String> params, String encode) {

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				list.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}

		try {
			// 实现将请求的参数封装到表单中，即请求体中
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, encode);
			// 使用post方式提交数据
			HttpPost httpPost = new HttpPost(path);
			httpPost.setEntity(entity);
			// 执行post请求，并获取服务器端的响应HttpResponse
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);

			//获取服务器端返回的状态码和输入流，将输入流转换成字符串
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				InputStream inputStream = httpResponse.getEntity().getContent();
				return changeInputStream(inputStream, encode);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";

	}

	/*
     * // 把从输入流InputStream按指定编码格式encode变成字符串String
     */
	public static String changeInputStream(InputStream inputStream,
										   String encode) {

		// ByteArrayOutputStream 一般叫做内存流
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
		if (inputStream != null) {

			try {
				while ((len = inputStream.read(data)) != -1) {
					byteArrayOutputStream.write(data, 0, len);

				}
				result = new String(byteArrayOutputStream.toByteArray(), encode);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return result;
	}


	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name){
		Map<String,Cookie> cookieMap = ReadCookieMap(request);
		if(cookieMap.containsKey(name)){
			Cookie cookie = (Cookie)cookieMap.get(name);
			return cookie;
		}else{
			return null;
		}
	}


	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){
		Cookie cookie = new Cookie(name,value);
		cookie.setPath("/");
		if(maxAge>0)  cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
			for(Cookie cookie : cookies){
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}


	public static void removeCookie(HttpServletRequest request,
                                    HttpServletResponse response, String name) {
		if (null == name) {
			return;
		}
		Cookie cookie = getCookieByName(request, name);
		if(null != cookie){
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
//	public static void main(String[] args){
//		//System.out.println(get("http://127.0.0.1:89/jf/platform/login"));
//		//System.out.println(post("http://127.0.0.1:89/jf/platform/login", null, null));
//
//		//System.out.println(get("http://littleant.duapp.com/msg"));
//
//		/*String returnMsg = "<xml>";
//		returnMsg += "<ToUserName><![CDATA[dongcb678]]></ToUserName>";
//		returnMsg += "<FromUserName><![CDATA[jiu_guang]]></FromUserName>";
//		returnMsg += "<CreateTime>"+ToolDateTime.getDateByTime()+"</CreateTime>";
//		returnMsg += "<MsgType><![CDATA[text]]></MsgType>";
//		returnMsg += "<Content><![CDATA[你好]]></Content>";
//		returnMsg += "</xml>";*/
//
//		Stringtring returnMsg = "<xml>";
//		returnMsg += " <ToUserName><![CDATA[jiu_guang]]></ToUserName>";
//		returnMsg += " <FromUserName><![CDATA[dongcb678]]></FromUserName> ";
//		returnMsg += " <CreateTime>1348831860</CreateTime>";
//		returnMsg += " <MsgType><![CDATA[text]]></MsgType>";
//		returnMsg += " <Content><![CDATA[this is a test]]></Content>";
//		returnMsg += " <MsgId>1234567890123456</MsgId>";
//		returnMsg += " </xml>";
////
////		//System.out.println(post("http://127.0.0.1:88/msg", returnMsg, "application/xml"));
//		System.out.println(post("http://littleant.duapp.com/msg", returnMsg, "application/xml"));
////
//		System.out.println(post(true, "https://www.oschina.net/home/login?goto_page=http%3A%2F%2Fwww.oschina.net%2F", null, "application/text"));
//		System.out.println(httpRequest(false, "https://passport.csdn.net/account/login", "GET", null));
//	}
}

