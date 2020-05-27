package com.gao.voicedemo;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{

	private static final String TAG = MainActivity.class.getSimpleName();
	private Toast mToast;
	private final int URL_REQUEST_CODE = 0X001;
	private TextView edit_text;

	@SuppressLint("ShowToast")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		edit_text = (TextView) findViewById(R.id.edit_text);
		StringBuffer buf = new StringBuffer();
		buf.append("当前APPID为：");
		buf.append(getString(R.string.app_id)+"\n");
		edit_text.setText(buf);
		requestPermissions();
		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		//mscInit(null);//采用sdk默认url
		Intent intent = new Intent(MainActivity.this, IatDemo.class);
		startActivity(intent);
	}


	private void requestPermissions(){
		try {
			if (Build.VERSION.SDK_INT >= 23) {
				int permission = ActivityCompat.checkSelfPermission(this,
						Manifest.permission.WRITE_EXTERNAL_STORAGE);
				if(permission!= PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(this,new String[]
							{Manifest.permission.WRITE_EXTERNAL_STORAGE,
							Manifest.permission.LOCATION_HARDWARE,Manifest.permission.READ_PHONE_STATE,
							Manifest.permission.WRITE_SETTINGS,Manifest.permission.READ_EXTERNAL_STORAGE,
							Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS},0x0010);
				}

				if(permission != PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(this,new String[] {
							Manifest.permission.ACCESS_COARSE_LOCATION,
							Manifest.permission.ACCESS_FINE_LOCATION},0x0010);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}
