package org.example.generator;

import main.java.org.example.generator.*;
import org.example.annotation.Generatable;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;


public class Generator {

    private final Random random = new Random();
    private final PrimitiveGenerator primitiveGenerator = new PrimitiveGenerator();
    private final AtomicGenerator atomicGenerator = new AtomicGenerator();
    private final QueueGenerator queueGenerator = new QueueGenerator();
    private final SequenceGenerator sequenceGenerator = new SequenceGenerator();
    private final MapGenerator mapGenerator = new MapGenerator();
    private static final int MAX_DEPTH = 10;

    public Object generateValueOfType(Class<?> clazz, int depth) throws
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchMethodException
    {
        if (depth > MAX_DEPTH) {
            return null;
        }
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
        Object mapValue = mapGenerator.generate(clazz);
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
        Object generatedType = generateType(clazz, depth+1);
        return generatedType;
    }

    private Object generateType(Class<?> clazz, int depth) throws
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchMethodException
    {
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        boolean annotation = clazz.isAnnotationPresent(Generatable.class);
        if (annotation) {
            Class<?>[] paramTypes = constructor.getParameterTypes();
            Object[] params = new Object[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i++) {
                params[i] = generateValueOfType(paramTypes[i], depth+1);
            }
            constructor.setAccessible(true);
            Object instance = constructor.newInstance(params);
            return instance;
        } else {
            throw new IllegalArgumentException("Class " + clazz.getName() + " has no Generatable annotation");
        }
    }
}
