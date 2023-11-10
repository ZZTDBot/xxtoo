package com.tg.base.tb.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合工具类
 */
public class CollecttionUtils {

    /**
     * 将一个一维集合转换成一个二位集合
     * @param list      一维集合
     * @param width     二维集合子集合的长度
     * @return          二维集合
     * @param <T>       数据类型
     */
    public static <T> List<List<T>> listToListList(List<T> list , int width){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        List<List<T>> fuList = new ArrayList<>();
        List<T> zi = new ArrayList<>();
        fuList.add(zi);

        for (int i = 0; i < list.size() ; i++){
            if(i%width == 0){
                zi = new ArrayList<>();
                fuList.add(zi);
            }
            zi.add(list.get(i));
        }
        return fuList;
    }
}
