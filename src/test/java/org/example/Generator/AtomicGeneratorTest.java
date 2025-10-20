package org.example.Generator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import main.java.org.example.generator.AtomicGenerator;
import org.junit.jupiter.api.Test;

public class AtomicGeneratorTest {

    private AtomicGenerator atomicGenerator;

    @BeforeEach
    void setUp() { atomicGenerator = new AtomicGenerator();}

    @Test
    public void test_generates_atomic_int()
    {
        Serializable atomicInt = (Serializable) atomicGenerator.generate(AtomicInteger.class);
        Assertions.assertNotNull(atomicInt);
        Assertions.assertEquals(AtomicInteger.class, atomicInt.getClass());
    }

    @Test
    public void test_generates_atomic_long()
    {
        Serializable atomicLong = (Serializable) atomicGenerator.generate(AtomicLong.class);
        Assertions.assertNotNull(atomicLong);
        Assertions.assertEquals(AtomicLong.class, atomicLong.getClass());
    }

    @Test
    public void test_generates_atomic_bool()
    {
        Serializable atomicBoolean = (Serializable) atomicGenerator.generate(AtomicBoolean.class);
        Assertions.assertNotNull(atomicBoolean);
        Assertions.assertEquals(AtomicBoolean.class, atomicBoolean.getClass());
    }

    @Test
    public void test_generates_atomic_reference()
    {
        Serializable atomicReference = (Serializable) atomicGenerator.generate(AtomicReference.class);
        Assertions.assertNotNull(atomicReference);
        Assertions.assertEquals(AtomicReference.class, atomicReference.getClass());
    }
}
