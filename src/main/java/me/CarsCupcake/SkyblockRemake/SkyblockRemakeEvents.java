package me.CarsCupcake.SkyblockRemake;


import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.Entities.BasicEntity;
import me.CarsCupcake.SkyblockRemake.FishingSystem.LavaFishingHook;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.farming.FarmingUtils;
import me.CarsCupcake.SkyblockRemake.NPC.EntityNPC;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.QuestNpc;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.projectile.SkyblockProjectile;
import me.CarsCupcake.SkyblockRemake.abilities.Ferocity;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.CurrentMiningBlock;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.MiningSys;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Titanium;
import me.CarsCupcake.SkyblockRemake.isles.privateIsle.PrivateIslandManager;
import me.CarsCupcake.SkyblockRemake.isles.privateIsle.PrivateIsle;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignGUI;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;

import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEnderDragonPart;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.Powers.Powers;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Commission.Puzzler;
import me.CarsCupcake.SkyblockRemake.Configs.PetMenus;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.NPC.NPC;
import me.CarsCupcake.SkyblockRemake.utils.PacketReader;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Pets.PetFollowRunner;
import me.CarsCupcake.SkyblockRemake.Skyblock.terminals.maze;
import me.CarsCupcake.SkyblockRemake.Skyblock.terminals.order;
import me.CarsCupcake.SkyblockRemake.abilities.HydraStrike;
import me.CarsCupcake.SkyblockRemake.cmd.itemCMD;

import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;

import net.minecraft.network.protocol.game.PacketPlayInBlockDig;
import net.minecraft.network.protocol.game.PacketPlayInBlockDig.EnumPlayerDigType;
import net.minecraft.network.protocol.game.PacketPlayOutEntity;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;

import net.minecraft.server.network.PlayerConnection;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("deprecation")
public class SkyblockRemakeEvents implements Listener {
    public static Inventory SoulShopInv;
    public static Inventory SoulReforging;
    public static Inventory SoulBuyInv;
    public String gm;

    public HashMap<Player, CurrentMiningBlock> breakTicksLeft = new HashMap<>();
    public static boolean iteminforge = false;
    public static int soulcost;
    public static HashMap<Location, Material> TitaniumRegen = new HashMap<>();
    public static HashMap<Location, Titanium> TitaniumObject = new HashMap<>();


    public SkyblockRemakeEvents Events;

