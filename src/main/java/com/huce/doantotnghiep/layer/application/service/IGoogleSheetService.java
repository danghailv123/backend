package com.huce.doantotnghiep.layer.application.service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.huce.doantotnghiep.layer.application.domain.model.ResponseSheet;

import java.util.List;

public interface IGoogleSheetService {
    void saveData(List<String> listMSSV) throws GoogleJsonResponseException;

    ResponseSheet getData(String id, String range);
}
