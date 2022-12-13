package com.huce.doantotnghiep.layer.application.service.impl;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.huce.doantotnghiep.config.GoogleOauthConfig;
import com.huce.doantotnghiep.layer.application.domain.model.ResponseSheet;
import com.huce.doantotnghiep.layer.application.service.IGoogleSheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GoogleSheetService implements IGoogleSheetService {

    private final Sheets sheets;

    public GoogleSheetService() throws GeneralSecurityException, IOException {
        this.sheets = GoogleOauthConfig.getSheetsService();
    }

    @Override
    public void saveData(List<String> listMSSV) throws GoogleJsonResponseException {
        UpdateValuesResponse result = null;
        try {
            List<List<Object>> lists = new ArrayList<>();
            for (String s : listMSSV) {
                List<Object> objects = new ArrayList<>();
                objects.add(s);
                lists.add(objects);
            }
            // Updates the values in the specified range.
            ValueRange body = new ValueRange()
                    .setValues(lists);
            result = sheets.spreadsheets().values().update("1pyU2V-skHE_koV6aM2CJZu9nZXbOg0IC1vGN0AWnQtI", "C6:E200000", body)
                    .setValueInputOption("RAW")
                    .execute();
            log.info("%d cells updated.", result.getUpdatedCells());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 404) {
                System.out.printf("Spreadsheet not found with id '%s'.\n", "1pyU2V-skHE_koV6aM2CJZu9nZXbOg0IC1vGN0AWnQtI");
            } else {
                throw e;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseSheet getData(String id, String range) {
        ResponseSheet responseSheet = new ResponseSheet();
        try {
            ValueRange response = sheets.spreadsheets().values().get(id, range).execute();
            List<List<Object>> values = response.getValues();
            for (int i = 0; i < values.size(); i++) {
                List<Object> arrData = values.get(i);
                Map<String, String> data = new HashMap<>();
                for (int j = 0; j < arrData.size(); j++) {
                    if (i == 0) {
                        responseSheet.getHeader().add(arrData.get(j).toString());
                    } else {
                        data.put(responseSheet.getHeader().get(j), arrData.get(j).toString());
                    }
                }
                if (i > 0) {
                    responseSheet.getData().add(data);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return responseSheet;
    }


}
