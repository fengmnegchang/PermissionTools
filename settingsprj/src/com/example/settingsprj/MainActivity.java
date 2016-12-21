package com.example.settingsprj;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.wenming.library.BackgroundUtil;

public class MainActivity extends Activity {

	/**
	 * v6 05-15 12:58:40.188: E/TopTaskReceiver(10622): className =
	 * com.miui.permcenter.permissions.AppPermissionsEditorActivity 05-15
	 * 13:00:06.865: E/TopTaskReceiver(10622): className =
	 * com.miui.permcenter.autostart.AutoStartManagementActivity 05-15
	 * 12:59:56.913: E/TopTaskReceiver(10622): className =
	 * com.android.settings.Settings$AccessibilitySettingsActivity 05-15
	 * 13:00:20.567: E/TopTaskReceiver(10622): className =
	 * com.android.settings.Settings$DevelopmentSettingsActivity
	 * */

	/**
	 * v5 05-15 13:53:42.842: E/TopTaskReceiver(10796): className =
	 * com.android.settings.applications.InstalledAppDetails 05-15 13:55:18.024:
	 * E/TopTaskReceiver(11691): className =
	 * com.miui.securitycenter.permission.AppPermissionsEditor 05-15
	 * 13:52:36.401: E/TopTaskReceiver(10796): className =
	 * com.android.settings.Settings$AccessibilitySettingsActivity 05-15
	 * 13:52:43.209: E/TopTaskReceiver(10796): className =
	 * com.android.settings.Settings$DevelopmentSettingsActivity
	 * */
	Button floatbtn, autostartbtn, startsevicebtn, screenbtn, broadbtn, broadatbtn, permissionsbtn, toastbtn, allpermissionlistbtn, notifactionbtn, settingsbtn, xiaomibtn;
	Button startappbtn,huaweibtn,voicebtn;
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 5:
				attime();
				break;
			default:
				break;
			}

		}
	};
	
	//Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
    //startActivity(intent);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		floatbtn = (Button) findViewById(R.id.floatbtn);
		autostartbtn = (Button) findViewById(R.id.autostartbtn);
		startsevicebtn = (Button) findViewById(R.id.startservicebtn);
		screenbtn = (Button) findViewById(R.id.screenbtn);
		broadbtn = (Button) findViewById(R.id.broadbtn);
		broadatbtn = (Button) findViewById(R.id.broadatbtn);
		permissionsbtn = (Button) findViewById(R.id.permissionsbtn);
		toastbtn = (Button) findViewById(R.id.toastbtn);
		allpermissionlistbtn = (Button) findViewById(R.id.allpermissionlistbtn);
		notifactionbtn = (Button) findViewById(R.id.notifactionbtn);
		settingsbtn = (Button) findViewById(R.id.settingsbtn);
		xiaomibtn = (Button) findViewById(R.id.xiaomibtn);
		startappbtn = (Button) findViewById(R.id.startappbtn);
		huaweibtn = (Button) findViewById(R.id.huaweibtn);
		voicebtn = (Button) findViewById(R.id.voicebtn);

		floatbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 悬浮框
				if (XiaoMiRomUtils.isMIUIforReflect()) {
					openMiuiPermissionActivity(MainActivity.this);
					checkOp(MainActivity.this, 0);
				} 
