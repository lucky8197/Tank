package com.luoliang.tank;

/**
 * @author luoliang
 * @date 创建时间：2019年7月19日下午2:49:38
 */
@SuppressWarnings("all")
public class Main {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = new TankFrame();
		int initTankCount = PropertyMgr.getPropertyMgr().getInt("initTankCount");

		// 初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			tf.tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, tf));
		}
		new Thread(() -> new Audio("audio/war1.wav").loop()).start(); // 音乐
		while (true) {
			Thread.sleep(50);
			tf.repaint();
		}
	}
}
