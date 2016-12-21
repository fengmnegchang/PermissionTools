package com.example.settingsprj;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class XiaoMiActivity extends Activity implements OnItemClickListener {
	ListView listview;
	ArrayList<AppBean> list = new ArrayList<AppBean>();
	MiAdapter mMiAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiaomi);

		setTitle("小米系统设置");
		prepareView();
		prepareMap();

	}

	private void prepareView() {
		listview = (ListView) findViewById(R.id.listview);
		mMiAdapter = new MiAdapter(this, list);
		listview.setAdapter(mMiAdapter);
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		AppBean bean = list.get(position);
		Intent intent = new Intent();
		intent.setClassName(bean.packageName, bean.className);
		startActivity(intent);
	}

	private void prepareMap() {
		// 安全月报
		list.add(new AppBean("com.miui.securityadd", "com.miui.monthreport.ui.MonthReportActivity", "安全月报"));
		// 安全中心主页
//		com.miui.securityscan.MainActivity
//		android.intent.action.MAIN
//		android.intent.category.LAUNCHER2
		list.add(new AppBean("com.miui.securitycenter", "com.miui.securityscan.MainActivity", "安全中心主页"));
		// 安全设置
//		com.miui.securityscan.ui.settings.SettingsActivity
//		miui.intent.action.APP_SETTINGS6
//		com.miui.securitycenter.action.SECURITYCENTER_SETTINGS
//		android.intent.category.DEFAULT2
		list.add(new AppBean("com.miui.securitycenter", "com.miui.securityscan.ui.settings.SettingsActivity", "安全设置"));
		
		//病毒扫描
		list.add(new AppBean("com.miui.securitycenter", "com.miui.antivirus.MainActivity", "病毒扫描"));
		list.add(new AppBean("com.miui.securitycenter", "com.miui.antivirus.SettingsActivity", "病毒扫描设置"));
 
		//授权管理
		list.add(new AppBean("com.miui.securitycenter", "com.miui.permcenter.MainAcitivty", "授权管理"));
		//自启动管理
		list.add(new AppBean("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity", "自启动管理"));
		list.add(new AppBean("com.miui.securitycenter", "com.miui.permcenter.SettingsAcitivty", "授权管理设置"));
  
		//省电优化
		list.add(new AppBean("com.miui.securitycenter", "com.miui.powercenter.PowerCenter", "省电优化"));
		list.add(new AppBean("com.miui.securitycenter", "com.miui.powercenter.PowerSettings", "省电优化设置"));
		
		if (XiaoMiRomUtils.getRom().equals(XiaoMiRomUtils.ROM_MIUI_V8)) {
			//应用锁
			list.add(new AppBean("com.miui.securitycenter", "com.miui.applicationlock.FirstUseAppLockActivity", "应用锁"));
			
			//SIM 联网控制
			list.add(new AppBean("com.miui.securitycenter", "com.miui.networkassistant.ui.NetworkAssistantActivity", "联网控制"));
			//所有应用联网控制
			list.add(new AppBean("com.miui.securitycenter", "com.miui.networkassistant.ui.activity.FirewallAcitvity", "所有应用联网控制"));
	 	
			//设置流量套餐
			list.add(new AppBean("com.miui.securitycenter", "com.miui.networkassistant.ui.activity.DataUsageActivity", "设置流量套餐"));
			//流量保护
			list.add(new AppBean("com.miui.securitycenter", "com.miui.networkassistant.ui.activity.TrafficProtectionActivity", "流量保护"));
			//境外漫游设置
			list.add(new AppBean("com.miui.securitycenter", "com.miui.networkassistant.ui.activity.InternationalRoamingSettingActivity", "境外漫游设置"));
			//网速诊断
			list.add(new AppBean("com.miui.securitycenter", "com.miui.networkassistant.ui.activity.NetworkDiagnosticsActivity", "网速诊断"));
		}
		
		if (XiaoMiRomUtils.getRom().equals(XiaoMiRomUtils.ROM_MIUI_V7)) {
		}
 
		
		mMiAdapter.notifyDataSetChanged();
	}

 
}
