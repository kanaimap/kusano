package com.example.maptest;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;


//データベースの位置情報を初期化
public class AsyncTest3 extends AsyncTask<Void, Void, Void> {

	@Override
	protected Void doInBackground(Void... unused) {

		String url = "http://";
		DefaultHttpClient http = new DefaultHttpClient();

		HttpPost post = new HttpPost(url);
		try {
			http.execute(post);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
