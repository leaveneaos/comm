package com.rjxx.utils;

/**
 * 金额转中文大写
 * Created by Him on 2015-09-23.
 */
public class ChinaNumber {

    private static String[] CH = {"", "", "拾", "佰", "仟", "万", "", "", "", "亿", "", "", "", "兆"};

    private static String[] CHS_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    /**
     * 传入数字金额字符串，返回数字金额对应的中文大字与读法
     *
     * @param money 金额字符串
     * @return 金额中文大写
     */
    public static String getCHSNumber(String money) {
        //判断是否是负数
        boolean negative = false;
        if ("-".equals(money.substring(0, 1))) {
            negative = true;
            money = money.substring(1);
        }
        String chs = "";
        int pos = money.indexOf(".");
        String tmp_int = null;
        String tmp_down = null;
        if (pos != -1) {
            tmp_int = money.substring(0, money.indexOf("."));
            tmp_down = money.substring(money.indexOf(".") + 1, money.length());
        } else {
            tmp_int = money;
        }


        char[] tmp_int_char = tmp_int.toCharArray();
        String[] tmp_chs = new String[tmp_int_char.length];

        int tab = 0;
        for (int i = 0; i < tmp_int_char.length; i++) {

            tab = tmp_int_char.length - i - 1;

            if (tmp_int_char.length <= 5) {
                tmp_chs[tab] = CHS_NUMBER[(int) Float.parseFloat(tmp_int_char[i] + ".0")];

                if (!tmp_chs[tab].equals("零")) {

                    // tmp_int_char.length - i 为数字所在的位数
                    chs = chs + tmp_chs[tab] + CH[tmp_int_char.length - i];
                } else {// 当数字中有零时就在后加上零，如果超过１个以上的零也只加一个零
                    if (!chs.endsWith("零") && tab != 0) {
                        chs = chs + tmp_chs[tab];

                    } else if (chs.endsWith("零") && tab == 0) {
                        chs = chs.substring(0, chs.length() - 1);
                    }

                }
            }

            // 　如果数字的位数大于５和小于９时
            if (tmp_int_char.length > 5 && tmp_int_char.length < 9) {
                tmp_chs[tab] = CHS_NUMBER[(int) Float.parseFloat(tmp_int_char[i] + ".0")];

                // 如：123,1234分成两部分
                // 第１部分123：万以上亿以下
                if (tab >= 4) {
                    // 当前数字不是大小零时
                    if (!tmp_chs[tab].equals("零")) {
                        chs = chs + tmp_chs[tab] + CH[tab - 3];

                        // 　当第１部分算完时在加上"万"
                        if (tab == 4) {
                            chs = chs + "万";
                        }

                    } else {
                        // 当前数字为大小"零"时
                        // 判断前一次形成在字符串结尾有没有零
                        // 　如果没有零就加上零
                        if (!chs.endsWith("零")) {
                            chs = chs + tmp_chs[tab];

                        }

                        // 当第１部分算完时

                        if (tab == 4) {
                            // 　先判断字符串有没有零
                            // 　如果有零时就把零去掉再加上"万"
                            if (chs.endsWith("零")) {
                                chs = chs.substring(0, chs.length() - 1);
                                chs = chs + "万";
                            } else {
                                // 　如果没有零就直接加上"万"
                                chs = chs + "万";
                            }
                        }
                    }
                }

                // 如：123,1234分成两部分
                // 第１部分1234：万以下
                if (tab < 4) {

                    if (!tmp_chs[tab].equals("零")) {

                        // tmp_int_char.length - i 为数字所在的位数
                        chs = chs + tmp_chs[tab] + CH[tmp_int_char.length - i];
                    } else {// 当数字中有零时就在后加上零，如果超过１个以上的零也只加一个零
                        if (!chs.endsWith("零") && tab != 0) {
                            chs = chs + tmp_chs[tab];

                        }

                        if (chs.endsWith("零") && tab == 0) {
                            chs = chs.substring(0, chs.length() - 1);
                        }
                    }
                }
            }

            // 　如果数字的位数大于５和小于９时
            if (tmp_int_char.length >= 9 && tmp_int_char.length <= 12) {
                tmp_chs[tab] = CHS_NUMBER[(int) Float.parseFloat(tmp_int_char[i] + ".0")];

                if (tab >= 8 && tab < 12) {
                    // 当前数字不是大小零时
                    if (!tmp_chs[tab].equals("零")) {
                        chs = chs + tmp_chs[tab] + CH[tab - 7];

                        // 　当第１部分算完时在加上"万"
                        if (tab == 8) {
                            chs = chs + "亿";
                        }

                    } else {
                        // 当前数字为大小"零"时
                        // 判断前一次形成在字符串结尾有没有零
                        // 　如果没有零就加上零
                        if (!chs.endsWith("零")) {
                            chs = chs + tmp_chs[tab];

                        }

                        // 当第１部分算完时

                        if (tab == 8) {
                            // 　先判断字符串有没有零
                            // 　如果有零时就把零去掉再加上"万"
                            if (chs.endsWith("零")) {
                                chs = chs.substring(0, chs.length() - 1);
                                chs = chs + "亿";
                            } else {
                                // 　如果没有零就直接加上"万"
                                chs = chs + "亿";
                            }
                        }
                    }
                }
                // 如：123,1234分成两部分
                // 第１部分123：万以上亿以下
                if (tab >= 4 && tab < 8) {
                    // 当前数字不是大小零时
                    if (!tmp_chs[tab].equals("零")) {
                        chs = chs + tmp_chs[tab] + CH[tab - 3];

                        // 　当第１部分算完时在加上"万"
                        if (tab == 4) {
                            chs = chs + "万";
                        }

                    } else {
                        // 当前数字为大小"零"时
                        // 判断前一次形成在字符串结尾有没有零
                        // 　如果没有零就加上零
                        if (!chs.endsWith("零")) {
                            chs = chs + tmp_chs[tab];

                        }

                        // 当第１部分算完时

                        if (tab == 4) {
                            // 　先判断字符串有没有零
                            // 　如果有零时就把零去掉再加上"万"
                            if (chs.endsWith("零")) {
                                chs = chs.substring(0, chs.length() - 1);

                                if (!chs.endsWith("亿"))
                                    chs = chs + "万";
                            } else {
                                // 　如果没有零就直接加上"万"
                                if (!chs.endsWith("亿"))
                                    chs = chs + "万";
                            }
                        }
                    }
                }

                // 如：123,1234分成两部分
                // 第１部分1234：万以下
                if (tab < 4) {

                    if (!tmp_chs[tab].equals("零")) {

                        // tmp_int_char.length - i 为数字所在的位数
                        chs = chs + tmp_chs[tab] + CH[tmp_int_char.length - i];
                    } else {// 当数字中有零时就在后加上零，如果超过１个以上的零也只加一个零
                        if (!chs.endsWith("零") && tab != 0) {
                            chs = chs + tmp_chs[tab];

                        }

                        if (chs.endsWith("零") && tab == 0) {
                            chs = chs.substring(0, chs.length() - 1);
                        }
                    }
                }
            }

            // 　如果数字的位数大于12和小于16时
            if (tmp_int_char.length > 12 && tmp_int_char.length <= 16) {
                tmp_chs[tab] = CHS_NUMBER[(int) Float.parseFloat(tmp_int_char[i] + ".0")];

                if (tab >= 12 && tab < 16) {
                    // 当前数字不是大小零时
                    if (!tmp_chs[tab].equals("零")) {
                        chs = chs + tmp_chs[tab] + CH[tab - 11];

                        // 　当第１部分算完时在加上"万"
                        if (tab == 12) {
                            chs = chs + "兆";
                        }

                    } else {
                        // 当前数字为大小"零"时
                        // 判断前一次形成在字符串结尾有没有零
                        // 　如果没有零就加上零
                        if (!chs.endsWith("零")) {
                            chs = chs + tmp_chs[tab];

                        }

                        // 当第１部分算完时

                        if (tab == 12) {
                            // 　先判断字符串有没有零
                            // 　如果有零时就把零去掉再加上"万"
                            if (chs.endsWith("零")) {
                                chs = chs.substring(0, chs.length() - 1);
                                chs = chs + "兆";
                            } else {
                                // 　如果没有零就直接加上"万"
                                chs = chs + "兆";
                            }
                        }
                    }
                }

                if (tab >= 8 && tab < 12) {
                    // 当前数字不是大小零时
                    if (!tmp_chs[tab].equals("零")) {
                        chs = chs + tmp_chs[tab] + CH[tab - 7];

                        // 　当第１部分算完时在加上"万"
                        if (tab == 8) {
                            chs = chs + "亿";
                        }

                    } else {
                        // 当前数字为大小"零"时
                        // 判断前一次形成在字符串结尾有没有零
                        // 　如果没有零就加上零
                        if (!chs.endsWith("零")) {
                            chs = chs + tmp_chs[tab];

                        }

                        // 当第１部分算完时

                        if (tab == 8) {
                            // 　先判断字符串有没有零
                            // 　如果有零时就把零去掉再加上"万"
                            if (chs.endsWith("零")) {
                                chs = chs.substring(0, chs.length() - 1);
                                if (!chs.endsWith("兆"))
                                    chs = chs + "亿";
                            } else {
                                // 　如果没有零就直接加上"万"
                                if (!chs.endsWith("兆"))
                                    chs = chs + "亿";
                            }
                        }
                    }
                }
                // 如：123,1234分成两部分
                // 第１部分123：万以上亿以下
                if (tab >= 4 && tab < 8) {
                    // 当前数字不是大小零时
                    if (!tmp_chs[tab].equals("零")) {
                        chs = chs + tmp_chs[tab] + CH[tab - 3];

                        // 　当第１部分算完时在加上"万"
                        if (tab == 4) {
                            chs = chs + "万";
                        }

                    } else {
                        // 当前数字为大小"零"时
                        // 判断前一次形成在字符串结尾有没有零
                        // 　如果没有零就加上零
                        if (!chs.endsWith("零")) {
                            chs = chs + tmp_chs[tab];

                        }

                        // 当第１部分算完时

                        if (tab == 4) {
                            // 　先判断字符串有没有零
                            // 　如果有零时就把零去掉再加上"万"
                            if (chs.endsWith("零")) {
                                chs = chs.substring(0, chs.length() - 1);

                                if (!chs.endsWith("亿"))
                                    if (!chs.endsWith("兆"))
                                        if (!chs.endsWith("兆"))
                                            chs = chs + "万";
                            } else {
                                // 　如果没有零就直接加上"万"
                                if (!chs.endsWith("亿"))
                                    if (!chs.endsWith("兆"))
                                        chs = chs + "万";
                            }
                        }
                    }
                }

                // 如：123,1234分成两部分
                // 第１部分1234：万以下
                if (tab < 4) {

                    if (!tmp_chs[tab].equals("零")) {

                        // tmp_int_char.length - i 为数字所在的位数
                        chs = chs + tmp_chs[tab] + CH[tmp_int_char.length - i];
                    } else {// 当数字中有零时就在后加上零，如果超过１个以上的零也只加一个零
                        if (!chs.endsWith("零") && tab != 0) {
                            chs = chs + tmp_chs[tab];

                        }

                        if (chs.endsWith("零") && tab == 0) {
                            chs = chs.substring(0, chs.length() - 1);
                        }
                    }
                }
            }

            // 　如果数字的位数大于16
            if (tmp_int_char.length > 16) {
                tmp_chs[tab] = CHS_NUMBER[(int) Float.parseFloat(tmp_int_char[i] + ".0")];

                if (tab >= 12) {
                    chs = chs + tmp_chs[tab];

                    // 　当第１部分算完时在加上"万"
                    if (tab == 12) {
                        chs = chs + "兆";
                    }

                }

                if (tab >= 8 && tab < 12) {
                    // 当前数字不是大小零时
                    if (!tmp_chs[tab].equals("零")) {
                        chs = chs + tmp_chs[tab] + CH[tab - 7];

                        // 　当第１部分算完时在加上"万"
                        if (tab == 8) {
                            chs = chs + "亿";
                        }

                    } else {
                        // 当前数字为大小"零"时
                        // 判断前一次形成在字符串结尾有没有零
                        // 　如果没有零就加上零
                        if (!chs.endsWith("零")) {
                            chs = chs + tmp_chs[tab];

                        }

                        // 当第１部分算完时

                        if (tab == 8) {
                            // 　先判断字符串有没有零
                            // 　如果有零时就把零去掉再加上"万"
                            if (chs.endsWith("零")) {
                                chs = chs.substring(0, chs.length() - 1);
                                if (!chs.endsWith("兆"))
                                    chs = chs + "亿";
                            } else {
                                // 　如果没有零就直接加上"万"
                                if (!chs.endsWith("兆"))
                                    chs = chs + "亿";
                            }
                        }
                    }
                }
                // 如：123,1234分成两部分
                // 第１部分123：万以上亿以下
                if (tab >= 4 && tab < 8) {
                    // 当前数字不是大小零时
                    if (!tmp_chs[tab].equals("零")) {
                        chs = chs + tmp_chs[tab] + CH[tab - 3];

                        // 　当第１部分算完时在加上"万"
                        if (tab == 4) {
                            chs = chs + "万";
                        }

                    } else {
                        // 当前数字为大小"零"时
                        // 判断前一次形成在字符串结尾有没有零
                        // 　如果没有零就加上零
                        if (!chs.endsWith("零")) {
                            chs = chs + tmp_chs[tab];

                        }

                        // 当第１部分算完时

                        if (tab == 4) {
                            // 　先判断字符串有没有零
                            // 　如果有零时就把零去掉再加上"万"
                            if (chs.endsWith("零")) {
                                chs = chs.substring(0, chs.length() - 1);

                                if (!chs.endsWith("兆"))

                                    if (!chs.endsWith("亿"))

                                        chs = chs + "万";
                            } else {
                                // 　如果没有零就直接加上"万"
                                if (!chs.endsWith("兆"))

                                    if (!chs.endsWith("亿"))
                                        chs = chs + "万";
                            }
                        }
                    }
                }

                // 如：123,1234分成两部分
                // 第１部分1234：万以下
                if (tab < 4) {

                    if (!tmp_chs[tab].equals("零")) {

                        // tmp_int_char.length - i 为数字所在的位数
                        chs = chs + tmp_chs[tab] + CH[tmp_int_char.length - i];
                    } else {// 当数字中有零时就在后加上零，如果超过１个以上的零也只加一个零
                        if (!chs.endsWith("零") && tab != 0) {
                            chs = chs + tmp_chs[tab];

                        }

                        if (chs.endsWith("零") && tab == 0) {
                            chs = chs.substring(0, chs.length() - 1);
                        }
                    }
                }
            }
        }

        if (tmp_down != null) {
            /*char[] tmp = tmp_down.toCharArray();

            if (tmp.length == 1) {
                if (tmp[0] != '0')
                    chs = chs + "圆" + CHS_NUMBER[(int) Float.parseFloat(tmp[0] + ".0")] + "角整";
                else
                    chs = chs + "圆整";
            } else {
                if (tmp[1] != '0' && tmp[0] != '0') {
                    chs = chs + "圆" + CHS_NUMBER[(int) Float.parseFloat(tmp[0] + ".0")] + "角"
                            + CHS_NUMBER[(int) Float.parseFloat(tmp[1] + ".0")] + "分";
                } else if (tmp[1] != '0' && tmp[0] == '0') {
                    chs = chs + "圆零" + CHS_NUMBER[(int) Float.parseFloat(tmp[1] + ".0")] + "分";
                }
            }

        } else {
            chs = chs + "圆整";
        }*/
            char[] tmp = tmp_down.toCharArray();
            if (!"".equals(chs)) {
                chs = chs + "圆";
            }
            if (tmp.length == 1) {
                if (tmp[0] != '0')
                    chs = chs + CHS_NUMBER[(int) Float.parseFloat(tmp[0] + ".0")] + "角整";
                else
                    chs = chs + "整";
            } else {
                if (tmp[1] != '0' && tmp[0] != '0') {
                    chs = chs + CHS_NUMBER[(int) Float.parseFloat(tmp[0] + ".0")] + "角"
                            + CHS_NUMBER[(int) Float.parseFloat(tmp[1] + ".0")] + "分";
                } else if (tmp[1] != '0' && tmp[0] == '0') {
                    chs = chs + "零" + CHS_NUMBER[(int) Float.parseFloat(tmp[1] + ".0")] + "分";
                } else if (tmp[1] == '0' && tmp[0] == '0') {
                    chs = chs + "整";
                } else if (tmp[1] == '0' && tmp[0] != '0') {
                    chs = chs + CHS_NUMBER[(int) Float.parseFloat(tmp[0] + ".0")] + "角";
                }
            }

        } else {
            chs = chs + "圆整";
        }
        if (negative) {
            chs = "负" + chs;
        }
        return chs;
    }

