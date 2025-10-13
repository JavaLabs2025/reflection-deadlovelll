package org.example.generator;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Generator {

    private final Random random = new Random();

    public Object generateValueOfType(Class<?> clazz) throws
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException
    {
        // Интерфейсе или абстрактные классы скипаем
        if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
            return null;
        }
        // Для интов генерируем рандомный инт
        if (clazz.equals(int.class) || clazz.equals(Integer.class)) {return random.nextInt(1000);}
        // Для даблов генерируем рандомный дабл
        if (clazz.equals(double.class) || clazz.equals(Double.class)) {return random.nextDouble();}
        // Если строка генерируем строку с префиксом в виде рандомного числа
        if (clazz.equals(String.class)) {return "some_string_"+random.nextInt(1000);}
        // Если буль - рандомный буль
        if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {return random.nextBoolean();}
        // Если плавающая точка - рандомную плавающую точка
        if (clazz.equals(float.class) || clazz.equals(Float.class)) {return random.nextFloat();}
        // Если лонг - генерируем рандомный лонг
        if (clazz.equals(long.class) || clazz.equals(Long.class)) {return random.nextLong();}
        // Если большое число - генерируем рандомное большое число
        if (clazz.equals(BigDecimal.class)) {return new BigDecimal(random.nextDouble());}
        // Если большой инт - генерируем большой инт
        if (clazz.equals(BigInteger.class)) {return new BigInteger(String.valueOf(random.nextLong()));}
        // Если символ - рандомный символ
        if (clazz.equals(char.class) || clazz.equals(Character.class)) {return (char) ('a' + random.nextInt(26));}
        // Если шорт - генерируем рандомный шорт
        if (clazz.equals(short.class) || clazz.equals(Short.class)) {return (short) random.nextInt(Short.MAX_VALUE);}
        // Если байт - генерируем рандомный байт
        if (clazz.equals(byte.class) || clazz.equals(Byte.class)) {return (byte) random.nextInt(Byte.MAX_VALUE);}
        // Если дата - генерим текущую дату
        if (clazz.equals(Date.class)) {return new Date();}

        // Обработка атомиков
        if (AtomicInteger.class.equals(clazz)) {return new AtomicInteger(random.nextInt(Integer.MAX_VALUE));}
        if (AtomicLong.class.equals(clazz)) {return new AtomicLong(random.nextInt(Integer.MAX_VALUE));}
        if (AtomicBoolean.class.equals(clazz)) {return new AtomicBoolean(random.nextBoolean());}
        if (AtomicReference.class.equals(clazz)) {return new AtomicReference<>(random.nextLong());}


        // Если последовательнсть то генерируем с рандомным количеством элементов
        if (clazz.isArray()) {
            int randomIndex = random.nextInt(100);
            Object arr = Array.newInstance(clazz.getComponentType(), randomIndex);
            for (int i = 0; i < randomIndex; i++) {
                Array.set(arr, i, generateValueOfType(clazz.getComponentType()));
            }
            return arr;
        }
        // Если мапа - генерируем либо обычную мапу, либо ее конкурентную версию
        if (Map.class.isAssignableFrom(clazz)) {
            Map<Object, Object> map;
            if (ConcurrentHashMap.class.isAssignableFrom(clazz)) {
                map = new ConcurrentHashMap<>();
            } else {
                map = new HashMap<>();
            }
            return map;
        }
        // Если обьект - очередь, начинаем перебор по очередям
        if (Queue.class.isAssignableFrom(clazz)) {
            Queue<Object> queue;
            // Блокирующие очереди
            if (BlockingQueue.class.isAssignableFrom(clazz)) {
                if (ArrayBlockingQueue.class.isAssignableFrom(clazz)) {
                    queue = new ArrayBlockingQueue(10);
                } else if  (LinkedBlockingQueue.class.isAssignableFrom(clazz)) {
                    queue = new LinkedBlockingQueue(10);
                }
                // Конкурентная очередь
            } else if (ConcurrentLinkedQueue.class.isAssignableFrom(clazz)) {
                queue = new ConcurrentLinkedQueue();
            } else {
                // Обычная очередь
                queue = new LinkedList<>();
            }
            int size = random.nextInt(5) + 1;
            for (int i = 0; i < size; i++) {
                queue.add("element_" + random.nextInt(1000));
            }
            return queue;
        }
        // Если enum - возвращаем рандомный енам
        if (clazz.isEnum()) {
            Object[] arr = clazz.getEnumConstants();
            return arr[random.nextInt(arr.length)];
        }
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        Class<?>[] paramTypes = constructor.getParameterTypes();

        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            params[i] = generateValueOfType(paramTypes[i]);
        }

        constructor.setAccessible(true);
        Object instance = constructor.newInstance(params);
        return instance;
    }
}
