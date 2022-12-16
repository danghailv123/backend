package com.huce.doantotnghiep.layer.application.domain.thread;

import com.huce.doantotnghiep.layer.application.service.ISubmitJobCompare;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ThreadSubmit implements Runnable {

    private final ISubmitJobCompare submitJobCompare;
    private final Integer type;

    private final Integer id;
    private List<String> data;

    public ThreadSubmit(ISubmitJobCompare submitJobCompare, Integer type, Integer id, List<String> data) {
        this.submitJobCompare = submitJobCompare;
        this.type = type;
        this.id = id;
        this.data = data;
    }

    @Override
    public void run() {
        switch (type) {
            case 1:
                submitJobCompare.jobFile(data, id);
                break;
            case 2:
                submitJobCompare.jobDatabase(id);
                break;
        }
    }
}
