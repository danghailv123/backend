package com.huce.doantotnghiep.layer.application.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.huce.doantotnghiep.layer.application.domain.dto.CompareShow;
import com.huce.doantotnghiep.layer.application.domain.dto.SinhVienCompareModel;
import com.huce.doantotnghiep.layer.application.domain.model.InfoSheet;
import com.huce.doantotnghiep.layer.application.domain.model.ResponseSheet;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICompareSinhVien {
    CompareShow isCompareSinhVien(String mssv);
    SinhVienCompareModel isCompareSinhVienModel(String mssv,Integer type) throws Exception;

    void compareFile(InfoSheet infoSheet) throws JsonProcessingException;

    List<String> compareFileExcel(MultipartFile file) throws IOException;
}
