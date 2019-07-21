package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

/**
 * @author luoliang
 * @date 创建时间：2019年7月21日上午11:49:37
 */
class ImageTest {

	@Test
	void test() {

		try {
			BufferedImage image = ImageIO.read(new File("C:/Users/lenovo/Desktop/pictures/123.png"));
			assertNotNull(image);// 断言，判断条件通不通过。

			BufferedImage image2 = ImageIO
					.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			// this.getClass()
			assertNotNull(image2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
