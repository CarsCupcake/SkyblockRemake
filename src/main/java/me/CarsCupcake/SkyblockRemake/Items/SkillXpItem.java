package me.CarsCupcake.SkyblockRemake.Items;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class SkillXpItem implements ItemGenerator{
    @Getter
    private final Skills skill;
    @Getter
    private final double value;
    public SkillXpItem(Skills skill, double value){
        this.skill = skill;
        this.value = value;
    }
    public SkillXpItem(ItemStack item){
        this.skill = Skills.valueOf(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "skillvalue"), PersistentDataType.STRING));
        this.value = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "amountvalue"), PersistentDataType.DOUBLE);
    }

    @Override
    public ItemStack createNewItemStack() {
        ItemStack item = Items.SkyblockItems.get("SKILL_XP_ITEM").createNewItemStack();
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "amountvalue"), PersistentDataType.DOUBLE, value);
        meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "skillvalue"), PersistentDataType.STRING, skill.toString());
        item.setItemMeta(meta);
        return item;
    }
    public void add(SkyblockPlayer player){
        player.addSkillXp(value, skill);
    }
    public static void init(){
        ItemManager manager = new ItemManager("Skill Xp", "SKILL_XP_ITEM", ItemType.Non, null, null, null, null, 0,0,0,0, Material.LIME_DYE, ItemRarity.SPECIAL);
        manager.setAbility((AbilityManager<PlayerInteractEvent>) event -> {
            new SkillXpItem(event.getItem()).add(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
            return false;
        }, AbilityType.LeftOrRightClick);
        Items.SkyblockItems.put(manager.itemID, manager);

    }
}
