package com.tianyu.jty.acount.util;

import java.text.SimpleDateFormat;
/***
 * 卡号生成器，根据一定的规则生成不重复的卡号
 * @author Administrator
 *
 */
public class CardIDUtil {
	public static String getID(Integer type){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return type+"00"+sdf.format(System.currentTimeMillis());
	}
}
