package me.CarsCupcake.SkyblockRemake.isles.hub.mobs;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.isles.hub.BasicSkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.loot.CoinLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import org.bukkit.Material;

@EntitySkillXp(15)
public class Wolf extends BasicSkyblockEntity<org.bukkit.entity.Wolf> {
    @Override
    public int getMaxHealth() {
        return 250;
    }

    @Override
    public String getName() {
        return "Wolf";
    }

    @Override
    public int getDamage() {
        return 60;
    }

    @Override
    public int getXpDrop() {
        return 4;
    }

    @Override
    public LootTable getLootTable() {
        return new LootTable().addLoot(new ItemLoot(Material.BONE)).addLoot(new CoinLoot(1));
    }
}
