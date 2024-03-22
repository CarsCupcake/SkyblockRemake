package me.CarsCupcake.SkyblockRemake.isles.hub.mobs;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.isles.hub.BasicSkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.loot.CoinLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

@EntitySkillXp(32)
public class CryptGhoul extends BasicSkyblockEntity<Zombie> {
    @Override
    public int getMaxHealth() {
        return 2_000;
    }

    @Override
    public String getName() {
        return "Crypt Ghoul";
    }

    @Override
    public int getLevel() {
        return 30;
    }

    @Override
    public int getDamage() {
        return 350;
    }

    @Override
    public int getXpDrop() {
        return 30;
    }

    private final static LootTable lootTable = new LootTable()
            .addLoot(new CoinLoot(13))
            .addLoot(new ItemLoot(Material.ROTTEN_FLESH, 1, 3));

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    protected void onSpawn(Zombie entity) {
        entity.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
        entity.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        entity.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        entity.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
    }
}
