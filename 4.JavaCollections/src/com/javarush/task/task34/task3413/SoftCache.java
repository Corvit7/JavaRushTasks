package com.javarush.task.task34.task3413;

import com.sun.org.apache.xalan.internal.res.XSLTErrorResources;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
//        SoftReference<AnyObject> softReference = cacheMap.get(key);

        //напишите тут ваш код
        if(cacheMap.containsKey(key))
            return cacheMap.get(key).get();
        else
            return null;
    }

    public AnyObject put(Long key, AnyObject value) {
//        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        //напишите тут ваш код
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        cacheMap.put(key, new SoftReference<>(value));
        AnyObject anyObject;

        if (softReference != null)
        {
            anyObject = softReference.get();
            softReference.clear();
            return anyObject;
        }
        else
            return null;
    }

    public AnyObject remove(Long key) {
//        SoftReference<AnyObject> softReference = cacheMap.remove(key);

//        SoftReference<AnyObject> softReference;
//        //напишите тут ваш код
//        if (!cacheMap.containsKey(key))
//        {
//            cacheMap.remove(key);
//            return null;
//        }
//        else {
//            softReference = cacheMap.get(key);
//            cacheMap.remove(key);
//            return softReference.get();
//        }

        SoftReference<AnyObject> softReference = cacheMap.get(key);
        AnyObject anyObject;

        if (softReference != null)
        {
            anyObject = softReference.get();
            cacheMap.remove(key);
            softReference.clear();
            return anyObject;
        }
        else
        {
            cacheMap.remove(key);
            return null;
        }

    }
}