    @EventHandler
    public void onTNT(EntityExplodeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void playerdrop(PlayerDropItemEvent event) {
        event.getItemDrop().addScoreboardTag("player");
    }

    public static void readBeakBlock(PacketPlayInBlockDig packet, Player player) {

        Location loc = new Location(player.getWorld(), packet.b().getX(), packet.b().getY(), packet.b().getZ());

        Bukkit.getScheduler().runTask(Main.getMain(), () -> {
            if (packet.d() == EnumPlayerDigType.a) MiningSys.getMiningSystem(player).startMining(loc.getBlock());
            if (packet.d() == EnumPlayerDigType.b || packet.d() == EnumPlayerDigType.c)
                MiningSys.getMiningSystem(player).stopMinig();
        });

    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getInventory().setItem(8, Items.SkyblockMenu());
        Main.absorbtion.put(player, 0);
        Main.absorbtionrunntime.put(player, 0);
        Main.shortbow_cd.put(player, false);
        Main.termhits.put(player, 0);
        Powers.initPower(player);


        new SkyblockPlayer((CraftServer) Main.getMain().getServer(), ((CraftPlayer) event.getPlayer()).getHandle());
        PacketReader reader = new PacketReader((event.getPlayer()));
        if (PetMenus.get().getConfigurationSection(player.getUniqueId().toString()) == null || !PetMenus.get().getConfigurationSection(player.getUniqueId().toString()).getKeys(false).contains("equiped")) {
            PetMenus.get().set(player.getUniqueId() + ".equiped", 0);
            PetMenus.save();
            PetMenus.reload();
        }

        if (PetMenus.get().getInt(player.getUniqueId() + ".equiped") != 0) {
            new PetFollowRunner(player, Pet.pets.get(PetMenus.get().getString(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() + ".equiped") + ".id")), PetMenus.get().getInt(player.getUniqueId() + ".equiped"));
        }
        if (SkyblockServer.getServer().type() == ServerType.PrivateIsle)
            PrivateIslandManager.addToIsle(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        else player.teleport(SkyblockServer.getServer().type().getLocation());


        reader.inject();
        if (NPC.getNPCs() == null) return;
        if (NPC.getNPCs().isEmpty()) return;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), new Runnable() {

            @Override
            public void run() {
                NPC.addJoinPacket(event.getPlayer());
            }
        }, 70);


    }

    @EventHandler
    public void MininSystem(BlockDamageEvent event) {


        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());

        if (Puzzler.activePlayerQuest(player)) {
            Puzzler puzzle = Puzzler.getPuzzler(player);
            if (!puzzle.isFailed) if (event.getBlock().equals(puzzle.getEstimatetBlockLocation().getBlock())) {
                player.sendMessage("§e[NPC] §dPuzzler§f: §a✌✓");
                player.sendMessage("§e[NPC] §dPuzzler§f: ▶▶Nice! ▲Here, ◀have ▼some◀ ▶ §2᠅ Mithril Powder§f!▲");
                player.sendMessage("§dPuzzler §6gave you §21,000 Mithril Powder §6for solving the puzzle!");
                player.addMithrilPowder(1000);
                puzzle.remove();
            } else {
                player.sendMessage("§e[NPC] §dPuzzler§f: §c╳§f (╯°□°）╯︵ ┻━┻");
                player.sendMessage("§e[NPC] §dPuzzler§f: §c▶▲Wrong▲ ◀block◀ ▼▶try again▶▶");
                puzzle.markAsFailed();
            }
        }
    }

    public static int getBlockBreakStage(int totalTicks, int currentTick) {

        double result = ((double) currentTick / (double) totalTicks) * 10;


        return (int) result;
    }

    public static int getBlockBreakingPower(Block block) {
        if (block.getType() == Material.STONE || block.getType() == Material.COBBLESTONE || block.getType() == Material.COAL_ORE || block.getType() == Material.GLOWSTONE)
            return 1;
        if (block.getType() == Material.IRON_ORE || block.getType() == Material.LAPIS_ORE) return 2;
        if (block.getType() == Material.GOLD_ORE || block.getType() == Material.DIAMOND_ORE) return 3;
        if (block.getType() == Material.GRAY_WOOL || block.getType() == Material.CYAN_TERRACOTTA || block.getType() == Material.PRISMARINE || block.getType() == Material.DARK_PRISMARINE || block.getType() == Material.PRISMARINE_BRICKS || block.getType() == Material.LIGHT_BLUE_WOOL)
            return 4;
        if (block.getType() == Material.POLISHED_DIORITE) return 5;


        return 0;
    }

    public static int[] getCustomBlockData(Block block) {
        //1. is hardenes 2. is insta break
        int[] result = {0, 0};

        if (block.getType() == Material.STONE) {
            result[0] = 15;
            result[1] = 450;
        }
        if (block.getType() == Material.COBBLESTONE) {
            result[0] = 20;
            result[1] = 600;
        }
        if (block.getType() == Material.COAL_ORE || block.getType() == Material.IRON_ORE || block.getType() == Material.GOLD_ORE || block.getType() == Material.DIAMOND_ORE || block.getType() == Material.GLOWSTONE || block.getType() == Material.LAPIS_ORE || block.getType() == Material.EMERALD_ORE) {
            result[0] = 30;
            result[1] = 1800;
        }
        //grey mithril
        if (block.getType() == Material.GRAY_WOOL || block.getType() == Material.CYAN_TERRACOTTA) {
            result[0] = 500;
            result[1] = 30000;
        }
        if (block.getType() == Material.PRISMARINE || block.getType() == Material.DARK_PRISMARINE || block.getType() == Material.PRISMARINE_BRICKS) {
            result[0] = 800;
            result[1] = 48000;
        }
        if (block.getType() == Material.LIGHT_BLUE_WOOL) {
            result[0] = 1500;
            result[1] = 90000;
        }
        if (block.getType() == Material.POLISHED_DIORITE) {
            result[0] = 2000;
            result[1] = 120000;
        }

        return result;
    }

    public static int estimateBreakingTime(Player player, double blockStrength) {
        double mining_speed = Main.getPlayerStat(SkyblockPlayer.getSkyblockPlayer(player), Stats.MiningSpeed);
        double SoftCap = Tools.round(6.66666666666666666666666666666666666 * blockStrength, 0);
        if (SoftCap <= mining_speed) mining_speed = SoftCap;

        double MiningTime = (blockStrength * 30) / mining_speed;


        return (int) MiningTime;
    }

    public static boolean checkValid(Block block) {
        if (block.getType() == Material.STONE || block.getType() == Material.COBBLESTONE || block.getType() == Material.COAL_ORE || block.getType() == Material.IRON_ORE || block.getType() == Material.GOLD_ORE || block.getType() == Material.DIAMOND_ORE || block.getType() == Material.GLOWSTONE || block.getType() == Material.LAPIS_ORE || block.getType() == Material.EMERALD_ORE || ((Main.getMain().getServer().getPort() == 25564 || !Main.isLocalHost) && (block.getType() == Material.GRAY_WOOL || block.getType() == Material.CYAN_TERRACOTTA || block.getType() == Material.PRISMARINE || block.getType() == Material.DARK_PRISMARINE || block.getType() == Material.PRISMARINE_BRICKS || block.getType() == Material.LIGHT_BLUE_WOOL || block.getType() == Material.POLISHED_DIORITE)))
            return true;
        return false;
    }

    @EventHandler
    public void BlockBreak(BlockBreakEvent event) {
        if (FarmingUtils.crops.containsKey(event.getBlock().getType())) {
            FarmingUtils.cropBreak(event);
        }

        if (SkyblockServer.getServer().type() == ServerType.PrivateIsle) return;

        if (Main.getMain().getConfig().getBoolean("StatSystem") == true) event.setCancelled(true);

        if (Main.getMain().getConfig().getBoolean("StatSystem") == true && event.getPlayer().getItemInHand().getItemMeta() != null && Items.SkyblockItems.get(event.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)) != null) {

            if (!MiningSys.getRegisteredBlocks().containsKey(event.getBlock().getType())) return;


            ItemManager manager = Items.SkyblockItems.get(event.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
            if (manager.type == ItemType.Drill) {
                ItemStack item = event.getPlayer().getItemInHand();
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer data = meta.getPersistentDataContainer();

                data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, event.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER) - 1);
                item.setItemMeta(meta);
                event.getPlayer().setItemInHand(Main.item_updater(event.getPlayer().getItemInHand(), SkyblockPlayer.getSkyblockPlayer(event.getPlayer())));

            }
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
            MiningSys.getMiningSystem(player).getBlock().breakBlock(event.getBlock(), player);
            player.playSound(event.getBlock().getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
        }
    }

    public static int dropAmount(int minigFortune, int lowest, int highest) {

        Random rand = new Random();
        int dropBaseAmount = rand.nextInt(highest - lowest) + lowest;
        if (minigFortune != 0) {
            if (minigFortune < 100) {
                Random r = new Random();
                int low = 1;//includes 1
                int high = 100;// includes 100
                int result = r.nextInt(high - low) + low;
                if (minigFortune >= result) {
                    return dropBaseAmount + rand.nextInt(highest - lowest) + lowest;

                }
            } else {
                double guranteed = (double) minigFortune / 100;

                if (guranteed % 1 == 0) {
                    for (int i = (int) guranteed; i != 0; i--) {
                        dropBaseAmount += rand.nextInt(highest - lowest) + lowest;
                    }

                    return dropBaseAmount;


                } else {
                    int minus = (int) ((int) guranteed * 100);
                    double hitchance = (double) minigFortune - (double) minus;

                    Random r = new Random();
                    int low = 1;//includes 1
                    int high = 100;// includes 100
                    int result = r.nextInt(high - low) + low;
                    int finalAmount = (int) guranteed;
                    if (hitchance >= result) {
                        finalAmount = finalAmount + 1;
                    }
                    for (int i = (int) finalAmount; i != 0; i--) {
                        dropBaseAmount += rand.nextInt(highest - lowest) + lowest;
                    }

                    return dropBaseAmount;

                }
            }
        }

        return dropBaseAmount;

    }

    public static int dropAmount(int minigFortune, int amount) {


        int dropBaseAmount = amount;
        if (minigFortune != 0) {
            if (minigFortune < 100) {
                Random r = new Random();
                int low = 1;//includes 1
                int high = 100;// includes 100
                int result = r.nextInt(high - low) + low;
                if (minigFortune >= result) {
                    return dropBaseAmount + amount;

                }
            } else {
                double guranteed = (double) minigFortune / 100;

                if (guranteed % 1 == 0) {
                    for (int i = (int) guranteed; i != 0; i--) {
                        dropBaseAmount += amount;
                    }

                    return dropBaseAmount;


                } else {
                    int minus = (int) ((int) guranteed * 100);
                    double hitchance = (double) minigFortune - (double) minus;

                    Random r = new Random();
                    int low = 1;//includes 1
                    int high = 100;// includes 100
                    int result = r.nextInt(high - low) + low;
                    int finalAmount = (int) guranteed;
                    if (hitchance >= result) {
                        finalAmount = finalAmount + 1;
                    }
                    for (int i = (int) finalAmount; i != 0; i--) {
                        dropBaseAmount += amount;
                    }

                    return dropBaseAmount;

                }
            }
        }

        return amount;

    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent event) {
        if (SkyblockServer.getServer().type() == ServerType.PrivateIsle) return;

        if (Main.getMain().getConfig().getBoolean("StatSystem") == true) event.setCancelled(true);

        if (event.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) != null && event.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).equals("orb")) {
            ArmorStand stand = event.getBlock().getWorld().spawn(event.getBlock().getLocation().add(0, -0.5, 0), ArmorStand.class, s -> {
                s.setInvisible(true);
                s.setInvulnerable(true);
                s.setGravity(false);
                s.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/1d5a09884cb83ef5c908dddd385f246fefdee221712c010177f54376da238fdd"));
                s.setCustomName("§9Orb.exe");
                s.setCustomNameVisible(true);
            });

            orbStuff(stand, true, 15, 15 * 20);
        }

    }

    public void orbStuff(ArmorStand stand, boolean upmotion, int runnable, int timer) {
        new BukkitRunnable() {

            @Override
            public void run() {

                Location loc = stand.getLocation();
                loc.setYaw((float) (loc.getYaw() + 15));
                if (upmotion) {

                    stand.teleport(loc.add(0, 0.07, 0));
                } else {
                    stand.teleport(loc.add(0, -0.07, 0));
                }
                if (timer % 2 == 0) Particles(stand);
                else Particles2(stand);
                if (timer != 0) {
                    boolean newmotion = upmotion;
                    int newrunnable = runnable - 1;
                    if (newrunnable == 0) {

                        if (upmotion) {
                            smothDirectionChange(stand, 0.07, timer - 1, upmotion);
                        } else smothDirectionChange(stand, -0.07, timer - 1, upmotion);
                    } else {

                        orbStuff(stand, newmotion, newrunnable, timer - 1);
                    }
                } else {
                    stand.remove();
                }


            }
        }.runTaskLater(Main.getMain(), 1L);
    }

    public void smothDirectionChange(ArmorStand stand, double currmotion, int timer, boolean upmotion) {
        new BukkitRunnable() {

            @Override
            public void run() {
                double newMotion = 0;
                if (upmotion) {
                    newMotion = currmotion - 0.005;
                } else {
                    newMotion = currmotion + 0.005;
                }
                Location loc = stand.getLocation();
                loc.setYaw((float) (loc.getYaw() + 15));
                stand.teleport(loc.add(0, newMotion, 0));
                if (timer % 2 == 0) Particles(stand);
                else Particles2(stand);
                if (timer - 1 != 0) {

                    if (newMotion <= -0.07 && upmotion) {
                        orbStuff(stand, false, 15, timer - 1);
                    } else {
                        if (newMotion >= 0.07 && !upmotion) {
                            orbStuff(stand, true, 15, timer - 1);
                        } else {
                            smothDirectionChange(stand, newMotion, timer - 1, upmotion);
                        }
                    }
                } else {
                    stand.remove();
                }


            }
        }.runTaskLater(Main.getMain(), 1L);
    }

    public void Particles(ArmorStand stand) {

        Location loc = stand.getEyeLocation();

        Vector dir = stand.getEyeLocation().getDirection();
        dir.normalize();
        dir.multiply(0.625); //1 blocks a way
        loc.add(dir);

        final Particle.DustOptions dust = new Particle.DustOptions(Color.fromBGR(126, 85, 49), 1);
        loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 1, dust);
    }

    public void Particles2(ArmorStand stand) {

        Location loc = stand.getEyeLocation();

        Vector dir = stand.getEyeLocation().getDirection();
        dir.normalize();
        dir.multiply(0.625); //1 blocks a way
        loc.add(dir);
        loc.setY(loc.getY() - 0.04);

        final Particle.DustOptions dust = new Particle.DustOptions(Color.fromBGR(131, 119, 58), 1);
        loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 1, dust);
    }

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection();
        for (int i = 1; i <= 5; i++) {

            dir.normalize();
            dir.multiply(1); //5 blocks a way
            loc.add(dir.getX(), dir.getY(), dir.getZ());
            if (loc.getBlock().getType() == Material.COMMAND_BLOCK) {
                CommandBlock block = (CommandBlock) loc.getBlock().getState();


                if (block.getCommand().equals("maze")) {
                    maze.createinventory(loc);
                    player.openInventory(maze.mazeterminal);
                }
                if (block.getCommand().equals("order")) {
                    order.createInventory();
                    player.openInventory(order.orderterminal);
                }

                break;
            }


        }

    }

    @EventHandler
    public void move(PlayerMoveEvent e) {


        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(e.getPlayer());
        NPC.getNPCs().forEach(npc -> {

            Location npcloc = npc.getBukkitEntity().getLocation();
            List<Entity> nearby = npc.getBukkitEntity().getNearbyEntities(5, 5, 5);
            if (nearby.contains(player)) {
                npcloc = npcloc.setDirection(player.getLocation().subtract(npcloc).toVector());
                PlayerConnection connection = player.getHandle().b;
                float yaw = npcloc.getYaw();
                float pitch = npcloc.getPitch();
                connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte) ((yaw % 360.) * 256 / 360), (byte) ((pitch % 360.) * 256 / 360), false));
                connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) ((yaw % 360.) * 256 / 360)));

            }
        });
        for (PlayerDisguise disguise : PlayerDisguise.fake.values())
            disguise.check(player);
        for (QuestNpc npc : QuestNpc.shownNpc.get(player)) {
            Location l = npc.getLocations().get(player);
            if (l == null) {
                continue;
            }
            double distance = l.distance(player.getLocation());
            if (distance < player.getClientViewDistance() * 16 && npc.getHidden().contains(player))
                npc.show(player);
            else if (distance > player.getClientViewDistance() * 16 && !npc.getHidden().contains(player)) {
                npc.hide(player);
            }
            if (distance < 6) {
                PlayerConnection connection = player.getHandle().b;
                float yaw = l.getYaw();
                float pitch = l.getPitch();
                connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getNpc().getId(), (byte) ((yaw % 360.) * 256 / 360), (byte) ((pitch % 360.) * 256 / 360), false));
                connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc.getNpc(), (byte) ((yaw % 360.) * 256 / 360)));
            }

        }
        if (Main.getMain().getConfig().getBoolean("LavaBounce") == true) {
            if (player.getLocation().getBlock().getType() == Material.LAVA) {
                player.setVelocity(player.getLocation().getDirection().multiply(0).setY(2));
                player.getWorld().getEntities().forEach(entity -> {
                    if (entity.getScoreboardTags() != null) entity.getScoreboardTags().forEach(tag -> {
                        if (tag.startsWith("eye_target:")) {
                            Player p = Bukkit.getPlayer(tag.split(":")[1]);
                            if (p == player)
                                entity.getLocation().setDirection(player.getLocation().subtract(entity.getLocation()).toVector());
                        }
                    });
                });
            } else {

            }
        }
    }

    @EventHandler
    public void GamemodeEvent(PlayerGameModeChangeEvent event) {
        Bukkit.broadcastMessage(ChatColor.GOLD + event.getPlayer().getName() + " just Changed his Gamemode to: " + event.getNewGameMode().toString());
    }

    @EventHandler
    public void onQuid(PlayerQuitEvent event) {
        PacketReader reader = new PacketReader(event.getPlayer());
        reader.uninject(event.getPlayer());
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        if (ServerType.getActiveType() == ServerType.PrivateIsle) {
            try {
                PrivateIsle.isles.get(SkyblockPlayer.getSkyblockPlayer(player)).remove();
                PrivateIsle.isles.remove(SkyblockPlayer.getSkyblockPlayer(player));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (player.getRegion() != null) player.getRegion().leave(player);
        player.saveCommissionProgress();
        Main.absorbtion.remove(player);
        Main.absorbtionrunntime.remove(player);

        Main.saveCoins(player);
        Main.saveBits(player);
        Main.saveMithrilPowder(player);
        if (QuestNpc.shownNpc.containsKey(player)) for (QuestNpc npc : new ArrayList<>(QuestNpc.shownNpc.get(player)))
            npc.remove(player);

        try {
            player.saveInventory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Main.petstand.containsKey(player)) Main.petstand.get(player).remove();


    }

    @EventHandler
    public void respawn(PlayerRespawnEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());

        player.setHealth(Main.getPlayerStat(player, Stats.Health));

        Main.deathPersons.remove(event.getPlayer());

        if (SkyblockServer.getServer().type() == ServerType.PrivateIsle)
            event.setRespawnLocation(PrivateIslandManager.baseLocations.get(player));
        else event.setRespawnLocation(SkyblockServer.getServer().type().getLocation());

        Main.updatebar(player);
    }

    @EventHandler
    public void noFallingBLocks(EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof FallingBlock fB) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void StopBurn(EntityCombustEvent event) {

        if (event.getEntity() instanceof LivingEntity)

            if (event.getEntityType() != EntityType.ZOMBIE) {
                ItemStack helmet = ((LivingEntity) event.getEntity()).getEquipment().getHelmet();

                if (helmet != null && helmet.getType() != Material.AIR) {
                    // Mob has a helmet.
                    return;
                } else event.setCancelled(true);
            }
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {

    }

    @EventHandler
    public void stopKuudraSplit(SlimeSplitEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void switchTarget(EntityTargetLivingEntityEvent event) {
        if (event.getTarget() instanceof Player) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void SoulSystem(EntityDeathEvent e) {
        if (PlayerDisguise.nonFake.containsKey(e.getEntity().getEntityId()))
            PlayerDisguise.nonFake.get(e.getEntity().getEntityId()).kill((e.getEntity() == null || !(e.getEntity() instanceof Player p)) ? null : SkyblockPlayer.getSkyblockPlayer(p));
        if (e.getEntity() instanceof FallingBlock) {
            if (e.getEntity().getScoreboardTags().contains("voidgloom_beacon")) e.getEntity().getLocation();
        }
        if (e.getEntityType() != EntityType.PLAYER && e.getEntityType() != EntityType.DROPPED_ITEM) {

            if (e.getEntity() instanceof LivingEntity) {


                if (e.getEntity() instanceof EnderDragon) {
                    e.setDroppedExp(0);
                }

                if (SkyblockEntity.livingEntity.exists(e.getEntity()) && !SkyblockEntity.livingEntity.getSbEntity(e.getEntity()).isHasDoneDeath()) {
                    SkyblockEntity.livingEntity.getSbEntity(e.getEntity()).kill();
                }
                if (Main.dinnerboneNametags.containsKey(e.getEntity())) {
                    Main.dinnerboneNametags.get(e.getEntity()).remove();
                    Main.dinnerboneNametags.remove(e.getEntity());
                }

                Main.WitherSmallStuff.remove(e.getEntity());


                e.getEntity().setCustomNameVisible(false);
                Main.currentityhealth.remove(e.getEntity());
                Main.baseentityhealth.remove(e.getEntity());

                if (Main.entitydamage.containsKey(e.getEntity())) Main.entitydamage.remove(e.getEntity());
                if (e.getEntity().getScoreboardTags().contains("revslayert4")) {
                    e.getEntity().getScoreboardTags().forEach(tag -> {
                        if (tag.startsWith("killer:")) {
                            new DropSystem(e.getEntity(), Bukkit.getServer().getPlayer(tag.split(":")[1]), e);
                        }
                    });
                }
                if (!e.getEntity().addScoreboardTag("minionkill")) {
                    if (SkyblockEntity.livingEntity.exists((LivingEntity) e.getEntity())) {
                        for (String tag : e.getEntity().getScoreboardTags())
                            if (tag.startsWith("killer:")) {
                                new DropSystem(e.getEntity(), Bukkit.getServer().getPlayer(tag.split(":")[1]), e);
                                break;
                            }
                    }
                } else e.getDrops().clear();

                if (e.getEntity().getScoreboardTags().contains("voidgloomt2")) {
                    e.getDrops().clear();

                    e.getEntity().getScoreboardTags().forEach(tag -> {

                        if (tag.startsWith("owner:")) {

                            new DropSystem(e.getEntity(), Bukkit.getServer().getPlayer(tag.split(":")[1]), e);
                        }
                        if (tag.startsWith("rider:")) {
                            Bukkit.getEntity(UUID.fromString(tag.split(":")[1])).remove();
                        }
                    });
                }

                e.getEntity().getScoreboardTags().forEach(tag -> {


                    if (tag.startsWith("rider:")) {
                        Bukkit.getEntity(UUID.fromString(tag.split(":")[1])).remove();
                    }
                });

                ArrayList<Player> p = new ArrayList<>();
                e.getEntity().getScoreboardTags().forEach(tag -> {

                    if (tag.startsWith("killer:")) {

                        p.add(Bukkit.getServer().getPlayer(tag.split(":")[1]));
                    }

                });

                if (!p.isEmpty()) {
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p.get(0));


                    for (String tag : e.getEntity().getScoreboardTags())
                        if (tag.startsWith("combatxp:"))
                            SkyblockPlayer.getSkyblockPlayer(player).addSkillXp(Double.parseDouble(tag.split(":")[1]), Skills.Combat);
                    if (!p.isEmpty() && p.get(0) != null && player.getItemInHand().getItemMeta() != null && player.getItemInHand().getItemMeta().getEnchants().containsKey(SkyblockEnchants.TELIKINESIS)) {
                        for (ItemStack d : e.getDrops()) {
                            d = Main.item_updater(d, SkyblockPlayer.getSkyblockPlayer(player));
                            d = Main.item_updater(d, SkyblockPlayer.getSkyblockPlayer(player));
                            player.addItem(d);

                        }
                        e.getDrops().clear();
                    }
                }


                Main.EntityDeath(e.getEntity());
            }
        } else {

        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;

        ItemStack itemStack = event.getItem();

        if (itemStack.getType() == Material.NETHER_STAR) {
            if (itemStack.hasItemMeta()) {
                if (itemStack.getItemMeta().getPersistentDataContainer() != null) {
                    if (itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) != null && itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).equals("skyblockmenu")) {


                        OpenMenu.createInventory(event.getPlayer());
                        event.getPlayer().openInventory(OpenMenu.skyblockmenu);

                    }
                }
            }
        }
    }

    public static void teleport(Player player) {
        for (int i = 1; i <= 10; i++) {
            Location loc = player.getLocation();
            Vector dir = loc.getDirection();
            dir.normalize();
            dir.multiply(1); //1 blocks a way
            loc.add(dir);

            if (loc.getBlock().isEmpty() || loc.getBlock().isLiquid() || loc.getBlock().isPassable()) {
                player.teleport(loc);
            }
        }
    }

    @EventHandler
    public void MobSpawnEvent(EntitySpawnEvent event) {
        if (event.getEntity().getScoreboardTags().contains("npc")) return;
        Entity entity = event.getEntity();
        if (!(entity instanceof Player) && !(entity.getType() == EntityType.DROPPED_ITEM) && !(entity.getType() == EntityType.ARMOR_STAND) && !(entity.getType() == EntityType.WITHER_SKULL)) {
            if (entity instanceof LivingEntity e) {
                if (Main.baseentityhealth.get(entity) == null && !SkyblockEntity.livingEntity.exists(e)) {
                    new BasicEntity(e, (int) (e.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 5));
                }
                Main.updateentitystats(e);
            }
        }
    }


    @EventHandler
    public void InventoryClickLEL(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getType() != null && event.getClickedInventory().getType() == InventoryType.PLAYER) {
            if (event.getSlot() == 8) {
                event.setCancelled(true);
                OpenMenu.createInventory((Player) event.getWhoClicked());
                event.getWhoClicked().openInventory(OpenMenu.skyblockmenu);
            }
        }
    }

    public boolean hasfreeSlot(Player p) {
        return Arrays.asList(p.getInventory().getStorageContents()).contains(null);
    }

    @EventHandler
    public void pickupevent(PlayerPickupItemEvent event) {

        Player player = event.getPlayer();

        ItemStack item = player.getItemInHand();
        event.getItem().setItemStack(Main.item_updater(event.getItem().getItemStack(), SkyblockPlayer.getSkyblockPlayer(player)));
        if (item.getItemMeta() == null || item.getItemMeta().getPersistentDataContainer() == null || item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) == null || !item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).contains("shortbow"))
            return;
        ItemStack i = event.getItem().getItemStack();
        if (i == null) return;
        if (i.getType() == Material.ARROW) {


            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1F, 1F);
            if (i.getItemMeta().hasDisplayName()) {

                item = Items.FakeArrow(i.getItemMeta().getDisplayName());
                item.setAmount(i.getAmount());

                event.getItem().setItemStack(item);
                player.updateInventory();
            } else {
                item = Items.FakeArrow("Arrow");
                item.setAmount(i.getAmount());
                event.getItem().setItemStack(item);
                player.updateInventory();
            }

        } else {


        }


        event.getPlayer().updateInventory();
    }

    @EventHandler
    public void onDraw(PlayerInteractEvent event) {
        //On interact
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
            if (event.getItem() != null && event.getItem().getType() == Material.BOW) {
                if (Items.SkyblockItems.get(event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)) != null && Items.SkyblockItems.get(event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)).customDataContainer.containsValue("term"))
                    if (Main.termhits.get(event.getPlayer()) >= 3) {
                        final Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 0, (int) 0), 1);


                        for (double i = 0; i <= 15; i += 0.5) { //iterate up to but not beyond point b

                            Vector point = event.getPlayer().getEyeLocation().getDirection().multiply(i);
                            Location loc = event.getPlayer().getEyeLocation();
                            loc.add(point);
                            event.getPlayer().getLocation().getWorld().spawnParticle(Particle.DRIP_LAVA, loc.getX(), loc.getY(), loc.getZ(), 1);
                            event.getPlayer().getLocation().getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 1, dust);
                        }

                        ArrayList<Entity> alrHitEntitys = new ArrayList<>();
                        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
                        for (double i = 0; i <= 15; i += 0.1) {
                            if (event.getPlayer().getWorld().getNearbyEntities(event.getPlayer().getEyeLocation().add(event.getPlayer().getEyeLocation().getDirection().multiply(i)), 0.2, 0.2, 0.2).isEmpty())
                                continue;

                            for (Entity entity : event.getPlayer().getWorld().getNearbyEntities(event.getPlayer().getEyeLocation().add(event.getPlayer().getEyeLocation().getDirection().multiply(i)), 1, 1, 1)) {
                                if (entity instanceof LivingEntity e && !(entity instanceof ArmorStand) && entity != event.getPlayer() && !alrHitEntitys.contains(entity)) {
                                    e.damage(0.0000001);
                                    Calculator c = new Calculator();
                                    c.playerToEntityDamage(e, player, new Bundle<>(2d, 1d));
                                    c.damageEntity(e, player);
                                    c.showDamageTag(e);
                                    alrHitEntitys.add(entity);
                                }
                            }
                        }
                        Main.termhits.replace(event.getPlayer(), 0);
                        Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
                        return;
                    }
            }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
            if (event.getItem() != null && event.getItem().getType() == Material.BOW) {
                ItemStack item = event.getItem();
                if (item.getItemMeta() == null || item.getItemMeta().getPersistentDataContainer() == null || item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) == null || !item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).contains("shortbow"))
                    return;


                event.setCancelled(true);
                final boolean infinity;
                if (item.getEnchantments().containsKey(Enchantment.ARROW_INFINITE)) {
                    infinity = true;
                } else {
                    infinity = false;
                }
                if (!Main.shortbow_cd.get(event.getPlayer())) try {

                    event.getPlayer().getInventory().forEach(i -> {
                        if (i != null) {
                            if (i.getItemMeta() == null || i.getItemMeta().getPersistentDataContainer() == null || i.getItemMeta().getPersistentDataContainer().get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING) == null || !i.getItemMeta().getPersistentDataContainer().get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING).contains("fakearrow")) {

                            } else {
                                if (!infinity) i.setAmount(i.getAmount() - 1);
                                event.getPlayer().updateInventory();
                                SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
                                Arrow arrow_entity = event.getPlayer().launchProjectile(Arrow.class);


                                int cc = (int) Main.getPlayerStat(player, Stats.CritChance);


                                int stre = (int) Main.getPlayerStat(player, Stats.Strength);
                                double cd = Main.getPlayerStat(player, Stats.CritDamage);
                                int weapondmg = (int) Main.weapondamage(event.getItem());
                                int ferocity = (int) Main.getPlayerStat(player, Stats.Ferocity);


                                arrow_entity.addScoreboardTag("cd:" + cd);
                                arrow_entity.addScoreboardTag("cc:" + cc);
                                arrow_entity.addScoreboardTag("strength:" + stre);
                                arrow_entity.addScoreboardTag("ferocity:" + ferocity);
                                arrow_entity.addScoreboardTag("dmg:" + weapondmg);

                                if (item.getEnchantments().containsKey(Enchantment.ARROW_DAMAGE)) {
                                    arrow_entity.addScoreboardTag("power:" + item.getEnchantments().get(Enchantment.ARROW_DAMAGE));
                                }
                                for (Enchantment enchantment : item.getItemMeta().getEnchants().keySet()) {
                                    arrow_entity.addScoreboardTag(enchantment.getKey().getKey() + ":" + item.getItemMeta().getEnchantLevel(enchantment));
                                }

                                arrow_entity.setDamage(1);


                                if (HydraStrike.hasHydraStrike(player)) {
                                    arrow_entity.setVelocity(arrow_entity.getVelocity().multiply(HydraStrike.get(player).getFlySpeed()));


                                    if (HydraStrike.get(player).stacks == 10) {
                                        Arrow arrow_entity2 = event.getPlayer().launchProjectile(Arrow.class);
                                        arrow_entity2.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(5)));
                                        arrow_entity2.setDamage(1);
                                        Arrow arrow_entity3 = event.getPlayer().launchProjectile(Arrow.class);
                                        arrow_entity3.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(-5)));
                                        arrow_entity3.setDamage(1);
                                        arrow_entity.addScoreboardTag("term:" + player.getName());
                                        arrow_entity.getScoreboardTags().forEach(tag -> {
                                            arrow_entity2.addScoreboardTag(tag);
                                            arrow_entity3.addScoreboardTag(tag);
                                        });
                                        arrow_entity2.addScoreboardTag("custom_arrow");
                                        arrow_entity3.addScoreboardTag("custom_arrow");
                                    }
                                }

                                player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
                                if (item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "shoottype"), PersistentDataType.STRING) != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "shoottype"), PersistentDataType.STRING).equals("term")) {
                                    Arrow arrow_entity2 = event.getPlayer().launchProjectile(Arrow.class);
                                    arrow_entity2.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(5)));
                                    arrow_entity2.setDamage(1);
                                    Arrow arrow_entity3 = event.getPlayer().launchProjectile(Arrow.class);
                                    arrow_entity3.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(-5)));
                                    arrow_entity3.setDamage(1);
                                    arrow_entity.addScoreboardTag("term:" + player.getName());
                                    arrow_entity.getScoreboardTags().forEach(tag -> {
                                        arrow_entity2.addScoreboardTag(tag);
                                        arrow_entity3.addScoreboardTag(tag);
                                    });
                                }


                                Main.getMain().juju_cooldown(event.getPlayer());

                                throw new RuntimeException();
                            }
                        }


                    });
                } catch (RuntimeException ignored) {
                }
            }
    }

    @EventHandler
    public void ReplaceArrows(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void swap(PlayerItemHeldEvent event) {

        Main.item_updater(event.getPlayer().getInventory().getItem(event.getNewSlot()), SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        event.getPlayer().updateInventory();
        Player player = event.getPlayer();
        player.updateInventory();
        ItemStack item = player.getInventory().getItem(event.getNewSlot());

        if (item == null || item.getItemMeta() == null || item.getItemMeta().getPersistentDataContainer() == null || item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) == null || !item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).contains("shortbow")) {

            for (int slot = 0; slot < player.getInventory().getSize() - 1; ++slot) {
                ItemStack i = player.getInventory().getItem(slot);
                if (i == null) continue;

                if (i.getItemMeta() == null || i.getItemMeta().getPersistentDataContainer() == null || i.getItemMeta().getPersistentDataContainer().get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING) == null || !i.getItemMeta().getPersistentDataContainer().get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING).contains("fakearrow"))
                    continue;


                player.getInventory().setItem(slot, Main.item_updater(Main.item_updater(new ItemStack(Material.ARROW), SkyblockPlayer.getSkyblockPlayer(player)), SkyblockPlayer.getSkyblockPlayer(player)));
                player.getInventory().getItem(slot).setAmount(i.getAmount());
                player.updateInventory();
            }
        } else {
            for (int slot = 0; slot < player.getInventory().getSize() - 1; ++slot) {
                ItemStack i = player.getInventory().getItem(slot);
                if (i != null) if (i.getType() == Material.ARROW) {
                    if (i.getItemMeta().hasDisplayName()) {
                        player.getInventory().setItem(slot, Items.FakeArrow(i.getItemMeta().getDisplayName()));
                        player.getInventory().getItem(slot).setAmount(i.getAmount());
                        player.updateInventory();
                    } else {
                        player.getInventory().setItem(slot, Items.FakeArrow("Arrow"));
                        player.getInventory().getItem(slot).setAmount(i.getAmount());
                        player.updateInventory();
                    }
                }

            }
        }
    }

    @EventHandler
    public void stopTeleport(EntityTeleportEvent event) {
        Entity e = event.getEntity();
        if (event.getEntity().getType() == EntityType.ENDERMAN)
            event.setCancelled(true);

    }

    @EventHandler
    public void bowshootevent(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(((Player) event.getEntity()).getPlayer());

        Entity arrow_entity = event.getProjectile();


        int cc = (int) Main.getPlayerStat(player, Stats.CritChance);

        float damage;

        int stre = (int) Main.getPlayerStat(player, Stats.Strength);
        double cd = Main.getPlayerStat(player, Stats.CritDamage);
        int weapondmg = (int) Main.weapondamage(event.getBow());

        if (event.getForce() != 1.0) cc = 0;
        arrow_entity.addScoreboardTag("cd:" + cd);
        arrow_entity.addScoreboardTag("cc:" + cc);
        arrow_entity.addScoreboardTag("strength:" + stre);
        arrow_entity.addScoreboardTag("dmg:" + weapondmg);
        arrow_entity.addScoreboardTag("ferocity:" + Main.getPlayerStat(player, Stats.Ferocity));
        for (Enchantment enchantment : event.getBow().getItemMeta().getEnchants().keySet()) {
            arrow_entity.addScoreboardTag(enchantment.getKey().getKey() + ":" + event.getBow().getItemMeta().getEnchantLevel(enchantment));
        }
        if (HydraStrike.hasHydraStrike(player)) {
            arrow_entity.setVelocity(arrow_entity.getVelocity().multiply(HydraStrike.get(player).getFlySpeed()));


            if (HydraStrike.get(player).stacks == 10) {
                Arrow arrow_entity2 = player.launchProjectile(Arrow.class);
                arrow_entity2.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(5)));
                arrow_entity2.setDamage(1);
                Arrow arrow_entity3 = player.launchProjectile(Arrow.class);
                arrow_entity3.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(-5)));
                arrow_entity3.setDamage(1);
                arrow_entity.addScoreboardTag("term:" + player.getName());
                arrow_entity.getScoreboardTags().forEach(tag -> {
                    arrow_entity2.addScoreboardTag(tag);
                    arrow_entity3.addScoreboardTag(tag);
                });
            }
        }


    }

    public static ItemStack newarrow(Material material, @NotNull ItemStack old) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (old.getItemMeta().getDisplayName() != "") {
            meta.setDisplayName(old.getItemMeta().getDisplayName());
        }

        item.setItemMeta(meta);
        ItemStack arrow = item;

        return arrow;
    }

    @EventHandler
    public void ProjectileHitEvent(ProjectileHitEvent e) {
        if (e.getHitBlock() != null) {
            e.getEntity().remove();
            if (e.getEntity() instanceof FishHook && LavaFishingHook.contains((FishHook) e.getEntity()))
                LavaFishingHook.get((FishHook) e.getEntity()).remove();
        }
        if (e.getHitEntity() != null && e.getHitEntity().getScoreboardTags().contains("npc")) {
            e.setCancelled(true);
            return;
        }
        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getHitEntity() != null && e.getHitEntity() instanceof Player) {
                e.setCancelled(true);
                return;
            }
            if (e.getHitEntity() == null) return;


            HashMap<String, Integer> stats = new HashMap<String, Integer>();
            SkyblockPlayer player = null;
            int power = 0;
            double cd = 0;
            for (String str : e.getEntity().getScoreboardTags()) {
                if (str.startsWith("cd:")) {
                    String[] num = str.split(":");
                    cd = Double.parseDouble(num[1]);
                }
                if (str.startsWith("cc:")) {
                    String[] num = str.split(":");
                    stats.put("cc", (int) Double.parseDouble(num[1]));
                }
                if (str.startsWith("strength:")) {
                    String[] num = str.split(":");
                    stats.put("stre", (int) Double.parseDouble(num[1]));
                }
                if (str.startsWith("dmg:")) {
                    String[] num = str.split(":");
                    stats.put("dmg", (int) Double.parseDouble(num[1]));
                }
                if (str.startsWith("ferocity:")) {
                    String[] num = str.split(":");
                    stats.put("ferocity", (int) Double.parseDouble(num[1]));
                }
                if (str.startsWith("term:")) {
                    stats.put("term", 1);
                }
                if (str.startsWith("power:")) power = Integer.parseInt(str.split(":")[1]);

            }
            player = SkyblockPlayer.getSkyblockPlayer((Player) e.getEntity().getShooter());

            if (e.getEntity().getScoreboardTags().contains("hit:" + e.getHitEntity().getEntityId())) return;
            e.getEntity().addScoreboardTag("hit:" + e.getHitEntity().getEntityId());


            int cc;
            int stre;
            int dmg;
            int ferocity = 0;


            if (stats.containsKey("cc")) {
                cc = stats.get("cc");
            } else {
                cc = 0;
            }
            if (stats.containsKey("stre")) {
                stre = stats.get("stre");
            } else {
                stre = 0;
            }
            if (stats.containsKey("dmg")) {
                dmg = stats.get("dmg");
            } else {
                dmg = 0;
            }
            if (stats.containsKey("ferocity")) {
                ferocity = stats.get("ferocity");
            } else {
                ferocity = 0;
            }


            if (e.getHitEntity() != null) {

                if (e.getHitEntity().getScoreboardTags().contains("invinc")) {
                    e.setCancelled(true);
                    return;
                }

                if (Main.baseentityhealth.containsKey(e.getHitEntity()) || SkyblockEntity.livingEntity.exists(e.getHitEntity())) {

                    int cccalc = (int) (Math.random() * 100 + 1);
                    Calculator calculator = new Calculator(e.getEntity());
                    calculator.playerToEntityDamage((LivingEntity) e.getHitEntity(), player);

                    double mult = 1;
                    double extramult = 0;
                    if (HydraStrike.hasHydraStrike(player)) {
                        extramult = 0.02 * HydraStrike.get(player).stacks;
                    }
                    mult += extramult;

                    mult += power * 0.08;

                    calculator.damage *= mult;
                    calculator.damageEntity((LivingEntity) e.getHitEntity(), player, DamageCause.PROJECTILE);


                    double damage = calculator.damage;

                    if (calculator.getResult().isCancelled()) return;

                    if (stats.containsKey("term")) {

                        for (String tag : e.getEntity().getScoreboardTags())
                            if (tag.startsWith("term:"))
                                player = SkyblockPlayer.getSkyblockPlayer(Bukkit.getPlayer(tag.split(":")[1]));
                        if (player != null) Main.termhits.replace(player, Main.termhits.get(player) + 1);
                        Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
                    }

                    final double FINAL_DAMAGE = damage;

                    if (ferocity != 0) {


                        if (ferocity < 100) {
                            Random r = new Random();
                            int low = 1;//includes 1
                            int high = 100;// includes 100
                            int result = r.nextInt(high - low) + low;
                            if (ferocity >= result) {

                                Ferocity.hit((LivingEntity) e.getHitEntity(), (int) damage, cccalc <= cc, player);
                                Main.updateentitystats((LivingEntity) e.getHitEntity());
                            }
                        } else {
                            double hits = (double) ferocity / 100;

                            if (hits % 1 == 0) {

                                ferocity_call((Entity) e.getHitEntity(), damage, cccalc, cc, player, (int) hits);


                            } else {
                                int minus = (int) ((int) hits * 100);
                                double hitchance = (double) ferocity - (double) minus;

                                Random r = new Random();
                                int low = 1;//includes 1
                                int high = 100;// includes 100
                                int result = r.nextInt(high - low) + low;

                                if (hitchance >= result) {
                                    hits = hits + 1;
                                }
                                ferocity_call((Entity) e.getHitEntity(), damage, cccalc, cc, player, (int) hits);
                            }
                        }
                    }


                    calculator.showDamageTag(e.getHitEntity());


                } else {
                    if (e.getHitEntity() instanceof CraftEnderDragonPart) {
                        Calculator calculator = new Calculator(e.getEntity());
                        calculator.playerToEntityDamage(((CraftEnderDragonPart) e.getHitEntity()).getParent(), player);

                        double mult = 1;
                        double extramult = 0;
                        if (HydraStrike.hasHydraStrike(player)) {
                            extramult = 0.02 * HydraStrike.get(player).stacks;
                        }
                        mult += extramult;

                        mult += power * 0.08;

                        calculator.damage *= mult;

                        calculator.damageEntity(((CraftEnderDragonPart) e.getHitEntity()).getParent(), player, DamageCause.PROJECTILE);
                        calculator.showDamageTag(e.getHitEntity());
                    }
                }

            } else {
                e.getEntity().remove();
            }
        }


        if (e.getHitEntity() == null) return;


        if (!(e.getEntity().getShooter() instanceof Player)) {


            if (e.getHitEntity() == null || !(e.getHitEntity() instanceof Player)) return;
            SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer((Player) e.getHitEntity());

            if (p.getGameMode() == GameMode.CREATIVE) {
                e.setCancelled(true);
                return;
            }

            LivingEntity entity = (LivingEntity) e.getEntity().getShooter();
            if (SkyblockEntity.livingEntity.exists(entity)) {

                e.getEntity().addScoreboardTag("hit:" + p.getName());
                Calculator c = new Calculator(e.getEntity());
                if (e.getEntity() instanceof SkyblockProjectile sbP) {
                    c.entityToPlayerDamage(SkyblockEntity.livingEntity.getSbEntity(entity), p, sbP.getDamageBundle());
                    sbP.onRawHit((LivingEntity) e.getHitEntity());
                } else c.entityToPlayerDamage(SkyblockEntity.livingEntity.getSbEntity(entity), p);
                c.damagePlayer(p, DamageCause.PROJECTILE);
            }
        }
    }

    @EventHandler
    public void DamageEvent(org.bukkit.event.entity.EntityDamageEvent event) {
        if (Main.entitydead.containsKey(event.getEntity())) return;
        if (event.getEntity().getScoreboardTags().contains("npc")) {
            if (!EntityNPC.isKillable) {
                event.setCancelled(true);
            }
            return;
        }
        if (event.getCause() == DamageCause.ENTITY_ATTACK) return;

        if (event.getEntity() instanceof Player) {
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) event.getEntity());

            if (Main.getMain().getConfig().getBoolean("StatSystem")) {
                if (event.getCause() == DamageCause.FALL) {
                    event.setDamage(0);

                    int falldistance = (int) player.getFallDistance();

                    int damage = (int) (falldistance * 5) - 15;
                    if (damage < 0) damage = 0;
                    if (Main.absorbtion.get(player) - damage < 0) {
                        int restdamage = damage - Main.absorbtion.get(player);
                        Main.absorbtion.replace(player, 0);
                        if (player.currhealth - restdamage < 0) restdamage = player.currhealth;
                        player.setHealth(player.currhealth - restdamage, HealthChangeReason.Damage);
                    } else {
                        Main.absorbtion.replace(player, Main.absorbtion.get(player) - damage);
                    }

                    if (player.currhealth <= 0) {
                        player.getPlayer().setHealth(0);

                    } else Main.updatebar(player);
                }
                if (event.getCause() == DamageCause.VOID) player.setHealth(0, HealthChangeReason.Creative);
                Main.updatebar(player);


            }

        } else {

            if (event.getEntityType() != EntityType.ARMOR_STAND && !(event.getEntity().getType() == EntityType.WITHER_SKULL)) {
                if (event.getEntity() instanceof LivingEntity) {
                    if (event.getCause() != DamageCause.CUSTOM && event.getCause() != DamageCause.PROJECTILE) {
                        LivingEntity e = (LivingEntity) event.getEntity();
                        int damage = (int) event.getDamage();
                        event.setDamage(0D);
                        if (event.getCause() != DamageCause.ENTITY_ATTACK && event.getCause() != DamageCause.ENTITY_SWEEP_ATTACK) {

                            if (Main.entitydead.containsKey(event.getEntity())) return;
                            if (!Main.currentityhealth.containsKey(event.getEntity())) return;
                            Main.currentityhealth.replace(event.getEntity(), (int) (Main.currentityhealth.get(event.getEntity()) - (damage * 5)));

                            Main.updateentitystats((LivingEntity) event.getEntity());
                            Location fakeloc = new Location(event.getEntity().getWorld(), 0, 0, 0);
                            Location loc = new Location(event.getEntity().getWorld(), event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY() + 0.5, event.getEntity().getLocation().getZ());
                            ArmorStand stand = (ArmorStand) event.getEntity().getWorld().spawnEntity(fakeloc, EntityType.ARMOR_STAND);
                            stand.setVisible(false);
                            stand.setGravity(false);
                            stand.teleport(loc);
                            stand.setCustomNameVisible(true);


                            stand.setInvulnerable(true);
                            stand.setCustomName("§7" + (int) damage * 5);
                            stand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
                            stand.addScoreboardTag("damage_tag");
                            stand.setArms(false);
                            stand.setBasePlate(false);
                            stand.setMarker(true);

                            Main.getMain().killarmorstand(stand);

                            e.setCustomNameVisible(true);
                        }
                    } else {

                        Main.updateentitystats((LivingEntity) event.getEntity());


                    }
                } else {
                    event.setDamage(0);
                }


            }
        }

    }

    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        if (event.getEntity() instanceof Zombie || event.getEntity() instanceof Skeleton) {
            event.setCancelled(true);

        }
        if (event.getEntity().getScoreboardTags().contains("npc")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void EntityDamageEvent(EntityDamageByEntityEvent event) {
        if (event.getEntity().getScoreboardTags().contains("npc")) {
            event.setCancelled(true);
            return;
        }
        if (event.getDamage() < 0.1) {
            return;
        }
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            event.setDamage(0);
            event.setCancelled(true);
            return;
        }

        Entity damager = event.getDamager();

        if (event.getEntity() instanceof Player && event.getDamager() instanceof LivingEntity) {
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) event.getEntity());
            if (SkyblockEntity.isOnCooldown((LivingEntity) event.getDamager())) {
                event.setCancelled(true);
                return;
            }
            SkyblockEntity.setOnCooldown((LivingEntity) event.getDamager());


            if (Main.getMain().getConfig().getBoolean("StatSystem")) {
                if (event.getCause() == DamageCause.FALL) {
                    event.setCancelled(true);

                    int falldistance = (int) player.getFallDistance();

                    int damage = (int) (falldistance * 5) - 15;
                    if (damage < 0) damage = 0;
                    if (Main.absorbtion.get(player) - damage < 0) {
                        int restdamage = damage - Main.absorbtion.get(player);
                        Main.absorbtion.replace(player, 0);
                        player.setHealth(player.currhealth - restdamage, HealthChangeReason.Damage);
                    } else {
                        Main.absorbtion.replace(player, Main.absorbtion.get(player) - damage);
                    }

                    Main.updatebar(player);


                } else {
                    if (event.getDamager() instanceof Player) {
                        event.setDamage(0);
                        event.setCancelled(true);
                        return;
                    } else {


                        float damage = (float) (event.getDamage() * 8);
                        event.setDamage(0);
                        event.setCancelled(true);
                        Calculator c = new Calculator();
                        if (SkyblockEntity.livingEntity.exists(event.getDamager())) {
                            c.entityToPlayerDamage(SkyblockEntity.livingEntity.getSbEntity(event.getDamager()), player);
                        } else {
                            if (Main.entitydamage.containsKey(event.getDamager()))
                                damage = Main.entitydamage.get(event.getDamager());
                            double health = Main.getPlayerStat(player, Stats.Health);
                            double ehp = health * (1 + (Main.getPlayerStat(player, Stats.Defense) / 100));
                            double effectivedmg = health / ehp;
                            double totaldmg = (damage * effectivedmg);
                            c.damage = totaldmg;
                        }


                        c.damagePlayer(player);

                    }
                }
            }
        } else {

            if (event.getEntityType() != EntityType.DROPPED_ITEM && event.getEntityType() != EntityType.ARMOR_STAND && !(event.getEntity().getType() == EntityType.WITHER_SKULL) && event.getCause() != DamageCause.PROJECTILE) {
                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity e = (LivingEntity) event.getEntity();
                    if (Main.entitydead.containsKey(e)) return;

                    event.setDamage(0D);
                    if (event.getDamager() instanceof Player) {
                        if (e instanceof Player) {
                            event.setCancelled(true);
                            return;
                        }

                        Player player = (Player) event.getDamager();
                        int cc = (int) Main.getPlayerStat(SkyblockPlayer.getSkyblockPlayer(player), Stats.CritChance);

                        double damage;
                        Calculator calculator = new Calculator();
                        calculator.playerToEntityDamage(e, SkyblockPlayer.getSkyblockPlayer(player));
                        int cccalc = calculator.cccalc;
                        damage = calculator.damage;


                        boolean voidgloom = false;


                        if (player.getItemInHand().getType() == Material.BOW) {
                            return;
                        }


                        event.setDamage(0);
                        if (e.getScoreboardTags().contains("invinc")) {
                            event.setCancelled(true);
                            return;
                        }

                        if (e.getScoreboardTags().contains("voidgloomt1") || e.getScoreboardTags().contains("voidgloomt2") || e.getScoreboardTags().contains("voidgloomt3") || e.getScoreboardTags().contains("voidgloomt4")) {
                            voidgloom = true;
                            final Vector vec = new Vector();
                            e.setVelocity(vec);

                            new BukkitRunnable() {
                                public void run() {
                                    e.setVelocity(vec);
                                }
                            }.runTaskLater(Main.getMain(), 1l);


                        } else {
                            voidgloom = false;
                        }
                        calculator.damageEntity(e, SkyblockPlayer.getSkyblockPlayer(player), DamageCause.ENTITY_ATTACK);

                        if (SkyblockEntity.livingEntity.exists(e)) {
                            SkyblockEntity se = SkyblockEntity.livingEntity.getSbEntity(e);
                            if (se.hasNoKB()) {
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        e.setVelocity(new Vector(0, 0, 0));
                                    }
                                }.runTaskLater(Main.getMain(), 1);
                            }


                        }


                        Main.updateentitystats((LivingEntity) event.getEntity());

                        calculator.showDamageTag(e);
                        e.setCustomNameVisible(true);
                    }

                }
            }
            event.setDamage(0);
        }


    }

    public static void kill_voidgloom_beacon(Entity e) {
        e.getWorld().getEntities().forEach(entity -> {
            if (entity.getScoreboardTags() != null) {
                entity.getScoreboardTags().forEach(tag -> {
                    if (tag.startsWith("entity:")) {
                        if (Bukkit.getEntity(UUID.fromString(tag.split(":")[1])) == e) {
                            entity.remove();
                        }
                    }
                });
            }
        });
    }


    public static void ferocity_call(Entity e, double damage, int cccalc, int cc, Player player, int times) {

        HashMap<Player, Integer> hits = new HashMap<>();
        hits.put(player, times);
        if (!hits.containsKey(player)) return;
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Ferocity.hit((LivingEntity) e, damage, cccalc <= cc, player);

                Main.updateentitystats((LivingEntity) e);
                if (getEntityHealth((LivingEntity) e) <= 0) hits.replace(player, 1);
                hits.replace(player, hits.get(player) - 1);
                if (hits.get(player) != 0) ferocity_call(e, damage, cccalc, cc, player, hits.get(player));
                else hits.remove(player);
            }
        };
        runnable.runTaskLater(Main.getMain(), 2);
    }

    private static int getEntityHealth(LivingEntity entity) {
        if (SkyblockEntity.livingEntity.exists(entity))
            return SkyblockEntity.livingEntity.getSbEntity(entity).getHealth();
        if (Main.currentityhealth.containsKey(entity)) return Main.currentityhealth.get(entity);


        return 0;
    }

    public static void ferocity_Player_call(Player e, double damage, int cccalc, int cc, Player player, int times, EntityDamageByEntityEvent event) {
        HashMap<Player, Integer> hits = new HashMap<>();
        hits.put(player, times);
        if (!hits.containsKey(player)) return;
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Ferocity.playerhit(e, (int) damage, cccalc <= cc, event);

                Main.updatebar(SkyblockPlayer.getSkyblockPlayer(e));
                if (SkyblockPlayer.getSkyblockPlayer(e) == null || SkyblockPlayer.getSkyblockPlayer(e).currhealth <= 0)
                    hits.replace(player, 1);
                hits.replace(player, hits.get(player) - 1);
                if (hits.get(player) != 0) ferocity_call(e, damage, cccalc, cc, player, hits.get(player));
                else hits.remove(player);
            }
        };
        runnable.runTaskLater(Main.getMain(), 2);
    }

    @EventHandler
    public void inventoryUpdate(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getType() != InventoryType.PLAYER || event.getClickedInventory().getType() != InventoryType.CREATIVE)
            return;
        new BukkitRunnable() {
            @Override
            public void run() {
                player.getInventory().forEach(item -> {
                    Main.item_updater(event.getCurrentItem(), SkyblockPlayer.getSkyblockPlayer(player));

                });
            }
        }.runTaskLater(Main.getMain(), 1L);

    }
}