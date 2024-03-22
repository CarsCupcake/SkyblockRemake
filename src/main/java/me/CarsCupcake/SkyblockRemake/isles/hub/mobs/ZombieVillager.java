package me.CarsCupcake.SkyblockRemake.isles.hub.mobs;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.isles.hub.BasicSkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.loot.CoinLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@EntitySkillXp(7)
public class ZombieVillager extends BasicSkyblockEntity<org.bukkit.entity.ZombieVillager> {
    @Override
    public int getMaxHealth() {
        return 120;
    }

    @Override
    public int getDamage() {
        return 24;
    }

    @Override
    public String getName() {
        return "Zombie";
    }

    @Override
    protected void onSpawn(org.bukkit.entity.ZombieVillager entity) {
        entity.setAdult();
        entity.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        entity.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        entity.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        entity.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
    }
    private static final LootTable lootTable = new LootTable().addLoot(new ItemLoot(Material.ROTTEN_FLESH))
            .addLoot(new ItemLoot(Material.POISONOUS_POTATO), 0.02)
            .addLoot(new CoinLoot(1));
    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public int getXpDrop() {
        return 3;
    }



}
