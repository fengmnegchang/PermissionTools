package com.example.settingsprj;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SettingActivity extends Activity implements OnItemClickListener {
	ListView listview;
	ArrayList<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		setTitle("设置权限");
		prepareMap();
		prepareView();

	}

	private void prepareView() {
		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, list));
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		String action = list.get(position);
		Intent intent = new Intent(action);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
	}
	 
	private void prepareMap() {
		list.add("android.settings.SETTINGS");
		list.add("android.settings.APN_SETTINGS");
		list.add("android.settings.LOCATION_SOURCE_SETTINGS");
		list.add("android.settings.WIRELESS_SETTINGS");
		list.add("android.settings.AIRPLANE_MODE_SETTINGS");
		list.add("android.settings.VOICE_CONTROL_AIRPLANE_MODE");
		list.add("android.settings.ACCESSIBILITY_SETTINGS");
		list.add("android.settings.USAGE_ACCESS_SETTINGS");
		
//		list.add("android.intent.category.USAGE_ACCESS_CONFIG");
//		list.add("android.settings.metadata.USAGE_ACCESS_REASON");
		list.add("android.settings.SECURITY_SETTINGS");
		list.add("com.android.settings.TRUSTED_CREDENTIALS_USER");
		list.add("com.android.settings.MONITORING_CERT_INFO");
		list.add("android.settings.PRIVACY_SETTINGS");
		list.add("android.settings.WIFI_SETTINGS");
		list.add("android.settings.WIFI_IP_SETTINGS");
		list.add("android.settings.BLUETOOTH_SETTINGS");
		list.add("android.settings.CAST_SETTINGS");
		list.add("android.settings.DATE_SETTINGS");
		list.add("android.settings.SOUND_SETTINGS");
		list.add("android.settings.DISPLAY_SETTINGS");
		list.add("android.settings.LOCALE_SETTINGS");
		list.add("android.settings.VOICE_INPUT_SETTINGS");
		list.add("android.settings.INPUT_METHOD_SETTINGS");
		list.add("android.settings.INPUT_METHOD_SUBTYPE_SETTINGS");
//		list.add("android.settings.SHOW_INPUT_METHOD_PICKER");
		list.add("android.settings.USER_DICTIONARY_SETTINGS");
		list.add("com.android.settings.USER_DICTIONARY_INSERT");
		list.add("android.settings.APPLICATION_SETTINGS");
		list.add("android.settings.APPLICATION_DEVELOPMENT_SETTINGS");
//		list.add("android.settings.QUICK_LAUNCH_SETTINGS");
		list.add("android.settings.MANAGE_APPLICATIONS_SETTINGS");
		list.add("android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS");
		list.add("android.settings.action.MANAGE_OVERLAY_PERMISSION");
		list.add("android.settings.action.MANAGE_WRITE_SETTINGS");
//		list.add("android.settings.APPLICATION_DETAILS_SETTINGS");
		list.add("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS");
//		list.add("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
//		list.add("android.settings.APP_OPS_SETTINGS");
//		list.add("android.settings.SYSTEM_UPDATE_SETTINGS");
		list.add("android.settings.SYNC_SETTINGS");
		list.add("android.settings.ADD_ACCOUNT_SETTINGS");
		list.add("android.settings.NETWORK_OPERATOR_SETTINGS");
		list.add("android.settings.DATA_ROAMING_SETTINGS");
		list.add("android.settings.INTERNAL_STORAGE_SETTINGS");
		list.add("android.settings.MEMORY_CARD_SETTINGS");
		list.add("android.search.action.SEARCH_SETTINGS");
		list.add("android.settings.DEVICE_INFO_SETTINGS");
		list.add("android.settings.NFC_SETTINGS");
		list.add("android.settings.NFCSHARING_SETTINGS");
		list.add("android.settings.NFC_PAYMENT_SETTINGS");
		list.add("android.settings.DREAM_SETTINGS");
		list.add("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
		list.add("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS");
		list.add("android.settings.ACTION_CONDITION_PROVIDER_SETTINGS");
		list.add("android.settings.CAPTIONING_SETTINGS");
		list.add("android.settings.ACTION_PRINT_SETTINGS");
		list.add("android.settings.ZEN_MODE_SETTINGS");
		list.add("android.settings.ZEN_MODE_PRIORITY_SETTINGS");
		list.add("android.settings.ZEN_MODE_AUTOMATION_SETTINGS");
		list.add("android.settings.VOICE_CONTROL_DO_NOT_DISTURB_MODE");
		list.add("android.settings.ZEN_MODE_SCHEDULE_RULE_SETTINGS");
		list.add("android.settings.ZEN_MODE_EVENT_RULE_SETTINGS");
		list.add("android.settings.ZEN_MODE_EXTERNAL_RULE_SETTINGS");
//		list.add("android.settings.SHOW_REGULATORY_INFO");
//		list.add("android.settings.DEVICE_NAME");
//		list.add("android.settings.PAIRING_SETTINGS");
		list.add("android.settings.BATTERY_SAVER_SETTINGS");
		list.add("android.settings.VOICE_CONTROL_BATTERY_SAVER_MODE");
		list.add("android.settings.HOME_SETTINGS");
		list.add("android.settings.NOTIFICATION_SETTINGS");
		list.add("android.settings.APP_NOTIFICATION_SETTINGS");
		list.add("android.settings.ACTION_APP_NOTIFICATION_REDACTION");
		
	}

}
