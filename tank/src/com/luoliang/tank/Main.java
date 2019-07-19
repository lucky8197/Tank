package com.luoliang.tank;

/**
 * @author luoliang
 * @date 创建时间：2019年7月19日下午2:49:38
 */
@SuppressWarnings("all")
public class Main {

	public static void main(String[] args) throws InterruptedException {
		TaskFrame tf = new TaskFrame();
		while (true) {
			Thread.sleep(50);
			tf.repaint();
		}
	}
}
