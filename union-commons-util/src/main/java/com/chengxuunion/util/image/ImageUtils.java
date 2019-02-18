package com.chengxuunion.util.image;

import com.chengxuunion.util.file.FilePathUtils;
import com.chengxuunion.util.id.IdGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

/**
 * 图片工具
 *
 * @author yp2
 * @date 2018年11月25日
 * @version V1.0
 */
public class ImageUtils {

	private static Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
	
	private ImageUtils() {
		
	}
	
	/**
	 * 判断指定的流是图片流
	 * 
	 * @param inputStream
	 * @return
	 */
	public static boolean isImage(InputStream inputStream) {
		try {
			ImageIO.read(inputStream);
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}

	/**
	 * 根据用户名生成图片
	 *
	 * @param baseDir	基础路径
	 * @param userName	用户名
	 * @return		生成的图片路径
	 */
	public static String generateImage(String baseDir, String userName) throws IOException {
		int width = 100;
		int height = 100;

		String fileName = FilePathUtils.buildDatePathDay()
				+ File.separator + IdGenerator.getInstance().nextIdString() + ".png";

		int length = userName.length();
		boolean isLetter = pattern.matcher(userName).matches();
		// 如果是汉字则保留2个汉字，如果是字母，则保留3个字母
		if (!isLetter && userName.length() > 2) {
			userName = userName.substring(userName.length() - 2);
			length = userName.length();
		} else {
			if (isLetter && userName.length() > 3) {
				userName = userName.substring(userName.length() - 3);
				length = userName.length();
			}
		}

		File file = new File(baseDir + File.separator + fileName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) bi.getGraphics();
		// 增加下面代码使得背景透明
		bi = g2.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
		g2.dispose();
		g2 = bi.createGraphics();

		// 消除锯齿
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// 填充圆
		g2.setColor(getRandomColor());
		g2.fillArc(0, 0, width, height, 0, 360);

		g2.setPaint(Color.white);
		if (!isLetter) {
			// 汉字，如果只有一个汉字
			if (length == 1) {
				Font font = new Font("黑体", Font.PLAIN, 40);
				g2.setFont(font);
				g2.drawString(userName, 30, 66);
			} else {
				// 两个汉字
				Font font = new Font("黑体", Font.PLAIN, 30);
				g2.setFont(font);
				g2.drawString(userName, 20, 62);
			}
		} else {
			// 字母
			Font font = new Font("黑体", Font.PLAIN, 50);
			g2.setFont(font);
			if (length < 3) {
				g2.drawString(userName, 24, 66);
			} else {
				g2.drawString(userName, 12, 66);
			}
		}

		ImageIO.write(bi,"png", file);

		return fileName;
	}

	/**
	 * 画星星
	 * @param g
	 */
	@SuppressWarnings("unused")
	private static void drawStar(Graphics2D g) {
		// 定义外切圆和内接圆的半径
		int R = 16;
		int r = (int) (R * Math.sin(Math.PI / 10) / Math.sin(3 * Math.PI / 10));

		// 定义两个数组, 分别存放10个顶点的x, y 坐标
		int[] x = new int[10];
		int[] y = new int[10];

		// 通过循环给两个数组赋值
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				x[i] = 65 + (int) (R * Math.cos(Math.PI / 10 + (i - 1) * Math.PI / 5));
				y[i] = 52 + (int) (R * Math.sin(Math.PI / 10 + (i - 1) * Math.PI / 5));
			} else {
				x[i] = 65 + (int) (r * Math.cos(Math.PI / 10 + (i - 1) * Math.PI / 5));
				y[i] = 52 + (int) (r * Math.sin(Math.PI / 10 + (i - 1) * Math.PI / 5));
			}
		}
		g.setPaint(Color.white);

		// 调用fillPolygon方法绘制
		g.fillPolygon(x, y, 10);
	}

	/**
	 * 生成随机颜色
	 *
	 * @return
	 */
	private static Color getRandomColor() {
		Random r = new Random();
		return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	public static void main(String[] args) throws Exception {
		generateImage("D:\\data\\headphoto", "li");
	}
}
