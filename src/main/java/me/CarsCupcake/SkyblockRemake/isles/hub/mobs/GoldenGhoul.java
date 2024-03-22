package me.CarsCupcake.SkyblockRemake.isles.hub.mobs;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.isles.hub.BasicSkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.loot.CoinLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

@EntitySkillXp(50)
public class GoldenGhoul extends BasicSkyblockEntity<Zombie> {
    @Override
    public int getMaxHealth() {
        return 45_000;
    }

    @Override
    public String getName() {
        return "Golden Ghoul";
    }

    @Override
    public int getLevel() {
        return 60;
    }

    @Override
    public int getDamage() {
        return 800;
    }

    @Override
    public int getXpDrop() {
        return 30;
    }

    private final static LootTable lootTable = new LootTable()
            .addLoot(new CoinLoot(100))
            .addLoot(new ItemLoot(Material.GOLD_INGOT, 1, 10))
            .addLoot(new ItemLoot(Material.ROTTEN_FLESH, 2))
            .addLoot(new ItemLoot("GOLDEN_POWDER"), 0.0005);

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    protected void onSpawn(Zombie entity) {
        entity.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));
        entity.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
        entity.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
        entity.getEquipment().setItemInMainHand(new ItemStack(Material.GOLDEN_SWORD));
    }
}
