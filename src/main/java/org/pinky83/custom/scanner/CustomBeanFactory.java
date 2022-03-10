package org.pinky83.custom.scanner;

import org.pinky83.custom.CustomInject;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomBeanFactory {
    private static final Map<String, Object> customBeanMap = new HashMap<>();

    //TODO refactor it to looks like real factory
    public static void addBean(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object instance = BeanUtils.instantiateClass(clazz);
        customBeanMap.put(clazz.getName(), instance);
        injectComponents(clazz);
    }

    public static Object getBean(String name) {
        return customBeanMap.get(name);
    }

    private static void injectComponents(Class<?> clazz) {
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            Class<?> fieldClass = null;
            try {
                fieldClass = Class.forName(field.getType().getCanonicalName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (field.isAnnotationPresent(CustomInject.class)) {
                Object bean = customBeanMap.get(Objects.requireNonNull(fieldClass).getName());
                if (bean != null) {
                    try {
                        field.set(customBeanMap.get(clazz.getName()), bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    bean = BeanUtils.instantiateClass(Objects.requireNonNull(fieldClass));
                    customBeanMap.put(fieldClass.getName(), bean);
                    try {
                        field.set(customBeanMap.get(clazz.getName()), bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
