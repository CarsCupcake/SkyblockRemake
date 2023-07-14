package me.CarsCupcake.SkyblockRemake.Skyblock.player.wardrobe;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Container;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.TemplateItems;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;

public class WardrobeSlot {
    private static final int baseSlots = 18;
    private final SkyblockPlayer player;
    private static final Material[] EMPTY_MATERIALS = {Material.RED_STAINED_GLASS_PANE, Material.ORANGE_STAINED_GLASS_PANE, Material.YELLOW_STAINED_GLASS_PANE, Material.LIME_STAINED_GLASS_PANE, Material.GREEN_STAINED_GLASS_PANE, Material.LIGHT_BLUE_STAINED_GLASS_PANE, Material.BLUE_STAINED_GLASS_PANE,Material.MAGENTA_STAINED_GLASS_PANE, Material.PURPLE_STAINED_GLASS_PANE};
    private final int id;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private boolean isEquiped = false;
    public WardrobeSlot(int id, SkyblockPlayer player){
        this.player = player;
        this.id = id;
        load();
    }
    public void save(){
        CustomConfig customConfig = new CustomConfig(player, "warderobe\\" + id);
        customConfig.get().set("helmet", helmet);
        customConfig.get().set("chestplate", chestplate);
        customConfig.get().set("leggings", leggings);
        customConfig.get().set("boots", boots);
        customConfig.get().set("equiped", isEquiped);
        customConfig.save();
    }
    public void load(){
        CustomConfig customConfig = new CustomConfig(player, "warderobe\\" + id);
        isEquiped = customConfig.get().getBoolean("equiped", false);
        if(isEquiped){
            helmet = player.getEquipment().getHelmet();
            chestplate = player.getEquipment().getChestplate();
            leggings = player.getEquipment().getLeggings();
            boots = player.getEquipment().getBoots();
        }else {
            helmet = customConfig.get().getItemStack("helmet", null);
            chestplate = customConfig.get().getItemStack("chestplate", null);
            leggings = customConfig.get().getItemStack("leggings", null);
            boots = customConfig.get().getItemStack("boots", null);
        }
    }
    public void draw(InventoryBuilder inventory, Material empty){
        List<Integer> slot = slots();
        inventory.setItem(slot.get(0), (helmet != null) ? helmet : new ItemBuilder(empty).setName("§aSlot " + (id + 1) + " Helmet").build());
        inventory.setItem(slot.get(1), (chestplate != null) ? chestplate : new ItemBuilder(empty).setName("§aSlot " + (id + 1) + " Chestplate").build());
        inventory.setItem(slot.get(2), (leggings != null) ? leggings : new ItemBuilder(empty).setName("§aSlot " + (id + 1) + " Leggings").build());
        inventory.setItem(slot.get(3), (boots != null) ? boots : new ItemBuilder(empty).setName("§aSlot " + (id + 1) + " Boots").build());
        inventory.setItem(slot.get(3) + 9, new ItemBuilder((isEquiped) ? Material.LIME_DYE : Material.MAGENTA_DYE).setName("§7Slot " + (id + 1)).build());
    }
    public void draw(Inventory inventory, Material empty){
        List<Integer> slot = slots();
        inventory.setItem(slot.get(0), (helmet != null) ? helmet : new ItemBuilder(empty).setName("§aSlot " + (id + 1) + " Helmet").build());
        inventory.setItem(slot.get(1), (chestplate != null) ? chestplate : new ItemBuilder(empty).setName("§aSlot " + (id + 1) + " Chestplate").build());
        inventory.setItem(slot.get(2), (leggings != null) ? leggings : new ItemBuilder(empty).setName("§aSlot " + (id + 1) + " Leggings").build());
        inventory.setItem(slot.get(3), (boots != null) ? boots : new ItemBuilder(empty).setName("§a Slot " + (id + 1) + " Boots").build());
        inventory.setItem(slot.get(3) + 9, new ItemBuilder((isEquiped) ? Material.LIME_DYE : Material.MAGENTA_DYE).setName("§7Slot " + (id + 1)).build());
    }
    public boolean isEmpty(){
        return helmet == null && chestplate == null && leggings == null && boots == null;
    }
    public void equip(Player player){
        if(player.getEquipment().getHelmet() != null) player.getInventory().addItem(player.getEquipment().getHelmet());
        player.getEquipment().setHelmet(helmet);
        if(player.getEquipment().getChestplate() != null) player.getInventory().addItem(player.getEquipment().getChestplate());
        player.getEquipment().setChestplate(chestplate);
        if(player.getEquipment().getLeggings() != null) player.getInventory().addItem(player.getEquipment().getLeggings());
        player.getEquipment().setLeggings(leggings);
        if(player.getEquipment().getBoots() != null) player.getInventory().addItem(player.getEquipment().getBoots());
        player.getEquipment().setBoots(boots);
        isEquiped = true;
        player.getPlayer().updateInventory();
    }
    public void unequip(){
        player.getEquipment().setHelmet(null);
        player.getEquipment().setChestplate(null);
        player.getEquipment().setLeggings(null);
        player.getEquipment().setBoots(null);
        isEquiped = false;
    }
    @Unmodifiable
    @NotNull
    private List<Integer> slots(){
        int min = (int) ((id) / 9.0);
        int baseSlot = id - (9 * min);
        return List.of(baseSlot, baseSlot + 9, baseSlot + 18, baseSlot + 27);
    }

