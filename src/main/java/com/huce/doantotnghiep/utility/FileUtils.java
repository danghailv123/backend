package com.huce.doantotnghiep.utility;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;


@Slf4j
public class FileUtils {


    public static boolean isValidHeader(String[] header) {
        boolean length = header.length > 1 && countNonEmpty(header) > 1;
        boolean valueType = false;
        for (String h : header) {
            try {
                Double.parseDouble(h);
            } catch (Exception e) {
                valueType = true;
                break;
            }
        }
        return length && valueType;
    }

    public static int countNonEmpty(String[] arr) {
        int count = 0;
        for (String r : arr) {
            if (!Strings.isNullOrEmpty(r)) count++;
        }
        return count;
    }

    public static boolean isValidFileSize(long size, String nameFile, Float MAX_FILE_MB) {
        float fileInMb = ((float) size / 1024) / 1024;
        log.info("Upload file [{}] with size: " + String.format("%.3f", fileInMb) + " mb", nameFile != null ? nameFile : "");
        return fileInMb < MAX_FILE_MB;
    }

    public static String getIdFileGoogleSheetFromUrl(String url) {
        String name = url.substring(0, url.lastIndexOf("/"));
        return name.substring(name.lastIndexOf("/") + 1);
    }

    public static int[] processFileSelection(Sheet sheet, String dataSelected) {
        int[] options = new int[4];
        String locationUpperLeft = "";
        String locationMustUnder = "";
        if (dataSelected != null) {
            String[] optionalRange = dataSelected.split(":");
            locationUpperLeft = optionalRange[0];
            locationMustUnder = optionalRange[1];
        }
        int rowsMaxDefault = sheet.getLastRowNum();
        int colsMinDefault = sheet.getLeftCol();
        int rowsMinDefault = sheet.getFirstRowNum();
        int colsMaxDefault = 256;
        int rowsMaxSelected = 0;
        int colsMaxSelected = 0;
        int rowsMinSelected = 0;
        int colsMinSelected = 0;
        if (dataSelected != null) {
            rowsMaxSelected = new CellAddress(locationMustUnder).getRow();
            colsMaxSelected = new CellAddress(locationMustUnder).getColumn();
            rowsMinSelected = new CellAddress(locationUpperLeft).getRow();
            colsMinSelected = new CellAddress(locationUpperLeft).getColumn();
        }
        if (dataSelected == null || dataSelected.isEmpty()) {
            options[0] = rowsMinDefault;
            options[1] = colsMinDefault;
            options[2] = rowsMaxDefault;
            options[3] = colsMaxDefault;
        } else {
            if (!locationUpperLeft.isEmpty()) {
                options[0] = Math.max(rowsMinSelected, rowsMinDefault);
                options[1] = Math.max(colsMinSelected, colsMinDefault);
            }
            if (!locationMustUnder.isEmpty()) {
                options[2] = Math.min(rowsMaxSelected, rowsMaxDefault);
                options[3] = Math.min(colsMaxSelected, colsMaxDefault);
            }
        }
        return options;
    }


    public static Workbook getWorkbook(InputStream data, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(data);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(data);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }

}
