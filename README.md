# com.alioth4j.reflect

Use reflection like the Builder pattern.

## Usage
```
MyClass myInstance = new Reflect<MyClass>()
                .clazz(MyClass.class)
                .construct(Class<?>[] parameterTypes, Object[] parameterValues)
                .set(String fieldName, Object fieldValue) | .set(Map<String, Object> parameterMap)
                .invoke(String methodName, Class<?>[] parameterTypes, Object[] parameterValues)
                .reflect();
```
