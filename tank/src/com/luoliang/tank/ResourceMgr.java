package com.luoliang.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 游戏资源管理
 * 
 * @author luoliang
 * @date 创建时间：2019年7月21日下午2:47:48
 */
public class ResourceMgr {
	public static BufferedImage goodTankL1, goodTankU1, goodTankR1, goodTankD1;
	public static BufferedImage goodTankL2, goodTankU2, goodTankR2, goodTankD2;
	public static BufferedImage badTankL1, badTankU1, badTankR1, badTankD1;
	public static BufferedImage badTankL2, badTankU2, badTankR2, badTankD2;
	public static BufferedImage bulletL, bulletU, bulletR, bulletD;
	public static BufferedImage[] explodes = new BufferedImage[16];
	static {
		try {
			goodTankU1 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL1 = ImageUtil.rotateImage(goodTankU1, -90);
			goodTankR1 = ImageUtil.rotateImage(goodTankU1, 90);
			goodTankD1 = ImageUtil.rotateImage(goodTankU1, 180);

			goodTankU2 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
			goodTankL2 = ImageUtil.rotateImage(goodTankU2, -90);
			goodTankR2 = ImageUtil.rotateImage(goodTankU2, 90);
			goodTankD2 = ImageUtil.rotateImage(goodTankU2, 180);

			badTankU1 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL1 = ImageUtil.rotateImage(badTankU1, -90);
			badTankR1 = ImageUtil.rotateImage(badTankU1, 90);
			badTankD1 = ImageUtil.rotateImage(badTankU1, 180);

			badTankU2 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));
			badTankL2 = ImageUtil.rotateImage(badTankU2, -90);
			badTankR2 = ImageUtil.rotateImage(badTankU2, 90);
			badTankD2 = ImageUtil.rotateImage(badTankU2, 180);

			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);

			for (int i = 0; i < 16; i++) {
				explodes[i] = ImageIO
						.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
