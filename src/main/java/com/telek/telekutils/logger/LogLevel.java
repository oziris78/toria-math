package com.telek.telekutils.logger;



public enum LogLevel {
    OFF(0), DEBUG(10), INFO(20), WARNING(30), ERROR(40), CRITICAL(50);

    private final int value;
    LogLevel(int value){
        this.value = value;
    }

    boolean isBefore(LogLevel that){
        return this.value < that.value;
    }
}
