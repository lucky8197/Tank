package com.luoliang.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 子弹类
 * 
 * @author luoliang
 * @date 创建时间：2019年7月19日下午9:56:10
 */
@SuppressWarnings("all")
public class Bullet {
	private static final int SPEED = 10;
	public static final int WIDTH = ResourceMgr.bulletD.getWidth(); // 子弹的大小
	public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
	private int x, y;
	private Dir dir;
	private boolean living = true; // 存活
	private TankFrame tf = null;
	private Group group = Group.BAD;
	Rectangle rect = new Rectangle();

	public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
		rect.x = this.x;
		rect.y = this.y;
		rect.width = this.WIDTH;
		rect.height = this.HEIGHT;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g) {
		if (!living) {
			tf.bullets.remove(this);
		}
		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		}
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
		rect.x = this.x;
		rect.y = this.y;
		if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			living = false;
		}

	}

	public void die() {
		this.living = false;
	}

	public void collideWith(Tank tank) {
		if (this.group == tank.getGroup()) {
			return;
		}
		// 重叠
		if (rect.intersects(tank.rect)) {
			tank.die();
			this.die();
			int ex = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
			int ey = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
			tf.explodes.add(new Explode(ex, ey, tf)); // 爆炸效果
		}
	}
}
