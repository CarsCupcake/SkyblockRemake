package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.LostAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.End.EndItems;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnstableAdventurer extends LostAdventurer {
    protected final BukkitRunnable runnable = new BukkitRunnable() {
        @Override
        public void run() {
            Stream<Entity> stream = entity.getNearbyEntities(15, 15, 15).stream().filter(entity1 -> entity1 instanceof Player);
            for (Entity e : stream.collect(Collectors.toSet())){
                if(e instanceof Player p){
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
                    player.getWorld().strikeLightningEffect(player.getLocation());
                    Calculator calculator = new Calculator();
                    int i = (int)(Main.getPlayerStat(player, Stats.Health) * 0.1);
                    calculator.entityToPlayerDamage(UnstableAdventurer.this, player, new Bundle<>(0, i));
                    player.sendMessage("§7Unstable Lost Adventurer's lightning strike dealth §c" + i + " §7damage");
                    calculator.damagePlayer(player);
                    calculator.showDamageTag(player);
                }
            }
        }
    };

    public UnstableAdventurer(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public void spawn(Location loc) {
        craftEntity = new SBEntity(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(craftEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        entity = (LivingEntity) craftEntity.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.getEquipment().setItem(EquipmentSlot.HEAD, EndItems.Items.UnstableHelmet.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.CHEST, EndItems.Items.UnstableChestplate.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.LEGS, EndItems.Items.UnstableLeggings.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.FEET, EndItems.Items.UnstableBoots.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.HAND, theSword.build());
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
        new PlayerDisguise(entity, "ewogICJ0aW1lc3RhbXAiIDogMTYwMTgxMzk1MzcwMiwKICAicHJvZmlsZUlkIiA6ICI3ZGEyYWIzYTkzY2E0OGVlODMwNDhhZmMzYjgwZTY4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHb2xkYXBmZWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZhMDM4M2E1Mjc4MDNkOTliNjYyYWQxOGJjYWM3OGMxMTkyNTBlYmJmYjE0NDc1YjRlYjRkNGE2NjI3OTZiNCIKICAgIH0KICB9Cn0=", "UWEpro7nRuOTopH9XzNksRQNC1Uq+vtjGkmb7/x8gsVjD3ealtJDZZvoe1Mi46ImNFnJs1EiEWetQKBQBAn1IYT59N2Xp1HNP1kseQDSFCnpF60eAxnAfZbClN6+8o4/c8PVif7ohmh3RG9X0mvX83FM7fe2nd5jchyCeIV591HFQiOQwDgMH+YFAVr/nwPk6HYn7ud0sGDz8aDnKYw4JkxMUmPHL6YKBX1cWQYZ1ot9d15uhHvgmV1vTx6ezTF0GHfsVuCXyfwVK1g2zyK7vZqpaRy+lMdNjiq1rYF9OncyJ0auqAOlsQMlfgDkau3fDiZ7fDr68arx9UvL9nAmz2xyfQ/qF9nXtuBUFIEkVbVpvs3/SA3SYNm9sO4JmQv1gcUP0nCCj+ZaWUC56yACoUTId+WnYDjzHfyMKR1+Td2z+anP6cdlaT1N8XsQEUPkD6Z8E7kuJjBtvq0nZPc92ndvie1lDD9bfgtV0TXF0o750+AzZslx27zhdioNVsjy5o2XUn6PNDr+4uNOQPb/AOSxDgu8mjTTEQmOD2UHBrqsXqnnt6CAwiB+QNi4VOhvQSa9C6CKFuvNyH2zBk624NcUp4SIqrx6XVsVNa3gXCURSJ61Ri+TRtOXxceMoH7ih2u2JVPU3K+fHqURN7guMEFeQpx4nXpwQkfhETDkrac=");
        livingEntity.addEntity(entity, this);
        updateNameTag();
        r.runTaskTimer(Main.getMain(), 5, 5);
        runnable.runTaskTimer(Main.getMain(), 200, 200);
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return Tools.mapOf(List.of(EndItems.Items.UnstableDragonFragment.getItem()), List.of(3));
    }

    @Override
    public void kill() {
        try {
            runnable.cancel();
        }catch (Exception ignored){}
        super.kill();
    }

    @Override
    protected int healthFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 9 -> (master) ? 92_000_000 : 2_600_000;
            case 10 -> (master) ? 150_000_000 : 6_600_000;
            default -> throw new IllegalArgumentException("Not Possible!");
        };
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 9 -> (master) ? 240_000 : 7_920;
            case 10 -> (master) ? 240_000 : 16_000;
            default -> throw new IllegalArgumentException("Not Possible!");
        };
    }

    @Override
    protected double defenseFromFloor(int floor, boolean master) {
        return 150;
    }
}
