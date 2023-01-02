package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.*;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals.*;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class F7Phase3 implements Listener {
    private final static Location[] tLocations = {new Location(Main.getMain().getServer().getWorld("world"), 111, 119.0, 79),new Location(Main.getMain().getServer().getWorld("world"), 111, 113.0, 73),new Location(Main.getMain().getServer().getWorld("world"), 89, 112.0, 92),new Location(Main.getMain().getServer().getWorld("world"), 89, 122.0, 101),new Location(Main.getMain().getServer().getWorld("world"), 68, 109.0, 121),new Location(Main.getMain().getServer().getWorld("world"), 59, 120.0, 122),new Location(Main.getMain().getServer().getWorld("world"), 40, 124.0, 122),new Location(Main.getMain().getServer().getWorld("world"), 47, 109.0, 121),new Location(Main.getMain().getServer().getWorld("world"), -2, 109.0, 112),new Location(Main.getMain().getServer().getWorld("world"), -2, 119.0, 93),new Location(Main.getMain().getServer().getWorld("world"), 19, 123.0, 93),new Location(Main.getMain().getServer().getWorld("world"), -2, 109.0, 77),new Location(Main.getMain().getServer().getWorld("world"), 44, 121.0, 29),new Location(Main.getMain().getServer().getWorld("world"), 41, 109.0, 29),new Location(Main.getMain().getServer().getWorld("world"), 72, 115.0, 48),new Location(Main.getMain().getServer().getWorld("world"), 67, 109.0, 29),new Location(Main.getMain().getServer().getWorld("world"), 39.5, 108.0, 143.5), new Location(Main.getMain().getServer().getWorld("world"), -2.5, 109.0, 112.5),new Location(Main.getMain().getServer().getWorld("world"), -2.5, 119.0, 93.5),new Location(Main.getMain().getServer().getWorld("world"), -2.5, 109.0, 77.5)};
    public static SimonSaysTerminal simonSaysTerminal;
    public static LightsTerminal lightsTerminal;
    public static ArrowShooting arrowShoot;
    public static ArrowPointing arrowPoint;
    public static F7Phase3 activePhase;
    public final HashMap<Block, Terminal> terminals = new HashMap<>();
    public final HashMap<Terminal, Block> terminalBlockHashMap = new HashMap<>();
    private int solvedTewrmianls = 0;
    @Getter
    private int gates = 0;
    private Goldor goldor;
    private static final Location[] checkPoints = {new Location(Bukkit.getWorld("world"), 100.5, 120, 132.5), new Location(Bukkit.getWorld("world"), 8.5, 120, 132.5),
            new Location(Bukkit.getWorld("world"), 8.5, 120, 40.5), new Location(Bukkit.getWorld("world"), 88.5, 120, 40.5)};
    private static final Location core = new Location(Bukkit.getWorld("world"), 54.5, 116, 40.5);
    private static final Location last = new Location(Bukkit.getWorld("world"), 54.5, 116, 117.5);
    private BukkitRunnable move;
    private boolean quickMove = false;
    private boolean isDone = false;
    private boolean isSlow = false;
    private BukkitRunnable lastSlow;
    public F7Phase3(boolean isListener){
        if(!isListener){
            ItemManager manager = new ItemManager("Helmet of the Universe", "ADMIN_HELMET", ItemType.Helmet,
                    new ArrayList<>(List.of("§7Defenetly Legit ;)")), "Fly", "Fly",
                    new ArrayList<>(List.of("§7I mean why not? its good :D")), 0,0,0,0, Material.GOLDEN_HELMET, ItemRarity.SPECIAL);
            manager.setFullSetBonus(Bonuses.AdminArmor, "Fly");
            manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
            Items.SkyblockItems.put(manager.itemID, manager);

            manager = new ItemManager("Chestplate of the Universe", "ADMIN_CHESTPLATE", ItemType.Chestplate, new ArrayList<>(List.of("§7Defenetly Legit ;)")), "Fly", "Fly",
                    new ArrayList<>(List.of("§7I mean why not? its good :D")), 0,0,0,0, Material.GOLDEN_CHESTPLATE, ItemRarity.SPECIAL);
            manager.setFullSetBonus(Bonuses.AdminArmor, "Fly");
            manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
            Items.SkyblockItems.put(manager.itemID, manager);

            manager = new ItemManager("Leggings of the Universe", "ADMIN_LEGGINGS", ItemType.Leggings, new ArrayList<>(List.of("§7Defenetly Legit ;)")), "Fly", "Fly",
                    new ArrayList<>(List.of("§7I mean why not? its good :D")), 0,0,0,0, Material.GOLDEN_LEGGINGS, ItemRarity.SPECIAL);
            manager.setFullSetBonus(Bonuses.AdminArmor, "Fly");
            manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
            Items.SkyblockItems.put(manager.itemID, manager);

            manager = new ItemManager("Boots of the Universe", "ADMIN_BOOTS", ItemType.Boots, new ArrayList<>(List.of("§7Defenetly Legit ;)")), "Fly", "Fly",
                    new ArrayList<>(List.of("§7I mean why not? its good :D")), 0,0,0,0, Material.GOLDEN_BOOTS, ItemRarity.SPECIAL);
            manager.setFullSetBonus(Bonuses.AdminArmor, "Fly");
            manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
            Items.SkyblockItems.put(manager.itemID, manager);
        }
    }
    public F7Phase3(){
        regenerate();
        for (Location l : tLocations){
            int i = new Random().nextInt(5);
            switch (i){
                case 0 -> terminals.put(l.getBlock(),new ColorTerminal(this, 0));
                case 1 -> terminals.put(l.getBlock(),new LockInSlotTerminal(this, 0));
                case 2 -> terminals.put(l.getBlock(),new OrderTerminal(this, 0));
                case 3 -> terminals.put(l.getBlock(),new PanesTerminal(this, 0));
                case 4 -> terminals.put(l.getBlock(), new TextTerminal(this, 0));
            }
        }
        activePhase = this;

        simonSaysTerminal = new SimonSaysTerminal(this, 0);
        lightsTerminal = new LightsTerminal(this, 0);
        arrowShoot = new ArrowShooting(this, 0);
        arrowPoint = new ArrowPointing(this, 0);
        LeverTerminal.registerAll();
        for (Block b : terminals.keySet())
            terminalBlockHashMap.put(terminals.get(b), b);
        new BukkitRunnable() {
            @Override
            public void run() {
                goldor = new Goldor();
                goldor.spawn(new Location(Bukkit.getWorld("world"), 100.5, 120, 40.5));
                move(gates);
            }
        }.runTaskLater(Main.getMain(), 40);
    }

    public void hit(){
        isSlow = true;
        try {
            lastSlow.cancel();
        }catch (Exception ignored){}
        lastSlow = new BukkitRunnable() {
            @Override
            public void run() {
                isSlow = false;
            }
        };
        lastSlow.runTaskLater(Main.getMain(), 40);
    }
    public void move(final int gate){
        try {
            move.cancel();
        }catch (Exception ignored){}
        isDone = false;
        goldor.setGate(gate);
        move = new BukkitRunnable() {
            @Override
            public void run() {
                if(goldor.getEntity() == null || goldor.getEntity().isDead())
                    cancel();

                if(!isDone){
                    double speed;
                    if (quickMove)
                        speed = 0.65;
                    else if(isSlow && gate != 5)
                        speed = 0.01;
                    else
                        speed = 0.05;
                    Location target;
                    if (gate < 4)
                        target = checkPoints[gate].clone();
                    else if(gate == 4)
                        target = core;
                    else target = last;
                    Location lo = goldor.getEntity().getLocation().setDirection(target.clone().subtract(goldor.getEntity().getLocation()).toVector());
                    lo = lo.add(lo.getDirection().clone().multiply(speed));
                    goldor.getEntity().teleport(lo);
                    if(lo.distance(target) <= speed + 0.05) {
                        goldor.getEntity().teleport(target);
                        isDone = true;
                        if(quickMove){
                            quickMove = false;
                            if(gate < 4)
                                move(gate + 1);
                        }
                        cancel();
                    }
                }
            }
        };
        move.runTaskTimer(Main.getMain(), 1, 1);
    }
    public static void regenerate(){
        Tools.loadShematic(new File(Main.getMain().getDataFolder().getPath() + "\\gates\\gate1.schem"), new Location(Bukkit.getWorld("world") ,104d, 114d, 122d));
        Tools.loadShematic(new File(Main.getMain().getDataFolder().getPath() + "\\gates\\gate2.schem"), new Location(Bukkit.getWorld("world") ,18, 114d, 136));
        Tools.loadShematic(new File(Main.getMain().getDataFolder().getPath() + "\\gates\\gate3.schem"), new Location(Bukkit.getWorld("world") ,4, 114d, 50));
        for(Block bbb : Tools.getBlocksBetween(new Location(Bukkit.getWorld("world") ,52, 121, 54).getBlock(), new Location(Bukkit.getWorld("world") ,56, 115, 54).getBlock()))
            bbb.setType(Material.GOLD_BLOCK);

    }
    public void solveTerminal(Terminal t, SkyblockPlayer player){
        if(terminalBlockHashMap.containsKey(t)) {
            System.out.println("Contains :o");
            terminals.remove(terminalBlockHashMap.get(t));
            terminalBlockHashMap.remove(t);
            System.out.println(terminalBlockHashMap.containsKey(t));
        }else if(t instanceof LeverTerminal) {
            for (Terminal llllllll : terminalBlockHashMap.keySet())
                if (llllllll instanceof LeverTerminal)
                    System.out.println(llllllll + " " + terminalBlockHashMap.get(llllllll));
            return;
        }
        solvedTewrmianls++;
        if((gates != 1 && solvedTewrmianls == 7) || (gates == 1 && solvedTewrmianls == 8)){
            Bukkit.broadcastMessage(player.getName() + " §aactivated a terminal! (7/"+((gates == 1) ? "8" : "7")+")");
            solvedTewrmianls = 0;
            gates++;
            for (Player p : Bukkit.getOnlinePlayers())
                p.sendTitle("§aGate will open in 5", "", 0, 30, 0);
            if(gates < 4){
                new BukkitRunnable() {
                    private int i = 5;
                    @Override
                    public void run() {
                        i--;
                        if(i == 0){
                            for (Player p : Bukkit.getOnlinePlayers())
                                p.sendTitle("§aGate is open", "", 0, 30, 10);
                            if(!isDone)
                                quickMove = true;
                            else move(gates);
                            WorldEditPlugin worldEditPlugin = (WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit");
                            if(worldEditPlugin == null) {
                                Bukkit.broadcastMessage("§cError! WorldEdit is not installed!");
                                return;
                            }
                            ArrayList<Block> b = null;
                            switch (gates){
                                case 1 -> b = Tools.getBlocksBetween(new Location(Bukkit.getWorld("world") ,104, 115, 122).getBlock(), new Location(Bukkit.getWorld("world") ,96, 135, 124).getBlock());
                                case 2 -> b = Tools.getBlocksBetween(new Location(Bukkit.getWorld("world") ,18, 115, 136).getBlock(), new Location(Bukkit.getWorld("world") ,16, 135, 128).getBlock());
                                case 3 -> b = Tools.getBlocksBetween(new Location(Bukkit.getWorld("world") ,4, 115, 50).getBlock(), new Location(Bukkit.getWorld("world") ,12, 135, 48).getBlock());
                            }
                            for(Block bbb : b) {
                                if(bbb.getType() != Material.BARRIER)
                                    new FallDownArmorstand(bbb.getType(), bbb.getLocation().clone().add(0,0.5,0));
                                bbb.setType(Material.AIR);
                            }
                            new BukkitRunnable() {
                                private boolean isInRect = false;
                                @Override
                                public void run() {
                                    if(!isInRect) {
                                        for (Player p : Bukkit.getOnlinePlayers()) {
                                            if(Tools.isInRect(p, new Location(Bukkit.getWorld("world"), 40, 131, 59), new Location(Bukkit.getWorld("world"), 66, 111, 92))) {
                                                isInRect = true;
                                                break;
                                            }
                                        }
                                    }
                                    if(isInRect && isDone){
                                        move(5);
                                        goldor.enableGoldor();
                                        cancel();
                                    }
                                }
                            }.runTaskTimer(Main.getMain(), 1, 1);
                            cancel();

                        }else for (Player p : Bukkit.getOnlinePlayers()) p.sendTitle("§aGate will open in " + i, "", 0, 30, 0);

                    }
                }.runTaskTimer(Main.getMain(), 20, 20);
            }else{
                new BukkitRunnable() {
                    private int i = 5;
                    @Override
                    public void run() {
                        i--;
                        if(i == 0){
                            for (Player p : Bukkit.getOnlinePlayers())
                                p.sendTitle("§aCore is open", "", 0, 30, 10);

                            quickMove = true;
                             move(4);
                            for(Block bbb : Tools.getBlocksBetween(new Location(Bukkit.getWorld("world") ,52, 121, 54).getBlock(), new Location(Bukkit.getWorld("world") ,56, 115, 54).getBlock()))
                                bbb.setType(Material.AIR);

                            cancel();

                        }else for (Player p : Bukkit.getOnlinePlayers()) p.sendTitle("§aCore will open in " + i, "", 0, 30, 0);

                    }
                }.runTaskTimer(Main.getMain(), 20, 20);
            }
        }else
            Bukkit.broadcastMessage(player.getName() + " §aactivated a terminal! (§c"+ solvedTewrmianls +"§a/"+((gates == 1) ? "8" : "7")+")");
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(activePhase != null){
            if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.STONE_BUTTON && event.getClickedBlock().getX() == 110
                    && event.getClickedBlock().getY() == 121 && event.getClickedBlock().getZ() == 91 && simonSaysTerminal != null) {
                simonSaysTerminal.open(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
            }
            if (event.getClickedBlock() != null) {
                if(activePhase.terminalBlockHashMap.containsValue(event.getClickedBlock())){
                    for(Block b : new ArrayList<>(activePhase.terminals.keySet())){
                        if(b.equals(event.getClickedBlock()))
                            if(!activePhase.terminals.get(b).isOpen())
                                activePhase.terminals.get(b).open(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
                    }
                }
            }
        }

    }
}
