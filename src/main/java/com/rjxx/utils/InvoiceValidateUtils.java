package com.rjxx.utils;

import java.math.BigDecimal;

/**
 * 发票校验工具类，返回空字符串为正常，否则返回异常信息
 * Created by ZhangBing on 2017-06-06.
 */
public class InvoiceValidateUtils extends ValidateUtils {

    /**
     * 校验购方名称
     *
     * @param buyerName
     * @return
     */
    public static String checkBuyerName(String buyerName) {
        int length = getByteLength(buyerName);
        if (length > 60) {
            return "购方名称不合法";
        }
        return "";
    }

    /**
     * 校验销方名称
     *
     * @param sellerName
     * @return
     */
    public static String checkSellerName(String sellerName) {
        int length = getByteLength(sellerName);
        if (length > 60) {
            return "销方名称不合法";
        }
        return "";
    }

    /***
     * 校验购方税号
     *
     * @param buyerIdentifier
     * @return
     */
    public static String checkBuyerIdentifier(String buyerIdentifier) {
        if (!checkIdentifier(buyerIdentifier)) {
            return "购方税号不合法";
        }
        return "";
    }

    /***
     * 校验销方税号
     *
     * @param sellerIdentifier
     * @return
     */
    public static String checkSellerIdentifier(String sellerIdentifier) {
        if (!checkIdentifier(sellerIdentifier)) {
            return "销方税号不合法";
        }
        return "";
    }

    /**
     * 校验税号
     *
     * @param identifier
     * @return
     */
    public static boolean checkIdentifier(String identifier) {
        char[] chArr = identifier.toCharArray();
        if (chArr.length < 15 || chArr.length > 20) {
            return false;
        }
        boolean success = true;
        for (char ch : chArr) {
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
                continue;
            } else {
                success = false;
            }
        }
        return success;
    }

    /**
     * 校验购方地址电话
     *
     * @param buyerAddress
     * @param buyerTelephoneNo
     * @return
     */
    public static String checkBuyerAddressTelephoneNo(String buyerAddress, String buyerTelephoneNo) {
        int length = getByteLength(buyerAddress) + getByteLength(buyerTelephoneNo);
        if (length > 60) {
            return "购方地址、电话不合法";
        }
        return "";
    }

    /**
     * 校验销方地址电话
     *
     * @param sellerAddress
     * @param sellerTelephoneNo
     * @return
     */
    public static String checkSellerAddressTelephoneNo(String sellerAddress, String sellerTelephoneNo) {
        int length = getByteLength(sellerAddress) + getByteLength(sellerTelephoneNo);
        if (length > 60) {
            return "销方地址、电话不合法";
        }
        return "";
    }

    /**
     * 校验购方银行及银行账号
     *
     * @param buyerBank
     * @param buyerBankAccount
     * @return
     */
    public static String checkBuyerBankAndAccount(String buyerBank, String buyerBankAccount) {
        int length = getByteLength(buyerBank) + getByteLength(buyerBankAccount);
        if (length > 60) {
            return "购方开户行及账号不合法";
        }
        return "";
    }

    /**
     * 检查销方银行及银行账号
     *
     * @param sellerBank
     * @param sellerBankAccount
     * @return
     */
    public static String checkSellerBankAndAccount(String sellerBank, String sellerBankAccount) {
        int length = getByteLength(sellerBank) + getByteLength(sellerBankAccount);
        if (length > 60) {
            return "销方开户行及账号不合法";
        }
        return "";
    }

    /**
     * 校验商品名称
     *
     * @param productName
     * @return
     */
    public static String checkProductName(String productName) {
        int length = getByteLength(productName);
        if (length > 100) {
            return "商品名称不合法";
        }
        return "";
    }

    /**
     * 校验规格型号
     *
     * @param spec
     * @return
     */
    public static String checkSpec(String spec) {
        int length = getByteLength(spec);
        if (length > 36) {
            return "规格型号不合法";
        }
        return "";
    }

    /**
     * 校验单位
     *
     * @param unit
     * @return
     */
    public static String checkUnit(String unit) {
        int length = getByteLength(unit);
        if (length > 32) {
            return "单位不合法";
        }
        return "";
    }

    /**
     * 校验备注，税控盘
     *
     * @param remark
     * @return
     */
    public static String checkRemark(String remark) {
        int length = getByteLength(remark);
        if (length > 140) {
            return "备注不合法";
        }
        return "";
    }

    /**
     * 校验备注，航信金税盘开票软件
     *
     * @param remark
     * @param isHx
     * @return
     */
    public static String checkRemark(String remark, boolean isHx) {
        int length = getByteLength(remark);
        if (length > 230) {
            return "备注不合法";
        }
        return "";
    }

    /**
     * 校验商品的运算关系（含税）
     *
     * @param productQuantity 商品数量
     * @param unitPrice       单价（含税）
     * @param amount          金额（含税）
     * @param taxRate         税率
     * @return
     */
    public static String checkProductOperationalRelationWithTax(BigDecimal productQuantity, BigDecimal unitPrice, BigDecimal amount, BigDecimal taxRate) {
        BigDecimal multiplyTaxRate = new BigDecimal(1).add(taxRate);
        BigDecimal taxAmount = amount.divide(multiplyTaxRate, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal amountWithoutTax = amount.subtract(taxAmount);
        if (unitPrice != null) {
            unitPrice = unitPrice.divide(multiplyTaxRate, 15, BigDecimal.ROUND_HALF_UP);
        }
        return checkProductOperationalRelationWithoutTax(productQuantity, unitPrice, amountWithoutTax, taxRate, taxAmount);
    }

    /**
     * 校验商品的运算关系（不含税）
     *
     * @param productQuantity 商品数量
     * @param unitPrice       单价（不含税）
     * @param amount          金额（不含税）
     * @param taxRate         税率
     * @param taxAmount       税额
     * @return
     */
    public static String checkProductOperationalRelationWithoutTax(BigDecimal productQuantity, BigDecimal unitPrice, BigDecimal amount, BigDecimal taxRate, BigDecimal taxAmount) {
        if (productQuantity == null && unitPrice != null) {
            return "商品数量不能为空";
        }
        if (productQuantity != null && unitPrice == null) {
            return "商品单价不能为空";
        }
        if (productQuantity != null && unitPrice != null) {
            int result = productQuantity.multiply(unitPrice).setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(amount);
            if (result != 0) {
                return "商品单价、数量或金额有误";
            }
        }
        BigDecimal taxAmountMargin = amount.multiply(taxRate).setScale(2, BigDecimal.ROUND_HALF_UP).subtract(taxAmount).abs();
        if (taxAmountMargin.doubleValue() > 0.01) {
            return "商品金额、税率或税额有误";
        }
        return "";
    }

}
