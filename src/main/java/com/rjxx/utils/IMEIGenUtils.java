package com.rjxx.utils;

/**
 * Created by Administrator on 2017-06-14.
 */
public class IMEIGenUtils {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String code = "10132561200250702000";
        String newCode = genCode(code);
        System.out.println("======" + newCode);
        System.out.println(code + newCode);
    }

    public static String genCode(String code) {
        int total = 0, sum1 = 0, sum2 = 0;
        int temp = 0;
        char[] chs = code.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int num = chs[i] - '0';     // ascii to num
            //System.out.println(num);
            /*(1)将奇数位数字相加(从1开始计数)*/
            if (i % 2 == 0) {
                sum1 = sum1 + num;
            } else {
                /*(2)将偶数位数字分别乘以2,分别计算个位数和十位数之和(从1开始计数)*/
                temp = num * 2;
                if (temp < 10) {
                    sum2 = sum2 + temp;
                } else {
                    sum2 = sum2 + temp + 1 - 10;
                }
            }
        }
        total = sum1 + sum2;
        /*如果得出的数个位是0则校验位为0,否则为10减去个位数 */
        if (total % 10 == 0) {
            return "0";
        } else {
            return (10 - (total % 10)) + "";
        }

    }


}
