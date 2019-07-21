package com.luoliang.tank;

import java.awt.Graphics;

/**
 * @author luoliang
 * @date 创建时间：2019年7月21日下午5:03:14
 */
public class Explode {
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getWidth();
	private int step = 0;
	private int x, y;
	private TankFrame tf = null;

	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if (step >= ResourceMgr.explodes.length) {
			step = 0;
		}
	}

	public Explode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
}
