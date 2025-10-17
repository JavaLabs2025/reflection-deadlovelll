package main.java.org.example.generator;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

public class QueueGenerator {

    private final Random random = new Random();
    private final Map<Class<?>, Supplier<Object>> queueGenerator = new HashMap<>();

    public QueueGenerator()
    {
        queueGenerator.put(ArrayBlockingQueue.class, () -> {
            Queue<Object> q = new ArrayBlockingQueue<>(10);
            fillQueue(q);
            return q;
        });
        queueGenerator.put(LinkedBlockingQueue.class, () -> {
            Queue<Object> q = new LinkedBlockingQueue<>(10);
            fillQueue(q);
            return q;
        });
        queueGenerator.put(ConcurrentLinkedQueue.class, () -> {
            Queue<Object> q = new LinkedBlockingQueue<>(10);
            fillQueue(q);
            return q;
        });
        queueGenerator.put(ConcurrentLinkedQueue.class, () -> {
            Queue<Object> q = new ConcurrentLinkedQueue<>();
            fillQueue(q);
            return q;
        });
        queueGenerator.put(LinkedList.class, () -> {
            Queue<Object> q = new LinkedList<>();
            fillQueue(q);
            return q;
        });
    }

    private void fillQueue(Queue<Object> queue)
    {
        int size = random.nextInt(1000) + 1;
        for (int i = 0; i < size; i++) {
            queue.add(new Object());
        }
    }

    public Object generate(Class<?> clazz)
    {
        Supplier<Object> supplier = queueGenerator.get(clazz);
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }
}
