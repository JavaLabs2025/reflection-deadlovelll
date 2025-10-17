package org.example.Generator;

import main.java.org.example.generator.PrimitiveGenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class PrimitiveGeneratorTest {

    private PrimitiveGenerator primitiveGenerator;

    @BeforeEach
    public void setup()
    {
        primitiveGenerator = new PrimitiveGenerator();
    }

    @Test
    public void test_generate_int()
    {
        Object someInt = primitiveGenerator.generate(int.class);
        Assertions.assertNotNull(someInt);
        Assertions.assertEquals(Integer.class, someInt.getClass());
    }

    @Test
    public void test_generate_Integer()
    {
        Object someInt = primitiveGenerator.generate(Integer.class);
        Assertions.assertNotNull(someInt);
        Assertions.assertEquals(Integer.class, someInt.getClass());
    }

    @Test
    public void test_generate_string()
    {
        Object someString = primitiveGenerator.generate(String.class);
        Assertions.assertNotNull(someString);
        Assertions.assertEquals(String.class, someString.getClass());
    }

    @Test
    public void test_generate_long()
    {
        Object someLong = primitiveGenerator.generate(long.class);
        Assertions.assertNotNull(someLong);
        Assertions.assertEquals(Long.class, someLong.getClass());
    }

    @Test
    public void test_generate_Long()
    {
        Object someLong = primitiveGenerator.generate(Long.class);
        Assertions.assertNotNull(someLong);
        Assertions.assertEquals(Long.class, someLong.getClass());
    }

    @Test
    public void test_generate_boolean()
    {
        Object someBoolean = primitiveGenerator.generate(boolean.class);
        Assertions.assertNotNull(someBoolean);
        Assertions.assertEquals(Boolean.class, someBoolean.getClass());
    }

    @Test
    public void test_generate_Boolean()
    {
        Object someBoolean = primitiveGenerator.generate(Boolean.class);
        Assertions.assertNotNull(someBoolean);
        Assertions.assertEquals(Boolean.class, someBoolean.getClass());
    }

    @Test
    public void test_generate_double()
    {
        Object someDouble = primitiveGenerator.generate(double.class);
        Assertions.assertNotNull(someDouble);
        Assertions.assertEquals(Double.class, someDouble.getClass());
    }

    @Test
    public void test_generate_Double()
    {
        Object someDouble = primitiveGenerator.generate(Double.class);
        Assertions.assertNotNull(someDouble);
        Assertions.assertEquals(Double.class, someDouble.getClass());
    }

    @Test
    public void test_generate_float()
    {
        Object someFloat = primitiveGenerator.generate(float.class);
        Assertions.assertNotNull(someFloat);
        Assertions.assertEquals(Float.class, someFloat.getClass());
    }

    @Test
    public void test_generate_Float()
    {
        Object someFloat = primitiveGenerator.generate(Float.class);
        Assertions.assertNotNull(someFloat);
        Assertions.assertEquals(Float.class, someFloat.getClass());
    }

    @Test
    public void test_generate_short()
    {
        Object someShort = primitiveGenerator.generate(short.class);
        Assertions.assertNotNull(someShort);
        Assertions.assertEquals(Short.class, someShort.getClass());
    }

    @Test
    public void test_generate_Short()
    {
        Object someShort = primitiveGenerator.generate(Short.class);
        Assertions.assertNotNull(someShort);
        Assertions.assertEquals(Short.class, someShort.getClass());
    }

    @Test
    public void test_generate_char()
    {
        Object someChar = primitiveGenerator.generate(char.class);
        Assertions.assertNotNull(someChar);
        Assertions.assertEquals(Character.class, someChar.getClass());
    }

    @Test
    public void test_generate_Character()
    {
        Object someChar = primitiveGenerator.generate(Character.class);
        Assertions.assertNotNull(someChar);
        Assertions.assertEquals(Character.class, someChar.getClass());
    }

    @Test
    public void test_generate_byte()
    {
        Object someByte = primitiveGenerator.generate(byte.class);
        Assertions.assertNotNull(someByte);
        Assertions.assertEquals(Byte.class, someByte.getClass());
    }

    @Test
    public void test_generate_Byte()
    {
        Object someByte = primitiveGenerator.generate(byte.class);
        Assertions.assertNotNull(someByte);
        Assertions.assertEquals(Byte.class, someByte.getClass());
    }

    @Test
    public void test_generate_BigInter()
    {
        Object someBigInteger = primitiveGenerator.generate(BigInteger.class);
        Assertions.assertNotNull(someBigInteger);
        Assertions.assertEquals(BigInteger.class, someBigInteger.getClass());
    }

    @Test
    public void test_generate_BigDecimal()
    {
        Object someBigDecimal = primitiveGenerator.generate(BigDecimal.class);
        Assertions.assertNotNull(someBigDecimal);
        Assertions.assertEquals(BigDecimal.class, someBigDecimal.getClass());
    }

    @Test
    public void test_generate_Date()
    {
        Object someDate = primitiveGenerator.generate(Date.class);
        Assertions.assertNotNull(someDate);
        Assertions.assertEquals(Date.class, someDate.getClass());
    }
}

