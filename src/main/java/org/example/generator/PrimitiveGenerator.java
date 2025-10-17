package main.java.org.example.generator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Supplier;

public class PrimitiveGenerator {
    private final Random random = new Random();
    private final Map<Class<?>, Supplier<Object>> generators = new HashMap<>();

    public PrimitiveGenerator() {
        generators.put(int.class, () -> random.nextInt(1000));
        generators.put(Integer.class, () -> random.nextInt(1000));
        generators.put(double.class, () -> random.nextDouble());
        generators.put(Double.class, () -> random.nextDouble());
        generators.put(String.class, () -> "some_string_" + random.nextInt(1000));
        generators.put(boolean.class, () -> random.nextBoolean());
        generators.put(Boolean.class, () -> random.nextBoolean());
        generators.put(float.class, () -> random.nextFloat());
        generators.put(Float.class, () -> random.nextFloat());
        generators.put(long.class, () -> random.nextLong());
        generators.put(Long.class, () -> random.nextLong());
        generators.put(BigDecimal.class, () -> new BigDecimal(random.nextDouble()));
        generators.put(BigInteger.class, () -> new BigInteger(String.valueOf(random.nextLong())));
        generators.put(char.class, () -> (char) ('a' + random.nextInt(26)));
        generators.put(Character.class, () -> (char) ('a' + random.nextInt(26)));
        generators.put(short.class, () -> (short) random.nextInt(Short.MAX_VALUE));
        generators.put(Short.class, () -> (short) random.nextInt(Short.MAX_VALUE));
        generators.put(byte.class, () -> (byte) random.nextInt(Byte.MAX_VALUE));
        generators.put(Byte.class, () -> (byte) random.nextInt(Byte.MAX_VALUE));
        generators.put(Date.class, Date::new);
    }

    public Object generate(Class<?> clazz)
    {
        Supplier<Object> supplier = generators.get(clazz);
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }
}
