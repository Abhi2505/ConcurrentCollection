package com.ConcurrentCollection;

import java.util.HashMap;
import java.util.HashSet;

public class HashsetInfo {
    public static void main(String[] args) {
        HashSet<String>h=new HashSet<>();
        h.add("ab");
        h.add("ram");
        h.add("as");
        System.out.println(h.add("as")); //already there return old value compared with null return false
        System.out.println(h.remove("as"));//true  in removal the return from map is compared with PRESEMT if value is already there then returns true
        System.out.println(h.remove("ases"));//false
        //how hashset not allow duplicate element
        //so when we call the add method hash set internaly uses
        // a hasmap
        //and call the h.put(key,object) the object is a constant defined as present .
        //and that add method would return h.put(key,object)==null
        //as if a new key is inserted in a hashput then put method return null
        //and if a value is alreayd there then the last inserted value
        //so if the return value is null and null==null it return true and add value to set as its first time occured
        //if it occured again then non null ==null will return false value is not adedd
        //for the removal operation
        //for removal map return null if mapping is not there if it is present then return prev value associated with key
        HashMap<String,Integer>hm=new HashMap<>();
        hm.put("da",2);
        System.out.println(hm.put("as",1));
        System.out.println(hm.put("da",3));
        System.out.println(hm.remove("da"));
        System.out.println(hm.remove("dd"));
    }
}
