package me.CarsCupcake.SkyblockRemake.utils;

import me.CarsCupcake.SkyblockRemake.utils.Exceptions.ClassInitiateException;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class ClassUtils {

    private static final Set<Class<?>> unknownEditorTypes =
            Collections.newSetFromMap(new ConcurrentReferenceHashMap<>(64));

    private static final Map<Class<?>, Object> DEFAULT_TYPE_VALUES = Map.of(
            boolean.class, false,
            byte.class, (byte) 0,
            short.class, (short) 0,
            int.class, 0,
            long.class, 0L,
            float.class, 0F,
            double.class, 0D,
            char.class, '\0');
    public static <T> T instantiateClass(Constructor<T> ctor, Object... args) throws ClassInitiateException {
        if(ctor == null)
            throw new NullPointerException("Constructor is null!");
        try {
            ReflectionUtils.makeAccessible(ctor);

                int parameterCount = ctor.getParameterCount();
                Assert.isTrue(args.length <= parameterCount, "Can't specify more arguments than constructor parameters");
                if (parameterCount == 0) {
                    return ctor.newInstance();
                }
                Class<?>[] parameterTypes = ctor.getParameterTypes();
                Object[] argsWithDefaultValues = new Object[args.length];
                for (int i = 0 ; i < args.length; i++) {
                    if (args[i] == null) {
                        Class<?> parameterType = parameterTypes[i];
                        argsWithDefaultValues[i] = (parameterType.isPrimitive() ? DEFAULT_TYPE_VALUES.get(parameterType) : null);
                    }
                    else {
                        argsWithDefaultValues[i] = args[i];
                    }
                }
                return ctor.newInstance(argsWithDefaultValues);

        }
        catch (InstantiationException ex) {
            throw new ClassInitiateException(ctor, "Is it an abstract class?", ex);
        }
        catch (IllegalAccessException ex) {
            throw new ClassInitiateException(ctor, "Is the constructor accessible?", ex);
        }
        catch (IllegalArgumentException ex) {
            throw new ClassInitiateException(ctor, "Illegal arguments for constructor", ex);
        }
        catch (Exception ex) {
            throw new ClassInitiateException(ctor, "Constructor threw exception", ex);
        }
    }
}
