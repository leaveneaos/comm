package com.rjxx.utils;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ImgCombineUtils {

    /**
     * 横向合并图片
     *
     * @param imageFirst
     * @param imageSecond
     * @return
     */
    public static BufferedImage xPic(BufferedImage imageFirst, BufferedImage imageSecond) {
        try {
            int width = imageFirst.getWidth();// 图片宽度
            int height = imageFirst.getHeight();// 图片高度
            int[] imageArrayFirst = new int[width * height];// 从图片中读取RGB
            imageArrayFirst = imageFirst.getRGB(0, 0, width, height, imageArrayFirst, 0, width);

			/* 1 对第二张图片做相同的处理 */
            int[] imageArraySecond = new int[width * height];
            imageArraySecond = imageSecond.getRGB(0, 0, width, height, imageArraySecond, 0, width);

            // 生成新图片
            BufferedImage imageResult = new BufferedImage(width * 2, height, BufferedImage.TYPE_INT_RGB);
            imageResult.setRGB(0, 0, width, height, imageArrayFirst, 0, width);// 设置左半部分的RGB
            imageResult.setRGB(width, 0, width, height, imageArraySecond, 0, width);// 设置右半部分的RGB
            return imageResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 纵向合并图片
     *
     * @param imageList
     * @return
     */
    public static BufferedImage yPic(List<BufferedImage> imageList) {
        try {
            int maxWidth = 0;
            int totalHeight = 0;
            for (BufferedImage bufferedImage : imageList) {
                if (bufferedImage.getWidth() > maxWidth) {
                    maxWidth = bufferedImage.getWidth();
                }
                totalHeight += bufferedImage.getHeight();
            }
            // 生成新图片
            BufferedImage imageResult = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
            int startHeight = 0;
            for (BufferedImage bufferedImage : imageList) {
                int width = bufferedImage.getWidth();// 图片宽度
                int height = bufferedImage.getHeight();// 图片高度
                int[] imageArray = new int[width * height];// 从图片中读取RGB
                imageArray = bufferedImage.getRGB(0, 0, width, height, imageArray, 0, width);
                imageResult.setRGB(0, startHeight, width, height, imageArray, 0, width);
                startHeight += height;
            }
            return imageResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
