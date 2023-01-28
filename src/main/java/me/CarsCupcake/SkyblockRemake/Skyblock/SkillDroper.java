package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.SkillXpItem;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkillDroper {
    Skills[] value();
}
