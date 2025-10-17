package main.java.org.example.generator;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Supplier;

public class SequenceGenerator {
    private final Random random = new Random();
    private final Map<Class<?>, Supplier<?>> sequnceGenerator = new HashMap<>();

    public SequenceGenerator() {
        sequnceGenerator.put(List.class, this::generateList);
        sequnceGenerator.put(ArrayList.class, this::generateList);
        sequnceGenerator.put(LinkedList.class, this::generateArray);
    }

    public Object generate(Class<?> clazz) {
        Supplier<?> supplier = sequnceGenerator.get(clazz);
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    private List<Object> generateList() {
        List<Object> list = new ArrayList<>();
        int num = random.nextInt(1000);
        for (int i = 0; i < num; i++) {
            list.add(new Object());
        }
        return list;
    }

    private Object generateArray() {
        int randomIndex = random.nextInt(100);
        Object arr = Array.newInstance(Object.class, randomIndex);
        for (int i = 0; i < randomIndex; i++) {
            Array.set(arr, i, new Object());
        }
        return arr;
    }
}
