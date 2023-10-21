package me.CarsCupcake.SkyblockRemake.Slayer.spider.entity;

import me.CarsCupcake.SkyblockRemake.Entities.StandCoreExtention;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import me.CarsCupcake.SkyblockRemake.Slayer.SlayerLootTable;
import me.CarsCupcake.SkyblockRemake.Slayer.spider.item.SpiderSlayerItems;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Location;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Spider;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpiderSlayerT1 extends Slayer {
    protected Spider entity;
    protected CaveSpider riding;
    protected SlayerLootTable lootTable;

    public SpiderSlayerT1(SkyblockPlayer player) {
        super(player);
        lootTable = new SlayerLootTable();
        lootTable.addLoot(new ItemLoot(SpiderSlayerItems.TarantularWeb.getItem(), 1, 3));
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return 35;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Spider.class);
        entity.setAware(true);
        entity.setTarget(owner);
        entity.setCustomNameVisible(true);
        riding = loc.getWorld().spawn(loc, CaveSpider.class, caveSpider -> {
            caveSpider.setAI(true);
        });
        riding.setCustomName("");
        riding.setCustomNameVisible(true);
        riding.setAware(true);
        riding.setTarget(owner);
        SkyblockEntity.addExtention(new StandCoreExtention(riding, this));
        entity.addPassenger(riding);
        new EntityRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (entity.getLocation().distance(owner.getLocation()) < 10) return;
                i++;
                if (i > 28) {
                    i = 0;
                    Vector v = owner.getLocation().toVector().subtract(entity.getLocation().toVector());
                    v.normalize();
                    v.add(new Vector(0, 0.2, 0));
                    v.multiply(entity.getLocation().distance(owner.getLocation()) * 0.1);
                    entity.setVelocity(v);
                }
            }
        }.runTaskTimer(this, 1, 1);
    }

    @Override
    public void updateNameTag() {
        super.updateNameTag();
        riding.setCustomName(entity.getCustomName());
    }

    @Override
    public String getName() {
        return "Tarantula Broodfather";
    }

    @Override
    public int getMaxHealth() {
        return 750;
    }

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public void kill() {
        super.kill();
        entity.removePassenger(riding);
        new BukkitRunnable() {

            @Override
            public void run() {
                riding.remove();
            }
        }.runTaskLater(Main.getMain(), 2);
    }
}

