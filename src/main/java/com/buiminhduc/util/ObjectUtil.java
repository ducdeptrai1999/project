package com.buiminhduc.util;

import com.buiminhduc.common.annotation.Entity;
import com.buiminhduc.common.annotation.Id;
import com.buiminhduc.model.entity.UserEntity;
import com.buiminhduc.model.request.UserRequest;
import com.buiminhduc.model.respond.UserResponse;
import com.buiminhduc.repository.impl.UserRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ObjectUtil {

    public static Object getMethod(Object object, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String fieldName = field.getName();
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        String getMethodName = "get" + fieldName;

        Method method = object.getClass().getMethod(getMethodName);

        return method.invoke(object);
    }

    public static void setMethod(Object object, Field field, Object fieldData) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(object, fieldData);
    }

    public static Field getFieldByName(Class clazz, String fieldName) throws NoSuchFieldException {
        return clazz.getDeclaredField(fieldName);
    }

    public static void copyProperties(Object source, Object destination) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field[] fields = source.getClass().getDeclaredFields();
        Field[] fieldsDestination = destination.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fieldsDestination.length; j++) {
                if (fields[i].getName().equals(fieldsDestination[j].getName())) {
                    Object fieldData = getMethod(source, fields[i]);
                    setMethod(destination, fieldsDestination[j], fieldData);
                }
            }
        }
    }
    public static Object map(Class<?> tClass, ResultSet resultSet) throws IllegalAccessException, InstantiationException, NoSuchFieldException, SQLException {
        Field[] fields = tClass.getDeclaredFields();
        Object object = tClass.newInstance();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Id.class)) {
                setMethod(object, fields[i], resultSet.getObject(AnnotationUtil.getPrimarykey(tClass, fields[i].getName())));
            } else {
                if (fields[i].getType().getName().equals("java.time.LocalTime")) {
                    setMethod(object, fields[i], resultSet.getTime(AnnotationUtil.getFields(tClass, fields[i].getName())).toLocalTime());
                    continue;
                } else {
                    setMethod(object, fields[i], resultSet.getObject(AnnotationUtil.getFields(tClass, fields[i].getName())));
                }
            }
        }
        return object;
    }
    public static Map<String , Object> convertToMap(Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String , Object> filter = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields){
            if (getMethod(object, field) != null)
            filter.put(field.getName() , getMethod(object, field));
        }
        return filter;
    }
    public static UserRequest convertParameterU(HttpServletRequest req, int roleID){
        String username= req.getParameter("username");
        String pass= req.getParameter("password");
        String phone= req.getParameter("phone");
        String email = req.getParameter("email");
        UserRequest request = new UserRequest(username,pass,phone,email,roleID);
        return request;
    }

}
