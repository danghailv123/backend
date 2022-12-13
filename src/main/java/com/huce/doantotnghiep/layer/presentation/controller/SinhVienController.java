package com.huce.doantotnghiep.layer.presentation.controller;

import com.huce.doantotnghiep.layer.application.domain.dto.SinhVienCompareModel;
import com.huce.doantotnghiep.layer.application.domain.dto.mapper.SinhVienMapper;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ConfigSetting;
import com.huce.doantotnghiep.layer.application.domain.model.InfoSheet;
import com.huce.doantotnghiep.layer.application.service.*;
import com.huce.doantotnghiep.utility.response.Response;
import com.huce.doantotnghiep.utility.response.ResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sinh-vien")
public class SinhVienController {
    private final ISinhVienOldService iSinhVienOldService;

    private final ISinhVienNewService iSinhVienNewService;

    private final ICompareSinhVien compareSinhVien;

    private final ISubmitJobCompare iSubmitJobCompare;
    private final IConfigSettingService iConfigSettingService;
    private final SinhVienMapper sinhVienMapper;

    private final IProcessCompareService iProcessCompareService;

    public SinhVienController(ISinhVienOldService iSinhVienOldService, ISinhVienNewService iSinhVienNewService, ICompareSinhVien compareSinhVien, ISubmitJobCompare iSubmitJobCompare, IConfigSettingService iConfigSettingService, SinhVienMapper sinhVienMapper, IProcessCompareService iProcessCompareService) {
        this.iSinhVienOldService = iSinhVienOldService;
        this.iSinhVienNewService = iSinhVienNewService;
        this.compareSinhVien = compareSinhVien;
        this.iSubmitJobCompare = iSubmitJobCompare;
        this.iConfigSettingService = iConfigSettingService;
        this.sinhVienMapper = sinhVienMapper;
        this.iProcessCompareService = iProcessCompareService;
    }

    @GetMapping("/view")
    public Response viewSinhVien(@RequestParam(name = "mssv") String mssv,
                                 @RequestParam(name = "type", defaultValue = "0", required = false) Integer type) {
        try {
            switch (type) {
                case 0:
                    return ResponseFactory.getSuccessResponse(Response.SUCCESS, iSinhVienOldService.getSinhView(mssv));
                case 1:
                    return ResponseFactory.getSuccessResponse(Response.SUCCESS, iSinhVienNewService.getSinhView(mssv));
                default:
                    return null;
            }
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/is-compare")
    public Response isCompare(@RequestParam(name = "mssv") String mssv,
                              @RequestParam(name = "type", defaultValue = "0", required = false) Integer type) {
        try {
            SinhVienCompareModel sinhVienCompareModel = compareSinhVien.isCompareSinhVienModel(mssv, type);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, sinhVienMapper.mapSinhVienCompareModel(sinhVienCompareModel));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("/sheet-compare")
    public Response isSheetCompare(@RequestParam("year") String year, @RequestParam("hk") String hk, @RequestBody InfoSheet infoSheet) {
        try {
            compareSinhVien.compareFile(infoSheet);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("/compare-file")
    public Response compareFile(@RequestParam("file") MultipartFile file) {
        ConfigSetting configSetting = iConfigSettingService.getConfigSetting(1);
        try {
            if (configSetting.getStatus().equals(1)) {
                return ResponseFactory.getSuccessResponse("Đang xử lý vui lòng chờ");
            }
            log.info("process file");
            configSetting.setStatus(1);
            iConfigSettingService.save(configSetting);
            List<String> data = compareSinhVien.compareFileExcel(file);
            iSubmitJobCompare.jobFile(data);
            configSetting.setStatus(0);
            iConfigSettingService.save(configSetting);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        } catch (Exception exception) {
            configSetting.setStatus(0);
            iConfigSettingService.save(configSetting);
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/compare-all")
    public Response compareAll() {
        ConfigSetting configSetting = iConfigSettingService.getConfigSetting(1);

        try {
            if (configSetting.getStatus().equals(1)) {
                return ResponseFactory.getSuccessResponse("Đang xử lý vui lòng chờ");
            }
            log.info("process all ");
            configSetting.setStatus(1);
            iConfigSettingService.save(configSetting);
            iSubmitJobCompare.jobDatabase();
            iConfigSettingService.save(configSetting);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        } catch (Exception exception) {
            configSetting.setStatus(0);
            iConfigSettingService.save(configSetting);
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/get-all")
    public Response getAllProcessCompare(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                         @RequestParam(name = "size", defaultValue = "30", required = false) Integer size) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProcessCompareService.getList(page, size));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }
}
