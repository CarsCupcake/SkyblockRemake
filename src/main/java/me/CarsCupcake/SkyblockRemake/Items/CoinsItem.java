package me.CarsCupcake.SkyblockRemake.Items;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class CoinsItem implements ItemGenerator{
    @Getter
    private final double value;
    public CoinsItem(double value){
        this.value = value;
    }
    public CoinsItem(ItemStack s){
        value = s.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "coinsvalue"), PersistentDataType.DOUBLE);
    }
    public void add(SkyblockPlayer player){
        player.setCoins(player.coins + value);
    }

    @Override
    public ItemStack createNewItemStack() {
        ItemStack item = Items.SkyblockItems.get("COINS_ITEM").createNewItemStack();
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "coinsvalue"), PersistentDataType.DOUBLE, value);
        item.setItemMeta(meta);
        return item;
    }
    public static void init(){
        ItemManager manager = new ItemManager("Coins", "COINS_ITEM", ItemType.Non, null, null, null, null, 0,0,0,0, Material.GOLD_INGOT, ItemRarity.SPECIAL);
        manager.setAbility(new AbilityManager<PlayerInteractEvent>() {
            @Override
            public boolean executeAbility(PlayerInteractEvent event) {
                new CoinsItem(event.getItem()).add(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
                return false;
            }

        }, AbilityType.LeftOrRightClick);
        Items.SkyblockItems.put(manager.itemID, manager);

    }

}
