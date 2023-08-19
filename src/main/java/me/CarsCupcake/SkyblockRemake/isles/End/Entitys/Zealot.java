package me.CarsCupcake.SkyblockRemake.isles.End.Entitys;

import me.CarsCupcake.SkyblockRemake.Configs.ExtraInformations;
import me.CarsCupcake.SkyblockRemake.isles.End.ZealotSpawning;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Random;

public class Zealot extends SkyblockEntity {
    public static HashMap<SkyblockPlayer, Double> specialZealotSpawnRateModifier = new HashMap<>();
    public static final HashMap<SkyblockPlayer, Integer> pityBonusStage = new HashMap<>();
    private Enderman entity;
    private boolean isLoot = false;
    private SkyblockPlayer lastHit;
    private static final LootTable lootTable;
    private static final LootTable enderLootTable;
    static {
        lootTable = new LootTable();
        lootTable.addLoot(new ItemLoot(Items.SkyblockItems.get(Material.ENDER_PEARL.toString()), 2, 4));
        lootTable.addLoot(new ItemLoot(Items.SkyblockItems.get("ENCHANTED_ENDER_PEARL"), 1, 1), 0.02);

        enderLootTable = new LootTable();
        enderLootTable.addLoot(new ItemLoot(Items.SkyblockItems.get(Material.ENDER_PEARL.toString()), 2, 4));
        enderLootTable.addLoot(new ItemLoot(Items.SkyblockItems.get("ENCHANTED_ENDER_PEARL"), 1, 1), 0.04);
        enderLootTable.addSubLootTable(ZealotBruiser.enhancedLootTable);
    }
    @Override
    public int getMaxHealth() {
        return 13000;
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
        isLoot = (new Random().nextInt(10) == 0);

        entity = loc.getWorld().spawn(loc, Enderman.class, enderman -> {
            enderman.setRemoveWhenFarAway(false);
            enderman.addScoreboardTag("combatxp:40");
            if(isLoot)
                enderman.setCarriedBlock(Material.ENDER_CHEST.createBlockData());
            enderman.setCustomNameVisible(true);
        });
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }


    @Override
    public String getName() {
        return "Zealot";
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(SkyblockEntity.getBaseName(this));
    }

    @Override
    public void kill() {
        super.kill();
        if (lastHit == null)
            return;
        ExtraInformations.reload();
        int zealotKills = ExtraInformations.get().getInt(lastHit.getUniqueId() + ".zealotkills", 0);
        zealotKills++;
        ExtraInformations.get().set(lastHit.getUniqueId() + ".zealotkills", zealotKills);
        double multi = specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d) + 1;
        double pers = 0.002381 * multi;
        double r = new Random().nextDouble();
        if(r <= pers){
            new SpecialZealot(lastHit).spawn(ZealotSpawning.getNearest(lastHit.getLocation()));
            lastHit.sendMessage("§aA special §5Zealot §ahas spawned nearby! §8(" + zealotKills + ")");
            ExtraInformations.get().set(lastHit.getUniqueId() + ".zealotkills", 0);
            Zealot.specialZealotSpawnRateModifier.put(lastHit, Zealot.specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d) - Zealot.pityBonusStage.get(lastHit));
            Zealot.pityBonusStage.remove(lastHit);
        }else {
            ExtraInformations.get().set(lastHit.getUniqueId() + ".zealotkills", zealotKills);
            if(zealotKills >= 420 && pityBonusStage.getOrDefault(lastHit, 0) == 0){
                pityBonusStage.put(lastHit, 1);
                specialZealotSpawnRateModifier.put(lastHit, specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d) + 1);

            }
            if(zealotKills >= 630 && pityBonusStage.getOrDefault(lastHit, 0) == 1){
                pityBonusStage.put(lastHit, 2);
                specialZealotSpawnRateModifier.put(lastHit, specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d) + 1);

            }
            if(zealotKills >= 840 && pityBonusStage.getOrDefault(lastHit, 0) == 2){
                pityBonusStage.put(lastHit, 3);
                specialZealotSpawnRateModifier.put(lastHit, specialZealotSpawnRateModifier.getOrDefault(lastHit, 0d) + 1);

            }
        }
        ExtraInformations.save();
        ExtraInformations.reload();

    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        super.damage(damage, player);
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

    @Override
    public LootTable getLootTable() {
        return (isLoot) ? enderLootTable : lootTable;
    }
}
