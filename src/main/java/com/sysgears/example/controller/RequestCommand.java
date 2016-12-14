package com.sysgears.example.controller;


public enum RequestCommand {
    EXIT,
    HISTORY,
    UNIQUE_HISTORY;


    public final String getName(){
        return name().replace("_", " ");
    }
}