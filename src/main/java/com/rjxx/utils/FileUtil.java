package com.rjxx.utils;
import java.io.*;
public class FileUtil {
    /**
     * 以制定编码格式读取文本内容，filePath为文件绝对路径地址(用于测试)
     * @param filePath
     * @param encoding
     * @return
     * @throws IOException
     */

    public static String readFileContent(String filePath, String encoding) throws IOException {

        BufferedReader reader = null;
        StringBuffer str = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath), encoding));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            str = new StringBuffer();
            while ((tempString = reader.readLine()) != null) {
                str.append(tempString).append("\n");
                line++;
            }
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return str.toString();

    }

    /**
     * 以流的形式读取指定编码的文件内容
     * @param in
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String readFileContentByIn(InputStream in, String encoding) throws IOException {

        BufferedReader reader = null;
        StringBuffer str = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(in, encoding));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            str = new StringBuffer();
            while ((tempString = reader.readLine()) != null) {
                str.append(tempString).append("\n");
                line++;
            }
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return str.toString();

    }
/*

    public static Map processTxtFile(String fileContent){

        String[] dataArry = fileContent.split("\n");

        //SJJK0101~~販売单据传入~~20180725 155719 713001 姚雪燕
        String[] ddArr = fileContent.split(dataArry[0]);


        String[] text = fileContent.split("SJJK0101");

//        String[] ddArr1 = fileContent.split(dataArry[2]);
        System.out.println("订单条数："+(ddArr.length-1));
        for (int i = 1,length = text.length; i < length;i++){
//            String arr = ddArr[i];
            String arr = text[i];
            //以换行分割  数组包括“”;
            String[] ddsj = arr.split("\n");
            //下标3
            String zsjAndxf = ddsj[3];
//            System.out.println("主数据+销方信息：---------"+zsjAndxf);

            //数据规范2-----begin
            String[] all = zsjAndxf.split("\\\\n");
            String zsj = all[0];
            String xfxx = all[1];
            String[] ddzsj = zsj.split("~~");
            System.out.println("主数据：---------"+zsj);

            //交易流水号
            String lsh = ddzsj[0];
            //商品数量
            String num = ddzsj[1];
            //购方名称
            String gfmc = ddzsj[2];
            //购方税号
            String gfsh = ddzsj[3];
            //购方地址电话
            String gfdzdh = ddzsj[4];
            //购方银行and账号
            String gfyhzh = ddzsj[5];
            //备注
            String bz = ddzsj[6];
            System.out.println("流水号：---------"+ddzsj[0]);
            System.out.println("销方信息：---------"+xfxx);

            String[] xfxxArr = xfxx.split("~~");
//            System.out.println(xfxxArr.length);

          if (xfxxArr.length != 0) {

           for (int j = 0,size = xfxxArr.length; j < size; j++) {
               //复核人
               if (j == 0) {
                   String fhr = xfxxArr[0];
                   System.out.println(fhr);
               }
               //收款人
               if (j == 1) {
                   String skr = xfxxArr[1];
                   System.out.println(skr);
               }

               //清单行商品名称
               if (j == 2) {
                   String spm = xfxxArr[2];
                   System.out.println(spm);
               }

               //单据日期
               if (j == 3) {
                   String djrq = xfxxArr[3];
                   System.out.println(djrq);
               }
               //销方银行账号
               if (j == 4) {
                   String xfyhzh = xfxxArr[4];
                   System.out.println(xfyhzh);
               }
               //销方地址电话

               if (j == 5) {
                   String xfdzdh = xfxxArr[5];
                   System.out.println(xfdzdh);
               }
               //购方邮箱地址

               if (j == 6) {
                   String gfyxdz = xfxxArr[6];
                   System.out.println(gfyxdz);
               }
               //购方手机号
               if (j == 7) {
                   String gfsjh = xfxxArr[7];
                   System.out.println(gfsjh);
               }

           }

          }
            //数据规范2-----end
            for (int j =4,len = ddsj.length;j < len;j++){
                System.out.println("明细信息：---------"+ddsj[j]);
                String jymx = ddsj[j];
                String[] jymxArr = jymx.split("~~");
                //货物名称
                String hwmc = jymxArr[0];
                //计量单位
                String unit = jymxArr[1];
                //规格
                String gk = jymxArr[2];
                //数量(数值)
                String sl = jymxArr[3];
                //不含税金额
                String bhsje = jymxArr[4];
                //税率
                String rat = jymxArr[5];
                //商品税目
                String spsm = jymxArr[6];
                //折扣金额
                String zkje = jymxArr[7];
                //税额
                String se = jymxArr[8];
                //折扣税额
                String zkse = jymxArr[9];
                //折扣率
                String zkl = jymxArr[10];
                //单价
                String dj = jymxArr[11];
                //价格方式
                String jgfs = jymxArr[12];

            }
        }

        return  null;
    }
*/

    public static void main(String args[]) throws Exception {
        //data.TXT
        /*String filePath = "E:/lhp/test.TXT";
        String encoding = "gbk";
        String data = FileUtil.readFileContent(filePath, encoding);
        FileUtil.processTxtFile(data);*/
    }
}

