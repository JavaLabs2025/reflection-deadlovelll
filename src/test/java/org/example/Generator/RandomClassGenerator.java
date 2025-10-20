package org.example.Generator;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Modifier;
import java.util.Random;

public class RandomClassGenerator {
    private final Random random = new Random();

    public Class<?> getRandomClass() {
        String randomClassName = "org.example.SomeRandomClass_" + random.nextInt(100000);
        Class<?> dynamicClass = new ByteBuddy()
                .subclass(Object.class)
                .name(randomClassName)
                .defineField("someString", String.class, Modifier.PUBLIC)
                .defineField("someInt", int.class, Modifier.PUBLIC)
                .defineField("someBoolean", Boolean.class, Modifier.PUBLIC)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();
        return dynamicClass;
    }
}
