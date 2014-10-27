package com.example.maptest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class AsyncTest6 extends AsyncTask<String, String, String>  {

	String id;
	MainActivity main;
	String icon_id = "0";
	
	public AsyncTest6(MainActivity main,String id){
		this.id = id;
		this.main = main;
	}
	
	@Override
	protected String doInBackground(String... unused) {

		String url = "http://10.29.31.66/get_icon_id.php";
		DefaultHttpClient http = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ID", id));
		
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			HttpResponse response = http.execute(post);
			HttpEntity entity = response.getEntity();
			icon_id = EntityUtils.toString(entity).trim();

		} catch (IOException e) {
		}
		return icon_id;
	}
	
	@Override
	protected void onPostExecute(String icon_id) {
		main.icon_id = Integer.parseInt(icon_id);
	}
}
