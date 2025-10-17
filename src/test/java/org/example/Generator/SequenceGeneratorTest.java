package org.example.Generator;

import main.java.org.example.generator.SequenceGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SequenceGeneratorTest {
    private SequenceGenerator sequenceGenerator;

    @BeforeEach
    public void setUp() {
        sequenceGenerator = new SequenceGenerator();
    }

    @Test
    public void test_generate_list()
    {
        Object someArray = sequenceGenerator.generate(List.class);
        Assertions.assertNotNull(someArray);
        Assertions.assertNotNull(someArray.getClass());
        Assertions.assertTrue(someArray instanceof ArrayList);
    }

    @Test
    public void test_generate_arraylist()
    {
        Object someArray = sequenceGenerator.generate(ArrayList.class);
        Assertions.assertNotNull(someArray);
        Assertions.assertNotNull(someArray.getClass());
        Assertions.assertTrue(someArray instanceof ArrayList);
    }

    @Test
    public void test_generate_linkedlist()
    {
        Object someLinkedList = sequenceGenerator.generate(LinkedList.class);
        Assertions.assertNotNull(someLinkedList);
        Assertions.assertNotNull(someLinkedList.getClass());
        Assertions.assertTrue(someLinkedList.getClass().isArray());
    }

    @Test
    public void test_generate_array_is_null()
    {
        Object someLinkedList = sequenceGenerator.generate(Array.class);
        Assertions.assertNull(someLinkedList);
    }
}
