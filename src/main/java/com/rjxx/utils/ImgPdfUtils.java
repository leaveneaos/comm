package com.rjxx.utils;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;


public class ImgPdfUtils {

    public static void main(String[] args) throws Exception {
        ImgPdfUtils.changePdfToImg(new File("C:\\inetpub\\wwwroot\\e-invoices\\91310101MA1FW0008P\\20160901\\250eaf29-c951-4b37-b23e-07edd805420e.pdf"), "c:\\aaa.jpg");
    }

    /**
     * pdf转换成img
     *
     * @param pdfFile
     * @param imagePath
     */
    public static void changePdfToImg(File pdfFile, String imagePath) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(pdfFile, "r");
        FileChannel channel = raf.getChannel();
        MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdffile = new PDFFile(buf);
        java.util.List<BufferedImage> imageList = new ArrayList<>();
        for (int i = 1; i <= pdffile.getNumPages(); i++) {
            PDFPage page = pdffile.getPage(i);
            Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox().getWidth()), ((int) page.getBBox().getHeight()));
            int width = (int) (rect.width );
            int height = (int) (rect.height);
            Image img = page.getImage(width, height, rect,
                    null, // null for the ImageObserver
                    true, // fill background with white
                    true // block until drawing is done
            );
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(img, 0, 0, width, height, null);
            tag = tag.getSubimage(10, 20, width - 20, height - 100);
            imageList.add(tag);
        }
        //合并图片
        BufferedImage resultImage = null;
        if (imageList.size() > 1) {
            resultImage = ImgCombineUtils.yPic(imageList);
        }else{
            resultImage = imageList.get(0);
        }
        FileOutputStream out = new FileOutputStream(imagePath); // 输出到文件流
        ImageIO.write(resultImage, "png", out);
//                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//                JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);
//                param2.setQuality(1f, false);// 1f是提高生成的图片质量
//                encoder.setJPEGEncodeParam(param2);
//                encoder.encode(tag); // JPEG编码
        out.close();
        channel.close();
        raf.close();
        unmap(buf);//如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法
    }

    private static void unmap(final Object buffer) {
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
                    cleaner.clean();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}