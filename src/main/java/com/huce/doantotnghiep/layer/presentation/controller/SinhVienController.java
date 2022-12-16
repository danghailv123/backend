package com.huce.doantotnghiep.layer.presentation.controller;

import com.huce.doantotnghiep.layer.application.domain.dao.last.ISinhVienNewDao;
import com.huce.doantotnghiep.layer.application.domain.dto.SinhVienCompareModel;
import com.huce.doantotnghiep.layer.application.domain.dto.mapper.SinhVienMapper;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ConfigSetting;
import com.huce.doantotnghiep.layer.application.domain.entity.process.HistoryProcess;
import com.huce.doantotnghiep.layer.application.domain.model.InfoSheet;
import com.huce.doantotnghiep.layer.application.domain.thread.ThreadSubmit;
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

    private final IHistorySettingService historySettingService;
    private final IProcessCompareService iProcessCompareService;

    private final ISinhVienNewDao sinhVienNewDao;

    public SinhVienController(ISinhVienOldService iSinhVienOldService, ISinhVienNewService iSinhVienNewService, ICompareSinhVien compareSinhVien, ISubmitJobCompare iSubmitJobCompare, IConfigSettingService iConfigSettingService, SinhVienMapper sinhVienMapper, IHistorySettingService historySettingService, IProcessCompareService iProcessCompareService, ISinhVienNewDao sinhVienNewDao) {
        this.iSinhVienOldService = iSinhVienOldService;
        this.iSinhVienNewService = iSinhVienNewService;
        this.compareSinhVien = compareSinhVien;
        this.iSubmitJobCompare = iSubmitJobCompare;
        this.iConfigSettingService = iConfigSettingService;
        this.sinhVienMapper = sinhVienMapper;
        this.historySettingService = historySettingService;
        this.iProcessCompareService = iProcessCompareService;
        this.sinhVienNewDao = sinhVienNewDao;
    }

    @GetMapping("/view")
    public Response viewSinhVien(@RequestParam(name = "mssv") String mssv, @RequestParam(name = "type", defaultValue = "0", required = false) Integer type) {
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
    public Response isCompare(@RequestParam(name = "mssv") String mssv, @RequestParam(name = "type", defaultValue = "0", required = false) Integer type) {
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
                return ResponseFactory.getClientErrorResponse("Hệ thống đang xử lý file vui lòng xin chờ");
            }
            log.info("process file {}", file.getOriginalFilename());
            configSetting.setStatus(1);
            iConfigSettingService.save(configSetting);
            List<String> data = compareSinhVien.compareFileExcel(file);
            HistoryProcess historyProcess = new HistoryProcess();
            historyProcess.setName(file.getOriginalFilename());
            historyProcess.setTotal(data.size());
            historyProcess.setStatus(0);
            historyProcess.setCreatedTime(System.currentTimeMillis());
            HistoryProcess process = historySettingService.create(historyProcess);
            ThreadSubmit threadSubmit = new ThreadSubmit(iSubmitJobCompare,1,process.getId(),data);
            threadSubmit.run();
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
            HistoryProcess historyProcess = new HistoryProcess();
            historyProcess.setName("Kiểm tra toàn bộ sinh viên chưa ra trường");
            historyProcess.setTotal(sinhVienNewDao.countSinhVienNewByStatus(1));
            historyProcess.setStatus(0);
            historyProcess.setCreatedTime(System.currentTimeMillis());
            HistoryProcess process = historySettingService.create(historyProcess);
            ThreadSubmit threadSubmit = new ThreadSubmit(iSubmitJobCompare,2,process.getId(),null);
            threadSubmit.run();
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        } catch (Exception exception) {
            configSetting.setStatus(0);
            iConfigSettingService.save(configSetting);
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/get-history")
    public Response getHistory(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                               @RequestParam(name = "size", defaultValue = "30", required = false) Integer size,
                               @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, historySettingService.getList(page, size, keyword));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/get-all")
    public Response getAllProcessCompare(@RequestParam(name = "id") Integer id,
                                         @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                         @RequestParam(name = "size", defaultValue = "30", required = false) Integer size,
                                         @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProcessCompareService.getList(id, page, size, keyword));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }
}
