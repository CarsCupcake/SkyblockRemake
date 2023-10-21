package me.CarsCupcake.SkyblockRemake.Slayer.spider.entity;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Slayer.SlayerLootTable;
import me.CarsCupcake.SkyblockRemake.Slayer.spider.item.SpiderSlayerItems;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import org.bukkit.Location;

@EntitySkillXp(200)
public class SpiderSlayerT3 extends SpiderSlayerT2{
    public SpiderSlayerT3(SkyblockPlayer player) {
        super(player);
        lootTable = new SlayerLootTable();
        lootTable.addLoot(new ItemLoot(SpiderSlayerItems.TarantularWeb.getItem(), 28, 48));
    }

    @Override
    public int getDamage() {
        return 210;
    }

    @Override
    public int getMaxHealth() {
        return 900_000;
    }

    @Override
    public int getLevel() {
        return 470;
    }
}
