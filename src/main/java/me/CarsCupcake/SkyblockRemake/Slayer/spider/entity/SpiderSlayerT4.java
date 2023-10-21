package me.CarsCupcake.SkyblockRemake.Slayer.spider.entity;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Slayer.SlayerLootTable;
import me.CarsCupcake.SkyblockRemake.Slayer.spider.item.SpiderSlayerItems;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;

@EntitySkillXp(500)
public class SpiderSlayerT4 extends SpiderSlayerT3{
    public SpiderSlayerT4(SkyblockPlayer player) {
        super(player);
        lootTable = new SlayerLootTable();
        lootTable.addLoot(new ItemLoot(SpiderSlayerItems.TarantularWeb.getItem(), 52, 64));
    }

    @Override
    public int getDamage() {
        return 530;
    }

    @Override
    public int getMaxHealth() {
        return 2_400_000;
    }

    @Override
    public int getLevel() {
        return 770;
    }
}
