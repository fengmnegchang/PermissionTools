package com.example.settingsprj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XiaoMiRomUtils {

	public static final String ROM_MIUI_V5 = "V5";
	public static final String ROM_MIUI_V6 = "V6";
	public static final String ROM_MIUI_V7 = "V7";
	public static final String ROM_MIUI_V8 = "V8";

	public static String getRom() {
		Class clazz;
		try {
			clazz = Class.forName("android.os.SystemProperties");
			Object o = clazz.newInstance();
			Method method = clazz.getDeclaredMethod("get", String.class);
			String miuiString = (String) method.invoke(o,
			"ro.miui.ui.version.name");
			if (miuiString != null && !"".equals(miuiString)) {
			return miuiString;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
	private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
	private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

	public static boolean isMIUI() {
		try {
			final BuildProperties prop = BuildProperties.newInstance();
			return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
					|| prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
					|| prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
		} catch (final IOException e) {
			return false;
		}
	}
	
	public static boolean isMIUIforReflect(){
		Class clazz;
		try {
			clazz = Class.forName("android.os.SystemProperties");
			Object o = clazz.newInstance();
			Method method = clazz.getDeclaredMethod("get", String.class);
			String miuiString = (String) method.invoke(o,
			"ro.miui.ui.version.name");
			if (miuiString != null && !"".equals(miuiString)) {
			return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return false;
	}

}
