package com.example.maptest;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class CheckServer extends AsyncTask<Void, Void, Void> {
	String r;
	MainActivity main;
	
	public CheckServer(MainActivity main){
		this.main = main;
	}
	
	@Override
	protected Void doInBackground(Void... unused) {

		String url = "http://10.29.31.66/check_server.php";
		DefaultHttpClient http = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		try {
			HttpResponse response = http.execute(post);
			r = EntityUtils.toString(response.getEntity(), "UTF-8");
			if(r.equals("OK")){
				main.server = true;
			}
		} catch (IOException e) {
		}
		return null;
	}
}
