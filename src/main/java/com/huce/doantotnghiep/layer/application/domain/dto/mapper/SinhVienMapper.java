package com.huce.doantotnghiep.layer.application.domain.dto.mapper;

import com.huce.doantotnghiep.config.CacheConfig;
import com.huce.doantotnghiep.config.Constants;
import com.huce.doantotnghiep.layer.application.domain.dao.last.*;
import com.huce.doantotnghiep.layer.application.domain.dao.old.IDiemTKHKDao;
import com.huce.doantotnghiep.layer.application.domain.dao.old.IMonHocDao;

import com.huce.doantotnghiep.layer.application.domain.dto.*;
import com.huce.doantotnghiep.layer.application.domain.entity.last.*;
import com.huce.doantotnghiep.layer.application.domain.entity.old.DiemOld;
import com.huce.doantotnghiep.layer.application.domain.entity.old.DiemTKHK;
import com.huce.doantotnghiep.layer.application.domain.entity.old.MonHoc;
import com.huce.doantotnghiep.layer.application.domain.entity.old.SinhVienOld;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;


@Slf4j
@Component
public class SinhVienMapper {
    private final CacheConfig cacheConfig;
    private final ITKBLopHocPhanDao itbkLopHocPhanDao;

    private final IMonHocDao iMonHocDao;

    private final IDMMonHocDao idmMonHocDao;

    private final ITKBMonHoc itkbMonHoc;
    private final IDotDao iDotDao;

    private final IDTTongKetDotDao idtTongKetDotDao;

    private final IDMLopHocDao lopHocDao;
    private final IDiemTKHKDao iDiemTKHKDao;

    public SinhVienMapper(CacheConfig cacheConfig, ITKBLopHocPhanDao itbkLopHocPhanDao, IMonHocDao iMonHocDao, IDMMonHocDao idmMonHocDao, ITKBMonHoc itkbMonHoc, IDotDao iDotDao, IDTTongKetDotDao idtTongKetDotDao, IDMLopHocDao lopHocDao, IDiemTKHKDao iDiemTKHKDao) {
        this.cacheConfig = cacheConfig;
        this.itbkLopHocPhanDao = itbkLopHocPhanDao;
        this.iMonHocDao = iMonHocDao;
        this.idmMonHocDao = idmMonHocDao;
        this.itkbMonHoc = itkbMonHoc;
        this.iDotDao = iDotDao;
        this.idtTongKetDotDao = idtTongKetDotDao;
        this.lopHocDao = lopHocDao;
        this.iDiemTKHKDao = iDiemTKHKDao;
    }

