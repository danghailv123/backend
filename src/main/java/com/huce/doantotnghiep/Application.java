package com.huce.doantotnghiep;

import com.huce.doantotnghiep.layer.application.domain.bot.CompareBotTelegram;
import com.huce.doantotnghiep.layer.application.domain.entity.last.SinhVienNew;
import com.huce.doantotnghiep.layer.application.domain.thread.JobCompare;
import com.huce.doantotnghiep.layer.application.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final ISinhVienOldService iSinhVienOldService;
    private final ISinhVienNewService iSinhVienNewService;

    private final ICompareSinhVien compareSinhVien;

    private final ISubmitJobCompare iSubmitJobCompare;

    private final IGoogleSheetService iGoogleSheetService;

    public Application(ISinhVienOldService iSinhVienOldService, ISinhVienNewService iSinhVienNewService, ICompareSinhVien compareSinhVien, ISubmitJobCompare iSubmitJobCompare, IGoogleSheetService iGoogleSheetService) {
        this.iSinhVienOldService = iSinhVienOldService;
        this.iSinhVienNewService = iSinhVienNewService;
        this.compareSinhVien = compareSinhVien;
        this.iSubmitJobCompare = iSubmitJobCompare;
        this.iGoogleSheetService = iGoogleSheetService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        System.out.println(compareSinhVien.isCompareSinhVien("59463"));

//        compareSinhVien.isCompareSinhVien("213347");

//        iSubmitJobCompare.startJob();
//        iSubmitJobCompare.endJob();
        // Instantiate Telegram Bots API
//        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//
//        // Register our bot
//        try {
//            botsApi.registerBot(new CompareBotTelegram(compareSinhVien));
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        System.out.println(iSinhVienOldService.getSinhViewOld("1500464"));
//        System.out.println(iSinhVienNewService.getSinhViewNew("1500464"));
//        List<DiemOld> diemOlds = iSinhVienOldService.getAllDiem("011326");
//        for (DiemOld diemOld : diemOlds) {
//            System.out.println(diemOld.toSt ring());
//        }
    }


}
