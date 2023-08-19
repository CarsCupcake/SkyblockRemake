package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.LostAdventurer;
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

public class YoungAdventurer extends LostAdventurer {
    public YoungAdventurer(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public void spawn(Location loc) {
        craftEntity = new SBEntity(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(craftEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        entity = (LivingEntity) craftEntity.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.getEquipment().setItem(EquipmentSlot.HEAD, EndItems.Items.YoungHelmet.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.CHEST, EndItems.Items.YoungChestplate.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.LEGS, EndItems.Items.YoungLeggings.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.FEET, EndItems.Items.YoungBoots.getItem().getRawItemStack());
        entity.getEquipment().setItem(EquipmentSlot.HAND, theSword.build());
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
        new PlayerDisguise(entity, "ewogICJ0aW1lc3RhbXAiIDogMTU5OTMyODYyMzc0NiwKICAicHJvZmlsZUlkIiA6ICJkZTU3MWExMDJjYjg0ODgwOGZlN2M5ZjQ0OTZlY2RhZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNSEZfTWluZXNraW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWU2MjZkYjEyZmFlNjJiZWRmMjRiMTE0ZDJiYTg1YmM1ZmQzYjA3ZDdjN2M5YzkxZjQ1ZGRmMTBkZmQ0MTc5IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=", "lJ5WWOjI53X4/NH96HhcVC+82kd4O3XBckrwomSDvo71jJuudYUVlRzUwCBkQ70YnX3r7kcAMf7Lk7a1qNBhTdJfswhsPZiIBfqxqTa0idOuGlLxJWGjSKB09LPgdvkkiKmwWcFnx799PqDjV5R9iXe/eCOUidMbh3HSsrc4l9byXErkiNf0uua/Tvr/zWk2EvqyvcdNziL+80Dpl225cCE4SDTIZVC2GJsSAK4HDtACD5/FCPxPf1aWcoOYjxOutqZ2g92rX71p6L/BynrWJVWSlrt4YhuXR1roorqMm6pw8s98LPQnzN/Clh4HsIypVKo9BRWPCEF+CGbT3Nqnm+KbBYHYk1xd8T2J8h85ufa8xyKLn2/VSsXmrhHl/mAz14jjeI5Fh7q1wYSvBJJcQHLRP7MaVa1WSDRGmx4rKyHUbriq4+joKltCN+yZ7UAsMvjyl6YrU93zl2W5L1HbOa3lKJv+ofwf+crDeC/GDwZQkkfLLhEBcrR+8vZvdtbZhnVZPngJrpYQt9pO/Ydf8OTK5fIZ/hzbprGl0BXL3sgtWvOUrjaoz5CQ0uvfetzuhcgQPisvXHt6t2VLwxItxfBvgmfl/pBHBAhqiekMclbdCIGLfuTxQ7s4WYUn6+NgLXaA24nFT1Ih5EGTXXhqqrvpvAC5I/+0745hnp4DNQc=");
        SkyblockEntity.livingEntity.addEntity(entity, this);
        updateNameTag();
        r.runTaskTimer(Main.getMain(), 5, 5);
    }

    @Override
    public HashMap<ItemManager, Integer> getGarantuedDrops(SkyblockPlayer player) {
        return Tools.mapOf(List.of(EndItems.Items.YoungDragonFragment.getItem()), List.of(3));
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
