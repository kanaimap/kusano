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


//post送信を用いて位置情報をデータベースに送信
public class AsyncTest extends AsyncTask<Void, Void, Void> {

	private String latitude, longitude, time,id;

	public AsyncTest(double latitude, double longitude, String time,String id) {
		this.latitude = String.valueOf(latitude);
		this.longitude = String.valueOf(longitude);
		this.time = time;
		this.id = id;
	}

	@Override
	protected Void doInBackground(Void... unused) {

		String url = "http://10.29.31.66/insert_mysql.php";
		DefaultHttpClient http = new DefaultHttpClient();

		HttpPost post = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>(4);
		params.add(new BasicNameValuePair("lat", latitude));
		params.add(new BasicNameValuePair("lon", longitude));
		params.add(new BasicNameValuePair("time", time));
		params.add(new BasicNameValuePair("ID", id));

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
