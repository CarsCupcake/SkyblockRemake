package me.CarsCupcake.SkyblockRemake.isles.Dungeon.MiniBoss.LostAdventurers;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.End.EndItems;

import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.List;

public class SuperiorAdventurer extends LostAdventurer{
    public SuperiorAdventurer(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public void spawn(Location loc) {
        craftEntity = new LostAdventurer.SBEntity(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(craftEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        entity = (LivingEntity) craftEntity.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.getEquipment().setItem(EquipmentSlot.HEAD, EndItems.Items.SuperiorHelmet.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.CHEST, EndItems.Items.SuperiorChestplate.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.LEGS, EndItems.Items.SuperiorLeggings.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.FEET, EndItems.Items.SuperiorBoots.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.HAND, theSword.build());
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
        DiguestMobsManager.createEntity(entity, "ewogICJ0aW1lc3RhbXAiIDogMTYwMTgxMzk1MzcwMiwKICAicHJvZmlsZUlkIiA6ICI3ZGEyYWIzYTkzY2E0OGVlODMwNDhhZmMzYjgwZTY4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHb2xkYXBmZWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZhMDM4M2E1Mjc4MDNkOTliNjYyYWQxOGJjYWM3OGMxMTkyNTBlYmJmYjE0NDc1YjRlYjRkNGE2NjI3OTZiNCIKICAgIH0KICB9Cn0=", "UWEpro7nRuOTopH9XzNksRQNC1Uq+vtjGkmb7/x8gsVjD3ealtJDZZvoe1Mi46ImNFnJs1EiEWetQKBQBAn1IYT59N2Xp1HNP1kseQDSFCnpF60eAxnAfZbClN6+8o4/c8PVif7ohmh3RG9X0mvX83FM7fe2nd5jchyCeIV591HFQiOQwDgMH+YFAVr/nwPk6HYn7ud0sGDz8aDnKYw4JkxMUmPHL6YKBX1cWQYZ1ot9d15uhHvgmV1vTx6ezTF0GHfsVuCXyfwVK1g2zyK7vZqpaRy+lMdNjiq1rYF9OncyJ0auqAOlsQMlfgDkau3fDiZ7fDr68arx9UvL9nAmz2xyfQ/qF9nXtuBUFIEkVbVpvs3/SA3SYNm9sO4JmQv1gcUP0nCCj+ZaWUC56yACoUTId+WnYDjzHfyMKR1+Td2z+anP6cdlaT1N8XsQEUPkD6Z8E7kuJjBtvq0nZPc92ndvie1lDD9bfgtV0TXF0o750+AzZslx27zhdioNVsjy5o2XUn6PNDr+4uNOQPb/AOSxDgu8mjTTEQmOD2UHBrqsXqnnt6CAwiB+QNi4VOhvQSa9C6CKFuvNyH2zBk624NcUp4SIqrx6XVsVNa3gXCURSJ61Ri+TRtOXxceMoH7ih2u2JVPU3K+fHqURN7guMEFeQpx4nXpwQkfhETDkrac=");
        SkyblockEntity.livingEntity.addEntity(entity, this);
        updateNameTag();
        r.runTaskTimer(Main.getMain(), 5, 5);
    }
    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return Tools.mapOf(List.of(EndItems.Items.SuperiorDragonFragment.getItem()), List.of(2));
    }

    @Override
    protected int healthFromFloor(int floor, boolean master) {
        return switch (floor){
            case 9 -> (master) ? 112_000_000 : 2_800_000;
            case 10 -> (master) ? 160_000_000 : 7_000_000;
            default -> throw new IllegalArgumentException("Not Possible!");
        };
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return switch (floor){
            case 9 -> (master) ? 280_000 :8_400;
            case 10 -> (master) ? 240_000 : 17_600;
            default -> throw new IllegalArgumentException("Not Possible!");
        };
    }

    @Override
    protected double defenseFromFloor(int floor, boolean master) {
        return 150;
    }
}
