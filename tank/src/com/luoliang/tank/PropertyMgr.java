package com.luoliang.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author luoliang
 * @date 创建时间：2019年7月22日下午2:54:30
 */
public class PropertyMgr {
	private Properties props;

	private PropertyMgr() {
		props = new Properties();
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class createClass {
		private static final PropertyMgr propsMgr = new PropertyMgr();
	}

	public static PropertyMgr getPropertyMgr() {
		return createClass.propsMgr;
	}

	public Integer getInt(String key) {
		if (props == null) {
			return null;
		}
		return Integer.parseInt((String) props.get(key));

	}

	public String getString(String key) {
		if (props == null) {
			return null;
		}
		return (String) props.get(key);

	}

}
