package me.CarsCupcake.SkyblockRemake.utils.Exceptions;

import me.CarsCupcake.SkyblockRemake.utils.Exceptions.NestedRuntimeException;
import org.jetbrains.annotations.Nullable;

public class FatalException extends NestedRuntimeException {

    /**
     * Create a new FatalBeanException with the specified message.
     * @param msg the detail message
     */
    public FatalException(String msg) {
        super(msg);
    }

    /**
     * Create a new FatalBeanException with the specified message
     * and root cause.
     * @param msg the detail message
     * @param cause the root cause
     */
    public FatalException(String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }

}