    @Override
    public String toString() {
        return "WardrobeSlot[id:" + id + "]";
    }

    public static void openWardrobe(SkyblockPlayer player){
        List<Inventory> invs = new ArrayList<>();
        InventoryBuilder builder = null;
        Container<WardrobeSlot> equiped = new Container<>();
        List<HashMap<Integer, RunnableWithParam<InventoryClickEvent>>> listeners = new ArrayList<>();
        Set<WardrobeSlot> slots = new HashSet<>();
        for (int i = 0; i < baseSlots; i++) {
            WardrobeSlot slot = new WardrobeSlot(i, player);
            slots.add(slot);
            if(slot.isEquiped) equiped.setContent(slot);
            if(i % 9 == 0){
                if(builder != null) invs.add(builder.build());
                builder = new InventoryBuilder(6, "Wardrobe");
                listeners.add(new HashMap<>());
            }
            Material m = EMPTY_MATERIALS[i - (9 * ((int) ((i) / 9.0)))];
            slot.draw(builder, m);
            List<Integer> sus = slot.slots();
            getLast(listeners).put(sus.get(0), event -> {
                if(slot.isEquiped){
                    player.sendMessage("§cCannot modify an equiped slot");
                    return;
                }

                if(event.getCursor() != null && event.getCursor().getType() != Material.AIR){
                    if(Items.SkyblockItems.get(ItemHandler.getOrDefaultPDC("id", event.getView().getCursor(), PersistentDataType.STRING, "DIRT")).type != ItemType.Helmet){
                        player.sendMessage("§cWrong type!");
                        return;
                    }
                    if(slot.helmet != null) {
                        event.setCancelled(false);
                        slot.helmet = event.getCursor();
                    }
                    else {
                        event.setCurrentItem(event.getView().getCursor());
                        event.getView().setCursor(null);
                        slot.helmet = event.getCurrentItem();
                    }
                }else {
                    if(slot.helmet != null) {
                        event.getView().setCursor(slot.helmet);
                        slot.helmet = null;
                    }
                }
                if(event.isCancelled())
                    slot.draw(event.getView().getTopInventory(), m);
            });
            getLast(listeners).put(sus.get(1), event -> {
                if(slot.isEquiped){
                    player.sendMessage("§cCannot modify an equiped slot");
                    return;
                }

                if(event.getCursor() != null && event.getCursor().getType() != Material.AIR){
                    if(Items.SkyblockItems.get(ItemHandler.getOrDefaultPDC("id", event.getView().getCursor(), PersistentDataType.STRING, "DIRT")).type != ItemType.Chestplate){
                        player.sendMessage("§cWrong type!");
                        return;
                    }
                    if(slot.chestplate != null) {
                        event.setCancelled(false);
                        slot.chestplate = event.getCursor();
                    }
                    else {
                        event.setCurrentItem(event.getView().getCursor());
                        event.getView().setCursor(null);
                        slot.chestplate = event.getCurrentItem();
                    }
                }else {
                    if(slot.chestplate != null) {
                        event.getView().setCursor(slot.helmet);
                        slot.chestplate = null;
                    }
                }
                if(event.isCancelled())
                    slot.draw(event.getView().getTopInventory(), m);
            });
            getLast(listeners).put(sus.get(2), event -> {
                if(slot.isEquiped){
                    player.sendMessage("§cCannot modify an equiped slot");
                    return;
                }

                if(event.getCursor() != null && event.getCursor().getType() != Material.AIR){
                    if(Items.SkyblockItems.get(ItemHandler.getOrDefaultPDC("id", event.getView().getCursor(), PersistentDataType.STRING, "DIRT")).type != ItemType.Leggings){
                        player.sendMessage("§cWrong type!");
                        return;
                    }
                    if(slot.leggings != null) {
                        event.setCancelled(false);
                        slot.leggings = event.getCursor();
                    }
                    else {
                        event.setCurrentItem(event.getView().getCursor());
                        event.getView().setCursor(null);
                        slot.leggings = event.getCurrentItem();
                    }
                }else {
                    if(slot.leggings != null) {
                        event.getView().setCursor(slot.leggings);
                        slot.leggings = null;
                    }
                }
                if(event.isCancelled())
                    slot.draw(event.getView().getTopInventory(), m);
            });
            getLast(listeners).put(sus.get(3), event -> {
                if(slot.isEquiped){
                    player.sendMessage("§cCannot modify an equiped slot");
                    return;
                }
                if(event.getCursor() != null && event.getCursor().getType() != Material.AIR){
                    if(Items.SkyblockItems.get(ItemHandler.getOrDefaultPDC("id", event.getView().getCursor(), PersistentDataType.STRING, "DIRT")).type != ItemType.Boots){
                        player.sendMessage("§cWrong type!");
                        return;
                    }
                    if(slot.boots != null) {
                        event.setCancelled(false);
                        slot.boots = event.getCursor();
                    }
                    else {
                        event.setCurrentItem(event.getView().getCursor());
                        event.getView().setCursor(null);
                        slot.boots = event.getCurrentItem();
                    }
                }else {
                    if(slot.boots != null) {
                        event.getView().setCursor(slot.helmet);
                        slot.boots = null;
                    }
                }
                if(event.isCancelled())
                    slot.draw(event.getView().getTopInventory(), m);
            });
            getLast(listeners).put(sus.get(3) + 9, event -> {
                if(slot.isEquiped) {
                    slot.unequip();
                    equiped.setContent(null);
                }else {
                    if(equiped.getContent() != null) {
                        equiped.getContent().unequip();
                        int id = equiped.getContent().id;
                        equiped.getContent().draw(event.getView().getTopInventory(), EMPTY_MATERIALS[id - (9 * ((int) ((id) / 9.0)))]);
                    }
                    slot.equip((Player) event.getWhoClicked());
                    equiped.setContent(slot);
                }
                slot.draw(event.getView().getTopInventory(), m);
            });
        }
        invs.add(builder.build());
        List<GUI> guis = new ArrayList<>();
        int i = 0;
        for (Inventory inv : invs){
            final int I = i;
            GUI gui = new GUI(inv);
            guis.add(gui);
            gui.bypassClickSpeed();
            if(i != 0) {
                gui.getInventory().setItem(45, TemplateItems.BackArrow.getItem());
                gui.inventoryClickAction(45, type -> guis.get(I - 1).showGUI(player));
            }
            if(i + 1 != invs.size()) {
                gui.getInventory().setItem(53, TemplateItems.NextArrow.getItem());
                gui.inventoryClickAction(53, type -> guis.get(I + 1).showGUI(player));
            }
            gui.setGeneralAction((slot, actionType, type) -> actionType == GUI.GUIActions.Click);
            gui.setDirectInventoryClickAction(listeners.get(i));
            gui.closeAction(type -> {
                for (WardrobeSlot slot : slots) slot.save();
            });
            i++;
        }
        guis.get(0).showGUI(player);
    }
    private static HashMap<Integer, RunnableWithParam<InventoryClickEvent>> getLast(List<HashMap<Integer, RunnableWithParam<InventoryClickEvent>>> m){
        return m.get(m.size() - 1);
    }
}
