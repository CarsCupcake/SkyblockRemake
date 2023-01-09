package me.CarsCupcake.SkyblockRemake.Potion;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemGenerator;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Constructor;
import java.util.*;

public class Potion implements ItemGenerator {
    @Getter
    private static final SortedSet<Potion> registered = new TreeSet<>(Comparator.comparing(o -> o.name));
    @Getter
    private final String name;
    private final PotionData base;
    private final PotionData[] rest;
    public Potion(String name, PotionData... data){
        base = data[0];
        if(data.length > 1)
            rest = Arrays.copyOfRange(data, 1, data.length);
        else
            rest = new PotionData[0];
        this.name = name;
        registered.add(this);
    }

    public static List<String> craftLore(SkyblockPlayer player, ItemStack item){
        if(!ItemHandler.hasPDC("potion", item, PersistentDataType.TAG_CONTAINER))
            return new ArrayList<>();
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        for(NamespacedKey key : ItemHandler.getPDC("potion", item, PersistentDataType.TAG_CONTAINER).getKeys()){
            PersistentDataContainer c = ItemHandler.getPDC("potion", item, PersistentDataType.TAG_CONTAINER).get(key, PersistentDataType.TAG_CONTAINER);
            PotionEffect effect = PotionEffect.getPotionEffects().get(key.getKey());
            if(effect == null) {
                continue;
            }
            int level = c.get(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER);
            long duration = c.get(new NamespacedKey(Main.getMain(), "duration"), PersistentDataType.LONG);
            Effect e = effect.getDummyEffect();
            lore.add(e.name() + " " + Tools.intToRoman(level) + " §f(" + Tools.ticksAsTime(duration) +")");
            lore.addAll(effect.getLore().makeLore(player, item));
            lore.add(" ");
        }
        lore.remove(lore.size() - 1);
        return lore;
    }

    @Override
    public ItemStack createNewItemStack() {
        ItemStack item = base.effect().createNewItemStack();
        PotionEffect.addEffectToBottle(item, base.effect(), base.level(), base.duration());
        for (PotionData data : rest) {
            PotionEffect.addEffectToBottle(item, data.effect(), data.level(), data.duration());
        }
        ItemHandler.setPDC("potion_name", item, PersistentDataType.STRING, name);
        return item;
    }
}
