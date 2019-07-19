package com.luoliang.tank;

import java.awt.Graphics;

/**
 * @author luoliang
 * @date 创建时间：2019年7月19日下午9:21:43
 */
public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 10;

	public Tank(int x, int y, Dir dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public static int getSpeed() {
		return SPEED;
	}

	public void paint(Graphics g) {
		g.fillRect(x, y, 50, 50);
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
	}
}
