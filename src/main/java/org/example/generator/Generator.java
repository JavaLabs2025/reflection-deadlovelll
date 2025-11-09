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
    private final FromInterfaceGenerator fromInterfaceGenerator = new FromInterfaceGenerator();
    private static final int MAX_DEPTH = 10;

    public Object generateValueOfType(Type type, int depth) throws
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchMethodException,
            ClassNotFoundException
    {
        if (depth > MAX_DEPTH) {
            return null;
        }
        if (type instanceof Class<?> clazz) {
            Object primitiveValue = primitiveGenerator.generate(clazz);
            if (primitiveValue != null) return primitiveValue;

            Object atomicValue = atomicGenerator.generate(clazz);
            if (atomicValue != null) return atomicValue;

            Object queueValue = queueGenerator.generate(clazz);
            if (queueValue != null) return queueValue;

            if (Collection.class.isAssignableFrom(clazz)) {
                return sequenceGenerator.generate(clazz);
            }
            if (Map.class.isAssignableFrom(clazz)) {
                return mapGenerator.generate(clazz);
            }
            if (clazz.isEnum()) {
                Object[] arr = clazz.getEnumConstants();
                return arr[random.nextInt(arr.length)];
            }
            if (clazz.isInterface()) {
                return fromInterfaceGenerator.generate(clazz);
            }
            return generateType(clazz, depth + 1);
        }

        if (type instanceof ParameterizedType pType) {
            Type raw = pType.getRawType();
            if (!(raw instanceof Class<?> rawClass)) {
                throw new IllegalArgumentException("Unsupported raw type: " + raw);
            }
            if (Collection.class.isAssignableFrom(rawClass)) {
                Collection<Object> collection;
                if (rawClass.isInterface()) {
                    collection = new ArrayList<>();
                } else {
                    collection = (Collection<Object>) rawClass.getDeclaredConstructor().newInstance();
                }
                Type elementType = pType.getActualTypeArguments()[0];
                for (int i = 0; i < 3; i++) {
                    Object element = generateValueOfType(elementType, depth + 1);
                    collection.add(element);
                }
                return collection;
            }
            return generateType((Class<?>) rawClass, depth + 1);
        }
        throw new IllegalArgumentException("Unsupported type: " + type);
    }

    private Object generateType(Class<?> clazz, int depth) throws
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchMethodException,
            ClassNotFoundException
    {
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];

        if (!clazz.isAnnotationPresent(Generatable.class)) {
            throw new IllegalArgumentException("Class " + clazz.getName() + " has no Generatable annotation");
        }

        Type[] paramTypes = constructor.getGenericParameterTypes();
        Object[] params = new Object[paramTypes.length];

        for (int i = 0; i < paramTypes.length; i++) {
            params[i] = generateValueOfType(paramTypes[i], depth + 1);
        }

        constructor.setAccessible(true);
        return constructor.newInstance(params);
    }
}
