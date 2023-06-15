package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import org.checkerframework.framework.qual.PostconditionAnnotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(EntitySkillXp.List.class)
public @interface EntitySkillXp {
    int value();
    Skills skill() default Skills.Combat;


    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @PostconditionAnnotation(qualifier = EntitySkillXp.class)
    @interface List {
        /**
         * Return the repeatable annotations.
         *
         * @return the repeatable annotations
         */
        EntitySkillXp[] value();
    }
}
