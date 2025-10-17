package main.java.org.example.generator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class MapGenerator {
    private final Random random = new Random();
    private final Map<Class<?>, Supplier<Object>> generators = new HashMap<>();

    public MapGenerator() {
        generators.put(HashMap.class, () -> {
            HashMap<String, Object> map = new HashMap<>();
            fillMap(map);
            return map;
        });
        generators.put(LinkedHashMap.class, () -> {
            LinkedHashMap<String , Object> map = new LinkedHashMap<>();
            fillMap(map);
            return map;
        });
        generators.put(TreeMap.class, () -> {
            TreeMap<String, Object> map = new TreeMap<>();
            fillMap(map);
            return map;
        });
        generators.put(ConcurrentHashMap.class, () -> {
            ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
            fillMap(map);
            return map;
        });
        generators.put(WeakHashMap.class, () -> {
            WeakHashMap<String, Object> map = new WeakHashMap<>();
            fillMap(map);
            return map;
        });
        generators.put(IdentityHashMap.class, () -> {
            IdentityHashMap<String, Object> map = new IdentityHashMap<>();
            fillMap(map);
            return map;
        });
    }

    private void fillMap(Map<String, Object> map)
    {
        int randomSize = random.nextInt(100);
        for (int i = 0; i < randomSize; i++) {
            map.put(String.valueOf(i), new Object());
        }
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
