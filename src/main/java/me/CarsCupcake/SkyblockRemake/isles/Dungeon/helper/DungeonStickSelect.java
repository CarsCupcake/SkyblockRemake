package me.CarsCupcake.SkyblockRemake.isles.Dungeon.helper;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.TemplateItems;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DungeonStickSelect implements AbilityManager<PlayerInteractEvent> {
    public static boolean isStared = false;
    public static DungeonStickAbility.EntityType entityType = DungeonStickAbility.EntityType.Melee;
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        click(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        return false;
    }
    public void click(SkyblockPlayer player) {
        GUI gui = new GUI(gen());
        gui.setCanceled(true);
        gui.inventoryClickAction(11, type -> {
            isStared = !isStared;
            click(player);
        });
        gui.inventoryClickAction(15, type -> {
            if(type == ClickType.LEFT || type == ClickType.SHIFT_LEFT) {
                entityType = switch (entityType) {
                    case Melee -> DungeonStickAbility.EntityType.Arch;
                    case Arch -> DungeonStickAbility.EntityType.MiniBoss;
                    case MiniBoss -> DungeonStickAbility.EntityType.Melee;
                };
            }else if(type == ClickType.RIGHT || type == ClickType.SHIFT_RIGHT) {
                entityType = switch (entityType) {
                    case Melee -> DungeonStickAbility.EntityType.MiniBoss;
                    case Arch -> DungeonStickAbility.EntityType.Melee;
                    case MiniBoss -> DungeonStickAbility.EntityType.Arch;
                };
            }
            click(player);
        });
        gui.showGUI(SkyblockPlayer.getSkyblockPlayer(player));
    }
    private Inventory gen() {
        return new InventoryBuilder(3, "Select").fill(TemplateItems.EmptySlot.getItem()).setItem(11, new ItemBuilder(Material.NETHER_STAR)
                        .setName("§aStared: " + isStared)
                        .setGlint(isStared)
                        .build())
                .setItem(15, new ItemBuilder(Material.IRON_SWORD)
                        .setName("§aMob")
                        .addAllLore(
                                "§" + ((entityType == DungeonStickAbility.EntityType.Melee) ? "a" : "7") + "‣ Melee",
                                "§" + ((entityType == DungeonStickAbility.EntityType.Arch) ? "a" : "7") + "‣ Archer",
                                "§" + ((entityType == DungeonStickAbility.EntityType.MiniBoss) ? "a" : "7") + "‣ MiniBoss"
                        )
                        .build())
                .build();
    }
}
