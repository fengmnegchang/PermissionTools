/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-6-29下午4:16:31
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
 * @createTime:2016-6-29下午4:16:31
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AppBean {
	public String className;
	public String packageName;
	public String funName;

	public AppBean(String packageName, String className, String funName) {
		this.packageName = packageName;
		this.className = className;
		this.funName = funName;
	}
}
