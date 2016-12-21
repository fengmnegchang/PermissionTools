package com.example.settingsprj;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AppOpsActivity extends Activity implements OnItemClickListener {
	ListView listview;
	HashMap<Integer, String> opsmap = new HashMap<Integer, String>();
	ArrayList<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_ops);

		setTitle("所有权限列表");
		prepareMap();
		prepareView();

	}

	private void prepareView() {
		listview = (ListView) findViewById(R.id.listview);

		for (Map.Entry<Integer, String> entry : opsmap.entrySet()) {
			list.add(entry.getKey() + "  " + entry.getValue());
		}
		listview.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, list));
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (opsmap.containsKey(position)) {
			reflect(position);
		}
	}
	

	private void reflect(int ops) {
		AppOpsManager manager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
		Class[] params = new Class[3];
		params[0] = int.class;
		params[1] = int.class;
		params[2] = String.class;

		ApplicationInfo ai = null;
		Method m = null;
		try {
			PackageManager pm = getPackageManager();
			ai = pm.getApplicationInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		int op = -1;
		try {
			m = manager.getClass().getDeclaredMethod("checkOp", params);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			m.setAccessible(true);
			op = (Integer) m.invoke(manager, new Object[] { ops, ai.uid, getPackageName() });
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(this, "op = " + op, Toast.LENGTH_SHORT).show();
		Log.e("reflect", "op = " + op);
	}

	private void prepareMap() {
		opsmap.put(-1, "OP_NONE");
		opsmap.put(0, "OP_COARSE_LOCATION");
		opsmap.put(1, "OP_FINE_LOCATION");
		opsmap.put(2, "OP_GPS");
		opsmap.put(3, "OP_VIBRATE");
		opsmap.put(4, "OP_READ_CONTACTS");
		opsmap.put(5, "OP_WRITE_CONTACTS");
		opsmap.put(6, "OP_READ_CALL_LOG");
		opsmap.put(7, "OP_WRITE_CALL_LOG");
		opsmap.put(8, "OP_READ_CALENDAR");
		opsmap.put(9, "OP_WRITE_CALENDAR");
		opsmap.put(10, "OP_WIFI_SCAN");
		opsmap.put(11, "OP_POST_NOTIFICATION");
		opsmap.put(12, "OP_NEIGHBORING_CELLS");
		opsmap.put(13, "OP_CALL_PHONE");
		opsmap.put(14, "OP_READ_SMS");
		opsmap.put(15, "OP_WRITE_SMS");
		opsmap.put(16, "OP_RECEIVE_SMS");
		opsmap.put(17, "OP_RECEIVE_EMERGECY_SMS");
		opsmap.put(18, "OP_RECEIVE_MMS");
		opsmap.put(19, "OP_RECEIVE_WAP_PUSH");
		opsmap.put(20, "OP_SEND_SMS");
		opsmap.put(21, "OP_READ_ICC_SMS");
		opsmap.put(22, "OP_WRITE_ICC_SMS");
		opsmap.put(23, "OP_WRITE_SETTINGS");
		opsmap.put(24, "OP_SYSTEM_ALERT_WINDOW");
		opsmap.put(25, "OP_ACCESS_NOTIFICATIONS");
		opsmap.put(26, "OP_CAMERA");
		opsmap.put(27, "OP_RECORD_AUDIO");
		opsmap.put(28, "OP_PLAY_AUDIO");
		opsmap.put(29, "OP_READ_CLIPBOARD");
		opsmap.put(30, "OP_WRITE_CLIPBOARD");
		opsmap.put(31, "OP_TAKE_MEDIA_BUTTONS");
		opsmap.put(32, "OP_TAKE_AUDIO_FOCUS");
		opsmap.put(33, "OP_AUDIO_MASTER_VOLUME");
		opsmap.put(34, "OP_AUDIO_VOICE_VOLUME");
		opsmap.put(35, "OP_AUDIO_RING_VOLUME");
		opsmap.put(36, "OP_AUDIO_MEDIA_VOLUME");
		opsmap.put(37, "OP_AUDIO_ALARM_VOLUME");
		opsmap.put(38, "OP_AUDIO_NOTIFICATION_VOLUME");
		opsmap.put(39, "OP_AUDIO_BLUETOOTH_VOLUME");
		opsmap.put(40, "OP_WAKE_LOCK");
		opsmap.put(41, "OP_MONITOR_LOCATION");
		opsmap.put(42, "OP_MONITOR_HIGH_POWER_LOCATION");
		opsmap.put(43, "OP_GET_USAGE_STATS");
		opsmap.put(44, "OP_MUTE_MICROPHONE");
		opsmap.put(45, "OP_TOAST_WINDOW");
		opsmap.put(46, "OP_PROJECT_MEDIA");
		opsmap.put(47, "OP_ACTIVATE_VPN");
		opsmap.put(48, "OP_WRITE_WALLPAPER");
		opsmap.put(49, "OP_ASSIST_STRUCTURE");
		opsmap.put(50, "OP_ASSIST_SCREENSHOT");
		opsmap.put(51, "OP_READ_PHONE_STATE");
		opsmap.put(52, "OP_ADD_VOICEMAIL");
		opsmap.put(53, "OP_USE_SIP");
		opsmap.put(54, "OP_PROCESS_OUTGOING_CALLS");
		opsmap.put(55, "OP_USE_FINGERPRINT");
		opsmap.put(56, "OP_BODY_SENSORS");
		opsmap.put(57, "OP_READ_CELL_BROADCASTS");
		opsmap.put(58, "OP_MOCK_LOCATION");
		opsmap.put(59, "OP_READ_EXTERNAL_STORAGE");
		opsmap.put(60, "OP_WRITE_EXTERNAL_STORAGE");
		opsmap.put(61, "OP_TURN_SCREEN_ON");
		opsmap.put(62, "_NUM_OP");
	}

}
