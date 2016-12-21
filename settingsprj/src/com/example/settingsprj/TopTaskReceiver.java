package com.example.settingsprj;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.wenming.library.BackgroundUtil;

public class TopTaskReceiver extends BroadcastReceiver {
	//adb shell am broadcast -a com.example.settingsprj.TopTaskReceiver.FLAG
	private String TOP_TASK = "com.example.settingsprj.TopTaskReceiver.FLAG";
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(action!=null && action.equals(TOP_TASK)){
			getTopComponentName(context);
//			queryUsageStats(context);
		}
	}
	
//com.android.packageinstaller.PackageInstallerActivity;PackageName = com.android.packageinstaller
	//D:\adt-bundle-windows\sdk\platform-tools>adb shell am force-stop com.android.packageinstaller
	//D:\adt-bundle-windows\sdk\platform-tools>adb shell
	//shell@cancro:/ $ pm clear com.android.packageinstaller
	//pm clear com.android.packageinstaller
	//Success
	//10-21 11:35:40.636: E/TopTaskReceiver(11654): className = com.kuzhuan.activitys.MainActivity;PackageName = com.kuzhuan


	  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
	    public static void queryUsageStats(Context context) {
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
	        Log.e("TopTaskReceiver", "currentTopPackage="+currentTopPackage);
	    }

	public static ComponentName getTopComponentName(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
		ComponentName componentName = taskInfo.get(0).topActivity;
		String log = "className = "+componentName.getClassName()+";PackageName = "+componentName.getPackageName();
		Toast.makeText(context, log, Toast.LENGTH_LONG).show();
		Log.e("TopTaskReceiver", log);
		return componentName;
	}
}
