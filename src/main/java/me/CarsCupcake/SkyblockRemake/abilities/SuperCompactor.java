package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.AccessoryListener;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.ArtifactAbility;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.TemplateItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_17_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_17_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class SuperCompactor extends ArtifactAbility implements Listener {
    public static final HashMap<SkyblockPlayer, Set<SuperCompactorRecipe>> active = new HashMap<>();
    private static final HashMap<String, SuperCompactorRecipe> registered = new HashMap<>();

    public static void init() {
        ItemManager manager = new ItemManager("Personal Compactor 4000", "PERSONAL_COMPACTOR_4000", ItemType.Accessory, Material.DROPPER, ItemRarity.UNCOMMON);
        manager.setUnstackeble(true);
        manager.setArtifactAbility(new SuperCompactor());
        manager.addAbility(new S4000(), AbilityType.RightClick, null, 0, 0);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Personal Compactor 5000", "PERSONAL_COMPACTOR_5000", ItemType.Accessory, Material.DROPPER, ItemRarity.RARE);
        manager.setUnstackeble(true);
        manager.setArtifactAbility(new SuperCompactor());
        manager.addAbility(new S5000(), AbilityType.RightClick, null, 0, 0);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Personal Compactor 6000", "PERSONAL_COMPACTOR_6000", ItemType.Accessory, Material.DROPPER, ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.setArtifactAbility(new SuperCompactor());
        manager.addAbility(new S6000(), AbilityType.RightClick, null, 0, 0);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Personal Compactor 7000", "PERSONAL_COMPACTOR_7000", ItemType.Accessory, Material.DROPPER, ItemRarity.LEGENDARY);
        manager.setUnstackeble(true);
        manager.setArtifactAbility(new SuperCompactor());
        manager.addAbility(new S7000(), AbilityType.RightClick, null, 0, 0);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        Items.SkyblockItems.put(manager.itemID, manager);
    }

    public static class S4000 implements AbilityManager<PlayerInteractEvent> {
        private void generateInv(ItemStack item, SkyblockPlayer player) {
            Set<NamespacedKey> active = ItemHandler.getOrDefaultPDC("active", item, PersistentDataType.TAG_CONTAINER, new CraftPersistentDataContainer(Map.of(), new CraftPersistentDataTypeRegistry())).getKeys();
            Iterator<NamespacedKey> keys = active.iterator();
            System.out.println(keys);
            InventoryBuilder builder = new InventoryBuilder(3, "Personal Compactor 4000").fill(TemplateItems.EmptySlot.getItem());
            boolean hasFree;
            if (keys.hasNext()) {
                builder.setItem(Items.SkyblockItems.get(keys.next().getKey().toUpperCase()), 13);
                hasFree = false;
            }
            else {
                builder.setItem(new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("§aAuto Craft Slot #1").build(), 13);
                hasFree = true;
            }
            GUI gui = new GUI(builder.build());
            gui.setCanceled(true);
            gui.setGeneralAction((slot, actionType, type) -> {
                if (actionType == GUI.GUIActions.Click) {
                    ItemStack s = gui.getInventory().getItem(slot);
                    if (s.getType() == Material.BLACK_STAINED_GLASS_PANE) return true;
                    if (s.getType() == Material.LIME_STAINED_GLASS_PANE) return true;
                    PersistentDataContainer data = ItemHandler.getPDC("active", item, PersistentDataType.TAG_CONTAINER);
                    data.remove(new NamespacedKey(Main.getMain(),ItemHandler.getPDC("id", s, PersistentDataType.STRING)));
                    ItemHandler.setPDC("active", item, PersistentDataType.TAG_CONTAINER, data);
                    generateInv(item, player);
                    AccessoryListener.startupAbilitys(player);
                } else if (actionType == GUI.GUIActions.PlayerClick) {
                    if(!hasFree) return true;
                    ItemStack s = player.getInventory().getItem(slot);
                    String id = ItemHandler.getPDC("id", s, PersistentDataType.STRING);
                    if(!registered.containsKey(id)) return true;
                    PersistentDataContainer data = ItemHandler.getOrDefaultPDC("active", item, PersistentDataType.TAG_CONTAINER, new CraftPersistentDataContainer(Map.of(), new CraftPersistentDataTypeRegistry()));
                    data.set(new NamespacedKey(Main.getMain(),ItemHandler.getPDC("id", s, PersistentDataType.STRING)), PersistentDataType.INTEGER, 1);
                    ItemHandler.setPDC("active", item, PersistentDataType.TAG_CONTAINER, data);
                    generateInv(item, player);
                    AccessoryListener.startupAbilitys(player);
                }
                return true;
            });
            gui.showGUI(player);
        }

        @Override
        public boolean triggerAbility(PlayerInteractEvent event) {
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
            generateInv(player.getItemInHand(), player);
            return false;
        }
    }
    public static class S5000 implements AbilityManager<PlayerInteractEvent> {
        private void generateInv(ItemStack item, SkyblockPlayer player) {
            Set<NamespacedKey> active = ItemHandler.getOrDefaultPDC("active", item, PersistentDataType.TAG_CONTAINER, new CraftPersistentDataContainer(Map.of(), new CraftPersistentDataTypeRegistry())).getKeys();
            Iterator<NamespacedKey> keys = active.iterator();
            System.out.println(keys);
            InventoryBuilder builder = new InventoryBuilder(3, "Personal Compactor 5000").fill(TemplateItems.EmptySlot.getItem());
            for (int i = 0; i < 3; i++){
                if (keys.hasNext()) {
                    builder.setItem(Items.SkyblockItems.get(keys.next().getKey().toUpperCase()), i + 12);
                }
                else {
                    builder.setItem(new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("§aAuto Craft Slot #" + (i + 1)).build(), i + 12);
                }
            }

            GUI gui = new GUI(builder.build());
            gui.setCanceled(true);
            gui.setGeneralAction((slot, actionType, type) -> {
                if (actionType == GUI.GUIActions.Click) {
                    ItemStack s = gui.getInventory().getItem(slot);
                    if (s.getType() == Material.BLACK_STAINED_GLASS_PANE) return true;
                    if (s.getType() == Material.LIME_STAINED_GLASS_PANE) return true;
                    PersistentDataContainer data = ItemHandler.getPDC("active", item, PersistentDataType.TAG_CONTAINER);
                    data.remove(new NamespacedKey(Main.getMain(),ItemHandler.getPDC("id", s, PersistentDataType.STRING)));
                    ItemHandler.setPDC("active", item, PersistentDataType.TAG_CONTAINER, data);
                    generateInv(item, player);
                    AccessoryListener.startupAbilitys(player);
                } else if (actionType == GUI.GUIActions.PlayerClick) {
                    if(active.size() >= 3) return true;
                    ItemStack s = player.getInventory().getItem(slot);
                    String id = ItemHandler.getPDC("id", s, PersistentDataType.STRING);
                    if(!registered.containsKey(id)) return true;
                    PersistentDataContainer data = ItemHandler.getOrDefaultPDC("active", item, PersistentDataType.TAG_CONTAINER, new CraftPersistentDataContainer(Map.of(), new CraftPersistentDataTypeRegistry()));
                    data.set(new NamespacedKey(Main.getMain(),ItemHandler.getPDC("id", s, PersistentDataType.STRING)), PersistentDataType.INTEGER, 1);
                    ItemHandler.setPDC("active", item, PersistentDataType.TAG_CONTAINER, data);
                    generateInv(item, player);
                    AccessoryListener.startupAbilitys(player);
                }
                return true;
            });
            gui.showGUI(player);
        }
        @Override
        public boolean triggerAbility(PlayerInteractEvent event) {
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
            generateInv(player.getItemInHand(), player);
            return false;
        }
    }

    public static class S6000 implements AbilityManager<PlayerInteractEvent> {
        private void generateInv(ItemStack item, SkyblockPlayer player) {
            Set<NamespacedKey> active = ItemHandler.getOrDefaultPDC("active", item, PersistentDataType.TAG_CONTAINER, new CraftPersistentDataContainer(Map.of(), new CraftPersistentDataTypeRegistry())).getKeys();
            Iterator<NamespacedKey> keys = active.iterator();
            System.out.println(keys);
            InventoryBuilder builder = new InventoryBuilder(3, "Personal Compactor 6000").fill(TemplateItems.EmptySlot.getItem());
            for (int i = 0; i < 7; i++){
                if (keys.hasNext()) {
                    builder.setItem(Items.SkyblockItems.get(keys.next().getKey().toUpperCase()), i + 10);
                }
                else {
                    builder.setItem(new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("§aAuto Craft Slot #" + (i + 1)).build(), i + 10);
                }
            }

            GUI gui = new GUI(builder.build());
            gui.setCanceled(true);
            gui.setGeneralAction((slot, actionType, type) -> {
                if (actionType == GUI.GUIActions.Click) {
                    ItemStack s = gui.getInventory().getItem(slot);
                    if (s.getType() == Material.BLACK_STAINED_GLASS_PANE) return true;
                    if (s.getType() == Material.LIME_STAINED_GLASS_PANE) return true;
                    PersistentDataContainer data = ItemHandler.getPDC("active", item, PersistentDataType.TAG_CONTAINER);
                    data.remove(new NamespacedKey(Main.getMain(),ItemHandler.getPDC("id", s, PersistentDataType.STRING)));
                    ItemHandler.setPDC("active", item, PersistentDataType.TAG_CONTAINER, data);
                    generateInv(item, player);
                    AccessoryListener.startupAbilitys(player);
                } else if (actionType == GUI.GUIActions.PlayerClick) {
                    if(active.size() >= 7) return true;
                    ItemStack s = player.getInventory().getItem(slot);
                    String id = ItemHandler.getPDC("id", s, PersistentDataType.STRING);
                    if(!registered.containsKey(id)) return true;
                    PersistentDataContainer data = ItemHandler.getOrDefaultPDC("active", item, PersistentDataType.TAG_CONTAINER, new CraftPersistentDataContainer(Map.of(), new CraftPersistentDataTypeRegistry()));
                    data.set(new NamespacedKey(Main.getMain(),ItemHandler.getPDC("id", s, PersistentDataType.STRING)), PersistentDataType.INTEGER, 1);
                    ItemHandler.setPDC("active", item, PersistentDataType.TAG_CONTAINER, data);
                    generateInv(item, player);
                    AccessoryListener.startupAbilitys(player);
                }
                return true;
            });
            gui.showGUI(player);
        }
        @Override
        public boolean triggerAbility(PlayerInteractEvent event) {
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
            generateInv(player.getItemInHand(), player);
            return false;
        }
    }

        public static class S7000 implements AbilityManager<PlayerInteractEvent> {
            private void generateInv(ItemStack item, SkyblockPlayer player) {
                Set<NamespacedKey> active = ItemHandler.getOrDefaultPDC("active", item, PersistentDataType.TAG_CONTAINER, new CraftPersistentDataContainer(Map.of(), new CraftPersistentDataTypeRegistry())).getKeys();
                Iterator<NamespacedKey> keys = active.iterator();
                System.out.println(keys);
                InventoryBuilder builder = new InventoryBuilder(3, "Personal Compactor 6000").fill(TemplateItems.EmptySlot.getItem());
                for (int i = 0; i < 12; i++){
                    if (keys.hasNext()) {
                        builder.setItem(Items.SkyblockItems.get(keys.next().getKey().toUpperCase()), i + 10 + ((i > 6) ? 3 : 0));
                    }
                    else {
                        builder.setItem(new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("§aAuto Craft Slot #" + (i + 1)).build(), i + 10 + ((i > 6) ? 3 : 0));
                    }
                }

                GUI gui = new GUI(builder.build());
                gui.setCanceled(true);
                gui.setGeneralAction((slot, actionType, type) -> {
                    if (actionType == GUI.GUIActions.Click) {
                        ItemStack s = gui.getInventory().getItem(slot);
                        if (s.getType() == Material.BLACK_STAINED_GLASS_PANE) return true;
                        if (s.getType() == Material.LIME_STAINED_GLASS_PANE) return true;
                        PersistentDataContainer data = ItemHandler.getPDC("active", item, PersistentDataType.TAG_CONTAINER);
                        data.remove(new NamespacedKey(Main.getMain(),ItemHandler.getPDC("id", s, PersistentDataType.STRING)));
                        ItemHandler.setPDC("active", item, PersistentDataType.TAG_CONTAINER, data);
                        generateInv(item, player);
                        AccessoryListener.startupAbilitys(player);
                    } else if (actionType == GUI.GUIActions.PlayerClick) {
                        if(active.size() >= 12) return true;
                        ItemStack s = player.getInventory().getItem(slot);
                        String id = ItemHandler.getPDC("id", s, PersistentDataType.STRING);
                        if(!registered.containsKey(id)) return true;
                        PersistentDataContainer data = ItemHandler.getOrDefaultPDC("active", item, PersistentDataType.TAG_CONTAINER, new CraftPersistentDataContainer(Map.of(), new CraftPersistentDataTypeRegistry()));
                        data.set(new NamespacedKey(Main.getMain(),ItemHandler.getPDC("id", s, PersistentDataType.STRING)), PersistentDataType.INTEGER, 1);
                        ItemHandler.setPDC("active", item, PersistentDataType.TAG_CONTAINER, data);
                        generateInv(item, player);
                        AccessoryListener.startupAbilitys(player);
                    }
                    return true;
                });
                gui.showGUI(player);
            }

        @Override
        public boolean triggerAbility(PlayerInteractEvent event) {
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
            generateInv(player.getItemInHand(), player);
            return false;
        }
    }

    public static void registerRecipe(SuperCompactorRecipe recipe) {
        registered.put(recipe.out, recipe);
    }

    @Override
    public void start(SkyblockPlayer player, ItemStack item) {
        Set<NamespacedKey> active = ItemHandler.getOrDefaultPDC("active", item, PersistentDataType.TAG_CONTAINER, new CraftPersistentDataContainer(Map.of(), new CraftPersistentDataTypeRegistry())).getKeys();
        Set<SuperCompactorRecipe> recipes = new HashSet<>();
        for (NamespacedKey key : active)
            recipes.add(registered.get(key.getKey().toUpperCase()));
        SuperCompactor.active.put(player, recipes);
        for (SuperCompactorRecipe recipe : recipes)
            recipe.check(player);
    }

    @Override
    public void stop(SkyblockPlayer player) {

    }

    public record SuperCompactorRecipe(String base, String out, int amount) {
        public void check(SkyblockPlayer player) {
            Set<Integer> pointer = new HashSet<>();
            int toNext = amount;
            ItemStack[] inv = player.getInventory().getContents();
            int i = -1;
            for (ItemStack item : inv) {
                i++;
                if (!ItemHandler.hasPDC("id", item, PersistentDataType.STRING)) continue;
                if (!ItemHandler.getPDC("id", item, PersistentDataType.STRING).equals(base)) continue;
                if (toNext - item.getAmount() <= 0) {
                    int alrRem = 0;
                    for (int p : pointer) {
                        ItemStack pI = inv[p];
                        alrRem += pI.getAmount();
                        pI.setAmount(0);
                    }
                    int iA = item.getAmount();
                    item.setAmount(amount - alrRem);
                    player.addItem(Items.SkyblockItems.get(out));
                    pointer.clear();
                    iA -= alrRem;
                    toNext = amount;
                    if (iA > 0) {
                        pointer.add(i);
                        toNext -= iA;
                    }
                } else {
                    pointer.add(i);
                    toNext -= item.getAmount();
                }

            }
        }
    }
}
