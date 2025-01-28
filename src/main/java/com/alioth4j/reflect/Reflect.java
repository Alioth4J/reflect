package com.alioth4j.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * The encapsulation of java reflection api.
 * <p>
 * Enable method chaining.
 *
 * @param <I> the type of instance to be constructed
 * @author Alioth4J
 */
public class Reflect<I> {

    private Class<? extends I> clazz;

    private I instance;


    public Reflect() {
    }

    public Reflect(Class<? extends I> clazz, Class<?>[] constructorParameterTypes, Object[] constructorParameterValues) {
        this.clazz = clazz;
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(constructorParameterTypes);
            constructor.setAccessible(true);
            this.instance = (I) constructor.newInstance(constructorParameterValues);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public Reflect(I existingObject) {
        this.clazz = (Class<? extends I>) existingObject.getClass();
        this.instance = existingObject;
    }


    public Reflect<I> clazz(Class<? extends I> clazz) {
        if (this.clazz != null) {
            throw new ReflectException("The clazz has already been set.");
        }
        this.clazz = clazz;
        return this;
    }

    public Reflect<I> construct(Class<?>[] parameterTypes, Object[] parameterValues) {
        if (this.instance != null) {
            throw new ReflectException("The instance already exists.");
        }
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            this.instance = (I) constructor.newInstance(parameterValues);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
        return this;
    }

    public Reflect<I> exist(I existingObject) {
        if (this.clazz != null || this.instance != null) {
            if (this.instance != null) {
                throw new ReflectException("The instance already exists");
            } else {
                throw new ReflectException("The clazz has already been set.");
            }
        }
        this.clazz = (Class<? extends I>) existingObject.getClass();
        this.instance = existingObject;
        return this;
    }

    public Reflect<I> set(String fieldName, Object fieldValue) {
        if (this.instance == null) {
            throw new ReflectException("The instance does not exist.");
        }
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, fieldValue);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
        return this;
    }

    public Reflect<I> set(Map<String/* fieldName */, Object/* fieldValue */> setterMap) {
        if (this.instance == null) {
            throw new ReflectException("The instance does not exist.");
        }
        for (Map.Entry<String, Object> entry : setterMap.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(instance, fieldValue);
            } catch (Exception e) {
                throw new ReflectException(e);
            }
        }
        return this;
    }

    public Reflect<I> invoke(String methodName, Class<?>[] parameterTypes, Object[] parameterValues) {
        if (this.instance == null) {
            throw new ReflectException("The instance does not exist");
        }
        try {
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            method.invoke(instance, parameterValues);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
        return this;
    }

    public I reflect() {
        return this.instance;
    }

}
