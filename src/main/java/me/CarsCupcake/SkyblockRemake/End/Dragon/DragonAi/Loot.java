package me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi;

import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonTypes;
import me.CarsCupcake.SkyblockRemake.End.Dragon.StartFight;
import me.CarsCupcake.SkyblockRemake.End.Dragon.SuperiorDragon;
import me.CarsCupcake.SkyblockRemake.End.EndItems;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.StandUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import net.minecraft.world.entity.item.EntityItem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.*;

import static me.CarsCupcake.SkyblockRemake.End.Dragon.StartFight.fightActive;
import static me.CarsCupcake.SkyblockRemake.End.Dragon.StartFight.spawnLoc;

public class Loot implements Listener {

    HashMap<Location, Material> storedBlocks = new HashMap<>();
    public static HashMap<String, ItemStack> lootDrops = new HashMap<>();
    public static HashMap<String, EntityItem> itemDropID = new HashMap<>();
    public static HashMap<String, EntityArmorStand> armorstandHoloID = new HashMap<>();
    public static Map<Player, Double> damage = new HashMap<>();
    public static Location lootLoc;
    public static HashMap<Player,ArrayList<LootObject>> loots = new HashMap<>();
    private static BukkitRunnable resetRunner;


    private static void strangeCircleStuff(ArrayList<Location> as, Player killer, Location middle) {
        HashMap<Location, Material> reset = new HashMap<Location, Material>();
        HashMap<Location, BlockData> resetData = new HashMap<>();
        new BukkitRunnable() {

            @SuppressWarnings("deprecation")
            @Override
            public void run() {

                try {
                    for (Location armor : as) {
                        while (armor.clone().add(0, -1, 0).getBlock().getType() == Material.AIR) {
                            armor.setY(armor.getY() - 1);
                        }
                        Block b = armor.clone().add(0, -1, 0).getBlock();
                        if (b.getLocation().getY() >= 10) {
                            //distance formula lmao
                            double wtfisthis = Math.abs(Math.sqrt((spawnLoc.getX() - (b.getLocation().getX()) * (spawnLoc.getX() - (b.getLocation().getX()))) + (spawnLoc.getZ() - (b.getLocation().getZ())) * (spawnLoc.getZ() - (b.getLocation().getZ()))));
                            if (!(wtfisthis >= 70)) {
                                b.getLocation().setY(9);
                            }

                        }
                        reset.put(b.getLocation(), b.getType());
                        resetData.put(b.getLocation(), b.getBlockData().clone());
                        b.setType(Material.TERRACOTTA);

                        if (b.getX() - 3 == middle.getBlockX() && b.getZ() == middle.getBlockZ()) {
                            HashMap<Player, Double> weightCopy = new HashMap<>(StartFight.weight);
                            for (Player p : weightCopy.keySet()) {
                                Double playerWeight = StartFight.weight.get(p);
                                damage = sortByValue(damage);
                                List<Player> damagers = new ArrayList<>(damage.keySet());
                                int place = (damagers.indexOf(p) + 1);

                                if (killer.getName().equals(p.getName())) {
                                    playerWeight += 50;
                                }
                                if (place >= 16) {
                                    playerWeight += 75;
                                } else {
                                    if (place < 15 && place >= 11) {
                                        playerWeight += 100;
                                    } else {
                                        if (place < 10 && place >= 4) {
                                            playerWeight += 125;
                                        } else {
                                            switch (place) {
                                                case 1:
                                                    playerWeight += 300;
                                                case 2:
                                                    playerWeight += 250;
                                                case 3:
                                                    playerWeight += 200;
                                            }
                                        }
                                    }
                                }
                                StartFight.weight.remove(p);
                                StartFight.weight.put(p, playerWeight);
                                Double finalPlayerWeight = playerWeight;

                                calculateDrop(p, b.getLocation(), finalPlayerWeight);
                                StartFight.weight.remove(p);
                                StartFight.aotdChance.remove(p);
                                StartFight.playerDMG.remove(p);
                                fightActive = false;

                            }
                        }
                        if (b.getX() + 3 == middle.getBlockX() && b.getZ() == middle.getBlockZ()) {
                            for(Player p : Bukkit.getOnlinePlayers()) {
                                ItemStack item = DragonTypes.dragonByEntity(StartFight.entityDragon).getFragment().createNewItemStack();
                                item.setAmount(22);
                                spawnLoot(p, b.getLocation(), item);
                            }
                        }
                        if (b.getZ() - 3 == middle.getBlockZ() && b.getX() == middle.getBlockX()) {
                            for(Player p : Bukkit.getOnlinePlayers()) {
                                ItemStack item = new ItemStack(Material.ENDER_PEARL);
                                item = Main.item_updater(Main.item_updater(item, SkyblockPlayer.getSkyblockPlayer(p)), SkyblockPlayer.getSkyblockPlayer(p));
                                item.setAmount(15);
                                spawnLoot(p, b.getLocation(), item);
                            }
                        }
                        if (b.getZ() + 3 == middle.getBlockZ() && b.getX() == middle.getBlockX()) {
                            //Enchanted e pearl here
                            for(Player p : Bukkit.getOnlinePlayers()) {
                                ItemStack item = EndItems.Items.EnchantedEnderPearl.getItem().createNewItemStack();
                                item.setAmount(5);
                                spawnLoot(p, b.getLocation(), item);
                            }
                        }

                        as.remove(armor);


                        /*if (armor.isOnGround()) {
                            Block b = armor.getLocation().clone().add(0, -1, 0).getBlock();
                            if (b.getLocation().getY() >= 10) {
                                //distance formula lmao
                                double wtfisthis = Math.abs(Math.sqrt((spawnLoc.getX() - (b.getLocation().getX()) * (spawnLoc.getX() - (b.getLocation().getX()))) + (spawnLoc.getZ() - (b.getLocation().getZ())) * (spawnLoc.getZ() - (b.getLocation().getZ()))));
                                if (!(wtfisthis >= 70)) {
                                    b.getLocation().setY(9);
                                }
                            }
                            reset.put(b.getLocation(), b.getType());
                            resetData.put(b.getLocation(), b.getData());
                            b.setType(Material.STAINED_CLAY);
                            b.setData((byte) 6);
                            armor.remove();
                            as.remove(armor);
                        }*/
                    }
                } catch (ConcurrentModificationException ignored) {
                }
                if (as.isEmpty()) {
                    startBlockResetter(reset, resetData);
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.getMain(), 0, 0);
    }
    public static HashMap<Location, Material> reset;
    public static HashMap<Location, BlockData> resetData;
    protected static void startBlockResetter(HashMap<Location, Material> reset, HashMap<Location, BlockData> resetData) {
        Loot.reset = reset;
        Loot.resetData = resetData;
        resetRunner = new BukkitRunnable() {
            @Override
            public void run() {
                resetBlocks(reset, resetData);
            }
        };
        resetRunner.runTaskLater(Main.getMain(), 20*30);


    }

    public static void resetBlocks(HashMap<Location, Material> reset, HashMap<Location, BlockData> resetData){
        try {
            resetRunner.cancel();
            for (Location loc : reset.keySet()) {
                loc.getBlock().setType(reset.get(loc));
                loc.getBlock().setBlockData(resetData.get(loc));
            }
            Loot.reset = null;
            Loot.resetData = null;

        }catch (Exception ignored){}


    }

    public static void dragonDownMessage(SkyblockDragon dragon, Player killer, Location location) {
        damage = sortByValue(damage);
        String pattern = "###,###";
        DecimalFormat format = new DecimalFormat(pattern);
        for (Player p : Bukkit.getOnlinePlayers()) {


            List<Player> damagers = new ArrayList<>(damage.keySet());
            String damage1 = (format.format(damage.get(damagers.get(0))));
            String yourDamage = "";
            try {
                yourDamage = (format.format(damage.get(p)));
            } catch (IllegalArgumentException ignored) {
                yourDamage = "0";
            }

            p.sendMessage(ChatColor.GREEN + "----------------------------------------------------");
            p.sendMessage(ChatColor.GOLD + "              " + ChatColor.BOLD + StartFight.entityDragon.getName() + " DOWN!");
            p.sendMessage(ChatColor.GREEN + "                " + killer.getName() + ChatColor.GRAY + " dealt the final blow.");
            p.sendMessage(ChatColor.YELLOW + "          " + ChatColor.BOLD + "1st Damager" + ChatColor.GRAY + " - " + damagers.get(0).getName() + ChatColor.GRAY + " - " + ChatColor.YELLOW + damage1);
            if (damagers.size() < 3) {

                if (damagers.size() == 2) {
                    String damage2 = (format.format(damage.get(damagers.get(1))));
                    p.sendMessage(ChatColor.GOLD + "          " + ChatColor.BOLD + "2nd Damager" + ChatColor.GRAY + " - " + damagers.get(1).getName() + ChatColor.GRAY + " - " + ChatColor.YELLOW + damage2);
                } else {
                    p.sendMessage(ChatColor.GOLD + "          " + ChatColor.BOLD + "2nd Damager" + ChatColor.GRAY + " - N/A" + ChatColor.GRAY);
                }
                p.sendMessage(ChatColor.RED + "          " + ChatColor.BOLD + "3rd Damager" + ChatColor.GRAY + " - N/A");
            } else {
                String damage2 = (format.format(damage.get(damagers.get(1))));
                String damage3 = (format.format(damage.get(damagers.get(2))));
                p.sendMessage(ChatColor.GOLD + "          " + ChatColor.BOLD + "2nd Damager" + ChatColor.GRAY + " - " + damagers.get(1).getName() + ChatColor.GRAY + " - " + ChatColor.YELLOW + damage2);
                p.sendMessage(ChatColor.RED + "          " + ChatColor.BOLD + "3rd Damager" + ChatColor.GRAY + " - " + damagers.get(2).getName() + ChatColor.GRAY + " - " + ChatColor.YELLOW + damage3);
            }
            p.sendMessage(ChatColor.YELLOW + "          Your Damage: " + ChatColor.GREEN + yourDamage + ChatColor.GRAY + " (Position #" + (damagers.indexOf(p) + 1) + ")");
            p.sendMessage(ChatColor.YELLOW + "             Runecrafting Experience: " + ChatColor.LIGHT_PURPLE + "0");
            p.sendMessage(ChatColor.GREEN + "----------------------------------------------------");
        }
        /*HashMap<Player, Double> weightCopy = new HashMap<>(StartFight.weight);
        for (Player p : weightCopy.keySet()) {
            Double playerWeight = StartFight.weight.get(p);
            damage = sortByValue(damage);
            List<Player> damagers = new ArrayList<>(damage.keySet());
            int place = (damagers.indexOf(p) + 1);

            if (killer.getName().equals(p.getName())) {
                playerWeight += 50;
            }
            if (place >= 16) {
                playerWeight += 75;
            } else {
                if (place < 15 && place >= 11) {
                    playerWeight += 100;
                } else {
                    if (place < 10 && place >= 4) {
                        playerWeight += 125;
                    } else {
                        switch (place) {
                            case 1:
                                playerWeight += 300;
                            case 2:
                                playerWeight += 250;
                            case 3:
                                playerWeight += 200;
                        }
                    }
                }
            }
            StartFight.weight.remove(p);
            StartFight.weight.put(p, playerWeight);
            System.out.println(StartFight.weight.get(p));
            ArmorStand lootAs = (ArmorStand) location.getWorld().spawn(location, ArmorStand.class, s ->{s.setVisible(false);});
            Double finalPlayerWeight = playerWeight;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (lootAs.isOnGround()) {
                        lootLoc = lootAs.getLocation().clone().subtract(0, 1, 0);
                        calculateDrop(p, lootLoc, finalPlayerWeight);
                        StartFight.weight.remove(p);
                        StartFight.aotdChance.remove(p);
                        StartFight.playerDMG.remove(p);
                        fightActive = false;
                        lootAs.remove();
                        this.cancel();
                    }
                }
            }.runTaskTimer(Main.getMain(), 0, 0);
        }*/
        /*ArrayList<ArmorStand> as = new ArrayList<>();
        for (Location loc : StandUtils.generateSphere(location, 6, false)) {
            ArmorStand uff = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(0.5, 0, 0.5), EntityType.ARMOR_STAND);
            uff.setVisible(false);
            uff.setMarker(false);
            uff.setMaxHealth(1000);
            uff.setHealth(1000);
            uff.setGravity(true);
            as.add(uff);
        }*/
        strangeCircleStuff(new ArrayList<>(StandUtils.generateSphere(location, 6, false)), killer, location);
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


    static void spawnLoot(Player player, Location location, ItemStack item) {
        //clientside loot
        EntityItem entityItem = new EntityItem(EntityTypes.Q, ((CraftWorld) player.getWorld()).getHandle());
        entityItem.setItemStack(CraftItemStack.asNMSCopy(item));
        entityItem.setPosition(location.getX() + 0.5, location.getY() + 1, location.getZ() + 0.5);

        CraftPlayer entityPlayer = (CraftPlayer) player;
        PacketPlayOutSpawnEntity itemPacket = new PacketPlayOutSpawnEntity(entityItem, 2);
        (entityPlayer.getHandle()).b.sendPacket(itemPacket);

        PacketPlayOutEntityMetadata meta = new PacketPlayOutEntityMetadata(entityItem.getId(), entityItem.getDataWatcher(), true);
        (entityPlayer.getHandle()).b.sendPacket(meta);


        //clientside stand
        EntityArmorStand entityArmorStand = new EntityArmorStand(EntityTypes.c, ((CraftWorld) player.getWorld()).getHandle());
        entityArmorStand.setCustomName(new ChatComponentText(item.getItemMeta().getDisplayName() + ((item.getAmount() > 1) ? " §8" + item.getAmount() + "x" : "")));
        entityArmorStand.setCustomNameVisible(true);

        entityArmorStand.setInvisible(true);
        entityArmorStand.setLocation(location.getX() + 0.5, location.getY(), location.getZ() + 0.5, 0, 0);

        PacketPlayOutSpawnEntityLiving standPacket = new PacketPlayOutSpawnEntityLiving(entityArmorStand);
        (entityPlayer.getHandle()).b.sendPacket(standPacket);
        PacketPlayOutEntityMetadata metaPacket = new PacketPlayOutEntityMetadata(entityArmorStand.getId(), entityArmorStand.getDataWatcher(), true);
        entityPlayer.getHandle().b.sendPacket(metaPacket);



        net.minecraft.world.item.ItemStack nmsItem1 = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag1 = (nmsItem1.hasTag()) ? nmsItem1.getTag() : new NBTTagCompound();
        NBTTagCompound data1 = tag1.getCompound("ExtraAttributes");
        String uuid = data1.getString("UUID");
        lootDrops.put(uuid, item);
        itemDropID.put(uuid, entityItem);
        armorstandHoloID.put(uuid, entityArmorStand);
        LootObject object = new LootObject(player, location,entityArmorStand, entityItem, item);
        ArrayList<LootObject> objects = loots.getOrDefault(player, new ArrayList<>());
        objects.add(object);
        loots.put(player, objects);

    }

    static void calculateDrop(Player p, Location spawnLoc, double playerWeight) {
        Location loc = spawnLoc.clone();

        ItemStack drop = null;
        SkyblockEntity dragID = StartFight.entityDragon;

        double random = Math.random() * 100;
        double petChance = Math.random() - (StartFight.aotdChance.get(p) / 1000000);
        double aotdChance = StartFight.aotdChance.get(p) + (Math.random() * 100);
        try {
            if (playerWeight >= 450) {
                if (petChance < 0.0008) {
                    //if(petChance>0.0001) {
                    drop = Items.SkyblockItems.get("ENDER_DRAGON;LEGENDARY").createNewItemStack();
                    /*double legChance = Math.random();
                    if (legChance <= 0.2) {
                        drop = DragonDrop.Universal.PET.getDrop(Rarity.LEGENDARY);
                    }*/
                }
            }
            if (playerWeight >= 450 && !(StartFight.entityDragon instanceof SuperiorDragon) && aotdChance > 140 && drop == null) {
                drop = EndItems.Items.AspectOfTheDragons.getItem().createNewItemStack();
                playerWeight -= 450;
            }
            if (drop == null && playerWeight >= 400) {
                if (random > 65) {
                    drop = DragonDrop.CHESTPLATE.getDrop(DragonTypes.dragonByEntity(dragID));
                    playerWeight -= 400;
                }
            }
            if (drop == null && playerWeight >= 350) {
                if (random > 55) {
                    drop = DragonDrop.LEGGINGS.getDrop(DragonTypes.dragonByEntity(dragID));
                    playerWeight -= 350;
                }
            }
            if (drop == null && playerWeight >= 325) {
                if (random > 40) {
                    drop = DragonDrop.HELMET.getDrop(DragonTypes.dragonByEntity(dragID));
                    playerWeight -= 325;
                }
            }
            if (drop == null && playerWeight >= 300) {
                if (random > 30) {
                    drop = DragonDrop.BOOTS.getDrop(DragonTypes.dragonByEntity(dragID));
                    playerWeight -= 300;
                }
            }
            //spawning drop
            if (drop != null) {
                drop = Main.item_updater(drop, SkyblockPlayer.getSkyblockPlayer(p));
                spawnLoot(p, loc, drop);
            }
        } catch (Exception ex) {
            System.out.println("Dragon loot could not be dropped at: ");
            ex.printStackTrace();
            p.sendMessage(ChatColor.RED + "Loot drop error §8("+ex.getClass().getSimpleName()+")");
        }
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        ArrayList<LootObject> objects = loots.getOrDefault(p, new ArrayList<>());
        for(LootObject loc : new ArrayList<>(objects)){
            if(loc.isInReach(p.getLocation())){
                loc.pickup();
                objects.remove(loc);
            }
        }
        if(!objects.isEmpty())
            loots.put(p, objects);

       /* for (Entity en : p.getNearbyEntities(0.2, 0.2, 0.2)) {
            if (en.hasMetadata(p.getName() + "_drag_loot")) {
                en.remove();
                p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);

                try {
                    String uuid = "";
                    for (MetadataValue value : en.getMetadata(p.getName() + "_drag_loot")) {
                        uuid = value.asString();
                    }
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has obtained " + lootDrops.get(uuid).getItemMeta().getDisplayName() + ChatColor.YELLOW + "!");
                    }
                    p.getInventory().addItem(lootDrops.get(uuid));


                    //deleting clientside item
                    CraftPlayer entityPlayer = (CraftPlayer) p;
                    PacketPlayOutEntityDestroy itemPacket = new PacketPlayOutEntityDestroy(itemDropID.get(uuid).getId());
                    (entityPlayer.getHandle()).b.sendPacket(itemPacket);

                    //and clientside stand
                    PacketPlayOutEntityDestroy standPacket = new PacketPlayOutEntityDestroy(armorstandHoloID.get(uuid).getId());
                    (entityPlayer.getHandle()).b.sendPacket(standPacket);
                    lootDrops.remove(uuid);
                    armorstandHoloID.remove(uuid);
                } catch (NullPointerException ignored) {}

            }
        }*/
    }

    private enum DragonDrop{
        CHESTPLATE,
        HELMET,
        LEGGINGS,
        BOOTS;

        public ItemStack getDrop(DragonTypes type){
            switch(type){
                case SUPERIOR -> {
                    switch (this){
                        case BOOTS -> {
                            return EndItems.Items.SuperiorBoots.getItem().createNewItemStack();
                        }
                        case HELMET -> {
                            return EndItems.Items.SuperiorHelmet.getItem().createNewItemStack();
                        }
                        case CHESTPLATE -> {
                            return EndItems.Items.SuperiorChestplate.getItem().createNewItemStack();
                        }
                        case LEGGINGS -> {
                            return EndItems.Items.SuperiorLeggings.getItem().createNewItemStack();
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                case OLD -> {
                    switch (this){
                        case BOOTS -> {
                            return EndItems.Items.OldBoots.getItem().createNewItemStack();
                        }
                        case HELMET -> {
                            return EndItems.Items.OldHelmet.getItem().createNewItemStack();
                        }
                        case CHESTPLATE -> {
                            return EndItems.Items.OldChestplate.getItem().createNewItemStack();
                        }
                        case LEGGINGS -> {
                            return EndItems.Items.OldLeggings.getItem().createNewItemStack();
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                case PROTECTOR -> {
                    switch (this){
                        case BOOTS -> {
                            return EndItems.Items.ProtectorBoots.getItem().createNewItemStack();
                        }
                        case HELMET -> {
                            return EndItems.Items.ProtectorHelmet.getItem().createNewItemStack();
                        }
                        case CHESTPLATE -> {
                            return EndItems.Items.ProtectorChestplate.getItem().createNewItemStack();
                        }
                        case LEGGINGS -> {
                            return EndItems.Items.ProtectorLeggings.getItem().createNewItemStack();
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                case STRONG -> {
                    switch (this){
                        case BOOTS -> {
                            return EndItems.Items.StrongBoots.getItem().createNewItemStack();
                        }
                        case HELMET -> {
                            return EndItems.Items.StrongHelmet.getItem().createNewItemStack();
                        }
                        case CHESTPLATE -> {
                            return EndItems.Items.StrongChestplate.getItem().createNewItemStack();
                        }
                        case LEGGINGS -> {
                            return EndItems.Items.StrongLeggings.getItem().createNewItemStack();
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                case WISE -> {
                    switch (this){
                        case BOOTS -> {
                            return EndItems.Items.WiseBoots.getItem().createNewItemStack();
                        }
                        case HELMET -> {
                            return EndItems.Items.WiseHelmet.getItem().createNewItemStack();
                        }
                        case CHESTPLATE -> {
                            return EndItems.Items.WiseChestplate.getItem().createNewItemStack();
                        }
                        case LEGGINGS -> {
                            return EndItems.Items.WiseLeggings.getItem().createNewItemStack();
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                case UNSTABLE -> {
                    switch (this){
                        case BOOTS -> {
                            return EndItems.Items.UnstableBoots.getItem().createNewItemStack();
                        }
                        case HELMET -> {
                            return EndItems.Items.UnstableHelmet.getItem().createNewItemStack();
                        }
                        case CHESTPLATE -> {
                            return EndItems.Items.UnstableChestplate.getItem().createNewItemStack();
                        }
                        case LEGGINGS -> {
                            return EndItems.Items.UnstableLeggings.getItem().createNewItemStack();
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                case YOUNG -> {
                    switch (this){
                        case BOOTS -> {
                            return EndItems.Items.YoungBoots.getItem().createNewItemStack();
                        }
                        case HELMET -> {
                            return EndItems.Items.YoungHelmet.getItem().createNewItemStack();
                        }
                        case CHESTPLATE -> {
                            return EndItems.Items.YoungChestplate.getItem().createNewItemStack();
                        }
                        case LEGGINGS -> {
                            return EndItems.Items.YoungLeggings.getItem().createNewItemStack();
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                

                default -> {
                    return null;
                }
            }
        }

    }


}
