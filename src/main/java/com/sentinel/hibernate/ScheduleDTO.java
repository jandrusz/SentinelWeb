package com.sentinel.hibernate;

import org.json.simple.JSONObject;

public class ScheduleDTO {


    public static JSONObject getSchedule(String idChild, String idUser) {


        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();
        JSONObject finalObj = new JSONObject();
//        finalObj.put("failure", "Jeszcze trzeba to zrobic");
        obj.put("name","Szkola");
        obj.put("timeStart","8:17");
        obj.put("timeStop","15:09");
        obj2.put("wpisHarmonogramu0",obj);
        obj = new JSONObject();
        obj.put("name","Kino");
        obj.put("timeStart","18:23");
        obj.put("timeStop","21:19");
        obj2.put("wpisHarmonogramu1",obj);
        finalObj.put("success", obj2);
        return finalObj;

    }


}
