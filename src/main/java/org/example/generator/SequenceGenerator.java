package main.java.org.example.generator;

import java.util.*;
import java.util.function.Supplier;

public class SequenceGenerator {

    private final Random random = new Random();
    private final Map<Class<?>, Supplier<Object>> generators = new HashMap<>();

    public SequenceGenerator() {
        generators.put(List.class, () -> {
            List<Object> list = new ArrayList<>();
            int randomIndex = random.nextInt(100);
            for (int i = 0; i < randomIndex; i++) {
                Object obj = generateValueOfType(clazz.getClass());
                list.add(obj);
            }
            return list;
        });
    }
}
