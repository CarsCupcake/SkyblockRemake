package me.CarsCupcake.SkyblockRemake.Potion;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.utils.ClassUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_17_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_17_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

public class PotionEffect implements ItemGenerator {
    @Getter
    private static final HashMap<String, PotionEffect> potionEffects = new HashMap<>();
    @Getter
    private final String name;
    private final String id;
    private final Class<? extends Effect> effect;
    private final Color color;
    @Getter
    @Setter
    private long baseDuration = 3 * 60 * 20;
    @Getter
    @Setter
    private AbilityLore lore = new AbilityLore(new ArrayList<>());
    public PotionEffect(String name, Class<? extends Effect> effect, Color color){
        this.name = name;
        this.effect =effect;
        this.color = color;
        this.id = getDummyEffect().id();
        potionEffects.put(id, this);
        for (int i = 1; i < 6; i++)
            new Potion(name + " " + Tools.intToRoman(i) + " Potion", new PotionData(this, i, baseDuration));
    }

    public void applyEffect(SkyblockPlayer player){
        applyEffect(player, 1);
    }
    public void applyEffect(SkyblockPlayer player, int level){
        applyEffect(player, level, baseDuration);
    }
    public void applyEffect(SkyblockPlayer player, int level, long duration){
        Constructor<? extends Effect> c = Tools.getConstructorIfAvailable(effect, SkyblockPlayer.class, Integer.class, Long.class);
        ClassUtils.instantiateClass(c, player, level, duration);
    }

    @Override
    public ItemStack createNewItemStack() {
        ItemStack item = Items.SkyblockItems.get(Material.POTION + "").createNewItemStack();
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setColor(color);
        item.setItemMeta(meta);
        ItemHandler.setPDC("potion_name", item, PersistentDataType.STRING, name);
        return addEffectToBottle(item, this, 1, baseDuration);
    }

    public static ItemRarity getRarityFromLevel(int i){
        if(i > 10)
            return ItemRarity.SPECIAL;
        switch (i){
            case 3,4 -> {
                return ItemRarity.UNCOMMON;
            }
            case 5,6 -> {
                return ItemRarity.RARE;
            }
            case 7,8 -> {
                return ItemRarity.EPIC;
            }
            case 9,10-> {
                return ItemRarity.LEGENDARY;
            }
            default -> {
                return ItemRarity.COMMON;
            }
        }
    }

    public static int getHighestLevel(ItemStack item){
        if(!ItemHandler.hasPDC("potion", item, PersistentDataType.TAG_CONTAINER))
            return 0;
        int highest = 0;
        for(NamespacedKey key : ItemHandler.getPDC("potion", item, PersistentDataType.TAG_CONTAINER).getKeys()){
            PersistentDataContainer c = ItemHandler.getPDC("potion", item, PersistentDataType.TAG_CONTAINER).get(key, PersistentDataType.TAG_CONTAINER);
            int level = c.get(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER);
            if(level > highest)
                highest = level;
        }
        return highest;
    }

    public Effect getDummyEffect(){
        Constructor<? extends Effect> constructor = Tools.getConstructorIfAvailable(effect, SkyblockPlayer.class, Integer.class, Long.class);
        return ClassUtils.instantiateClass(constructor, null, 0, 0L);
    }

    public static ItemStack addEffectToBottle(ItemStack item, PotionEffect effect, int level, long duration){
        if(!ItemHandler.hasPDC("potion", item, PersistentDataType.TAG_CONTAINER))
            ItemHandler.setPDC("potion", item, PersistentDataType.TAG_CONTAINER, new CraftPersistentDataContainer(new CraftPersistentDataTypeRegistry()));
        PersistentDataContainer potion = ItemHandler.getPDC("potion", item, PersistentDataType.TAG_CONTAINER);
        PersistentDataContainer pdc = new CraftPersistentDataContainer(new CraftPersistentDataTypeRegistry());
        pdc.set(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER, level);
        pdc.set(new NamespacedKey(Main.getMain(), "duration"), PersistentDataType.LONG, duration);
        potion.set(new NamespacedKey(Main.getMain(), effect.id), PersistentDataType.TAG_CONTAINER, pdc);
        ItemHandler.setPDC("potion", item, PersistentDataType.TAG_CONTAINER, potion);
        return item;
    }
}
