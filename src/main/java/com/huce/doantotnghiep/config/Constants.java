package com.huce.doantotnghiep.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huce.doantotnghiep.layer.application.domain.entity.last.SinhVienNew;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ProcessCompare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class Constants {
    public static ObjectMapper SERIALIZER = new ObjectMapper();

    public static Map<String, String> CONVERT_SCORE = new HashMap<>();
    public static Map<Integer, String> STATUS = new HashMap<>();
    public static List<Future<List<ProcessCompare>>> jobSubmit = new ArrayList<>();

    static {
        SERIALIZER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SERIALIZER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        SERIALIZER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        CONVERT_SCORE.put("","0.0");
        CONVERT_SCORE.put(" ","0.0");
        CONVERT_SCORE.put("V","0.0");
        CONVERT_SCORE.put("BH","0.0");
        CONVERT_SCORE.put("QC","0.0");
        CONVERT_SCORE.put("K","0.0");
        CONVERT_SCORE.put("R","0.0");

        STATUS.put(1,"SUCCESS");
        STATUS.put(0,"IN PROCESS");
        STATUS.put(-1,"ERROR");
    }

}
