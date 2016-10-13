package com.sentinel.hibernate;

import org.json.simple.JSONObject;

public class ScheduleDAO {


    //TODO do zrobienia
    public static JSONObject getSchedulesByUserId(String idUser) {

        JSONObject finalObj = new JSONObject();
        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();


        obj.put("idSchedule","1");
        obj.put("name","harmonogram dev");
        obj.put("idUser","2");
        obj2.put("schedule0",obj);
        obj = new JSONObject();
        obj.put("idSchedule","2");
        obj.put("name","harmonogram jakis");
        obj.put("idUser","2");
        obj2.put("schedule1",obj);
        finalObj.put("success", obj2);

        return finalObj;
    }
}
