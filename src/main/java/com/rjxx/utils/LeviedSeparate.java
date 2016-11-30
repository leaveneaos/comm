package com.rjxx.utils;



import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 价税分离 输出数据为两位小数
 *
 * @author k
 */
public class LeviedSeparate {

    /**
     * 获得单价 不含税unitPrice1
     * 使用BigDecimal是为保证0.585保留成0.59，使用getDecimalFormat则使0.5保留为0.50
     *
     * @param unitPrice
     * @param taxRate
     * @return
     */
    public String getUnitPrice(Double unitPrice, Double taxRate) {

        if ("".equals(unitPrice) || unitPrice == null)
            return "";
        BigDecimal b1 = new BigDecimal(Double.toString(unitPrice));
        BigDecimal b2 = new BigDecimal(Double.toString(taxRate + 1.0));
        return b1.divide(b2, 6, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 获得数量
     *
     * @param quantity
     * @return 未保留小数，直接就是数据库中的值
     */
    public String getQuantity(Double quantity) {
        if ("".equals(quantity) || quantity == null) {
            return "";
        } else {
            double q = Double.valueOf(quantity);
            int a = (int) q;
            if (q == a)
                return String.valueOf(a);
            return String.valueOf(q);
        }
    }

    /**
     * 获得金额 不含税amount0 BigDecimal可以保留正常四舍五入并返回double
     */

    public BigDecimal getAmount(Double amount, Double taxRate) {
        BigDecimal b1 = new BigDecimal(Double.toString(amount));
        BigDecimal b2 = new BigDecimal(Double.toString(taxRate + 1.0));
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 获得税额taxAmount getBigDecimal1可以正常四舍五入并返回Double
     */
    public BigDecimal getTaxAmount(Double amount0, BigDecimal amount1) {
        BigDecimal amount = new BigDecimal(Double.toString(amount0));
        return getBigDecimal(amount.subtract(amount1).toString());
    }



    /**
     * 获得合计税额totaltaxAmount
     */
    public String getTotalTaxAmount(BigDecimal total, BigDecimal totalAmount) {
        // 因total与totalAmount都已正常四舍五入保留为两位小数，则返回值亦可以

        return getBigDecimal(total.subtract(totalAmount).toString()).toString();
    }

    /**
     * 将double类型的税率表示为%
     *
     * @param taxRate
     * @return
     */
    public static String getTaxRate(Double taxRate) {
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumIntegerDigits(3);
        num.setMaximumFractionDigits(2);
        return num.format(taxRate);
    }

    /**
     * 将String类型的数据保留两位小数 若参数为Double类型，则会出现0.585变成0.58，String就可以0.585=0.59
     */
    public static BigDecimal getBigDecimal(String data) {
        BigDecimal bd = new BigDecimal(data);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    /**
     * 将String类型的数据保留6位小数，针对税率，数量等数据
     *
     * @param data
     * @return
     */
    public String getBigDecimal1(String data) {
        BigDecimal bd = new BigDecimal(data);
        bd = bd.setScale(6, BigDecimal.ROUND_HALF_UP);
        return bd.toString();
    }

    public static void main(String[] args) {

        //System.out.println(Double.parseDouble(getNumberFormat(5000)));
    }


    /**
     * 提供精确的加法运算。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */

    public static Double add(Number value1, Number value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(Number value1, Number value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static Double mul(Number value1, Number value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后指定位，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 两个参数的商
     */
    public static Double div(Double dividend, Double divisor) {
        return div(dividend, divisor, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static Double div(Double dividend, Double divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    // 默认除法运算精度
    private static final Integer DEF_DIV_SCALE = 2;
    private static final Integer detailsNumber = 8;
}
