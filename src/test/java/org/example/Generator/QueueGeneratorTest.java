package org.example.Generator;

import main.java.org.example.generator.QueueGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueGeneratorTest {
    private QueueGenerator queueGenerator;

    @BeforeEach
    public void setUp() {
        queueGenerator = new QueueGenerator();
    }

    @Test
    public void test_generate_array_blocking_queue()
    {
        Object queue = queueGenerator.generate(ArrayBlockingQueue.class);
        Assertions.assertNotNull(queue);
        Assertions.assertTrue(queue instanceof ArrayBlockingQueue);
    }

    @Test
    public void test_generate_linked_blocking_queue()
    {
        Object queue = queueGenerator.generate(LinkedBlockingQueue.class);
        Assertions.assertNotNull(queue);
        Assertions.assertTrue(queue instanceof LinkedBlockingQueue);
    }

    @Test
    public void test_generate_concurrent_blocking_queue()
    {
        Object queue = queueGenerator.generate(ConcurrentLinkedQueue.class);
        Assertions.assertNotNull(queue);
        Assertions.assertTrue(queue instanceof ConcurrentLinkedQueue);
    }
}
