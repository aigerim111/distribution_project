package com.example.demo.payload.response;

import java.util.HashMap;
import java.util.Map;

public class ResponseWrapper {

    private Map<String, String> res = new HashMap<>();


    public void putMessage(String key, String message){
        res.put(key, message);
    }

    public Map<String, String> getMessage(){
        return res;
    }
}
