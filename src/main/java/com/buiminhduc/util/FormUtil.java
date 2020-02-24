package com.buiminhduc.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtil {
    public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
        try {
            T object = tClass.newInstance();
            BeanUtils.populate(object, request.getParameterMap());
            return object;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }
}
