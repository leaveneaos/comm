package com.rjxx.utils;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.itextpdf.text.pdf.security.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
public class PdfSignUtils {

    /**
     * 查找pdf中关键词的坐标,目前只查找第一张
     *
     * @param keyword
     * @param pdfPath
     * @return
     */
    public static List<float[]> findCoordinateByKeyword(final String keyword, String pdfPath) {
        final List<float[]> arrays = new ArrayList<float[]>();
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(pdfPath);
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(
                    pdfReader);
            for (int i = 1; i < 2; i++) {
                final int pageNum = i;
                pdfReaderContentParser.processContent(i, new RenderListener() {

                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText(); // 整页内容
                        if (null != text && text.contains(keyword)) {
                            Rectangle2D.Float boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
                            float[] resu = new float[3];
                            resu[0] = boundingRectange.x;
                            resu[1] = boundingRectange.y;
                            resu[2] = pageNum;
                            arrays.add(resu);
                        }
                    }

                    public void renderImage(ImageRenderInfo arg0) {
                        // TODO Auto-generated method stub

                    }

                    public void endTextBlock() {
                        // TODO Auto-generated method stub

                    }

                    public void beginTextBlock() {
                        // TODO Auto-generated method stub

                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pdfReader != null) {
                pdfReader.close();
            }
        }
        return arrays;
    }

    /**
     * 对电子发票pdf签名
     *
     * @param pdfPath
     * @param imagePath
     * @param outputFilePath
     */
    public static void eInvoicePdfSign(String pdfPath, String imagePath, String outputFilePath) {
        List<float[]> list = findCoordinateByKeyword("销货方：（章）", pdfPath);
        signPdf(pdfPath, list.get(0), imagePath, outputFilePath);
    }

    /**
     * 对pdf签名
     *
     * @param pdfPath
     * @param coordinate
     * @param imagePath
     */
    public static void signPdf(String pdfPath, float[] coordinate, String imagePath, String outputFilePath) {
        PdfReader reader = null;
        FileOutputStream fout = null;
        PdfStamper stp = null;
        try {
            KeyStore ks = KeyStore.getInstance("jks");
            String cert_pwd = "Rjxx1234";
            String keyStorePath = ResourceLoader.getPath("config/keys") + "/tydzfp.jks";
            ks.load(new FileInputStream(keyStorePath), cert_pwd.toCharArray());
            String alias = "1tydzfp";
            PrivateKey key = (PrivateKey) ks.getKey(alias, cert_pwd.toCharArray());
            Certificate[] chain = ks.getCertificateChain(alias);
            reader = new PdfReader(pdfPath);


            fout = new FileOutputStream(outputFilePath);
            stp = PdfStamper.createSignature(reader, fout, '\0');

            Image image = Image.getInstance(imagePath);
            float startX = coordinate[0] + 10f;
            float startY = coordinate[1] - 16f;
            float width = image.getWidth() * 0.37f;
            float height = image.getHeight() * 0.37f;
            PdfSignatureAppearance sap = stp.getSignatureAppearance();
            sap.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
            Rectangle r = new Rectangle(startX, startY, startX + width, startY + height);
            sap.setVisibleSignature(r, 1, null);
            sap.setContact("www.datarj.com");
            // sap.setAcro6Layers(true);
            sap.setSignatureGraphic(image);
            sap.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);

            ExternalDigest digest = new BouncyCastleDigest();
            BouncyCastleProvider provider = new BouncyCastleProvider();
            Security.addProvider(provider);
            ExternalSignature signature =
                    new PrivateKeySignature(key, DigestAlgorithms.SHA256, provider.getName());
            MakeSignature.signDetached(sap, digest, signature, chain, null, null, null, 0, MakeSignature.CryptoStandard.CADES);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fout != null) {
                try {
                    fout.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (stp != null) {
                try {
                    stp.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        String pdfPath = "c:\\aa.pdf";
        eInvoicePdfSign(pdfPath, "c:\\logo.png", "c:\\signed.pdf");
    }

}
