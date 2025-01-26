package com.alioth4j.reflect;

/**
 * Thrown when an exception occurs during the usage of {@link com.alioth4j.reflect.Reflect}.
 * <p>
 * It is an encapsulation of the reflection-related exceptions, providing an unified way to handle exceptions.
 * <p>
 * As reflection is an unsafe mechanism itself, the safety should be guaranteed by the user.
 *
 * @author Alioth4J
 */
public class ReflectException extends RuntimeException {

    public ReflectException() {
        super();
    }

    public ReflectException(String message) {
        super(message);
    }

    public ReflectException(Throwable cause) {
        super(cause);
    }

    public ReflectException(String message, Throwable cause) {
        super(message, cause);
    }

}
