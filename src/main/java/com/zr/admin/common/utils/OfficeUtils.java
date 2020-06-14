package com.zr.admin.common.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfficeUtils {

    protected static final Logger logger = LoggerFactory.getLogger(OfficeUtils.class);

    public static Map<Integer, Map<Integer, Object>> readExcelContentz(MultipartFile file) throws Exception {
        Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();
        // 上传文件名
        Workbook wb = getWb(file);
        if (wb == null) {
            throw new IOException();
        }
        Sheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        Row row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 0; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                cellValue.put(j, obj);
                j++;
            }
            content.put(i, cellValue);

        }
        return content;
    }

    //根据Cell类型设置数据
    private static Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                    break;
                case FORMULA: {
                    cellvalue = cell.getDateCellValue();
                    break;
                }
                case STRING:
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    private static Workbook getWb(MultipartFile mf) {
        String filepath = mf.getOriginalFilename();
        String ext = filepath.substring(filepath.lastIndexOf("."));
        Workbook wb = null;
        try {
            InputStream is = mf.getInputStream();
            if (".xls".equals(ext)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(ext)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
        return wb;
    }

    public static JSONObject importMain(String fileName, MultipartFile file, int sheetNum) throws Exception {

        JSONObject ret = new JSONObject();
        boolean isSave = true;
        String msg = "导入失败！";

        List<Map<String, String>> movieList = new ArrayList<Map<String, String>>();

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            msg += "\n"+"上传文件格式不正确";
        }else {
            InputStream is = file.getInputStream();
            Workbook wb = null;//声明一个工作簿
            if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
                wb = new XSSFWorkbook(is);
            }else {
                wb = new HSSFWorkbook(is);
            }

            Sheet sheet = wb.getSheetAt(sheetNum);
            if(sheet != null){
                int columnNum ;
                for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                    Map<String, String> map = new HashMap<String, String>();
                    Row row = sheet.getRow(r);
                    if (row == null){
                        continue;
                    }else {
                        // 获取总列数
                        columnNum = row.getLastCellNum();

                        for(int i=0; i<columnNum; i++) {
                            String str = null;
                            if(row.getCell(i) == null) {
                                str = row.createCell(i).getStringCellValue();
                            }else {
                                row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                                str = row.getCell(i).getStringCellValue();
                            }
                            if(str == null || str.isEmpty()){
                                isSave = false;
                                msg += "\n"+"第"+(r+1)+"行,"+sheet.getRow(0).getCell(i).getStringCellValue()+"为空！";
                            }else {
                                map.put(sheet.getRow(0).getCell(i).getStringCellValue(), str);
                            }
                        }
                    }
                    movieList.add(map);
                }
            }
        }
        if(isSave) {
            ret.put("data", movieList);
            ret.put("success", true);
        }else {
            ret.put("data", msg);
            ret.put("success", false);
        }
        return ret;
    }
}