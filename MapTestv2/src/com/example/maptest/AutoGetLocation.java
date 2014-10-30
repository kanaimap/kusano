package com.example.maptest;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;

//バックグラウンドで位置情報を取得するService
public class AutoGetLocation extends Service implements LocationListener {
	private final static long mintime = 10000;
	private final static float mindistance = 0;

	boolean p;
	String name_result;
	
	SharedPreferences sharedpreferences;

	// 現在地取得用の変数
	LocationManager myLocation;
	
	
	@Override
	public void onCreate() {
		sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		name_result = (String)sharedpreferences.getString("name","unknown");
	}

	// 200mごとにgpsかnetworkを用いて現在地を取得
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		myLocation = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// 使用providerを確認
		String provider = "";
		if (myLocation.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			// GPSが利用可能
			provider = "gps";
			p = true;
		} else if (myLocation
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			// ネットワークが利用可能
			provider = "network";
			p = false;
		}

		myLocation.requestLocationUpdates(provider, mintime, mindistance, this);
		return START_STICKY;
	}

	// Service終了処理. 現在地の取得を停止
	@Override
	public void onDestroy() {
		myLocation.removeUpdates(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	// 実際に位置情報を取得するメソッド.
	@Override
	public void onLocationChanged(Location location) {

		// 現在時刻を取得し、データベースに送信
		SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.JAPANESE);
		String time = df.format(location.getTime());

		double latitude, longitude;
		latitude = location.getLatitude();
		longitude = location.getLongitude();

		InsertMyLocation post = new InsertMyLocation(latitude, longitude, time,name_result);
		post.execute();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ

	}

}