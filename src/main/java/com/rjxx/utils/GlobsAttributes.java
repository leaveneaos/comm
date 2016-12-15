package com.rjxx.utils;

/**
 * 公共信息
 */
public class GlobsAttributes {
	/*
	 * ================各种格式样式=========================================================================
	 */
	/**
	 * 英文编码 ISO-8859-1
	 */
	public final static String ENG_CODE_PAGE = "ISO-8859-1";

	/**
	 * 中文编码 GBK
	 */
	public final static String CN_CODE_PAGE = "GBK";

	/**
	 * UTF编码 UTF-8
	 */
	public final static String UTF_CODE_PAGE = "UTF-8";

	/**
	 * 时间格式 yyyy-MM-dd
	 */
	public final static String TIME_FORMAT_TEN = "yyyy-MM-dd";

	/**
	 * 时间格式 yyyy-MM
	 */
	public final static String TIME_FORMAT_SEVEN = "yyyy-MM";
	/**
	 * 时间格式 yyyyMM
	 */
	public final static String TIME_FORMAT_FIVE = "yyyyMM";
	/**
	 * 时间格式 yyyyMMdd
	 */
	public final static String TIME_FORMAT_EIGHT = "yyyyMMdd";

	/**
	 * 时间格式 HH:mm:ss
	 */
	public final static String TIME_FORMAT_SIX = "HH:mm:ss";

	/**
	 * 时间格式 yyyy-MM-dd HH:mm:ss
	 */
	public final static String TIME_FORMAT_THIRTEEN = "yyyy-MM-dd HH:mm:ss";

	/*
	 * ================各种参数常量=========================================================================
	 */
	/**
	 * 表格内容字段显示的最大长度：20
	 */
	public final static int TD_MAX_LENGTH = 50;

	/**
	 * 所在国家的ID：1
	 */
	public final static String DEFAULT_COUNTRY_ID = "1";

	/**
	 * Struts2文件上传相关：高级缓冲区大小：20 * 1024(Bit)
	 */
	public final static int FILEUPLOAD_SIZETHRESHOLD = 100 * 1024;

	/**
	 * 整个系统查询条件区时间条件是否默认显示：true
	 */
	public final static boolean IS_DEFAULT_DISPLAY_DATE = Boolean.TRUE;

	/*
	 * ================几种符号标点=========================================================================
	 */
	/**
	 * 空字符串 ""
	 */
	public final static String STR_EMPTY = "";

	/**
	 * 字符串 "null"
	 */
	public final static String STR_NULL = "null";

	/**
	 * 字符串 "..."
	 */
	public final static String SUSPENSION_POINTS = "...";

	/**
	 * 英文标点符号 ","
	 */
	public final static String COMMA = ",";

	/**
	 * 英文标点符号 ":"
	 */
	public final static String COLON = ":";

	/**
	 * 英文标点符号 "%"
	 */
	public final static String PERCENTSIGN = "%";

	/**
	 * 英文标点符号 "-"
	 */
	public final static String RAIL = "-";

	/**
	 * 英文标点符号 "?"
	 */
	public final static String INTERROGATION = "?";

	/**
	 * 英文符号 " "
	 */
	public final static String SPACE = " ";

	/**
	 * 英文符号 "#"
	 */
	public final static String WELL = "#";

	/**
	 * 英文符号 "="
	 */
	public final static String EQUAL = "=";

	/**
	 * 英文符号 "&"
	 */
	public final static String AND = "&";

	/**
	 * 英文符号 "$"
	 */
	public final static String DOLLAR = "$";

	/**
	 * 英文符号 "*"
	 */
	public final static String CLUB = "*";

	/**
	 * 英文符号 "_"
	 */
	public final static String UNDERLINE = "_";

	/**
	 * 英文符号 "."
	 */
	public final static String SPOT = ".";

	/**
	 * 英文符号 "/"
	 */
	public final static String SLASH = "/";

	/**
	 * HTML空格符 "&nbsp;"
	 */
	public final static String BLANK = "&nbsp;";

    /**
     * 排序desc
     */
    public final static String SORT_DESC = "desc";

    /**
     * 排序asc
     */
    public final static String SORT_ASC = "asc";
/**
 * 返回结果报文
 */
    public final static String ERROR_9032 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Responese>\n  <ReturnCode>9999</ReturnCode>\n"+
            "  <Djh></Djh>\n  <ReturnMessage>9032:电子发票平台解析请求报文失败或报文有误.</ReturnMessage>\n</Responese>";
    public final static String ERROR_9030 ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Responese>\n  <ReturnCode>9999</ReturnCode>\n"+
            "  <Djh></Djh>\n  <ReturnMessage>9030:电子发票平台保存请求报文失败.</ReturnMessage>\n</Responese>";
    public final static String ERROR_9031 ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Responese>\n  <ReturnCode>9999</ReturnCode>\n" +
            "  <Djh></Djh>\n  <ReturnMessage>9031:电子发票平台数据库异常或电商平台请求参数SerialNumber重复.</ReturnMessage>\n</Responese>";
    public final static String ERROR_9036 ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Responese>\n  <ReturnCode>9999</ReturnCode>\n"+
            "  <Djh></Djh>\n  <ReturnMessage>9036:电商平台请求Details有误.</ReturnMessage>\n</Responese>";
    public final static String SUCCESS_0000 ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Responese>\n  <ReturnCode>0000</ReturnCode>\n" +
            "  <ReturnMessage>待开票数据保存成功</ReturnMessage>\n</Responese>";
}