//				Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//			    startActivity(intent);
			}
		});

		autostartbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 信任白名单
				Intent intent = new Intent();
				if (XiaoMiRomUtils.getRom().equals(XiaoMiRomUtils.ROM_MIUI_V5)) {
					intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
					// intent.setClassName("com.android.settings",
					// "com.miui.securitycenter.permission.AppPermissionsEditor");
					// intent.putExtra("extra_pkgname", getPackageName());

					// 05-15 14:13:57.268: E/TopTaskReceiver(15995): className =
					// com.miui.securitycenter.permission.AppPermissionsEditor;PackageName
					// = com.android.settings
					PackageInfo pInfo = null;
					try {
						pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
					} catch (NameNotFoundException e) {
						Log.e("", e.toString());
					}
					intent.setClassName("com.android.settings", "com.miui.securitycenter.permission.AppPermissionsEditor");
					intent.putExtra("extra_package_uid", pInfo.applicationInfo.uid);
				}

				if (XiaoMiRomUtils.getRom().equals(XiaoMiRomUtils.ROM_MIUI_V6)) {
					intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
				}
				if (XiaoMiRomUtils.getRom().equals(XiaoMiRomUtils.ROM_MIUI_V7)) {
					intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
				}
				if (XiaoMiRomUtils.getRom().equals(XiaoMiRomUtils.ROM_MIUI_V8)) {
					intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
				}
				startActivityForResult(intent, 3);

				startFloat(MainActivity.this, "请允许“settingsprj”开机自启动");
			}
		});

		startsevicebtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 辅助功能
				Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
				startActivityForResult(intent, 4);

				startFloat(MainActivity.this, "请在服务中开启“文字锁屏”");
			}
		});

		screenbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 开发者选项
				Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
				startActivityForResult(intent, 5);

				startFloat(MainActivity.this, "1.请打开【开发者选项】\n 2.请勾选【直接进入系统】");
			}
		});

		broadbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 发送广播 延时10s
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent intent = new Intent("com.example.settingsprj.TopTaskReceiver.FLAG");
						sendBroadcast(intent);
					}
				}, 10000);
			}
		});
		broadatbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 发送广播 延时10s
				attime();
			}
		});

		// 有权限查看使用情况的程序
		permissionsbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (BackgroundUtil.HavaPermissionForTest(MainActivity.this) == false) {
					Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					MainActivity.this.startActivity(intent);
					Toast.makeText(MainActivity.this, "权限不够\n请打开手机设置，点击安全-高级，在有权查看使用情况的应用中，为这个App打上勾", Toast.LENGTH_SHORT).show();
				}
			}
		});

		// 允许通知
		toastbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int resultOp = CheckPermissionUtils.checkPermiion(MainActivity.this, 11);
				if (resultOp == 0) {
					Toast.makeText(MainActivity.this, "允许通知", Toast.LENGTH_LONG).show();
				} else {
					// className =
					// com.android.settings.applications.InstalledAppDetails;PackageName
					// = com.android.settings
					// className =
					// com.android.settings.Settings$NotificationFilterActivity;PackageName
					// = com.android.settings
					Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
					Uri uri = Uri.fromParts("package", getPackageName(), null);
					intent.setData(uri);
					startActivity(intent);
				}
			}
		});

		allpermissionlistbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, AppOpsActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});

		// 通知使用权
		notifactionbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
				// Intent intent = new
				// Intent(Settings.Secure.ENABLED_NOTIFICATION_LISTENERS);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MainActivity.this.startActivity(intent);
			}
		});

		settingsbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SettingActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});

		xiaomibtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, XiaoMiActivity.class);
				startActivity(intent);
			}
		});
		
		startappbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 try {
					 //com.example.screenrecordprj com.taoguba.app
					 startAppLaunchIntentForPackage(MainActivity.this,"com.example.screenrecordprj");
				} catch (Exception e) {
				}
			}
		});
		
		huaweibtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, HuaWeiActivity.class);
				startActivity(intent);
			}
		});

		voicebtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, VoicePermissionActivity.class);
				startActivity(intent);
			}
		});
		
	}
	
	public   void doStartApplicationWithPackageName(Context context, String packagename) {

		// 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
		PackageInfo packageinfo = null;
		try {
			packageinfo = context.getPackageManager().getPackageInfo(packagename, 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (packageinfo == null) {
			return;
		}

		// 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
		Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
		resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		resolveIntent.setPackage(packageinfo.packageName);

		// 通过getPackageManager()的queryIntentActivities方法遍历
		List<ResolveInfo> resolveinfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);

		ResolveInfo resolveinfo = resolveinfoList.iterator().next();
		if (resolveinfo != null) {
			// packagename = 参数packname
			String packageName = resolveinfo.activityInfo.packageName;
			// 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
			String className = resolveinfo.activityInfo.name;
			// LAUNCHER Intent
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);

			// 设置ComponentName参数1:packagename参数2:MainActivity路径
			ComponentName cn = new ComponentName(packageName, className);

			intent.setComponent(cn);
			context.startActivity(intent);
		}
	}

	/***
	 * 启动app
	 * @param context
	 * @param appPackageName
	 */
	public   void startAppLaunchIntentForPackage(Context context, String appPackageName) {
		try {
			Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
			context.startActivity(intent);
		} catch (Exception e) {

		}
	}
	

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public void queryUsageStats(Context context) {
		class RecentUseComparator implements Comparator<UsageStats> {
			@Override
			public int compare(UsageStats lhs, UsageStats rhs) {
				return (lhs.getLastTimeUsed() > rhs.getLastTimeUsed()) ? -1 : (lhs.getLastTimeUsed() == rhs.getLastTimeUsed()) ? 0 : 1;
			}
		}
		RecentUseComparator mRecentComp = new RecentUseComparator();
		long ts = System.currentTimeMillis();
		UsageStatsManager mUsageStatsManager = (UsageStatsManager) context.getSystemService("usagestats");
		List<UsageStats> usageStats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, ts - 1000 * 10, ts);
		if (usageStats == null || usageStats.size() == 0) {
			return;
		}
		Collections.sort(usageStats, mRecentComp);
		String currentTopPackage = usageStats.get(0).getPackageName();
		Log.e("TopTaskReceiver", "currentTopPackage=" + currentTopPackage);
	}

	private void attime() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent("com.example.settingsprj.TopTaskReceiver.FLAG");
				sendBroadcast(intent);
				mHandler.sendEmptyMessage(5);
			}
		}, 5000);
	}

	/**
	 * 打开MIUI权限管理界面(MIUI v5, v6)
	 * 
	 * @param context
	 */
	public static void openMiuiPermissionActivity(Context context) {
		String packageName = "com.example.settingsprj";
		String rom = XiaoMiRomUtils.getRom();

		if (XiaoMiRomUtils.ROM_MIUI_V5.equals(rom)) {
			showInstalledAppDetails(context, packageName);
			startFloat(context, "请将【显示悬浮框】设置为允许");
		} else if (XiaoMiRomUtils.ROM_MIUI_V6.equals(rom)) {
			Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
			intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
			intent.putExtra("extra_pkgname", context.getPackageName());
			Activity a = (Activity) context;
			a.startActivityForResult(intent, 2);

			startFloat(context, "请滑动至底部，\n将【显示悬浮框】设置为允许");
		} else if (XiaoMiRomUtils.ROM_MIUI_V7.equals(rom)) {
			Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
			intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
			intent.putExtra("extra_pkgname", context.getPackageName());
			Activity a = (Activity) context;
			a.startActivityForResult(intent, 2);

			startFloat(context, "请滑动至底部，\n将【显示悬浮框】设置为允许");
		} else if (XiaoMiRomUtils.ROM_MIUI_V8.equals(rom)) {
			Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
			intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
			intent.putExtra("extra_pkgname", context.getPackageName());
			Activity a = (Activity) context;
			a.startActivityForResult(intent, 2);

			startFloat(context, "请滑动至底部，\n将【显示悬浮框】设置为允许");
		}

	}

	private static void startFloat(Context context, String content) {
		if (context instanceof Activity) {
			Activity a = (Activity) context;
			Intent i = new Intent();
			i.putExtra("content", content);
			i.setClass(context, FloatActivity.class);
			a.startActivity(i);
		}
	}

	private static final String SCHEME = "package";
	/**
	 * 调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.1及之前版本)
	 */
	private static final String APP_PKG_NAME_21 = "com.android.settings.ApplicationPkgName";
	/**
	 * 调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.2)
	 */
	private static final String APP_PKG_NAME_22 = "pkg";
	/**
	 * InstalledAppDetails所在包名
	 */
	private static final String APP_DETAILS_PACKAGE_NAME = "com.android.settings";
	/**
	 * InstalledAppDetails类名
	 */
	private static final String APP_DETAILS_CLASS_NAME = "com.android.settings.InstalledAppDetails";

	/**
	 * 调用系统InstalledAppDetails界面显示已安装应用程序的详细信息。 对于Android 2.3（Api Level
	 * 9）以上，使用SDK提供的接口； 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）。
	 * 
	 * @param context
	 * 
	 * @param packageName
	 *            应用程序的包名
	 */
	public static void showInstalledAppDetails(Context context, String packageName) {
		Intent intent = new Intent();
		final int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= 9) { // 2.3（ApiLevel 9）以上，使用SDK提供的接口
			intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
			Uri uri = Uri.fromParts(SCHEME, packageName, null);
			intent.setData(uri);
		} else { // 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）
			// 2.2和2.1中，InstalledAppDetails使用的APP_PKG_NAME不同。
			final String appPkgName = (apiLevel == 8 ? APP_PKG_NAME_22 : APP_PKG_NAME_21);
			intent.setAction(Intent.ACTION_VIEW);
			intent.setClassName(APP_DETAILS_PACKAGE_NAME, APP_DETAILS_CLASS_NAME);
			intent.putExtra(appPkgName, packageName);
		}
		context.startActivity(intent);
	}

	/**
	 * 判断MIUI的悬浮窗权限
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMiuiFloatWindowOpAllowed(Context context) {
		final int version = Build.VERSION.SDK_INT;
		if (version >= 19) {
			checkOp(context, 24); // 自己写就是24 为什么是24?看AppOpsManager
		} else {
			if ((context.getApplicationInfo().flags & 1 << 27) == 1) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static boolean checkOp(Context context, int op) {
		final int version = Build.VERSION.SDK_INT;
		if (version >= 19) {
			AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
			try {

				Class[] cparams = new Class[3];
				cparams[0] = int.class;
				cparams[1] = int.class;
				cparams[2] = String.class;
				Method method = Class.forName("android.app.AppOpsManager").getMethod("checkOp", cparams);
				method.setAccessible(true);

				Object[] vparams = new Object[3];
				vparams[0] = 24;// OP_SYSTEM_ALERT_WINDOW
				vparams[1] = Binder.getCallingUid();
				vparams[2] = new String("com.example.settingsprj");
				Object result = method.invoke(manager, vparams);

				if (AppOpsManager.MODE_ALLOWED == Integer.parseInt(result + "")) { // 这儿反射就自己写吧
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				Log.e("", e.getMessage());
			}
		} else {
			Log.e("", "Below API 19 cannot invoke!");
		}
		return false;
	}
 
}
