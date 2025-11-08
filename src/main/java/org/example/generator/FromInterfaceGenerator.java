package org.example.generator;

import net.bytebuddy.ByteBuddy;

import java.lang.reflect.InvocationTargetException;

public class FromInterfaceGenerator {

    public Object generate(Class<?> clazz) throws
            ClassNotFoundException,
            InvocationTargetException,
            IllegalAccessException,
            NoSuchMethodException,
            InstantiationException
    {
        String className = clazz.getName();
        Class<?> interfaceClass = Class.forName(className);
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .implement(interfaceClass)
                .make()
                .load(clazz.getClassLoader())
                .getLoaded();
        Object instance = dynamicType.getDeclaredConstructor().newInstance();
        boolean implementsInterface = clazz.isAssignableFrom(instance.getClass());
        System.out.println("Implements interface? " + implementsInterface);
        return instance;
    }
}
