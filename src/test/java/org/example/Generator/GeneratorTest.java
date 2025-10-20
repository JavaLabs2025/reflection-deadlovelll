package org.example.Generator;

import org.example.generator.Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


public class GeneratorTest {
    private Generator generator;
    private RandomClassGenerator randomClassGenerator;

    @BeforeEach
    void setUp()
    {
        generator = new Generator();
        randomClassGenerator = new RandomClassGenerator();
    }

    @Test
    public void generate_random_object() throws
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException
    {
        for (int i = 0; i < 100; i++)
        {
            Class<?> randomClass = randomClassGenerator.getRandomClass();
            Object newRandomClass = generator.generateValueOfType(randomClass);
            String randomClassFields = Arrays.toString(randomClass.getDeclaredFields());
            String randomNewClassFields = Arrays.toString(randomClass.getDeclaredFields());
            Assertions.assertNotNull(newRandomClass);
            Assertions.assertEquals(newRandomClass.getClass(), randomClass);
            Assertions.assertEquals(randomClassFields, randomNewClassFields);
        }
    }
}
