package com.djp.cache.redis;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {


    public static void main(String[] args) throws InterruptedException {
        Map<Object,Object> whm=new WeakHashMap<>();

//        for (int i = 0; i < 1000; i++) {
//            whm.put(i, i);
//            System.gc();
//            System.out.println("v" + whm.get(i));
//            System.out.println("Map size :" + whm.size());
//        }

        whm.put(String.valueOf(1),"test1");
        whm.put(String.valueOf(2),"test2");

        System.err.println("v1:"+whm.get("1"));
        System.err.println("v2:"+whm.get("2"));
        System.err.println(whm.size());

        //System.gc();
        System.err.println("=============================================");

        Thread.sleep(5000);

        System.err.println(whm.size());
        System.err.println("v1:"+whm.get("1"));
        System.err.println("v2:"+whm.get("2"));


    }

}
