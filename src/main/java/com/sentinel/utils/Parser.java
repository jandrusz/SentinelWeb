package com.sentinel.utils;

public class Parser {

    public static Integer getNumberFromDay(String day) {
        switch (day) {
            case "PON":
                return 0;
            case "WT":
                return 1;
            case "SR":
                return 2;
            case "CZW":
                return 3;
            case "PIA":
                return 4;
            case "SOB":
                return 5;
            case "NIE":
                return 6;
        }
        return null;
    }

    public static String getDayInPolish(String day) {
        switch (day) {
            case "MONDAY":
                return "PON";
            case "TUESDAY":
                return "WT";
            case "THURSDAY":
                return "SR";
            case "WEDNESDAY":
                return "CZW";
            case "FRIDAY":
                return "PIA";
            case "SATURDAY":
                return "SOB";
            case "SUNDAY":
                return "NIE";
        }
        return null;
    }

}
