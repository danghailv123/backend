package com.huce.doantotnghiep.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.huce.doantotnghiep.layer.application.domain.dao.last.ILopHocDao;
import com.huce.doantotnghiep.layer.application.domain.entity.last.TKBLopHocPhan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
//    private final ILopHocDao iLopHoc;
//
//    private LoadingCache<Integer, ILopHocDao> loadingCacheLopHoc = CacheBuilder.newBuilder().maximumSize(500).expireAfterAccess(7, TimeUnit.DAYS).build(new CacheLoader<Integer, ILopHocDao>() {
//        @Override
//        public ILopHocDao load(Integer idLop) throws Exception {
//            return iLopHoc.getLopHocById(idLop);
//        }
//    });
//
//    public CacheConfig(ILopHocDao iLopHoc) {
//        this.iLopHoc = iLopHoc;
//    }
//
//
//    public TKBLopHocPhan getTenLopHoc(Integer idLop) throws ExecutionException {
//        return loadingCacheLopHoc.get(idLop);
//    }



}