    /**
     * 开票日期：传入一个String的字符串，将其表示为年月日
     *
     * @param date
     * @return
     */
    public static String getYMDNumber(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        date = year + "年" + month + "月" + day + "日";
        return date;
    }

    /**
     * 发票密文：将一串字符串112个字符换行 每行28个字符
     *
     * @param s
     * @return
     */
    public static String getAt(String s) {
        return s.substring(0, 28) + "\n" +
                s.substring(28, 56) + "\n" +
                s.substring(56, 84) + "\n" +
                s.substring(84, 112);
    }

    /**
     * 传入校验码，使每五个中间有空格
     *
     * @param s
     * @return
     */
    public static String getWs(String s) {
        return s.substring(0, 5) + " " +
                s.substring(5, 10) + " " +
                s.substring(10, 15) + " " +
                s.substring(15, 20);
    }

    public static void main(String[] args) {

    	/* System.out.println(LeviedSeparate.getQuantity(null));
        String s="03<>4<>44<-/9*0/244404283-544-038/6>2</1-00<9<4*-684>255605+/>10-9<566/5-63<0->>7303>+2/7976+3003>45191*6-7+0/00";
   System.out.println( getAt(s));
   System.out.println(s.length());*/
//    String s="20120505";
//    System.out.println(ChinaNumber.getYMDNumber(s));
//       String m = "6136347.42";
//        System.out.println(ChinaNumber.getCHSNumber(m));
//        m = "641295403.34";
//        System.out.println(ChinaNumber.getCHSNumber(m));
//
//        m = "138.06";
//        System.out.println(ChinaNumber.getCHSNumber(m));
//
//        m = "1323.00";
//        System.out.println(ChinaNumber.getCHSNumber(m));
        String m = "1000";
        System.out.println(ChinaNumber.getCHSNumber(m));
        //陆佰壹拾叁万陆仟叁佰肆拾柒元肆角贰分
        //         陆亿肆仟壹佰贰拾玖万伍仟肆佰零叁元叁角肆分
        //壹佰叁拾捌元零陆分
        //        壹仟叁佰贰拾叁元整
    }
}