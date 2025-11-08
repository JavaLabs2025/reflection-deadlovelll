package org.example;

import org.example.generator.Generator;

import java.lang.reflect.Field;

public class GenerateExample {
    public static void main(String[] args) {
        var gen = new Generator();
        try {
            int depth = 0;
            Object generated = gen.generateValueOfType(org.example.classes.Shape.class, depth);
            if (generated == null) {
                System.out.println("Generated object is null");
                return;
            }
            System.out.println("Generated " + generated);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}