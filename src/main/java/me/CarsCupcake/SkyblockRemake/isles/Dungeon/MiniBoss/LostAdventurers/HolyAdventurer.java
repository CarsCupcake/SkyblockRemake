package me.CarsCupcake.SkyblockRemake.isles.Dungeon.MiniBoss.LostAdventurers;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.End.EndItems;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.minecraft.network.protocol.game.PacketPlayOutEntityStatus;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;

public class HolyAdventurer extends LostAdventurer {
    public HolyAdventurer(int floor, boolean master) {
        super(floor, master);
        tenPers = getMaxHealth() * 0.2;
    }

    @Override
    public void spawn(Location loc) {
        craftEntity = new SBEntity(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(craftEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        entity = (LivingEntity) craftEntity.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.getEquipment().setItem(EquipmentSlot.HEAD, EndItems.Items.HolyHelmet.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.CHEST, EndItems.Items.HolyChestplate.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.LEGS, EndItems.Items.HolyLeggings.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.FEET, EndItems.Items.HolyBoots.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.HAND, theSword.build());
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
        DiguestMobsManager.createEntity(entity, loc, "-", "ewogICJ0aW1lc3RhbXAiIDogMTU4OTEzOTA2Mjc5NSwKICAicHJvZmlsZUlkIiA6ICJmZDYwZjM2ZjU4NjE0ZjEyYjNjZDQ3YzJkODU1Mjk5YSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZWFkIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNhMjZjZjY2YWY0MmE0OGY2YmU1MmEwYTQ4YThlYjU5OGYzYTIzNjM5YjdhNWE4ZWUxNzFjMTM5OTllZTk0YzkiCiAgICB9CiAgfQp9", "CNO1y2pwfuWv79KqIVVbE7ZGWvHbOJe1zFWLzTH+kScgOpuj2ihu1ETrhtSpNYV48x/pSHBcZmlzOBtXCa/GBuS1BjKlb7rzo4naI5vWbJ2R2X7c2rFHficym50VPPQmh12NTuEYhrZbhfaMBZNkcDfKLLi2r89lrYGZhSDGsa1LExlWmdIWPQx4B3hfC+MTBYrIraB2TDWZd2SonwkxRWdMbQgSKQv1SPTrOcQ80+tu9m3QpmldHYvpkVohDb/30xdmqa7Q+b3K4H8+yPRaIuxod+6pNsceyGpF+Ih5W17qpn0xNt/Gyb2SkvcJMvSwZ15wtifRI7unKKXdEVMmATqAcUwUTRxO64mO1nVyJO9eodeKABWqePBdGHH7xQZ2wj5Wmx0Rva5ydKsyxNy1bjL/dnNwAlkbtZmCRsIEdgf2z58URlHLJHbLw/luMYJ9+B4uWs2QqzPVQQFNOnvUw9wvURtqof8zSKL4XoVCLKMIpy/gSOmRdZn2PbM83zNucQe/HFPstUZ19O1GakE4evuujijHcMEjhS9HSyZCCKUn4KttBcLvycPc0vloTkyZG07fN+aZbj+Q7KiLdHMn4z++gB54whE6ClHCFYwN8oV/ngHtmz0uWkS8A+ci1kxKu0TToO0lohYn/lzznT4Vv9gC0QkM846tGqn6JjAwQ8Y=");
        SkyblockEntity.livingEntity.addEntity(entity, this);
        updateNameTag();
        r.runTaskTimer(Main.getMain(), 5, 5);
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return Tools.mapOf(List.of(EndItems.Items.HolyDragonFragment.getItem()), List.of(3));
    }
    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
        if (!healDone && !heal && health <= tenPers) {
            heal = true;
            entity.setAI(false);
            entity.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.GOLDEN_APPLE));
            for (Player p : Bukkit.getOnlinePlayers()) {
                PacketPlayOutEntityStatus status = new PacketPlayOutEntityStatus(craftEntity, (byte) 9);
                ((CraftPlayer) p).getHandle().b.sendPacket(status);
            }
            entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1, 1);
            new BukkitRunnable() {
                int i = 0;
                @Override
                public void run() {
                    if (entity == null || entity.isDead()) {
                        cancel();
                        return;
                    }
                    if(i == 7){
                        cancel();
                        entity.getWorld().spawnParticle(Particle.HEART, entity.getLocation().clone().add(0, entity.getEyeHeight() / 2, 0), 20, 0.6, entity.getEyeHeight() / 2, 0.6);
                        health += tenPers * 3;
                        tenPers -= tenPers * 0.1;
                        entity.setAI(true);
                        healDone = true;
                        heal = false;
                        entity.getEquipment().setItem(EquipmentSlot.HAND, theSword.build());
                        cooldown = new BukkitRunnable() {
                            @Override
                            public void run() {
                                healDone = false;
                            }
                        };
                        cooldown.runTaskLater(Main.getMain(), 100);
                        entity.getWorld().playSound(entity.getEyeLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
                    }else
                        entity.getWorld().playSound(entity.getEyeLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
                    i++;
                }
            }.runTaskTimer(Main.getMain(), 0, 4);
        }
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
