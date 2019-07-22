package com.luoliang.tank;

import java.awt.Graphics;
import java.util.Random;

/**
 * 
 * 坦克类
 * 
 * @author luoliang
 * @date 创建时间：2019年7月19日下午9:21:43
 */
public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 8;
	public static final int WIDTH = ResourceMgr.goodTankL1.getWidth(); // 坦克的大小
	public static final int HEIGHT = ResourceMgr.goodTankL1.getHeight();
	private boolean moving = true;
	private boolean living = true;
	private Random random = new Random();
	private Group group = Group.BAD; // 分组
	boolean tankSwitch = true; // 切换坦克

	private TankFrame tf;

	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean isLiving() {
		return living;
	}

	public void setLiving(boolean living) {
		this.living = living;
	}

	public void paint(Graphics g) {

		if (!living) {
			tf.tanks.remove(this);
		}
		switch (dir) {
		case LEFT:
			if (tankSwitch && group == Group.BAD) {
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL1 : ResourceMgr.badTankL1, x, y, null);
			} else {
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL2 : ResourceMgr.badTankL2, x, y, null);
			}
			break;
		case UP:
			if (tankSwitch && group == Group.BAD) {
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU1 : ResourceMgr.badTankU1, x, y, null);
			} else {
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU2 : ResourceMgr.badTankU2, x, y, null);
			}
			break;
		case RIGHT:
			if (tankSwitch && group == Group.BAD) {
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR1 : ResourceMgr.badTankR1, x, y, null);
			} else {
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR2 : ResourceMgr.badTankR2, x, y, null);
			}
			break;
		case DOWN:
			if (tankSwitch && group == Group.BAD) {
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD1 : ResourceMgr.badTankD1, x, y, null);
			} else {
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD2 : ResourceMgr.badTankD2, x, y, null);
			}
			break;
		}
		tankSwitch = !tankSwitch;
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
		// 敌军开火。
		if (this.group == Group.BAD && random.nextInt(100) > 95) {
			this.fire();
		}
		if (this.group == Group.BAD) {
			randomDir();
		}
		boundsCheck();
	}

	/**
	 * 随机转向
	 */
	private void randomDir() {
		if (random.nextInt(100) > 95) {
			this.dir = Dir.values()[random.nextInt(4)];
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
			tf.bullets.add(new Bullet(bx - 15, by + 1, this.dir, this.getGroup(), this.tf));
			break;
		case UP:
			tf.bullets.add(new Bullet(bx, by - 15, this.dir, this.getGroup(), this.tf));
			break;
		case RIGHT:
			tf.bullets.add(new Bullet(bx + 15, by - 1, this.dir, this.getGroup(), this.tf));
			break;
		case DOWN:
			tf.bullets.add(new Bullet(bx, by + 15, this.dir, this.getGroup(), this.tf));
			break;
		}

	}

	public void die() {
		this.living = false;
	}

	/**
	 * 碰撞检测
	 */
	public void boundsCheck() {
		if (this.x < 2) {
			x = 2;
		}
		if (this.y < 32) {
			y = 32;
		}
		if (this.x > TankFrame.GAME_WIDTH - WIDTH - 2) {
			x = TankFrame.GAME_WIDTH - WIDTH - 2;
		}
		if (this.y > TankFrame.GAME_HEIGHT - HEIGHT) {
			y = TankFrame.GAME_HEIGHT - HEIGHT - 2;
		}
	}

}
