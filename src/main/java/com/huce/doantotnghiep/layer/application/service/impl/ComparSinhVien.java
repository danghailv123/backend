package com.huce.doantotnghiep.layer.application.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.huce.doantotnghiep.config.Constants;
import com.huce.doantotnghiep.layer.application.domain.dto.*;
import com.huce.doantotnghiep.layer.application.domain.model.InfoSheet;
import com.huce.doantotnghiep.layer.application.domain.model.ResponseSheet;
import com.huce.doantotnghiep.layer.application.service.ICompareSinhVien;
import com.huce.doantotnghiep.layer.application.service.IGoogleSheetService;
import com.huce.doantotnghiep.layer.application.service.ISinhVienNewService;
import com.huce.doantotnghiep.layer.application.service.ISinhVienOldService;
import com.huce.doantotnghiep.utility.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;


@Service
@Slf4j
public class ComparSinhVien implements ICompareSinhVien {
    private final ISinhVienNewService iSinhVienNewService;
    private final ISinhVienOldService iSinhVienOldService;
    private final DataFormatter dataFormatter = new DataFormatter();
    private final IGoogleSheetService iGoogleSheetService;

    public ComparSinhVien(ISinhVienNewService iSinhVienNewService, ISinhVienOldService iSinhVienOldService, IGoogleSheetService iGoogleSheetService) {
        this.iSinhVienNewService = iSinhVienNewService;
        this.iSinhVienOldService = iSinhVienOldService;
        this.iGoogleSheetService = iGoogleSheetService;
    }


    @Override
    public CompareShow isCompareSinhVien(String mssv) {
        SinhVienCompareTest old = iSinhVienOldService.getSinhView(mssv);
        SinhVienCompareTest last = iSinhVienNewService.getSinhView(mssv);
        CompareShow compareShow = new CompareShow();
        if (old == null || last == null) {
            compareShow.setCompare(false);
            compareShow.setTile("Không tồn tại trong database mới hoặc cũ");
            return compareShow;
        }
        try {
            String old_string = Constants.SERIALIZER.writeValueAsString(old);
            String last_string = Constants.SERIALIZER.writeValueAsString(last);
            compareShow.setCompare(old_string.equals(last_string));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            compareShow.setTile(e.getMessage());
            compareShow.setCompare(false);
        }
        return compareShow;
    }

