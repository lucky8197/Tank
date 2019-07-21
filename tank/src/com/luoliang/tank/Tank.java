package com.luoliang.tank;

import java.awt.Graphics;

/**
 * @author luoliang
 * @date 创建时间：2019年7月19日下午9:21:43
 */
public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 5;
	private boolean moving;

	private TankFrame tf;

	public Tank(int x, int y, Dir dir, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void paint(Graphics g) {
		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		}
		move();
	}

	/**
	 * 移动
	 */
	public void move() {
		if (!moving) {
			return;
		}
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

	/**
	 * 开火
	 */
	public void fire() {
		tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.tf));
	}

}
