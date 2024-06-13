package com.ConcurrentCollection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcModiInHashmap {

    public static void main(String[] args) {

//        Map<String,Integer> h=new HashMap<>();
        Map<String,Integer> h=new ConcurrentHashMap<>();
        h.put("one",1);
        h.put("two",2);
        h.put("three",3);
        Iterator<Map.Entry<String,Integer>>it=h.entrySet().iterator();
        //iterator in hashmap are fail fast
        //concurrent hashmap divides data into segments or partitions each acting as independent hash table
        //read are fully concurrent allowing multiple thread to read from same or differnet segment
        //write are sync at segment level to ensure thread safety
        while(it.hasNext()){
            Map.Entry<String,Integer>e=it.next();
            if(e.getKey().equals("two")){
                h.put("four",4);
            }
        }
        System.out.println(h);

    }
}
