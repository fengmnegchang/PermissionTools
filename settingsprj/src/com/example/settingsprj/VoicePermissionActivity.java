/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-6-30下午2:01:15
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.example.settingsprj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-6-30下午2:01:15
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class VoicePermissionActivity extends Activity {
	public static final String HUAWEI_SYS_MANAGER_APPNAME = "com.huawei.systemmanager";
	public static final String HUAWEI_SYS_MANAGER_10_PERMISSION_CLASSNAME = "com.huawei.permissionmanager.ui.MainActivity";//权限管理
	public static final String HUAWEI_SYS_MANAGER_30_PERMISSION_CLASSNAME = "com.huawei.notificationmanager.ui.NotificationManagmentActivity";//通知管理
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		int resultOp = CheckPermissionUtils.checkPermiion(VoicePermissionActivity.this, 27);
		Log.e("VoicePermissionActivity","OP_RECORD_AUDIO resultOp = "+resultOp);
		if(resultOp==0){
			
		}
		//判断机型
		String manufacturer = SystemUtils.getPhoneManufacturer();
		if("HUAWEI".equalsIgnoreCase(manufacturer)){
			//华为
			//检测 手机管家的版本
			Intent intent = new Intent();
			intent.setClassName(HUAWEI_SYS_MANAGER_APPNAME, HUAWEI_SYS_MANAGER_10_PERMISSION_CLASSNAME);
			startActivity(intent);
//			String systemmanagerVersion =  SystemUtils.getAppVersionName(this, HUAWEI_SYS_MANAGER_APPNAME).replace(".", "");
//			if(Integer.parseInt(systemmanagerVersion)<=10){
//				//1.0
//				Intent intent = new Intent();
//				intent.setClassName(HUAWEI_SYS_MANAGER_APPNAME, HUAWEI_SYS_MANAGER_10_PERMISSION_CLASSNAME);
//				startActivity(intent);
//			}else if(Integer.parseInt(systemmanagerVersion)==30){
//				//3.0
//				Intent intent = new Intent();
//				intent.setClassName(HUAWEI_SYS_MANAGER_APPNAME, HUAWEI_SYS_MANAGER_30_PERMISSION_CLASSNAME);
//				startActivity(intent);
//			}
		}else if("Xiaomi".equalsIgnoreCase(manufacturer)){
			//小米
			openMiuiPermissionActivity(this,getPackageName());
		}
	}
	
	
	/**
	 * 打开MIUI权限管理界面(MIUI v5, v6)
	 * 
	 * @param context
	 */
	public  void openMiuiPermissionActivity(Context context,String packageName) {
		String rom = XiaoMiRomUtils.getRom();
		if (XiaoMiRomUtils.ROM_MIUI_V5.equals(rom)) {
			SystemUtils.showInstalledAppDetails(context, packageName);
		} else if (XiaoMiRomUtils.ROM_MIUI_V6.equals(rom)) {
			Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
			intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
			intent.putExtra("extra_pkgname", context.getPackageName());
			context.startActivity(intent);

		} else if (XiaoMiRomUtils.ROM_MIUI_V7.equals(rom)) {
			Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
			intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
			intent.putExtra("extra_pkgname", context.getPackageName());
			context.startActivity(intent);

		} else if (XiaoMiRomUtils.ROM_MIUI_V8.equals(rom)) {
			Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
			intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
			intent.putExtra("extra_pkgname", context.getPackageName());
			context.startActivity(intent);
		}

	}

}
