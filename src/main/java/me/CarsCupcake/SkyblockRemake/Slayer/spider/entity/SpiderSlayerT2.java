package me.CarsCupcake.SkyblockRemake.Slayer.spider.entity;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Slayer.SlayerLootTable;
import me.CarsCupcake.SkyblockRemake.Slayer.spider.item.SpiderSlayerItems;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import org.bukkit.Location;

@EntitySkillXp(100)
public class SpiderSlayerT2 extends SpiderSlayerT1{
    public SpiderSlayerT2(SkyblockPlayer player) {
        super(player);
        lootTable = new SlayerLootTable();
        lootTable.addLoot(new ItemLoot(SpiderSlayerItems.TarantularWeb.getItem(), 9, 18));
    }

    @Override
    public int getDamage() {
        return 45;
    }

    @Override
    public int getMaxHealth() {
        return 30_000;
    }

    @Override
    public int getLevel() {
        return 90;
    }

    @Override
    public void spawn(Location loc) {
        super.spawn(loc);
        SpiderListener.players.add(owner);
    }

    @Override
    public void kill() {
        super.kill();
        SpiderListener.players.remove(owner);
    }
}
