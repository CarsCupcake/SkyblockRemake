package me.CarsCupcake.SkyblockRemake.isles.hub.mobs;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.isles.hub.BasicSkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.loot.CoinLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import org.bukkit.Material;
import org.bukkit.entity.Wolf;

@EntitySkillXp(40)
public class OldWolf extends BasicSkyblockEntity<Wolf> {
    @Override
    public int getMaxHealth() {
        return 15_000;
    }

    @Override
    public String getName() {
        return "Old Wolf";
    }

    @Override
    public int getDamage() {
        return 800;
    }

    @Override
    public int getXpDrop() {
        return 30;
    }

    @Override
    public LootTable getLootTable() {
        return new LootTable().addLoot(new CoinLoot(40)).addLoot(new ItemLoot(Material.BONE)).addLoot(new ItemLoot("WOLF_TALISMAN"), 0.001);
    }
}
