package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class SpititBat extends SkyblockEntity {
    private Bat bat;
    @Override
    public int getMaxHealth() {
        return 69420;
    }

    @Override
    public int getHealth() {
        return 69420;
    }

    @Override
    public LivingEntity getEntity() {
        return bat;
    }



    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void spawn(Location loc) {
        bat = loc.getWorld().spawn(loc, Bat.class, (b) ->{
            b.setAI(false);
            b.setAwake(true);
            b.setCustomNameVisible(false);
            b.setRemoveWhenFarAway(false);
            b.setSilent(true);
            b.addScoreboardTag("invinc");
        });
        SkyblockEntity.livingEntity.put(bat,this);
        Main.updateentitystats(bat);
    }


    @Override
    public String getName() {
        return "";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        bat.setCustomNameVisible(false);
    }

    @Override
    public void kill() {
        bat.remove();
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {

    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }
}
