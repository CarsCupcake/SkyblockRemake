package me.CarsCupcake.SkyblockRemake.elements;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class ElementalReaction {
    public static final HashMap<Element, Set<Bundle<Element, ElementalReaction>>> reactions = new HashMap<>();
    protected final Pair<Element> elements;
    protected final String name;
    protected final LivingEntity applier;
    protected final SkyblockEntity entity;

    public ElementalReaction(String name, Pair<Element> elements, @Nullable LivingEntity applier, @NotNull SkyblockEntity entity) {
        this.elements = elements;
        this.name = name;
        this.applier = applier;
        this.entity = entity;
        show();
    }

    private static void addReaction(ElementalReaction reaction) {
        reaction.elements.forSite((e) -> {
            Set<Bundle<Element, ElementalReaction>> r = reactions.getOrDefault(e, new HashSet<>());
            r.add(new Bundle<>(reaction.getMissing(e), reaction));
            reactions.put(e, r);
        });
    }

    public Element getMissing(Element e) {
        if (elements.getFirst() == e) return elements.getLast();
        if (elements.getLast() == e) return elements.getFirst();
        return null;
    }

    protected void show() {
        Main.getMain().killarmorstand(entity.getEntity().getWorld().spawn(entity.getEntity().getEyeLocation(), ArmorStand.class, armorStand -> {
            armorStand.setInvisible(true);
            armorStand.setBasePlate(false);
            armorStand.setCustomName(name);
            armorStand.setCustomNameVisible(true);
        }));
    }
}