    // type
    // 0 all
    // 1 score
    // 2 hoten
    @Override
    public SinhVienCompareModel isCompareSinhVienModel(String mssv, Integer type) throws Exception {
        SinhVienCompareTest old = iSinhVienOldService.getSinhView(mssv);
        SinhVienCompareTest last = iSinhVienNewService.getSinhView(mssv);
        SinhVienCompareModel sinhVienCompareModel = new SinhVienCompareModel();
        sinhVienCompareModel.setMssv(mssv);
        Map<Integer, Map<Integer, ShowCompareModel>> compare = new HashMap<>();
        Integer totalSubjects = 0;
        Integer differenceSubjects = 0;
        Integer totalHk = 0;
        Integer differenceHk = 0;
        if (old == null) {
//            sinhVienCompareModel.setNote("Sinh viên không tồn tại trong hệ thống cũ");
//            sinhVienCompareModel.setIsCompare(false);
//            return sinhVienCompareModel;
            throw new Exception("Sinh viên không tồn tại trong hệ thống cũ");
        }
        if (last == null) {
//            sinhVienCompareModel.setNote("Sinh viên không tồn tại trong hệ thống mới");
//            sinhVienCompareModel.setIsCompare(false);
//            return sinhVienCompareModel;
            throw new Exception("Sinh viên không tồn tại trong hệ thống mới");
        }
        InfoModel infoOld = new InfoModel();
        InfoModel infoNew = new InfoModel();
        infoOld.setName(old.getName());
        infoOld.setBirthday(old.getBirthday());
        infoOld.setGender(old.getGender());
        infoOld.setLop(old.getNameClass());
        infoNew.setName(last.getName());
        infoNew.setBirthday(last.getBirthday());
        infoNew.setGender(last.getGender());
        infoNew.setLop(last.getNameClass());
        InfoCompare infoCompare = new InfoCompare();
        infoCompare.setInfoOld(infoOld);
        infoCompare.setInfoNew(infoNew);
        try {
            String compareOld = Constants.SERIALIZER.writeValueAsString(infoOld);
            String compareNew = Constants.SERIALIZER.writeValueAsString(infoNew);
            infoCompare.setIsCompare(Objects.equals(compareOld, compareNew));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            infoCompare.setIsCompare(false);
        }
        sinhVienCompareModel.setInfoCompare(infoCompare);
        try {
            Map<String, Map<String, ShowCompareTest>> dataOld = old.getCompare();
            Map<String, Map<String, ShowCompareTest>> dataNew = last.getCompare();
            Set<String> years = dataOld.keySet();
            for (String year : years) {
                Map<String, ShowCompareTest> dataYearOld = dataOld.get(year);
                Set<String> hks = dataYearOld.keySet();
                for (String hk : hks) {
                    totalHk++;
                    ShowCompareTest dataHK = dataYearOld.get(hk);
                    Map<String, CompareSubject> mapSubject = dataHK.getSemester();
                    Set<String> subjects = mapSubject.keySet();
                    ShowCompareModel showCompareModel = new ShowCompareModel();
                    for (String subject : subjects) {
                        totalSubjects++;
                        CompareSubjectModel compareSubjectModel = new CompareSubjectModel();
                        CompareSubject compareSubjectOld = null;
                        CompareSubject compareSubjectNew = null;
                        try {
                            compareSubjectOld = mapSubject.get(subject);
                            compareSubjectNew = dataNew.get(year).get(hk).getSemester().get(subject);
                        } catch (Exception exception) {
                            log.error(mssv);
                            log.error(exception.getMessage(), exception);
                        }
                        compareSubjectModel.setInfoOld(compareSubjectOld);
                        compareSubjectModel.setInfoNew(compareSubjectNew);
                        if (type == 0) {
                            try {
                                String compareSubjectOldString = Constants.SERIALIZER.writeValueAsString(compareSubjectOld);
                                String compareSubjectNewString = Constants.SERIALIZER.writeValueAsString(compareSubjectNew);
                                boolean check = compareSubjectOldString.equals(compareSubjectNewString);
                                compareSubjectModel.setIsCompare(check);
                                if (!check) {
                                    sinhVienCompareModel.setIsCompare(false);
                                    differenceSubjects++;
                                }
                            } catch (Exception exception) {
                                compareSubjectModel.setIsCompare(false);
                                differenceSubjects++;
                                sinhVienCompareModel.setIsCompare(false);
                            }
                        } else if (type == 1) {
                            compareSubjectModel.setIsCompare(compareSubjectOld.getQt().equals(compareSubjectNew.getQt())
                                    && compareSubjectOld.getKt().equals(compareSubjectNew.getKt())
                                    && compareSubjectOld.getSummary().equals(compareSubjectNew.getSummary())
                                    && compareSubjectOld.getScore4().equals(compareSubjectNew.getScore4())
                                    && compareSubjectOld.getScoreString().equals(compareSubjectNew.getScoreString()));
                        }
                        showCompareModel.getSemester().add(compareSubjectModel);
                    }
                    CompareSummary compareSummaryOld = null;
                    CompareSummary compareSummaryNew = null;
                    try {
                        compareSummaryOld = dataHK.getSemesterSummary();
                        compareSummaryNew = dataNew.get(year).get(hk).getSemesterSummary();
                    } catch (Exception exception) {
                        log.error(mssv);
                        log.error(exception.getMessage(), exception);
                        sinhVienCompareModel.setIsCompare(false);
                    }
                    showCompareModel.getSemesterSummary().setInfoNew(compareSummaryNew);
                    showCompareModel.getSemesterSummary().setInfoOld(compareSummaryOld);
                    if (type == 1 || type == 0) {
                        try {
                            String compareSummaryOldString = Constants.SERIALIZER.writeValueAsString(compareSummaryOld);
                            String compareSummaryNewString = Constants.SERIALIZER.writeValueAsString(compareSummaryNew);
                            boolean check = compareSummaryOldString.equals(compareSummaryNewString);
                            if (!check) differenceHk++;
                            showCompareModel.getSemesterSummary().setIsCompare(check);
                        } catch (Exception exception) {
                            showCompareModel.getSemesterSummary().setIsCompare(false);
                            sinhVienCompareModel.setIsCompare(false);
                            differenceHk++;
                        }
                    }
                    if (compare.containsKey(Integer.parseInt(year))) {
                        compare.get(Integer.parseInt(year)).put(Integer.parseInt(hk), showCompareModel);
                    } else {
                        Map<Integer, ShowCompareModel> showCompareModelMap = new HashMap<>();
                        showCompareModelMap.put(Integer.parseInt(hk), showCompareModel);
                        compare.put(Integer.parseInt(year), showCompareModelMap);
                    }
                }
            }
        } catch (Exception exception) {
            log.error(mssv);
            log.error(exception.getMessage());
            sinhVienCompareModel.setIsCompare(false);
            sinhVienCompareModel.setIsCompare(false);
        }
        Overview overview = new Overview();
        overview.setDifferenceHk(differenceHk);
        overview.setTotalHk(totalHk);
        overview.setDifferenceSubjects(differenceSubjects);
        overview.setTotalSubjects(totalSubjects);
        sinhVienCompareModel.setOverview(overview);
        sinhVienCompareModel.setCompare(compare);
        return sinhVienCompareModel;
    }

