package com.buiminhduc.autowire;


import com.buiminhduc.common.annotation.Component;
import com.buiminhduc.common.annotation.Repository;
import com.buiminhduc.common.annotation.Service;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanFactory {
    public static Map<String, Object> beans;

    static {
        synchronized (BeanFactory.class) {
            beans = new HashMap<>();
            createBean();
        }
    }

    public static synchronized void createBean() {
        Reflections reflection = new Reflections("com.buiminhduc");
        Set<? extends Class> repository = reflection.getTypesAnnotatedWith(Repository.class);
        Set<? extends Class> services = reflection.getTypesAnnotatedWith(Service.class);

        repository.forEach(s -> {
            try {
                String name = s.getSimpleName().substring(0, 1).toLowerCase() + s.getSimpleName().substring(1, s.getSimpleName().lastIndexOf("Impl"));
                beans.put(name, s.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        services.forEach(s -> {
            try {
                String name = s.getSimpleName().substring(0, 1).toLowerCase() + s.getSimpleName().substring(1, s.getSimpleName().lastIndexOf("Impl"));
                beans.put(name, s.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static Map<String, Object> getBeans() {
        return beans;
    }


}

