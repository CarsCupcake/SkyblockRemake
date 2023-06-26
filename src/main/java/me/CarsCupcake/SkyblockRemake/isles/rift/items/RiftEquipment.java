package me.CarsCupcake.SkyblockRemake.isles.rift.items;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.abilitys.ABILITIES;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class RiftEquipment {
    public static ItemManager RIFT_NECKLACE_INSIDE;
    public static void init() {
        RIFT_NECKLACE_INSIDE = new ItemManager("Rift Necklace", "RIFT_NECKLACE_INSIDE", ItemType.Necklace, ItemRarity.MYTHIC, "ewogICJ0aW1lc3RhbXAiIDogMTY0Nzg2ODQ3MDQxOCwKICAicHJvZmlsZUlkIiA6ICIwNjNhMTc2Y2RkMTU0ODRiYjU1MjRhNjQyMGM1YjdhNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJkYXZpcGF0dXJ5IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzljMDg5NDkzMWE1MTAzODdlNGY2MTM4MjhmMDIzOWNhNGQ5MDg1MzQ5OTYzN2Y2MDc5ZDM5MzI3ZjE0NmYwZTkiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==");
        RIFT_NECKLACE_INSIDE.setUnstackeble(true);
        RIFT_NECKLACE_INSIDE.setIsSkullValue(true);
        ABILITIES.registerEvent(new RiftNecklace());
    }
    static class RiftNecklace implements Listener {
        List<List<Bundle<Stats, Double>>> insideStatBoost = List.of(
                List.of(
                        new Bundle<>(Stats.RiftTime, 60d)
                ),
                List.of(
                        new Bundle<>(Stats.RiftTime, 110d)
                ),
                List.of(
                        new Bundle<>(Stats.RiftTime, 155d),
                        new Bundle<>(Stats.RiftInteligence, 5d),
                        new Bundle<>(Stats.RiftSpeed, 1d)
                ),
                List.of(
                        new Bundle<>(Stats.RiftTime, 200d),
                        new Bundle<>(Stats.RiftInteligence, 5d),
                        new Bundle<>(Stats.RiftSpeed, 2d),
                        new Bundle<>(Stats.ManaRegen, 10d)
                ),
                List.of(
                        new Bundle<>(Stats.RiftTime, 245d),
                        new Bundle<>(Stats.RiftInteligence, 10d),
                        new Bundle<>(Stats.RiftSpeed, 3d),
                        new Bundle<>(Stats.ManaRegen, 10d)
                ),
                List.of(
                        new Bundle<>(Stats.RiftTime, 270d),
                        new Bundle<>(Stats.RiftDamage, 1d),
                        new Bundle<>(Stats.RiftInteligence, 10d),
                        new Bundle<>(Stats.RiftSpeed, 4d),
                        new Bundle<>(Stats.ManaRegen, 15d)
                ),
                List.of(
                        new Bundle<>(Stats.RiftTime, 335d),
                        new Bundle<>(Stats.Hearts, 1d),
                        new Bundle<>(Stats.RiftDamage, 1d),
                        new Bundle<>(Stats.RiftInteligence, 10d),
                        new Bundle<>(Stats.RiftSpeed, 5d),
                        new Bundle<>(Stats.ManaRegen, 20d)
                )
        );
        @EventHandler
        public void onStatEvent(GetStatFromItemEvent event){
            if(ItemHandler.getPDC("id", event.getItem(), PersistentDataType.STRING).equals(RIFT_NECKLACE_INSIDE.itemID)){
                if(ServerType.getActiveType() != ServerType.Rift) return;
                RiftPlayer player = RiftPlayer.getRiftPlayer(event.getPlayer());
                int amount = player.getTimeCharms().size();
                if(amount == 0) return;
                if(amount > 7) amount = 7;
                amount--;
                for (Bundle<Stats, Double> b : insideStatBoost.get(amount))
                    if(b.getFirst() == event.getStat()) event.addValue(b.getLast());
            }
        }
    }

}
