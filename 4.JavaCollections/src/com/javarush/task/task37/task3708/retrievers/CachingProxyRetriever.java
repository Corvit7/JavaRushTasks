package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

//import java.util.HashMap;
//import java.util.Map;

public class CachingProxyRetriever implements Retriever{

    Storage storage;

    OriginalRetriever retriever;

    LRUCache<Long, Object> cache = new LRUCache<>(10);

    public CachingProxyRetriever(Storage storage) {
        this.storage = storage;
        retriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
//        Object res;
//        if (cache.containsKey(id))
//             res = cache.get(id);
//        else {
//            res = storage.get(id);
//            cache.put(id, res);
//        }
//        return res;

        Object res;
        if(cache.find(id) != null)
            res = cache.find(id);
        else
        {
            res = retriever.retrieve(id);
            cache.set(id, res);
        }
        return res;
    }
}
