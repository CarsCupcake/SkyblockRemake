package me.CarsCupcake.SkyblockRemake.End.Entitys;

import me.CarsCupcake.SkyblockRemake.Configs.ExtraInformations;
import me.CarsCupcake.SkyblockRemake.End.EndItems;
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

public class SpecialZealot extends SkyblockEntity {
    private int health = 13000;
    private Enderman entity;
    private SkyblockPlayer lastHit;
    private final SkyblockPlayer owner;

    public SpecialZealot(SkyblockPlayer owner){
        this.owner = owner;
    }

    @Override
    public int getMaxHealth() {
        return 13000;
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
        return 1250;
    }

    @Override
    public void spawn(Location loc) {

        entity = loc.getWorld().spawn(loc, Enderman.class, enderman -> {
            enderman.setRemoveWhenFarAway(false);
            enderman.addScoreboardTag("combatxp:40");
            enderman.setCarriedBlock(Material.END_PORTAL_FRAME.createBlockData());
        });
        SkyblockEntity.livingEntity.put(entity, this);
    }


    @Override
    public String getName() {
        return "Special Zealot";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        HashMap<ItemManager, Integer> drops = new HashMap<>();
        drops.put(Items.SkyblockItems.get(Material.ENDER_PEARL.toString()), new Random().nextInt(2) + 2);
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
        double pers = 0.002381 + Zealot.specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d);
        if(new Random().nextDouble() <= pers){
            new SpecialZealot(lastHit).spawn(ZealotSpawning.getNearest(lastHit.getLocation()));
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
        owner.getInventory().addItem(EndItems.Items.SummoningEye.getItem().createNewItemStack());
        owner.sendMessage("§6§lRARE §5Summoning Eye");

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
