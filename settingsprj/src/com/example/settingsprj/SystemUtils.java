/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-6-30上午11:30:49
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.example.settingsprj;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-6-30上午11:30:49
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

public class SystemUtils {
	/**
	 * Android开发中程序判断手机操作系统版本。
	 * 
	 * @return
	 */
	public static int getAndroidSDKVersion() {
		int version = 0;
		try {
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		} catch (NumberFormatException e) {
			Log.e(e.toString(), null);

		}
		return version;
	}

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public static String getPhoneMdel() {
		String s = android.os.Build.MODEL;
		return s;

	}

	/**
	 * 获取手机厂商
	 * 
	 * @return
	 */
	public static String getPhoneManufacturer() {
		String phoneManufacturer = android.os.Build.MANUFACTURER;
		return phoneManufacturer;
	}

	/**
	 * 获取系统版本号
	 * 
	 * @return
	 */
	public static String getSystemVersion() {
		String systemVersion = android.os.Build.VERSION.RELEASE;
		return systemVersion;
	}

	/**
	 * 获取当前软件版本号
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppVersionName(Context context,String packageName) {
		String versionName = "";
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
			versionName = packageInfo.versionName;
			if (TextUtils.isEmpty(versionName)) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
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
//	/**
//	 * 获取手机号码
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public static String getPhoneNumber(Context context) {
//		TelephonyManager phoneMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//		String phoneNumber = phoneMgr.getLine1Number();
//		if (TextUtils.isEmpty(phoneNumber)) {
//			return "手机号码获取不到，运营商未保存在卡中";
//		}
//		return phoneNumber;
//
//	}
//
//	/**
//	 * 获取手机mac地址
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public static String getMacAddress(Context context) {
//
//		String result = "";
//
//		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//
//		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//
//		result = wifiInfo.getMacAddress();
//
//		Log.d("Utils", "macAdd:" + result);
//
//		return result;
//
//	}
//
//	/**
//	 * 获取手机ＣＰＵ信息
//	 * 
//	 * @return
//	 */
//	public static String[] getCpuInfo() {
//		String str1 = "/proc/cpuinfo";
//		String str2 = "";
//		String[] cpuInfo = { "", "" };
//		String[] arrayOfString;
//		try {
//			FileReader fr = new FileReader(str1);
//			BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
//			str2 = localBufferedReader.readLine();
//			arrayOfString = str2.split("\\s+");
//			for (int i = 2; i < arrayOfString.length; i++) {
//				cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
//			}
//			str2 = localBufferedReader.readLine();
//			arrayOfString = str2.split("\\s+");
//			cpuInfo[1] += arrayOfString[2];
//			localBufferedReader.close();
//		} catch (IOException e) {
//		}
//		return cpuInfo;
//	}
//
//	/**
//	 * 手机可用内存大小
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public static String getTotalMemory(Context context) {
//		ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//		MemoryInfo memoryInfo = new MemoryInfo();
//		mActivityManager.getMemoryInfo(memoryInfo);
//		Long memorySize = memoryInfo.availMem;
//		String phoneMemory = Formatter.formatFileSize(context, memorySize);
//		return phoneMemory;
//
//	}
//
//	/**
//	 * 获取ＳＤＫ大小
//	 * 
//	 * @return
//	 */
//	public static long[] getSDCardMemory() {
//		long[] sdCardInfo = new long[2];
//		String state = Environment.getExternalStorageState();
//		if (Environment.MEDIA_MOUNTED.equals(state)) {
//			File sdcardDir = Environment.getExternalStorageDirectory();
//			StatFs sf = new StatFs(sdcardDir.getPath());
//			long bSize = sf.getBlockSize();
//			long bCount = sf.getBlockCount();
//			long availBlocks = sf.getAvailableBlocks();
//
//			sdCardInfo[0] = bSize * bCount;// 总大小
//			sdCardInfo[1] = bSize * availBlocks;// 可用大小
//		}
//		return sdCardInfo;
//	}
//
//	/**
//	 * 获取运行内存大小
//	 */
//	public static String getAllTotalMemory() {
//		String str1 = "/proc/meminfo";
//		String str2 = "";
//		try {
//			FileReader fr = new FileReader(str1);
//			BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
//			while ((str2 = localBufferedReader.readLine()) != null) {
//				return str2;
//			}
//		} catch (IOException e) {
//		}
//		return "获取失败";
//	}
//
//	/**
//	 * 从字符串里面提取数字转为long类型
//	 * 
//	 * @param str
//	 * @return
//	 */
//	public static long getNumberToString(String str) {
//
//		String regEx = "[^0-9]";
//		Pattern p = Pattern.compile(regEx);
//		Matcher m = p.matcher(str);
//		String s = m.replaceAll("").trim();
//		return Integer.valueOf(s);
//	}
//
//	/**
//	 * 获取手机上的所有app，除系统app
//	 * 
//	 * @return
//	 */
//	public static String getAllApp(Context context) {
//		String result = "";
//		List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
//		for (PackageInfo i : packages) {
//			if ((i.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
//				result += i.applicationInfo.loadLabel(context.getPackageManager()).toString() + ",";
//			}
//		}
//		return result.substring(0, result.length() - 1);
//
//	}
//
//	/**
//	 * 查询手机内系统应用
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public static List<PackageInfo> getAllSystemApps(Context context) {
//		List<PackageInfo> apps = new ArrayList<PackageInfo>();
//		PackageManager pManager = context.getPackageManager();
//		// 获取手机内所有应用
//		List<PackageInfo> paklist = pManager.getInstalledPackages(0);
//		for (int i = 0; i < paklist.size(); i++) {
//			PackageInfo pak = (PackageInfo) paklist.get(i);
//			// 判断是否为非系统预装的应用程序
//			if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) >= 0) { // 设置>0为系统应用，小于0未非系统应用
//				// customs applications
//				apps.add(pak);
//			}
//		}
//		return apps;
//	}
//
//	/**
//	 * 查询手机内非系统应用
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public static List<PackageInfo> getAllOtherApps(Context context) {
//		List<PackageInfo> apps = new ArrayList<PackageInfo>();
//		PackageManager pManager = context.getPackageManager();
//		// 获取手机内所有应用
//		List<PackageInfo> paklist = pManager.getInstalledPackages(0);
//		for (int i = 0; i < paklist.size(); i++) {
//			PackageInfo pak = (PackageInfo) paklist.get(i);
//			// 判断是否为非系统预装的应用程序
//			if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) { // 设置>0为系统应用，小于0未非系统应用
//				// customs applications
//				apps.add(pak);
//			}
//		}
//		return apps;
//	}
//
//	/**
//	 * 跳转到应用信息
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public static Intent getAppDetailSettingIntent(Context context) {
//		Intent localIntent = new Intent();
//		localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		if (Build.VERSION.SDK_INT >= 9) {
//			localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//			localIntent.setData(Uri.fromParts("package", "com.example.activity", null));
//		} else if (Build.VERSION.SDK_INT <= 8) {
//			localIntent.setAction(Intent.ACTION_VIEW);
//			localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
//			localIntent.putExtra("com.android.settings.ApplicationPkgName", "com.example.activity");
//		}
//		return localIntent;
//	}
//
//	/**
//	 * 小米手机跳转到权限管理页面
//	 * 
//	 * @param context
//	 */
//	public static void goMiUiSetting(Context context) {
//		PackageInfo info = null;
//		PackageManager pm = context.getPackageManager();
//		try {
//
//			info = pm.getPackageInfo(context.getPackageName(), 0);
//		} catch (NameNotFoundException e) {
//			e.printStackTrace();
//		}
//		Intent i = new Intent("miui.intent.action.APP_PERM_EDITOR");
//		i.setClassName("com.android.settings", "com.miui.securitycenter.permission.AppPermissionsEditor");
//		i.putExtra("extra_package_uid", info.applicationInfo.uid);
//		try {
//			context.startActivity(i);
//		} catch (Exception e) {
//			Toast.makeText(context, "只有MIUI才可以设置哦", Toast.LENGTH_SHORT).show();
//		}
//	}
//
//	/**
//	 * 华为手机跳转到权限设置页面
//	 * 
//	 * @param context
//	 */
//	public static void goHuaWeiSetting(Context context) {
//		try {
//			// HUAWEI H60-l02 P8max测试通过
//			Log.d(MainActivity.class.getSimpleName(), "进入指定app悬浮窗管理页面失败，自动进入所有app悬浮窗管理页面");
//			Intent intent = new Intent("com.example.activity");
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			// ComponentName comp = new
//			// ComponentName("com.huawei.systemmanager","com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
//			// ComponentName comp = new
//			// ComponentName("com.huawei.systemmanager",
//			// "com.huawei.permissionmanager.ui.SingleAppActivity");//华为权限管理，跳转到本app的权限管理页面,这个需要华为接口权限，未解决
//			ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");// 悬浮窗管理页面
//			intent.setComponent(comp);
//			context.startActivity(intent);
//		} catch (SecurityException e) {
//			Intent intent = new Intent("com.example.activity");
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			// ComponentName comp = new
//			// ComponentName("com.huawei.systemmanager","com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
//			ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");// 华为权限管理，跳转到本app的权限管理页面,这个需要华为接口权限，未解决
//			// ComponentName comp = new
//			// ComponentName("com.huawei.systemmanager","com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");//悬浮窗管理页面
//			intent.setComponent(comp);
//			context.startActivity(intent);
//			Log.d(MainActivity.class.getSimpleName(), "正在进入指定app悬浮窗开启位置..");
//		} catch (ActivityNotFoundException e) {
//			/**
//			 * 手机管家版本较低 HUAWEI SC-UL10
//			 */
//			// Toast.makeText(MainActivity.this, "act找不到",
//			// Toast.LENGTH_LONG).show();
//			Intent intent = new Intent("com.example.activity");
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.permission.TabItem");// 权限管理页面
//																														// android4.4
//			// ComponentName comp = new
//			// ComponentName("com.android.settings","com.android.settings.permission.single_app_activity");//此处可跳转到指定app对应的权限管理页面，但是需要相关权限，未解决
//			intent.setComponent(comp);
//			context.startActivity(intent);
//			e.printStackTrace();
//		} catch (Exception e) {
//			// 抛出异常时提示信息
//			Toast.makeText(context, "进入设置页面失败，请手动设置", Toast.LENGTH_LONG).show();
//		}
//
//	}
//
//	/**
//	 * 自动启动管理
//	 * 
//	 * @param context
//	 */
//	public static final void goChangPwd(Context context) {
//		try {
//			Intent intent = new Intent("com.example.activitiy");
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");// 仅对华为有效
//			intent.setComponent(comp);
//			context.startActivity(intent);
//			Log.d(MainActivity.class.getSimpleName(), "正在进入手机管家自动启动权限管理页面。。。");
//		} catch (Exception e) {
//			// 抛出异常时提示信息
//			Toast.makeText(context, "操作异常，进入失败", Toast.LENGTH_LONG).show();
//		}
//	}
//
//	/**
//	 * 受保护应用
//	 * 
//	 * @param context
//	 *            cmp=com.huawei.systemmanager/.optimize.process.ProtectActivity
//	 */
//	public static final void setProtectApp(Context context) {
//		try {
//			Intent intent = new Intent("com.example.activitiy");
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");// 仅对华为有效
//			intent.setComponent(comp);
//			context.startActivity(intent);
//			Log.d(MainActivity.class.getSimpleName(), "正在进入手机管家自动启动权限管理页面。。。");
//		} catch (Exception e) {
//			// 抛出异常时提示信息
//			Toast.makeText(context, "操作异常，进入失败", Toast.LENGTH_LONG).show();
//		}
//	}
//
//	/**
//	 * 判断某个服务是否正在运行的方法
//	 * 
//	 * @param mContext
//	 * @param serviceName
//	 *            是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
//	 * @return true代表正在运行，false代表服务没有正在运行
//	 */
//	public static boolean isServiceWork(Context mContext, String serviceName) {
//		boolean isWork = false;
//		ActivityManager myAM = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
//		List<RunningServiceInfo> myList = myAM.getRunningServices(40);
//		if (myList.size() <= 0) {
//			return false;
//		}
//		for (int i = 0; i < myList.size(); i++) {
//			String mName = myList.get(i).service.getClassName().toString();
//			if (mName.equals(serviceName)) {
//				isWork = true;
//				break;
//			}
//		}
//		return isWork;
//	}
}