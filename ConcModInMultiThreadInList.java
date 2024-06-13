package com.ConcurrentCollection;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcModInMultiThreadInList extends Thread {
    //how to get concurrent modification execption in multithreaded env and resolution
    //In the given example main thread is reading and child thread is modifying but child will create a new array and will
    //add the element to end of the new araay whereas main would iterate original array snapshot so it won't be able to read
    //the new inserted object but if a new thread now reads the list then it cn read the new list with the added element thus eventual consistency
    static List<Integer> l;
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        l.add(4);
    }

    public static void main(String[] args) throws InterruptedException {
       l=new CopyOnWriteArrayList<>();
//       l=new ArrayList<>();
       //why copyOnwritearraylist is used
       //this arraylist do not use locks for read operation . when read operation is performed ,
        // It works on original array snapshot wihtout any lock
        //however for write operation this list does use lock internally to ensure only one thread can perform a write operation at a time.
        //while addign an element
        //1) acquire lock 2) creat a new array one element larger than original
        // 3) copy elment from old to new 4)add new to end of newarray 5) add new element to end of new array
        //6) update refrence to new array 7) maintain eventual consitency view for readers , readers read from snapshot but eventually value is modifed and consistency is achived


        l.add(1);
        l.add(3);
        l.add(2);
        l.add(8);
        l.add(7);

        ConcModInMultiThreadInList myThread=new ConcModInMultiThreadInList();
        myThread.start();
        Iterator<Integer>it=l.iterator();
        while(it.hasNext()){
            int v=it.next();
//            if(v==3){
//                l.remove(3);
//            }
            System.out.println("Main thread iterating list and current object is "+ v);
            Thread.sleep(2000);
        }
        System.out.println(l);
    }
}
