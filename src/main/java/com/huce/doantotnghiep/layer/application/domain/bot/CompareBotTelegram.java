package com.huce.doantotnghiep.layer.application.domain.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huce.doantotnghiep.config.Constants;
import com.huce.doantotnghiep.layer.application.service.ICompareSinhVien;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;

@Slf4j
public class CompareBotTelegram extends TelegramLongPollingBot {

    private final ICompareSinhVien compareSinhVien;


    public CompareBotTelegram(ICompareSinhVien compareSinhVien) {
        this.compareSinhVien = compareSinhVien;
    }

    @Override
    public String getBotUsername() {
        return "huce_bot";
    }

    @Override
    public String getBotToken() {
        return "5645378725:AAEy2YKgfvX_beV9NNYhMlP6EfRfQfj6xX4";
    }

    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            if (update.getMessage().getText().contains("/compare")) {
                SendMessage message = new SendMessage(); // Create a message object object
                message.setChatId(829952029l);
                String mssv = message_text.substring(9);
                JSONArray array = new JSONArray();

                if (mssv.contains(",")){
                    for (String id : Arrays.asList(mssv.split(","))) {
                        array.put(id);
                    }
                }else {
                    array.put(mssv);
                }

//                try {
//                    message.setText(Constants.SERIALIZER.writeValueAsString(compareSinhVien.compareArrSinhVien(array)));
//                } catch (JsonProcessingException e) {
//                    message.setText("Error");
//                }

                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {

                    log.error(e.getMessage(), e);
                }
            } else {

            }

        } else if (update.hasCallbackQuery()) {
        }
    }
}
