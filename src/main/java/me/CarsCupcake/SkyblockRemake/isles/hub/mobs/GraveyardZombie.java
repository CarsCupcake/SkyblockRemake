package me.CarsCupcake.SkyblockRemake.isles.hub.mobs;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.isles.hub.BasicSkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.loot.CoinLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;

@EntitySkillXp(6)
public class GraveyardZombie extends BasicSkyblockEntity<Zombie> {
    @Override
    public int getMaxHealth() {
        return 100;
    }

    @Override
    public int getDamage() {
        return 20;
    }

    @Override
    public String getName() {
        return "Zombie";
    }

    @Override
    protected void onSpawn(Zombie entity) {
        entity.setAdult();
    }

    private static final LootTable lootTable = new LootTable().addLoot(new ItemLoot(Material.ROTTEN_FLESH))
            .addLoot(new ItemLoot(Material.POISONOUS_POTATO), 0.02)
            .addLoot(new ItemLoot(Material.POTATO), 0.01)
            .addLoot(new ItemLoot(Material.CARROT), 0.01)
            .addLoot(new CoinLoot(1));

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public int getXpDrop() {
        return 1;
    }
}
