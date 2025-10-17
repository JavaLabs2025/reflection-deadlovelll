package main.java.org.example.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class AtomicGenerator {
    private final Random random = new Random();
    private final Map<Class<?>, Supplier<Object>> generators;

    public AtomicGenerator()
    {
        generators = new HashMap<>();
        generators.put(AtomicInteger.class, () -> new AtomicInteger(random.nextInt(Integer.MAX_VALUE)));
        generators.put(AtomicLong.class, () -> new AtomicLong(random.nextInt(Integer.MAX_VALUE)));
        generators.put(AtomicBoolean.class, () -> new AtomicBoolean(random.nextBoolean()));
        generators.put(AtomicReference.class, () -> new AtomicReference<>(random.nextInt()));
    }

    public Object generate(Class<?> clazz)
    {
        Supplier<Object> supplier = generators.get(clazz);
        if (supplier == null) {
            return null;
        }
        return supplier.get();
    }
}