    @Override
    public void compareFile(InfoSheet infoSheet) throws JsonProcessingException {
//        ResponseSheet data = iGoogleSheetService.getData(infoSheet.getId(), infoSheet.getRange());
//        for (Map<String, String> datum : data.getData()) {
//            System.out.println(datum.get("MSSV") + ":" + Constants.SERIALIZER.writeValueAsString(isCompareSinhVienModel(datum.get("MSSV"), 0).getIsCompare()));
//        }

    }

    @Override
    public List<String> compareFileExcel(MultipartFile file) throws IOException {
        List<JSONObject> listRecords = new ArrayList<>();
        List<String> listMssv = new ArrayList<>();
        byte[] data = file.getBytes();
        Workbook workbook = FileUtils.getWorkbook(new ByteArrayInputStream(data), file.getOriginalFilename());
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        for (Iterator<Sheet> it = workbook.sheetIterator(); it.hasNext(); ) {
            Sheet sheet = it.next();
            if (sheet != null) {
                Map<Integer, String> header = new HashMap<>();
                int[] arrLocation = FileUtils.processFileSelection(sheet, null);
                int rowsMin = arrLocation[0];
                int colsMin = arrLocation[1];
                int rowsMax = arrLocation[2];
                int colsMax = arrLocation[3];
                Iterator<Row> iterator = sheet.iterator();
                boolean lock = true;
                while (iterator.hasNext() && lock) {
                    Row row = iterator.next();
                    if (row.getRowNum() < rowsMin) continue;
                    colsMax = row.getLastCellNum();
                    for (int i = colsMin; i <= colsMax; i++) {
                        Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                        if (cell != null && !cell.equals("")) {
                            header.put(i, cell.toString());
                            lock = false;
                        }
                    }
                }
                while (iterator.hasNext()) {
                    JSONObject record = new JSONObject();
                    Row row = iterator.next();
                    if (row.getRowNum() > rowsMax) break;
                    header.forEach((key, field) -> {
                        Cell cell = row.getCell(key, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                        if (cell != null && !cell.equals("")) {
                            if (cell.getCellType().equals(CellType.NUMERIC)) {
                                CellStyle style = workbook.createCellStyle();
                                cell.setCellStyle(style);
                            }
                            String value = dataFormatter.formatCellValue(cell, evaluator);
                            if (!value.equals("")) {
                                record.put(field, value);
                            }
                        }
                    });
                    if (record.length() > 0) {
                        listRecords.add(record);
                    }
                }
            }
        }
        for (JSONObject record : listRecords) {
            if (record.has("MSSV")){
                listMssv.add(record.getString("MSSV"));
            }
        }
        return listMssv;
    }
}
