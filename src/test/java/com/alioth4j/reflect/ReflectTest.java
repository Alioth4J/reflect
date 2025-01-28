package com.alioth4j.reflect;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReflectTest {

    @Test
    void testReflectWithNormalSetter() throws NoSuchFieldException, IllegalAccessException {
        TestClass testInstance = new Reflect<TestClass>()
                .clazz(TestClass.class)
                .construct(new Class<?>[0], new Object[0])
                .set("field1", "Hello World!")
                .reflect();

        assertNotNull(testInstance);

        Field field1Field = testInstance.getClass().getDeclaredField("field1");
        field1Field.setAccessible(true);
        String field1Value = (String) field1Field.get(testInstance);
        assertEquals("Hello World!", field1Value);
    }

    @Test
    void testReflectWithSetterMap() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> setterMap = new HashMap<>();
        setterMap.put("field1", "Hello World!");
        TestClass testInstance = new Reflect<TestClass>()
                .clazz(TestClass.class)
                .construct(new Class<?>[0], new Object[0])
                .set(setterMap)
                .reflect();

        assertNotNull(testInstance);

        Field field1Field = testInstance.getClass().getDeclaredField("field1");
        field1Field.setAccessible(true);
        String field1Value = (String) field1Field.get(testInstance);
        assertEquals("Hello World!", field1Value);
    }

    @Test
    void testReflectWithInvoke() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        TestClass testInstance = new Reflect<TestClass>()
                .clazz(TestClass.class)
                .construct(new Class<?>[0], new Object[0])
                .invoke("method1", new Class<?>[0], new Object[0])
                .reflect();
        System.setOut(System.out);

        assertNotNull(testInstance);

        String output = outputStream.toString().trim();
        assertEquals("successfully invoke", output);
    }

    @Test
    void testReflectWithClazzAndConstructorParametersConstructor() {
        TestClass testInstance = new Reflect<TestClass>(TestClass.class, new Class<?>[0], new Object[0]).reflect();

        assertNotNull(testInstance);
    }

    @Test
    void testReflectWithExistingObjectConstructor() {
        Integer existingObject = 1;
        Integer testInstance = new Reflect<Integer>(existingObject).reflect();

        assertEquals(1, testInstance);
    }

}
