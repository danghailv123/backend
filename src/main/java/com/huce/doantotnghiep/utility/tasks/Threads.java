package com.huce.doantotnghiep.utility.tasks;

public class Threads {
    public static void sleep(Long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception ignored) {
        }
    }
}
