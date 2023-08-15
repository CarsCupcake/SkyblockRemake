package me.CarsCupcake.SkyblockRemake.Items.blocks.customBlocks;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.blocks.CustomBlock;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Location2d;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.TemplateItems;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.type.Piston;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;


public class LogicalBlock extends CustomBlock implements Listener {
    private static final HashMap<String, Component> components = new HashMap<>();
    public static void init(){
        addComponent(new Wire());
    }
    private static void addComponent(Component component){
        components.put(component.id(), component);
    }
    public LogicalBlock(ItemManager represent) {
        super(Material.AMETHYST_BLOCK, represent);
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        GUI gui = new GUI(load(event.getClickedBlock()).build());
        gui.setCanceled(true);
        gui.closeAction(type -> apply(event.getClickedBlock(), gui.getInventory()));
        gui.showGUI(player);
    }
    private void apply(Block block, Inventory inventory){
        for (int i = 0; i < 45; i++){
            if(block.hasMetadata("Slot_" + i))
                block.removeMetadata("Slot_" + i, Main.getMain());
            ItemStack item = inventory.getItem(i);
            if(!item.hasItemMeta() || !ItemHandler.hasPDC("id", item, PersistentDataType.STRING)) continue;
            block.setMetadata("Slot_" + i,new FixedMetadataValue(Main.getMain(), ItemHandler.getPDC("id", item, PersistentDataType.STRING)));
        }
    }
    private InventoryBuilder load(Block block){
        InventoryBuilder builder = new InventoryBuilder(6, "Logic Block");
        builder.fill(TemplateItems.EmptySlot.getItem(), 45, 53);
        builder.setItem(new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setName("§aNorth Exit").build(), 4);
        builder.setItem(new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setName("§aEast Exit").build(), 26);
        builder.setItem(new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setName("§aSouth Exit").build(), 49);
        builder.setItem(new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setName("§aWest Exit").build(), 18);
        builder.setItem(new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§aInput").build(), 22);
        for (int i = 0; i < 45; i++){
            if(!block.hasMetadata("Slot_" + i)) continue;
            Component component = components.get(block.getMetadata("Slot_" + i).get(0).asString());
            builder.setItem(new ItemBuilder(component.getShow()).setName("§a" + component.name()).build(), i);
        }
        return builder;
    }
    private HashMap<Integer, Component> loadComponents(Block block){
        HashMap<Integer, Component> componentHashMap = new HashMap<>();
        for (int i = 0; i < 45; i++){
            if(!block.hasMetadata("Slot_" + i)) continue;
            Component component = components.get(block.getMetadata("Slot_" + i).get(0).asString());
            componentHashMap.put(i, component);
        }
        return componentHashMap;
    }
    private static boolean notOutOfBounds(int i){
        return i > -1 && (i < 45);
    }
    private static boolean notOutOfBounds(Location2d i){
        return notOutOfBounds(toSlot(i));
    }
    @EventHandler
    public void onRedstone(BlockRedstoneEvent event){
        if(event.getNewCurrent() == 0){
            event.getBlock().getRelative(BlockFace.NORTH).getState().update();
            event.getBlock().getRelative(BlockFace.EAST).getState().update();
            event.getBlock().getRelative(BlockFace.SOUTH).getState().update();
            event.getBlock().getRelative(BlockFace.WEST).getState().update();
            return;
        }
        HashMap<Integer, Component> componentMap = loadComponents(event.getBlock());
        HashMap<Location2d, Component> locationMap = toLocationMap(componentMap);
        Set<Location2d> moves = new HashSet<>();
        moves.add(toLocation(22));
        Set<Location2d> alreadyMoved = new HashSet<>();
        while (!moves.isEmpty()){
            for (Location2d l : new HashSet<>(moves)){
                moves.remove(l);
                alreadyMoved.add(l);
                Output output = Output.getOutput(toSlot(l));
                if(output != null){
                    output.activate(event.getBlock(), event.getNewCurrent());
                    continue;
                }
                if(!locationMap.containsKey(l)) continue;
                List<Location2d> nextGen = locationMap.get(l).onActivation(l);
                nextGen.removeIf(alreadyMoved::contains);
                moves.addAll(nextGen);
            }
        }
    }
    private <T> HashMap<Location2d, T> toLocationMap(HashMap<Integer, T> map){
        HashMap<Location2d, T> newMap = new HashMap<>();
        for (Integer i : map.keySet()){
            newMap.put(toLocation(i), map.get(i));
        }
        return newMap;
    }
    public interface Component{
        List<Location2d> onActivation(Location2d location);
        Material getShow();
        String id();
        String name();
        default Component leftClick(){
            return this;
        }
    }
    public static class Output{
        private final BlockFace face;
        private Output(BlockFace face){
            this.face = face;
        }
        public void activate(Block block, int power){
            Block out = block.getRelative(face);
            if(out instanceof AnaloguePowerable powerable){
                powerable.setPower(power);
            }
            if(out instanceof Piston piston && power > 0)
                piston.setExtended(true);
            if(power == 0)
                out.getState().update();
        }
        public static Output getOutput(int slot){
            switch (slot){
                case 4 -> {
                    return new Output(BlockFace.NORTH);
                }
                case 26 -> {
                    return new Output(BlockFace.EAST);
                }
                case 49 -> {
                    return new Output(BlockFace.SOUTH);
                }
                case 18 -> {
                    return new Output(BlockFace.WEST);
                }
            }
            return null;
        }
    }
    public static class Wire implements Component{

        @Override
        public List<Location2d> onActivation(Location2d location) {
            return validate(new ArrayList<>(List.of(location.clone().addX(1), location.clone().addX(-1), location.clone().addY(1), location.clone().addY(-1))));
        }

        @Override
        public Material getShow() {
            return Material.YELLOW_STAINED_GLASS_PANE;
        }

        @Override
        public String id() {
            return "wire";
        }

        @Override
        public String name() {
            return "Wire";
        }
    }
    private static List<Location2d> validate(List<Location2d> list){
        list.removeIf(l -> !notOutOfBounds(toSlot(l)));
        return list;
    }
    private static Location2d toLocation(int slot){
        int y = slot/9;
        int x = slot - (9*y);
        return new Location2d(x, y);
    }
    private static int toSlot(Location2d location){
        return (location.getY() * 9) + location.getX();
    }
}
