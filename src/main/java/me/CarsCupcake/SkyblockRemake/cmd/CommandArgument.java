package me.CarsCupcake.SkyblockRemake.cmd;

import org.checkerframework.framework.qual.PostconditionAnnotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(CommandArgument.List.class)
public @interface CommandArgument {
    int position() default 0;

    String[] lastArg() default {};

    String[] args();

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @PostconditionAnnotation(qualifier = CommandArgument.class)
    @interface List {
        /**
         * Return the repeatable annotations.
         *
         * @return the repeatable annotations
         */
        CommandArgument[] value();
    }
}
