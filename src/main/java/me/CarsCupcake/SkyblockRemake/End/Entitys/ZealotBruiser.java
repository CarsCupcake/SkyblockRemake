package me.CarsCupcake.SkyblockRemake.End.Entitys;

import me.CarsCupcake.SkyblockRemake.Configs.ExtraInformations;
import me.CarsCupcake.SkyblockRemake.End.EndItems;
import me.CarsCupcake.SkyblockRemake.End.ZealotBruiserSpawning;
import me.CarsCupcake.SkyblockRemake.End.ZealotSpawning;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Random;

public class ZealotBruiser extends SkyblockEntity {
    private int health = 65000;
    private Enderman entity;
    private SkyblockPlayer lastHit;
    private boolean isLoot = false;

    @Override
    public int getMaxHealth() {
        return 65000;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }


    @Override
    public int getDamage() {
        return 2500;
    }

    @Override
    public void spawn(Location loc) {
        isLoot = (new Random().nextInt(15) == 0);

        entity = loc.getWorld().spawn(loc, Enderman.class, enderman -> {
            enderman.setRemoveWhenFarAway(false);
            enderman.addScoreboardTag("combatxp:78");
            if(isLoot)
                enderman.setCarriedBlock(Material.ENDER_CHEST.createBlockData());
        });
        SkyblockEntity.livingEntity.put(entity, this);
    }


    @Override
    public String getName() {
        return "Zealot Bruiser";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        HashMap<ItemManager, Integer> drops = new HashMap<>();
        drops.put(Items.SkyblockItems.get(Material.ENDER_PEARL.toString()), new Random().nextInt(2) + 2);
        if (isLoot)
            player.sendMessage("Extra loot is not done yet!");
        return drops;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(SkyblockEntity.getBaseName(this));
    }

    @Override
    public void kill() {
        if (lastHit == null)
            return;

        ExtraInformations.reload();
        int zealotKills = ExtraInformations.get().getInt(lastHit.getUniqueId() + ".zealotkills", 0);
        zealotKills++;
        ExtraInformations.get().set(lastHit.getUniqueId() + ".zealotkills", zealotKills);
        double pers = 0.0026315 + Zealot.specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d);
        if(new Random().nextDouble() <= pers){
            new SpecialZealot(lastHit).spawn(ZealotBruiserSpawning.getNearest(lastHit.getLocation()));
            lastHit.sendMessage("§aA special §5Zealot §ahas spawned nearby! §8(" + zealotKills + ")");
            ExtraInformations.get().set(lastHit.getUniqueId() + ".zealotkills", 0);

            Zealot.specialZealotSpawnRateModifier.put(lastHit, Zealot.specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d) - Zealot.pityBonusStage.get(lastHit));
        Zealot.pityBonusStage.remove(lastHit);
        }else {
            ExtraInformations.get().set(lastHit.getUniqueId() + ".zealotkills", zealotKills);
            if(zealotKills == 420 && Zealot.pityBonusStage.getOrDefault(lastHit, 0) == 0){
                Zealot.pityBonusStage.put(lastHit, 1);
                Zealot.specialZealotSpawnRateModifier.put(lastHit, Zealot.specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d) + 1);

            }
            if(zealotKills == 630 && Zealot.pityBonusStage.getOrDefault(lastHit, 0) == 1){
                Zealot.pityBonusStage.put(lastHit, 2);
                Zealot.specialZealotSpawnRateModifier.put(lastHit, Zealot.specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d) + 1);

            }
            if(zealotKills == 840 && Zealot.pityBonusStage.getOrDefault(lastHit, 0) == 2){
                Zealot.pityBonusStage.put(lastHit, 3);
                Zealot.specialZealotSpawnRateModifier.put(lastHit, Zealot.specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d) + 1);

            }
        }
        ExtraInformations.save();
        ExtraInformations.reload();

    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
        lastHit = player;
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }
}
