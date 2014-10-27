package com.example.maptest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
	// map操作用変数
	GoogleMap map;

	// 現在地に打つマーカ-
	MarkerOptions options1 = new MarkerOptions();

	// 法政大学に打つマーカ
	MarkerOptions options2 = new MarkerOptions();

	// ボタン用変数
	Button mButton1, mButton2, mButton3, mButton4;

	// タイムゾーン取得用変数
	TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
	
	MainActivity main = this;
	
	//使用アイコン
	int icon_id = 0;
	//0:足跡 1:アンドロイドアイコン
	
	
	String num;
	
	String json = "";
	int number;

	double[] latitude, longitude,id;
	String[] time;

	// 初期画面を構成
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MapsInitializer.initialize(getApplicationContext());

		// 現在地に打つマーカーの画像を足跡へ変更
		BitmapDescriptor icon = BitmapDescriptorFactory
				.fromResource(R.drawable.footprint);
		options1.icon(icon);

		setContentView(R.layout.activity_main);

		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		MapsInitializer.initialize(this);

		// ボタン割り当て
		mButton1 = (Button) findViewById(R.id.Button01);
		mButton2 = (Button) findViewById(R.id.Button02);
		mButton3 = (Button) findViewById(R.id.Button03);

		// タイマーOFFボタンをクリック禁止に
		mButton3.setEnabled(false);

		// 初期位置 = 法政大学を中心とする
		moveToFirstRocation();
		
		
		//端末IDを取得
		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		num = tm.getSimSerialNumber();
		
		AsyncTest4 set_id = new AsyncTest4(icon_id,num);
		set_id.execute();

		try {
			Class.forName("android.os.AsyncTask");
		} catch (ClassNotFoundException e) {
		}
	}
	
	//メニュー設定
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, 0, 0, "マーカー配置");
		menu.add(0, 1, 0, "データベース初期化");
		
		SubMenu sub;
		sub = menu.addSubMenu(0, 2, 0, "マーカー変更");
		
		sub.add(0,3,0,"android");
		sub.setIcon(R.drawable.ic_launcher);
		sub.add(0,4,0,"足跡");
		sub.setIcon(R.drawable.footprint);
		
		return true;
	}
	
	//メニュー項目設定
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			putmarker();
			return true;
		case 1:
			AsyncTest3 post = new AsyncTest3();
			post.execute();
			return true;
		case 3:
			icon_id = 1;
			AsyncTest5 set_icon_id1 = new AsyncTest5(icon_id,num);
			set_icon_id1.execute();
			return true;
		case 4:
			icon_id = 0;
			AsyncTest5 set_icon_id2 = new AsyncTest5(icon_id,num);
			set_icon_id2.execute();
			return true;
		}
		return false;
	}

	// ユーザーの操作待ち
	@Override
	protected void onResume() {
		super.onResume();

		// ズームボタンと現在地取得ボタンを可視化
		UiSettings settings = map.getUiSettings();
		settings.setZoomControlsEnabled(true);
		map.setMyLocationEnabled(true);
		

		// Nowボタンが押された時の処理
		mButton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 現在地が取得できない場合は、何もしない
				if (map.getMyLocation() == null);
				else {
					// 現在地の緯度、経度を取得
					double mylat = map.getMyLocation().getLatitude();
					double mylon = map.getMyLocation().getLongitude();

					// 現在地を取得
					LatLng position = new LatLng(map.getMyLocation()
							.getLatitude(), map.getMyLocation().getLongitude());

					// マーカーを打つ場所を現在地に設定
					options1.position(position);

					// マップの中心を現在地へ
					CameraUpdate cu = CameraUpdateFactory.newLatLng(new LatLng(
							mylat, mylon));
					map.moveCamera(cu);

					// 現在時刻を取得
					Calendar calendar = Calendar.getInstance(tz);
					SimpleDateFormat df = new SimpleDateFormat("HH:mm",
							Locale.JAPANESE);
					String temp = df.format(calendar.getTime());
					

					AsyncTest6 get_icon_id = new AsyncTest6(main,num);
					get_icon_id.execute();
					
					BitmapDescriptor icon = null;
					
					if(icon_id == 0){
						icon = BitmapDescriptorFactory
								.fromResource(R.drawable.footprint);
					}
					
					else if(icon_id == 1){
						icon = BitmapDescriptorFactory
								.fromResource(R.drawable.ic_launcher);
					}
					
					options1.icon(icon);
					
					// マーカーを配置
					options1.title("今ここ！at " + temp);
					map.addMarker(options1);
					
					//端末IDを取得
					TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
					String num = tm.getSimSerialNumber();
					//位置情報をデータベースに送信
					AsyncTest post = new AsyncTest(mylat, mylon, temp,num);
					post.execute();
				}
			}
		});

		// 自動更新ONボタンが押された時の処理
		mButton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// 自動更新ONボタンをクリック禁止に
				mButton2.setEnabled(false);

				// 自動更新OFFボタンをクリック可能に
				mButton3.setEnabled(true);

				// バックグラウンドでも動作を可能とするためにServiceを呼び出す
				startService(new Intent(MainActivity.this, MyService.class));
			}
		});

		// 自動更新OFFボタンが押された時の処理
		mButton3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// 自動更新ONボタンをクリック可能に
				mButton2.setEnabled(true);

				// 自動更新OFFボタンをクック禁止に
				mButton3.setEnabled(false);

				// Serviceを停止する.
				stopService(new Intent(MainActivity.this, MyService.class));
			}
		});


	}

	// 初期位置を設定するメソッド
	protected void moveToFirstRocation() {
		// 法政大学が中心となるように移動
		CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(
				35.710085, 139.523088), 13);
		map.moveCamera(cu);

		// マーカーを打つ位置を法政大学に設定
		LatLng position2 = new LatLng(35.710085, 139.523088);
		options2.position(position2);

		// 法政大学にマーカーを打つ
		options2.title("法政大学");
		map.addMarker(options2);
	}

	// データベースから位置情報を取得しマーカーを打つ
	public void putmarker() {
		AsyncTest2 post = new AsyncTest2(this);
		post.execute();
		BitmapDescriptor icon = BitmapDescriptorFactory
				.fromResource(R.drawable.footprint);
		try {
			JSONArray jsonArray = new JSONArray(json);
			latitude = new double[jsonArray.length()];
			longitude = new double[jsonArray.length()];
			time = new String[jsonArray.length()];
			id = new double[jsonArray.length()];
			number = jsonArray.length();
			for (int i = 0; i < number; i++) {

				JSONObject jsonObject = jsonArray.getJSONObject(i);
				
				id[i] = jsonObject.getDouble("id");
				latitude[i] = jsonObject.getDouble("latitude");
				longitude[i] = jsonObject.getDouble("longitude");
				time[i] = jsonObject.getString("time");
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < number; ++i) {

			// 緯度、経度を読み込み、マーカーを打つ位置を設定する
			LatLng position = new LatLng(latitude[i], longitude[i]);
			options1.position(position);
			
			if(id[i] == 0){
				icon = BitmapDescriptorFactory
						.fromResource(R.drawable.footprint);
			}
			
			else if(id[i] == 1){
				icon = BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher);
			}
			
			options1.icon(icon);
			
			// マーカーを打つ
			options1.title("今ここ! at " + time[i]);
			map.addMarker(options1);

		}

	}
}