    public SinhVienCompareTest createSinhVienCompareTestOld(SinhVienOld sinhVienOld, List<DiemOld> diemOldList) {
        SinhVienCompareTest sinhVienCompareTest = new SinhVienCompareTest();
        sinhVienCompareTest.setMssv(sinhVienOld.getMaSV());
        sinhVienCompareTest.setName(sinhVienOld.getHoTenSV());
        sinhVienCompareTest.setNameClass(sinhVienOld.getMaLop());
        sinhVienCompareTest.setBirthday(sinhVienOld.getNgaySinh());
        sinhVienCompareTest.setEmail(sinhVienOld.getEmailSV1());
        sinhVienCompareTest.setGender(sinhVienOld.getPhai() == null ? "Nam" : "Nữ");
        Map<String, Map<String, ShowCompareTest>> compare = new HashMap<>();
        if (!diemOldList.isEmpty()) {
            for (DiemOld diemOld : diemOldList) {
                MonHoc monHoc = iMonHocDao.getMonHocByMaMH(diemOld.getMaMH());
                CompareSubject compareSubject = new CompareSubject();
                compareSubject.setMaMonHoc(diemOld.getMaMH());
                compareSubject.setQt(diemOld.getDiemKT1() == null ? "0.0" : Constants.CONVERT_SCORE.containsKey(diemOld.getDiemKT1()) ? Constants.CONVERT_SCORE.get(diemOld.getDiemKT1()) : diemOld.getDiemKT1().replace(" ", ""));
                compareSubject.setKt(diemOld.getDiemTH() == null ? "0.0" : Constants.CONVERT_SCORE.containsKey(diemOld.getDiemTH()) ? Constants.CONVERT_SCORE.get(diemOld.getDiemTH()) : diemOld.getDiemTH().replace(" ", ""));
                compareSubject.setGroupCode(diemOld.getMaNH());
                compareSubject.setNameSubject(monHoc.getName());
                compareSubject.setSummary(diemOld.getDiemTK() == null ? "0.0" : Constants.CONVERT_SCORE.containsKey(diemOld.getDiemTK()) ? Constants.CONVERT_SCORE.get(diemOld.getDiemTK()) : diemOld.getDiemTK().replace(" ", ""));
                compareSubject.setTc(monHoc.getSoTC().toString());
                compareSubject.setScore4(diemOld.getDiems() == null ? "0.0" : Constants.CONVERT_SCORE.containsKey(diemOld.getDiems()) ? Constants.CONVERT_SCORE.get(diemOld.getDiems()) : diemOld.getDiems().replace(" ", ""));
                compareSubject.setScoreString(diemOld.getDiemCH() == null ? "0.0" : Constants.CONVERT_SCORE.containsKey(diemOld.getDiemCH()) ? Constants.CONVERT_SCORE.get(diemOld.getDiemCH()) : diemOld.getDiemCH().replace(" ", ""));
                String year;
                String hk;
                if (diemOld.getNhHk() == null || diemOld.equals("")) {
                    year = "unknown";
                    hk = "unknown";
                } else {
                    year = diemOld.getNhHk().substring(0, 4);
                    hk = diemOld.getNhHk().substring(4);
                }
                ShowCompareTest showCompareTest = new ShowCompareTest();
                Map<String, ShowCompareTest> mapHK = new HashMap<>();
                if (compare.containsKey(year)) {
                    mapHK = compare.get(year);
                    if (mapHK.containsKey(hk)) {
                        showCompareTest = mapHK.get(hk);
                        showCompareTest.getSemester().put(diemOld.getMaMH(), compareSubject);
                    } else {
                        showCompareTest.getSemester().put(diemOld.getMaMH(), compareSubject);
                        DiemTKHK diemTKHK = iDiemTKHKDao.findDiemTKHKByMaSVAndNhhk(sinhVienOld.getMaSV(), diemOld.getNhHk());
                        CompareSummary compareSummary = new CompareSummary();
                        compareSummary.setHk(hk);
                        compareSummary.setYear(year);
                        compareSummary.setSummary(diemTKHK.getDtb()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getDtb().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getDtb().replace(" ","")):diemTKHK.getDtb().replace(" ",""):"0.0");
                        compareSummary.setScore4(diemTKHK.getDtbs()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getDtbs().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getDtbs().replace(" ","")):diemTKHK.getDtbs().replace(" ",""):"0.0");
                        compareSummary.setScore4All(diemTKHK.getDtbtls()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getDtbtls().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getDtbtls().replace(" ","")):diemTKHK.getDtbtls().replace(" ",""):"0.0");
                        compareSummary.setSummaryAll(diemTKHK.getDtbtl()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getDtbtl().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getDtbtl().replace(" ","")):diemTKHK.getDtbtl().replace(" ",""):"0.0");
                        compareSummary.setTongSoTCHK(diemTKHK.getTcdthk()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getTcdthk().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getTcdthk().replace(" ","")):diemTKHK.getTcdthk().replace(" ",""):"0.0");
                        compareSummary.setTongSoTCTL(diemTKHK.getTcdttl()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getTcdttl().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getTcdttl().replace(" ","")):diemTKHK.getTcdttl().replace(" ",""):"0.0");
                        showCompareTest.setSemesterSummary(compareSummary);
                    }
                } else {
                    showCompareTest.getSemester().put(diemOld.getMaMH(), compareSubject);
                    CompareSummary compareSummary = new CompareSummary();
                    DiemTKHK diemTKHK = iDiemTKHKDao.findDiemTKHKByMaSVAndNhhk(sinhVienOld.getMaSV(), diemOld.getNhHk());
                    compareSummary.setHk(hk);
                    compareSummary.setYear(year);
                    compareSummary.setSummary(diemTKHK.getDtb()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getDtb().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getDtb().replace(" ","")):diemTKHK.getDtb().replace(" ",""):"0.0");
                    compareSummary.setScore4(diemTKHK.getDtbs()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getDtbs().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getDtbs().replace(" ","")):diemTKHK.getDtbs().replace(" ",""):"0.0");
                    compareSummary.setScore4All(diemTKHK.getDtbtls()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getDtbtls().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getDtbtls().replace(" ","")):diemTKHK.getDtbtls().replace(" ",""):"0.0");
                    compareSummary.setSummaryAll(diemTKHK.getDtbtl()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getDtbtl().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getDtbtl().replace(" ","")):diemTKHK.getDtbtl().replace(" ",""):"0.0");
                    compareSummary.setTongSoTCHK(diemTKHK.getTcdthk()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getTcdthk().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getTcdthk().replace(" ","")):diemTKHK.getTcdthk().replace(" ",""):"0.0");
                    compareSummary.setTongSoTCTL(diemTKHK.getTcdttl()!=null?Constants.CONVERT_SCORE.containsKey(diemTKHK.getTcdttl().replace(" ",""))?Constants.CONVERT_SCORE.get(diemTKHK.getTcdttl().replace(" ","")):diemTKHK.getTcdttl().replace(" ",""):"0.0");
                    showCompareTest.setSemesterSummary(compareSummary);
                }
                mapHK.put(hk, showCompareTest);
                compare.put(year, mapHK);
            }
        }
        sinhVienCompareTest.setCompare(compare);
        return sinhVienCompareTest;
    }


    public SinhVienCompareTest createSinhVienCompareTestNew(SinhVienNew sinhVienNew, List<DiemNew> diemNewList) {
        String year = "unknown";
        String hk = "unknown";
        SinhVienCompareTest sinhVienCompareTest = new SinhVienCompareTest();
        sinhVienCompareTest.setMssv(sinhVienNew.getMaSinhVien());
        sinhVienCompareTest.setEmail(sinhVienNew.getEmail());
        sinhVienCompareTest.setNameClass(lopHocDao.getDMLopHocById(sinhVienNew.getIdLopHoc()).getTenLop());
        sinhVienCompareTest.setGender(Boolean.TRUE.equals(sinhVienNew.getGioiTinh()) ? "Nữ" : "Nam");
        if (sinhVienNew.getNgaySinh() != null)
            sinhVienCompareTest.setBirthday(sinhVienNew.getNgaySinh().substring(0, 6) + sinhVienNew.getNgaySinh().substring(sinhVienNew.getNgaySinh().length() - 2));
        sinhVienCompareTest.setName(sinhVienNew.getHoDem() + " " + sinhVienNew.getTen());
        Map<String, Map<String, ShowCompareTest>> compare = new HashMap<>();
        if (!diemNewList.isEmpty()) {
            for (DiemNew diemNew : diemNewList) {
                CompareSubject compareSubject = new CompareSubject();
                compareSubject.setSummary(diemNew.getDiemTongKet() == null ? "0.0" : diemNew.getDiemTongKet().toString().replace(" ", ""));
                compareSubject.setScore4(diemNew.getDiemTinChi() == null ? "null" : diemNew.getDiemTinChi().toString().replace(" ", ""));
                compareSubject.setScoreString(diemNew.getDiemChu() == null ? "null" : diemNew.getDiemChu().replace(" ", ""));
                compareSubject.setQt(diemNew.getDiemChuyenCan() == null ? "0.0" : diemNew.getDiemChuyenCan().toString());
                compareSubject.setKt(diemNew.getDiemThi() == null ? "0.0" : diemNew.getDiemThi().toString());
                TKBLopHocPhan tkbLopHocPhan = itbkLopHocPhanDao.getTKBLopHocPhanById(diemNew.getIdLopHocPhan());
                if (tkbLopHocPhan != null) {
                    compareSubject.setGroupCode(tkbLopHocPhan.getLopDuKien());
                    TKBMonHoc tkbMonHoc = itkbMonHoc.getTKBMonHocById(tkbLopHocPhan.getIdMonHoc());
                    compareSubject.setNameSubject(tkbMonHoc.getTenMonHoc());
                    compareSubject.setMaMonHoc(tkbMonHoc.getMaMonHoc());
                    if (compareSubject.getMaMonHoc().equals("430106")
                            || compareSubject.getMaMonHoc().equals("430107")
                            || compareSubject.getMaMonHoc().equals("430108")
                            || compareSubject.getMaMonHoc().equals("430109")
                            || compareSubject.getMaMonHoc().equals("430110")
                            || compareSubject.getMaMonHoc().equals("430111")
                            || compareSubject.getMaMonHoc().equals("430112")
                            || compareSubject.getMaMonHoc().equals("430113")
                            || compareSubject.getMaMonHoc().equals("430114")
                            || compareSubject.getMaMonHoc().equals("430115")
                            || compareSubject.getMaMonHoc().equals("430116")
                            || compareSubject.getMaMonHoc().equals("430117")
                            || compareSubject.getMaMonHoc().equals("480111")
                            || compareSubject.getMaMonHoc().equals("480112")
                            || compareSubject.getMaMonHoc().equals("480113")
                            || compareSubject.getMaMonHoc().equals("480114")
                            || compareSubject.getMaMonHoc().equals("480107")
                            || compareSubject.getMaMonHoc().equals("480106")
                            || compareSubject.getMaMonHoc().equals("480109")) {
                        compareSubject.setTc("0.0");
                    } else
                        compareSubject.setTc(tkbMonHoc.getSoTinChi() == null ? "0.0" : Float.toString((float) tkbMonHoc.getSoTinChi()));
                    Dot dot = iDotDao.getDotById(tkbLopHocPhan.getIdDot());
                    year = dot.getTenDot().substring(4, 8);
                    hk = dot.getSoThuTu().toString();
                    ShowCompareTest showCompareTest = new ShowCompareTest();
                    Map<String, ShowCompareTest> mapHK = new HashMap<>();
                    if (compare.containsKey(year)) {
                        mapHK = compare.get(year);
                        if (mapHK.containsKey(hk)) {
                            showCompareTest = mapHK.get(hk);
                            showCompareTest.getSemester().put(tkbMonHoc.getMaMonHoc(), compareSubject);
                        } else {
                            showCompareTest.getSemester().put(tkbMonHoc.getMaMonHoc(), compareSubject);
                            CompareSummary compareSummary = new CompareSummary();
                            DTTongKetDot dtTongKetDot = idtTongKetDotDao.findDTTongKetDotByIdSinhVienAndIdDot(sinhVienNew.getId(), tkbLopHocPhan.getIdDot());
                            if (dtTongKetDot != null) {
                                compareSummary.setHk(hk);
                                compareSummary.setYear(year);
                                compareSummary.setSummary(dtTongKetDot.getDiemTBHocLuc()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getDiemTBHocLuc().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getDiemTBHocLuc().toString()):String.format("%.2f",(double)dtTongKetDot.getDiemTBHocLuc()):"0.0");
                                compareSummary.setScore4(dtTongKetDot.getDiemTBTinChi()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getDiemTBTinChi().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getDiemTBTinChi().toString()):String.format("%.2f",(double)dtTongKetDot.getDiemTBTinChi()):"0.0");
                                compareSummary.setScore4All(dtTongKetDot.getDiemTBTinChiTichLuy()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getDiemTBTinChiTichLuy().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getDiemTBTinChiTichLuy().toString()):String.format("%.2f",(double)dtTongKetDot.getDiemTBTinChiTichLuy()):"0.0");
                                compareSummary.setSummaryAll(dtTongKetDot.getDiemTBHocLucTichLuy()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getDiemTBHocLucTichLuy().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getDiemTBHocLucTichLuy().toString()):String.format("%.2f",(double)dtTongKetDot.getDiemTBHocLucTichLuy()):"0.0");
                                compareSummary.setTongSoTCHK(dtTongKetDot.getSoTCTichLuyHK()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getSoTCTichLuyHK().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getSoTCTichLuyHK().toString()):(float)dtTongKetDot.getSoTCTichLuyHK()+"":"0.0");
                                compareSummary.setTongSoTCTL(dtTongKetDot.getSoTCTichLuy()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getSoTCTichLuy().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getSoTCTichLuy().toString()):(float)dtTongKetDot.getSoTCTichLuy()+"":"0.0");
                                showCompareTest.setSemesterSummary(compareSummary);
                            }
                        }
                    } else {
                        showCompareTest.getSemester().put(tkbMonHoc.getMaMonHoc(), compareSubject);
                        CompareSummary compareSummary = new CompareSummary();
                        DTTongKetDot dtTongKetDot = idtTongKetDotDao.findDTTongKetDotByIdSinhVienAndIdDot(sinhVienNew.getId(), tkbLopHocPhan.getIdDot());
                        if (dtTongKetDot != null) {
                            compareSummary.setHk(hk);
                            compareSummary.setYear(year);
                            compareSummary.setSummary(dtTongKetDot.getDiemTBHocLuc()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getDiemTBHocLuc().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getDiemTBHocLuc().toString()):String.format("%.2f",(double)dtTongKetDot.getDiemTBHocLuc()):"0.0");
                            compareSummary.setScore4(dtTongKetDot.getDiemTBTinChi()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getDiemTBTinChi().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getDiemTBTinChi().toString()):String.format("%.2f",(double)dtTongKetDot.getDiemTBTinChi()):"0.0");
                            compareSummary.setScore4All(dtTongKetDot.getDiemTBTinChiTichLuy()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getDiemTBTinChiTichLuy().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getDiemTBTinChiTichLuy().toString()):String.format("%.2f",(double)dtTongKetDot.getDiemTBTinChiTichLuy()):"0.0");
                            compareSummary.setSummaryAll(dtTongKetDot.getDiemTBHocLucTichLuy()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getDiemTBHocLucTichLuy().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getDiemTBHocLucTichLuy().toString()):String.format("%.2f",(double)dtTongKetDot.getDiemTBHocLucTichLuy()):"0.0");
                            compareSummary.setTongSoTCHK(dtTongKetDot.getSoTCTichLuyHK()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getSoTCTichLuyHK().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getSoTCTichLuyHK().toString()):(float)dtTongKetDot.getSoTCTichLuyHK()+"":"0.0");
                            compareSummary.setTongSoTCTL(dtTongKetDot.getSoTCTichLuy()!=null?Constants.CONVERT_SCORE.containsKey(dtTongKetDot.getSoTCTichLuy().toString())?Constants.CONVERT_SCORE.get(dtTongKetDot.getSoTCTichLuy().toString()):(float)dtTongKetDot.getSoTCTichLuy()+"":"0.0");
                            showCompareTest.setSemesterSummary(compareSummary);
                        }
                    }
                    mapHK.put(hk, showCompareTest);
                    compare.put(year, mapHK);
                } else {
//                        log.warn(diemNew.getId().toString());
                }
            }
        }
        sinhVienCompareTest.setCompare(compare);
        return sinhVienCompareTest;
    }

    public SinhVienCompareModelDTO mapSinhVienCompareModel(SinhVienCompareModel sinhVienCompareModel) {
        SinhVienCompareModelDTO sinhVienCompareModelDTO = new SinhVienCompareModelDTO();
        List<ShowCompareDTO> compare = new ArrayList<>();
        sinhVienCompareModelDTO.setMssv(sinhVienCompareModel.getMssv());
        sinhVienCompareModelDTO.setInfoCompare(sinhVienCompareModel.getInfoCompare());
        sinhVienCompareModelDTO.setOverview(sinhVienCompareModel.getOverview());
        sinhVienCompareModelDTO.setIsCompare(sinhVienCompareModel.getIsCompare());
        sinhVienCompareModelDTO.setNote(sinhVienCompareModelDTO.getNote());
        Map<Integer, Map<Integer, ShowCompareModel>> map = sinhVienCompareModel.getCompare();
        List<Integer> years = new ArrayList<>(map.keySet());
        Collections.sort(years);
        for (Integer year : years) {
            Map<Integer, ShowCompareModel> mapHK = map.get(year);
            Set<Integer> hks = mapHK.keySet();
            for (Integer hk : hks) {
                ShowCompareDTO showCompareDTO = new ShowCompareDTO();
                ShowCompareModel showCompareModel = mapHK.get(hk);
                showCompareDTO.setYear(String.valueOf(year));
                showCompareDTO.setHk(String.valueOf(hk));
                showCompareDTO.setSemester(showCompareModel.getSemester());
                showCompareDTO.setSemesterSummary(showCompareModel.getSemesterSummary());
                showCompareDTO.sort();
                compare.add(showCompareDTO);
            }
        }
        sinhVienCompareModelDTO.setCompare(compare);
        return sinhVienCompareModelDTO;
    }

}
