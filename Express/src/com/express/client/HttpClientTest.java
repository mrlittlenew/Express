package com.express.client;


import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	public static String[] checkOrder(String orderNum) throws Exception {
		String[] arr = {};
		String text = orderNum;
		HttpClient httpclient = new DefaultHttpClient();
		URI uri = URIUtils.createURI("http", "www.fengleisd.com/SearchEx.aspx",
				-1, "", "ordernum=" + orderNum, null);
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				text = text + getText(EntityUtils.toString(entity));
				// System.out.println(text);
				arr = text.split("#");
				System.out.println("====================================");
				for (String str : arr) {
					System.out.println(str);
				}
				return arr;
			} catch (Exception e) {
				System.out.println("Not found the order!");
			}
		}
		return arr;

	}

	public static String getText(String htmlString) {
		int start = htmlString.indexOf("ContentPlaceHolder1_lbNewState");
		int end = htmlString.indexOf("ContentPlaceHolder1_lbFYState");
		String text = htmlString.substring(start, end);

		start = text.indexOf("<tr><td>") + 4;
		end = text.indexOf("</tbody>") - 5;
		text = text.substring(start, end);

		text = text.replace("</tr><tr>", "#");

		text = text.replace("收件人：", "");

		text = text.replace("</td><td>", "#");

		text = text.replace("</td>", "");

		text = text.replace("<td>", "");

		text = text.replace("链接：<a href=\"http://www.yundaex.com\" target=\"blank\">点击进入查询链接</a>，",":韵达。");
		
		text = text.replace("链接：<a href=\"http://www.ems.com.cn\" target=\"blank\">点击进入查询链接</a>，",":EMS。");

		
		
		
		return text;
	}
	
	public static void init(int start, int end, int step) throws Exception{
		List<String[]> list= new ArrayList<String[]>();
		
		int count=(end-start)/step;
		
		if(count>100){
			System.err.println("cannot more than 100 once time!!!! count:"+count);
			return;
		}
		
		while(start <= end){
			String[] arr=checkOrder("THD000" + start + "LA");
			list.add(arr);
			start = start + step;
		}
		//ExcelGen.export(list);
		System.out.println("Total get record:"+count);
	}
	
	

	public static void main(String[] args) {
		try {
			long start = System.currentTimeMillis();
			init(462500,482500,2000);
			long end = System.currentTimeMillis();
			System.out.println("Time:" + (end - start) / 1000 + "s");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
