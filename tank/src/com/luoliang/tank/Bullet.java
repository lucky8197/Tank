package com.luoliang.tank;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author luoliang
 * @date 创建时间：2019年7月19日下午9:56:10
 */
@SuppressWarnings("all")
public class Bullet {
	private static final int SPEED = 10;
	private static final int WIDTH = 10, HEIGHT = 10;
	private int x, y;
	private Dir dir;

	public Bullet(int x, int y, Dir dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		move();
	}

	public void move() {
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
