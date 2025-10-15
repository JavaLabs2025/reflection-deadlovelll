package main.java.org.example.generator;

import java.lang.reflect.Constructor;

public class TypeGenerator {
    public Object generate(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        Class<?>[] paramTypes = constructor.getParameterTypes();
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            // Если аттрибуты обьекта являются типом обьекта -
            // ломаем цикл
            String paramName = paramTypes[i].getSimpleName();
            if (simpleName.equals(paramName)) {
                return null;
            }
            params[i] = generateValueOfType(paramTypes[i]);
        }

        constructor.setAccessible(true);
        Object instance = constructor.newInstance(params);
        return instance;
    }
}
