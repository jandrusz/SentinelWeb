package com.sentinel.hibernate;

import org.json.simple.JSONObject;

public class MonitorDTO {

    //na podstawie emaila wygrzebac z bazy danych wszystkie dzieci
    public static JSONObject getChildrenByUserEmail(String email) {

        JSONObject obj = new JSONObject();
        JSONObject finalObj = new JSONObject();

        finalObj.put("success","Januszek");

        return finalObj;
    }


}
