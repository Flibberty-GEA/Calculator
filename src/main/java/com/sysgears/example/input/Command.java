package com.sysgears.example.input;


public enum Command {
    EXIT,
    HISTORY,
    UNIQUE_HISTORY;


    public final String getName(){
        return name().replace("_", " ");
    }
}