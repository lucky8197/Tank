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
	private boolean moving = false;
	public static final int WIDTH = ResourceMgr.tankL.getWidth(); // 坦克的大小
	public static final int HEIGHT = ResourceMgr.tankL.getHeight();
	private boolean living = true;

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

	public void paint(Graphics g) {
		if (!living) {
			tf.tanks.remove(this);
		}
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
		int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
		int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
		switch (dir) {
		case LEFT:
			tf.bullets.add(new Bullet(bx - 15, by + 3, this.dir, this.tf));
			break;
		case UP:
			tf.bullets.add(new Bullet(bx + 1, by - 15, this.dir, this.tf));
			break;
		case RIGHT:
			tf.bullets.add(new Bullet(bx + 15, by + 4, this.dir, this.tf));
			break;
		case DOWN:
			tf.bullets.add(new Bullet(bx - 1, by + 15, this.dir, this.tf));
			break;
		}
	}

	public void die() {
		this.living = false;
	}

}
