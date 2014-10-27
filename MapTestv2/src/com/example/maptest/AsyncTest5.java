package com.example.maptest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;


//データベースの位置情報を初期化
public class AsyncTest5 extends AsyncTask<Void, Void, Void> {
	
	String icon_id,num;
	
	public AsyncTest5(int icon_id,String num){
		this.icon_id =String.valueOf(icon_id);
		this.num = num;
	}

	@Override
	protected Void doInBackground(Void... unused) {

		String url = "http://";
		DefaultHttpClient http = new DefaultHttpClient();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ICON_ID", icon_id));
		params.add(new BasicNameValuePair("ID", num));

		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			http.execute(post);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
