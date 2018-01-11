package com.alphaz.util.extension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.util.extension
 * User: C0dEr
 * Date: 2017/7/21
 * Time: 上午10:10
 * Description:This is a class of com.alphaz.util.extension
 */
public class StreamPredicate {
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
