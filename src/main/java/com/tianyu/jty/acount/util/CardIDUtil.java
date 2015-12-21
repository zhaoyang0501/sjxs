package com.tianyu.jty.acount.util;

import java.text.SimpleDateFormat;

public class CardIDUtil {
	public static String getID(Integer type){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return type+"00"+sdf.format(System.currentTimeMillis());
	}
}
