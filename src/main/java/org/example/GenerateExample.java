package org.example;

import org.example.generator.Generator;

import java.lang.reflect.Field;

public class GenerateExample {
    public static void main(String[] args) {
        var gen = new Generator();
        try {
            Object generated = gen.generateValueOfType(org.example.classes.Cart.class);
            if (generated == null) {
                System.out.println("Generated object is null");
                return;
            }
            System.out.println("Generated " + generated);
            System.out.println("With attributes:");
            Field[] fields = generated.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.println(
                        "Field " + field.getName() + " with type " + field.getType() + " " + field.get(generated)
                );
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}