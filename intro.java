package com.ConcurrentCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class intro {
    //        why concurrent collection is needed
//
//        1)Most of existing collection are not thread safe so data inconsitency problem
//        2) some are thread safe but performance wise is a big issue like even for reading requires a whole lock on collection object.
//        3) while one thread iterarint the other thread is not allowed to modify if it does then it gives concurrent modification exception
//        so to overcome we need to use concurrent collections
    // concurrent collections example : ConcurrentHashMap,CopyOnwriteArraylist,CopyOnWriteArraySet
    //concurrent hashmap does not allow null for both key and value and in that one thread can iterate and other can read

    //why to chose copyonwritelist over regular araylist or araylist with explicit sync
    //explicity sync arraylist requires locking for both read nd write . Coup on writr only lock during write when it needs
    //to create a new copy of internal array . reads are lock free

    //any issue with copy on write
    //yes it has a highh memory overhead due to copying entire array on each write . writes are slower reads are fast

   //copyonwrite list is appropriate when u need concurrent read access to a list and writes are less frequent
    public static void main(String[] args) {

        //List<String> list = new CopyOnWriteArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        list.stream().forEach(item -> {
            if ("two".equals(item)) {
                list.add("six"); // This should cause ConcurrentModificationException
            }
        });

        //so to prevent concurrent modification you should use CopyOnWriteArrayList
        System.out.println(list);
    }

}
