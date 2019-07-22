package com.luoliang.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author luoliang
 * @date 创建时间：2019年7月22日下午2:54:30
 */
public class PropertyMgr {
	static Properties props = new Properties();

	static {
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object get(String key) {
		if (props == null) {
			return null;
		}
		return props.get(key);

	}

	public static void main(String[] args) {
		System.out.println(PropertyMgr.get("initTankCount"));

	}
}
