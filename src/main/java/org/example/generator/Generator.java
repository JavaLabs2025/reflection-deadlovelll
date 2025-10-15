package org.example.generator;

import main.java.org.example.generator.AtomicGenerator;
import main.java.org.example.generator.PrimitiveGenerator;
import main.java.org.example.generator.QueueGenerator;
import main.java.org.example.generator.SequenceGenerator;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;


public class Generator {

    private final Random random = new Random();
    private final PrimitiveGenerator primitiveGenerator = new PrimitiveGenerator();
    private final AtomicGenerator atomicGenerator = new AtomicGenerator();
    private final QueueGenerator queueGenerator = new QueueGenerator();
    private final SequenceGenerator sequenceGenerator = new SequenceGenerator();

    public Object generateValueOfType(Class<?> clazz) throws
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException
    {
        Object primitiveValue = primitiveGenerator.generate(clazz);
        if (primitiveValue != null) {
            return primitiveValue;
        }
        Object atomicValue = atomicGenerator.generate(clazz);
        if (atomicValue != null) {
            return atomicValue;
        }
        Object queueValue = queueGenerator.generate(clazz);
        if (queueValue != null) {
            return queueValue;
        }
        Object sequenceValue = sequenceGenerator.generate(clazz);
        if (sequenceValue != null) {
            return sequenceValue;
        }
        Object mapValue = generateMap(clazz);
        if (mapValue != null) {
            return mapValue;
        }
        if (clazz.isEnum()) {
            Object[] arr = clazz.getEnumConstants();
            return arr[random.nextInt(arr.length)];
        }
        if (clazz.isInterface()) {
            return null;
        }
        Object generatedType = generateType(clazz);
        return generatedType;
    }

    private Object generateMap(Class<?> clazz)
    {
        if (Map.class.isAssignableFrom(clazz)) {
            Map<Object, Object> map;
            if (ConcurrentHashMap.class.isAssignableFrom(clazz)) {
                map = new ConcurrentHashMap<>();
            } else {
                map = new HashMap<>();
            }
            return map;
        }
        return null;
    }

    private Object generateType(Class<?> clazz) throws
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException
    {
        String simpleName = clazz.getSimpleName();
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        Class<?>[] paramTypes = constructor.getParameterTypes();
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
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
