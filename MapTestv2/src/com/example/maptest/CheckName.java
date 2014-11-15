package com.example.maptest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;


//重複をチェックするAsyncTask
public class CheckName extends AsyncTask<Void, Void, Void> {
	
	String name,id,abnormal;
	MainActivity main;
	
	public CheckName(String name,String id,MainActivity main){
		this.name =name;
		this.id = id;
		this.main = main;
	}

	@Override
	protected Void doInBackground(Void... unused) {

		String url = "http://**************/check_name.php";
		DefaultHttpClient http = new DefaultHttpClient();
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("id", id));

		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			HttpResponse response = http.execute(post);
			abnormal = EntityUtils.toString(response.getEntity(), "UTF-8").trim();
			main.abnormal = abnormal;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
