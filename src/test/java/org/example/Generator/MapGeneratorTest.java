package org.example.Generator;

import main.java.org.example.generator.MapGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class MapGeneratorTest {

    private MapGenerator mapGenerator;

    @BeforeEach
    void setUp() {
        mapGenerator = new MapGenerator();
    }

    @Test
    void test_generate_map()
    {
        Map<String, Object> map = mapGenerator.generate(HashMap.class);
        Assertions.assertNotNull(map);
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map instanceof HashMap);
    }

    @Test
    void test_generate_concurrent_map()
    {
        Map<String, Object> map = mapGenerator.generate(ConcurrentHashMap.class);
        Assertions.assertNotNull(map);
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map instanceof ConcurrentHashMap);
    }

    @Test
    void test_generate_treemap()
    {
        Map<String, Object> map = mapGenerator.generate(TreeMap.class);
        Assertions.assertNotNull(map);
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map instanceof TreeMap);
    }

    @Test
    void test_generate_linked_hashmap()
    {
        Map<String, Object> map = mapGenerator.generate(LinkedHashMap.class);
        Assertions.assertNotNull(map);
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map instanceof LinkedHashMap);
    }

    @Test
    void test_generate_weak_hashmap()
    {
        Map<String, Object> map = mapGenerator.generate(WeakHashMap.class);
        Assertions.assertNotNull(map);
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map instanceof WeakHashMap);
    }

    @Test
    void test_generate_identity_map()
    {
        Map<String, Object> map = mapGenerator.generate(IdentityHashMap.class);
        Assertions.assertNotNull(map);
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map instanceof IdentityHashMap);
    }
}
