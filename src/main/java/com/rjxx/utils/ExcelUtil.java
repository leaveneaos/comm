package com.rjxx.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel组件
 *
 * @author Snowolf
 * @version 1.0
 * @since 1.0
 */
public class ExcelUtil {

    /**
     * Excel 2003
     */
    private final static String XLS = "xls";
    /**
     * Excel 2007
     */
    private final static String XLSX = "xlsx";

    /**
     * 由Excel文件的Sheet导出至List
     *
     * @param file
     * @param sheetNum
     * @return
     */
    public static List<List> exportListFromExcel(File file, int sheetNum)
            throws IOException {
        return exportListFromExcel(new FileInputStream(file),
                FilenameUtils.getExtension(file.getName()), sheetNum);
    }

    /**
     * 由Excel流的Sheet导出至List
     *
     * @param is
     * @param extensionName
     * @param sheetNum
     * @return
     * @throws IOException
     */
    public static List<List> exportListFromExcel(InputStream is,
                                                 String extensionName, int sheetNum) throws IOException {

        Workbook workbook = null;

        if (extensionName.toLowerCase().equals(XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals(XLSX)) {
            workbook = new XSSFWorkbook(is);
        }

        return exportListFromExcel(workbook, sheetNum);
    }

    /**
     * 由指定的Sheet导出至List
     *
     * @param workbook
     * @param sheetNum
     * @return
     * @throws IOException
     */
    private static List<List> exportListFromExcel(Workbook workbook,
                                                  int sheetNum) {

        Sheet sheet = workbook.getSheetAt(sheetNum);

        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper()
                .createFormulaEvaluator();

        List<List> finalList = new ArrayList<>();

        int minRowIx = sheet.getFirstRowNum();
        int maxRowIx = sheet.getLastRowNum();
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            if(row == null){
                break;
            }
            List list = new ArrayList();

            short minColIx = 0;
            short maxColIx = row.getLastCellNum();
            boolean flag = false;
            for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
                Cell cell = row.getCell(new Integer(colIx));
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue == null) {
                    list.add(null);
                    continue;
                }
                flag = true;
                // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
                // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
                switch (cellValue.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        list.add(cellValue.getBooleanValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        // 这里的日期类型会被转换为数字类型，需要判别后区分处理
                        if (DateUtil.isCellDateFormatted(cell)) {
                            list.add(cell.getDateCellValue());
                        } else {
                            DecimalFormat df = new DecimalFormat("0.###############");
                            String  r = df.format(cellValue.getNumberValue());
                            list.add(r);
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        list.add(cellValue.getStringValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    default:
                        break;
                }
            }
            if (flag) {
            	finalList.add(list);
			}
        }
        return finalList;
    }
}
