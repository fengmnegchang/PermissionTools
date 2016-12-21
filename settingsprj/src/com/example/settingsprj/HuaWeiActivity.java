package com.example.settingsprj;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class HuaWeiActivity extends Activity implements OnItemClickListener {
	ListView listview;
	ArrayList<AppBean> list = new ArrayList<AppBean>();
	MiAdapter mMiAdapter;
	public static final String HUAWEI_SYS_MANAGER_APPNAME = "com.huawei.systemmanager";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_huawei);

		setTitle("华为手机管家");
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
		String manufacturer = SystemUtils.getPhoneManufacturer();
		if ("HUAWEI".equalsIgnoreCase(manufacturer)) {
			// 华为
			// 检测 手机管家的版本
			String systemmanagerVersion = SystemUtils.getAppVersionName(this, HUAWEI_SYS_MANAGER_APPNAME);
			
			if (systemmanagerVersion.startsWith("1.")) {
				// 1.0
				// className =
				// com.huawei.systemmanager.SystemManagerMainActivity;PackageName
				// =
				// com.huawei.systemmanager
				list.add(new AppBean("com.huawei.systemmanager", "com.huawei.systemmanager.SystemManagerMainActivity", "手机管家"));
			} else if (systemmanagerVersion.startsWith("3.")) {
				//330510
				// 3.30.510
				// className =
				// com.huawei.systemmanager.SystemManagerMainActivity;PackageName
				// =
				// com.huawei.systemmanager
				list.add(new AppBean("com.huawei.systemmanager", "com.huawei.systemmanager.mainscreen.MainScreenActivity", "手机管家"));
				list.add(new AppBean("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity", "悬浮窗管理"));
				
				// className =
				// com.huawei.permissionmanager.ui.SingleAppActivity;PackageName
				// =
 				// com.huawei.systemmanager
				list.add(new AppBean("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.SingleAppActivity", "单app权限列表"));
			}

			// className =
			// com.huawei.permissionmanager.ui.MainActivity;PackageName
			// = com.huawei.systemmanager
			list.add(new AppBean("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity", "权限管理"));

			// className =
			// com.huawei.permissionmanager.ui.PermissionSettingActivity;PackageName
			// = com.huawei.systemmanager
			// 各种读取修改权限列表
			// list.add(new AppBean("com.huawei.systemmanager",
			// "com.huawei.permissionmanager.ui.PermissionSettingActivity",
			// "权限"));

			// className =
			// com.huawei.permissionmanager.ui.SingleAppActivity;PackageName
			// =
			// com.huawei.systemmanager
			// list.add(new AppBean("com.huawei.systemmanager",
			// "com.huawei.permissionmanager.ui.SingleAppActivity",
			// "单app权限列表"));

			// className =
			// com.huawei.notificationmanager.ui.NotificationManagmentActivity;PackageName
			// = com.huawei.systemmanager
			list.add(new AppBean("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity", "通知管理"));

			// className =
			// com.huawei.systemmanager.antivirus.ui.AntiVirusActivity;PackageName
			// =
			// com.huawei.systemmanager
			list.add(new AppBean("com.huawei.systemmanager", "com.huawei.systemmanager.antivirus.ui.AntiVirusActivity", "病毒查杀"));

			// className =
			// com.huawei.netassistant.ui.NetAssistantMainActivity;PackageName
			// =
			// com.huawei.systemmanager
			list.add(new AppBean("com.huawei.systemmanager", "com.huawei.netassistant.ui.NetAssistantMainActivity", "流量管理"));

			// className =
			// com.huawei.harassmentinterception.ui.InterceptionActivity;PackageName
			// = com.huawei.systemmanager
			list.add(new AppBean("com.huawei.systemmanager", "com.huawei.harassmentinterception.ui.InterceptionActivity", "骚扰拦截"));

			// className =
			// com.huawei.systemmanager.optimize.bootstart.BootStartActivity;PackageName
			// = com.huawei.systemmanager
			list.add(new AppBean("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.bootstart.BootStartActivity", "开机自动启动"));

			// className =
			// com.huawei.systemmanager.optimize.process.ProtectActivity;PackageName
			// = com.huawei.systemmanager
			list.add(new AppBean("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity", "受保护的后台应用"));

		}

		mMiAdapter.notifyDataSetChanged();
	}

}
