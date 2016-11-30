package com.rjxx.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 主要用于新旧bean转换
 * Created by Administrator on 2016/10/9.
 */
public class BeanConvertUtils {

    /**
     * 对象转换
     *
     * @param oldList
     * @return
     */
    public static <T> List<T> convertList(List<? extends Object> oldList,Class<T> tClass) throws Exception {
        List<T> list = new ArrayList<>();
        for (Object old : oldList) {
            T object = tClass.newInstance();
            BeanUtils.copyProperties(object,old);
            list.add(object);

        }
        return list;
    }


}
