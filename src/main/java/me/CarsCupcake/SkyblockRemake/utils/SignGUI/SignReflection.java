package me.CarsCupcake.SkyblockRemake.utils.SignGUI;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

public class SignReflection {

    /**
     * Get the label of a field in a non-static way.
     *
     * @param instance the instance
     * @param field    the name of the field
     * @param <T>      field label
     * @return the label of the field
     * @throws ReflectionException if any non-runtime exception is thrown
     */
    static <T> T getValue(@Nonnull Object instance, @Nonnull String field)
    {
        return fieldGet(findField(instance.getClass(), field), instance);
    }

    /**
     * Search field in parent classes.
     *
     * @param clazz parent class
     * @param name  field name
     * @return a field if found
     */
    private static Field findField(Class<?> clazz, String name)
    {
        return findField(clazz, name, clazz);
    }

    /**
     * Recursive search field in parent classes
     *
     * @param clazz  parent class
     * @param name   field name
     * @param search recursive search
     * @return a field if found
     */
    private static Field findField(Class<?> clazz, String name, Class<?> search)
    {
        Field[] fields = search.getDeclaredFields();
        for (Field field : fields)
        {
            if (field.getName().equals(name)) return field;
        }

        Class<?> superClass = search.getSuperclass();

        if (superClass != null) return findField(clazz, name, superClass);

        throw new ReflectionException("Cannot find field " + name + " in " + clazz);
    }

    /**
     * Access to a field
     *
     * @param field a field to get
     * @param inst  instance to get from
     * @param <T>   generic class
     * @return class
     */
    private static <T> T fieldGet(Field field, Object inst)
    {
        try
        {
            field.setAccessible(true);
            return (T) field.get(inst);
        }
        catch (ReflectiveOperationException e)
        {
            throw new ReflectionException(e);
        }
    }


    /**
     * A runtime exception to rethrow any non-runtime exception related to Reflection.
     */
    public static final class ReflectionException extends RuntimeException {

        private ReflectionException(String arg0)
        {
            super(arg0);
        }

        ReflectionException(Throwable arg0)
        {
            super(arg0);
        }
    }

}
