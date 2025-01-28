# com.alioth4j.reflect

Use reflection like the Builder pattern.

## Usage
### Step 1: Instantiate
```
new Reflect<MyClass>()
    .clazz(MyClass.class)
    .construct(Class<?>[] parameterTypes, Object[] parameterValues)
```
or
```
new Reflect<MyClass>(MyClass.class, Class<?>[] constructorParameterTypes, Object[] constructorParameterValues)
```
or
```
new Reflect<MyClass>(existingObject)
```
or
```
new Reflect<MyClass>()
    .exist(existingObject)
```

### Step 2: Invoke methods
```
.set(String fieldName, Object fieldValue) | .set(Map<String, Object> parameterMap)
.invoke(String methodName, Class<?>[] parameterTypes, Object[] parameterValues)
```

### Step 3: Get the instance
```
.reflect()
```

## Usage Example
```
MyClass myInstance = new Reflect<MyClass>()
                .clazz(MyClass.class)
                .construct(Class<?>[] parameterTypes, Object[] parameterValues)
                .set(String fieldName, Object fieldValue) | .set(Map<String, Object> parameterMap)
                .invoke(String methodName, Class<?>[] parameterTypes, Object[] parameterValues)
                .reflect();
```
