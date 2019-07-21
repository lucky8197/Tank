package com.luoliang.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luoliang
 * @date 创建时间：2019年7月19日下午3:12:57
 */
@SuppressWarnings("all")
public class TankFrame extends Frame {

	Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> tanks = new ArrayList<>();
	public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
	Image offscreenImage = null;

	public TankFrame() {
		setVisible(true);
		setResizable(false);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setTitle("坦克大战");
		addKeyListener(new MyKeyListener());
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

	}

	@Override
	// 双缓冲处理闪烁。会在调用paint的时候，先调用该方法。
	public void update(Graphics g) {
		if (offscreenImage == null) {
			offscreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offscreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offscreenImage, 0, 0, null);
	}

	@Override
	// 首次出现窗口时，或者窗口重显时(被最小化)自动调用
	// 调用前，先清理窗口。
	public void paint(Graphics g) {
		if (!myTank.isLiving()) {
			System.exit(0);
		}
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量" + bullets.size(), 10, 60);
		g.drawString("敌人的数量" + tanks.size(), 10, 80);
		g.setColor(c);
		myTank.paint(g);
		// 不能用foreach（内部iterator），因为paint(g)方法需要删除。
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}

		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}

		// 碰撞检测
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
			bullets.get(i).collideWith(myTank);
		}

//		for (Bullet b : bullets) {   //不能这样删除
//			b.paint(g);
//		}
	}

	class MyKeyListener extends KeyAdapter {

		boolean bL = false;
		boolean bR = false;
		boolean bU = false;
		boolean bD = false;

		@Override
		// 按下键
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;

			default:
				break;
			}
			setMainTankDir();
		}

		@Override
		// 抬起键
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			case KeyEvent.VK_CONTROL:
				myTank.fire();
				break;
			default:
				break;
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			if (!bL && !bR && !bU && !bD) {
				myTank.setMoving(false);
			} else {
				myTank.setMoving(true);
				if (bL) {
					myTank.setDir(Dir.LEFT);
				}
				if (bR) {
					myTank.setDir(Dir.RIGHT);
				}
				if (bU) {
					myTank.setDir(Dir.UP);
				}
				if (bD) {
					myTank.setDir(Dir.DOWN);
				}
			}
		}

	}

}
