package me.CarsCupcake.SkyblockRemake.Entities;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;

public class EntityHandler {
    private EntityHandler() {}
    public static <T, Z> Z get(String key, Entity entity, PersistentDataType<T, Z> dataType) {
        return entity.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), key), dataType);
    }
    public static <T, Z> boolean has(String key, Entity entity, PersistentDataType<T, Z> dataType) {
        return entity.getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), key), dataType);
    }
    public static <T, Z> Z getOrDefault(String key, Entity entity, PersistentDataType<T, Z> dataType, Z def) {
        if (entity == null) return def;
        return (has(key, entity, dataType)) ? get(key, entity, dataType) : def;
    }
    public static <T, Z> void set(String key, Entity entity, PersistentDataType<T, Z> dataType, Z data) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), key), dataType, data);
    }
    public static net.minecraft.world.entity.Entity getHandle(Entity entity) {
        return ((CraftEntity) entity).getHandle();
    }
}
