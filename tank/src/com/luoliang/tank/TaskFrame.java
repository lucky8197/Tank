package com.luoliang.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author luoliang
 * @date 创建时间：2019年7月19日下午3:12:57
 */
@SuppressWarnings("all")
public class TaskFrame extends Frame {
	Tank myTank = new Tank(200, 200, Dir.DOWN);

	public TaskFrame() {
		setVisible(true);
		setResizable(false);
		setSize(800, 600);
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
	// 首次出现窗口时，或者窗口重显时(被最小化)自动调用
	// 调用前，先清理窗口。
	public void paint(Graphics g) {
		myTank.paint(g);

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
