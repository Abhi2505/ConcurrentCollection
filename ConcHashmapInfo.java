package com.ConcurrentCollection;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcHashmapInfo {
    private static Runnable insertRecord(){

        return()->{
            for(int i=0;i<NUM_INSERTION;i++){
                h.put(i+Thread.currentThread().getName(),i);
            }
        };
    }
    //provides  a thread safe implementaion of hashmap; It allow multiple thread to afccess and modify map concurrently
    //without explicitly syncronizing

    private static final int NUM_THREADS=5;
    private static final int NUM_INSERTION=100;

//    private static HashMap<String,Integer>h=new HashMap<>();
    private static ConcurrentHashMap<String,Integer>h=new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(NUM_THREADS);
        for(int i=0;i<NUM_THREADS;i++){
            executorService.execute(insertRecord());
        }
        executorService.shutdown();

        //we write this so that executor service would get completed otherwise we get the size as zero as code get terminated
        //before completion of task submitted to exector service we should get 500 as required but geeting different output of size
        //every time
        //since hashmap is not thread safe so when manipulated by multiple thread its writes are overriden so getting different size this condition is called as race condition

        if(!executorService.isTerminated()){
            Thread.sleep(1000);
        }
        System.out.println("size "+h.size());

    }



}
