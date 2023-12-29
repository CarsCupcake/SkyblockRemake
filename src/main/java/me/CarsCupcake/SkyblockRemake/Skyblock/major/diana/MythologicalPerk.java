package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana;

import kotlin.Triple;
import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs.*;
import me.CarsCupcake.SkyblockRemake.utils.Factory;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.loot.CoinLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.Loot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class MythologicalPerk {
    public static List<Location[]> blocks = List.of(
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -203.5, 74.0, -76.5), new Location(Main.getMain().getServer().getWorld("world"), -202.5, 74.0, -76.5), new Location(Main.getMain().getServer().getWorld("world"), -203.5, 74.0, -75.5), new Location(Main.getMain().getServer().getWorld("world"), -202.5, 74.0, -75.5), new Location(Main.getMain().getServer().getWorld("world"), -200.5, 74.0, -76.5), new Location(Main.getMain().getServer().getWorld("world"), -200.5, 74.0, -75.5), new Location(Main.getMain().getServer().getWorld("world"), -200.5, 74.0, -74.5), new Location(Main.getMain().getServer().getWorld("world"), -201.5, 74.0, -74.5), new Location(Main.getMain().getServer().getWorld("world"), -201.5, 74.0, -73.5), new Location(Main.getMain().getServer().getWorld("world"), -201.5, 74.0, -72.5), new Location(Main.getMain().getServer().getWorld("world"), -202.5, 74.0, -72.5), new Location(Main.getMain().getServer().getWorld("world"), -202.5, 74.0, -70.5), new Location(Main.getMain().getServer().getWorld("world"), -200.5, 74.0, -70.5), new Location(Main.getMain().getServer().getWorld("world"), -199.5, 74.0, -70.5), new Location(Main.getMain().getServer().getWorld("world"), -201.5, 74.0, -68.5), new Location(Main.getMain().getServer().getWorld("world"), -203.5, 74.0, -68.5), new Location(Main.getMain().getServer().getWorld("world"), -205.5, 74.0, -68.5), new Location(Main.getMain().getServer().getWorld("world"), -206.5, 74.0, -69.5), new Location(Main.getMain().getServer().getWorld("world"), -208.5, 74.0, -68.5), new Location(Main.getMain().getServer().getWorld("world"), -208.5, 74.0, -67.5), new Location(Main.getMain().getServer().getWorld("world"), -207.5, 74.0, -65.5), new Location(Main.getMain().getServer().getWorld("world"), -205.5, 74.0, -64.5), new Location(Main.getMain().getServer().getWorld("world"), -202.5, 74.0, -64.5), new Location(Main.getMain().getServer().getWorld("world"), -198.5, 75.0, -64.5), new Location(Main.getMain().getServer().getWorld("world"), -198.5, 75.0, -61.5), new Location(Main.getMain().getServer().getWorld("world"), -199.5, 75.0, -59.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -219.5, 73.0, -24.5), new Location(Main.getMain().getServer().getWorld("world"), -216.5, 73.0, -24.5), new Location(Main.getMain().getServer().getWorld("world"), -217.5, 73.0, -21.5), new Location(Main.getMain().getServer().getWorld("world"), -216.5, 73.0, -19.5), new Location(Main.getMain().getServer().getWorld("world"), -218.5, 73.0, -19.5), new Location(Main.getMain().getServer().getWorld("world"), -219.5, 73.0, -17.5), new Location(Main.getMain().getServer().getWorld("world"), -220.5, 73.0, -15.5), new Location(Main.getMain().getServer().getWorld("world"), -216.5, 73.0, -15.5), new Location(Main.getMain().getServer().getWorld("world"), -213.5, 73.0, -15.5), new Location(Main.getMain().getServer().getWorld("world"), -210.5, 74.0, -12.5), new Location(Main.getMain().getServer().getWorld("world"), -210.5, 74.0, -9.5), new Location(Main.getMain().getServer().getWorld("world"), -216.5, 74.0, -7.5), new Location(Main.getMain().getServer().getWorld("world"), -220.5, 75.0, -6.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -254.5, 79.0, 43.5), new Location(Main.getMain().getServer().getWorld("world"), -254.5, 79.0, 40.5), new Location(Main.getMain().getServer().getWorld("world"), -254.5, 79.0, 39.5), new Location(Main.getMain().getServer().getWorld("world"), -252.5, 79.0, 38.5), new Location(Main.getMain().getServer().getWorld("world"), -250.5, 79.0, 38.5), new Location(Main.getMain().getServer().getWorld("world"), -249.5, 79.0, 36.5), new Location(Main.getMain().getServer().getWorld("world"), -252.5, 78.0, 33.5), new Location(Main.getMain().getServer().getWorld("world"), -251.5, 78.0, 31.5), new Location(Main.getMain().getServer().getWorld("world"), -248.5, 79.0, 32.5), new Location(Main.getMain().getServer().getWorld("world"), -246.5, 80.0, 37.5), new Location(Main.getMain().getServer().getWorld("world"), -244.5, 81.0, 33.5), new Location(Main.getMain().getServer().getWorld("world"), -242.5, 81.0, 29.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -214.5, 91.0, 40.5), new Location(Main.getMain().getServer().getWorld("world"), -211.5, 91.0, 40.5), new Location(Main.getMain().getServer().getWorld("world"), -210.5, 91.0, 42.5), new Location(Main.getMain().getServer().getWorld("world"), -210.5, 91.0, 44.5), new Location(Main.getMain().getServer().getWorld("world"), -208.5, 91.0, 44.5), new Location(Main.getMain().getServer().getWorld("world"), -210.5, 91.0, 46.5), new Location(Main.getMain().getServer().getWorld("world"), -208.5, 91.0, 47.5), new Location(Main.getMain().getServer().getWorld("world"), -209.5, 91.0, 49.5), new Location(Main.getMain().getServer().getWorld("world"), -206.5, 91.0, 50.5), new Location(Main.getMain().getServer().getWorld("world"), -207.5, 91.0, 52.5), new Location(Main.getMain().getServer().getWorld("world"), -204.5, 91.0, 51.5), new Location(Main.getMain().getServer().getWorld("world"), -202.5, 90.0, 50.5), new Location(Main.getMain().getServer().getWorld("world"), -200.5, 90.0, 48.5), new Location(Main.getMain().getServer().getWorld("world"), -199.5, 90.0, 44.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -223.5, 92.0, 64.5), new Location(Main.getMain().getServer().getWorld("world"), -224.5, 92.0, 65.5), new Location(Main.getMain().getServer().getWorld("world"), -226.5, 92.0, 67.5), new Location(Main.getMain().getServer().getWorld("world"), -231.5, 100.0, 56.5), new Location(Main.getMain().getServer().getWorld("world"), -234.5, 100.0, 54.5), new Location(Main.getMain().getServer().getWorld("world"), -236.5, 100.0, 52.5), new Location(Main.getMain().getServer().getWorld("world"), -231.5, 100.0, 66.5), new Location(Main.getMain().getServer().getWorld("world"), -225.5, 92.0, 80.5), new Location(Main.getMain().getServer().getWorld("world"), -225.5, 92.0, 101.5), new Location(Main.getMain().getServer().getWorld("world"), -251.5, 92.0, 88.5), new Location(Main.getMain().getServer().getWorld("world"), -240.5, 94.0, 74.5), new Location(Main.getMain().getServer().getWorld("world"), -240.5, 94.0, 71.5), new Location(Main.getMain().getServer().getWorld("world"), -259.5, 94.0, 73.5), new Location(Main.getMain().getServer().getWorld("world"), -252.5, 93.0, 62.5), new Location(Main.getMain().getServer().getWorld("world"), -254.5, 93.0, 61.5), new Location(Main.getMain().getServer().getWorld("world"), -256.5, 93.0, 60.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -278.5, 90.0, 70.5), new Location(Main.getMain().getServer().getWorld("world"), -276.5, 90.0, 70.5), new Location(Main.getMain().getServer().getWorld("world"), -273.5, 90.0, 69.5), new Location(Main.getMain().getServer().getWorld("world"), -272.5, 90.0, 74.5), new Location(Main.getMain().getServer().getWorld("world"), -277.5, 90.0, 78.5), new Location(Main.getMain().getServer().getWorld("world"), -276.5, 90.0, 89.5), new Location(Main.getMain().getServer().getWorld("world"), -277.5, 90.0, 92.5), new Location(Main.getMain().getServer().getWorld("world"), -278.5, 90.0, 95.5), new Location(Main.getMain().getServer().getWorld("world"), -273.5, 90.0, 99.5), new Location(Main.getMain().getServer().getWorld("world"), -269.5, 90.0, 98.5), new Location(Main.getMain().getServer().getWorld("world"), -275.5, 90.0, 105.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -240.5, 92.0, 120.5), new Location(Main.getMain().getServer().getWorld("world"), -238.5, 92.0, 121.5), new Location(Main.getMain().getServer().getWorld("world"), -234.5, 92.0, 122.5), new Location(Main.getMain().getServer().getWorld("world"), -232.5, 92.0, 125.5), new Location(Main.getMain().getServer().getWorld("world"), -230.5, 92.0, 126.5), new Location(Main.getMain().getServer().getWorld("world"), -227.5, 92.0, 125.5), new Location(Main.getMain().getServer().getWorld("world"), -226.5, 92.0, 127.5), new Location(Main.getMain().getServer().getWorld("world"), -223.5, 92.0, 124.5), new Location(Main.getMain().getServer().getWorld("world"), -220.5, 91.0, 127.5), new Location(Main.getMain().getServer().getWorld("world"), -216.5, 91.0, 125.5), new Location(Main.getMain().getServer().getWorld("world"), -211.5, 91.0, 123.5), new Location(Main.getMain().getServer().getWorld("world"), -207.5, 90.0, 113.5), new Location(Main.getMain().getServer().getWorld("world"), -205.5, 89.0, 106.5), new Location(Main.getMain().getServer().getWorld("world"), -203.5, 89.0, 101.5), new Location(Main.getMain().getServer().getWorld("world"), -195.5, 87.0, 97.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -121.5, 72.0, 59.5), new Location(Main.getMain().getServer().getWorld("world"), -116.5, 73.0, 59.5), new Location(Main.getMain().getServer().getWorld("world"), -112.5, 73.0, 60.5), new Location(Main.getMain().getServer().getWorld("world"), -108.5, 74.0, 58.5), new Location(Main.getMain().getServer().getWorld("world"), -105.5, 75.0, 53.5), new Location(Main.getMain().getServer().getWorld("world"), -100.5, 77.0, 54.5), new Location(Main.getMain().getServer().getWorld("world"), -98.5, 78.0, 59.5), new Location(Main.getMain().getServer().getWorld("world"), -95.5, 80.0, 54.5), new Location(Main.getMain().getServer().getWorld("world"), -93.5, 81.0, 60.5), new Location(Main.getMain().getServer().getWorld("world"), -90.5, 86.0, 53.5), new Location(Main.getMain().getServer().getWorld("world"), -87.5, 88.0, 56.5), new Location(Main.getMain().getServer().getWorld("world"), -82.5, 90.0, 52.5), new Location(Main.getMain().getServer().getWorld("world"), -77.5, 91.0, 48.5), new Location(Main.getMain().getServer().getWorld("world"), -73.5, 93.0, 44.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -119.5, 72.0, -4.5), new Location(Main.getMain().getServer().getWorld("world"), -122.5, 72.0, -1.5), new Location(Main.getMain().getServer().getWorld("world"), -127.5, 72.0, 0.5), new Location(Main.getMain().getServer().getWorld("world"), -131.5, 72.0, -1.5), new Location(Main.getMain().getServer().getWorld("world"), -136.5, 72.0, -1.5), new Location(Main.getMain().getServer().getWorld("world"), -147.5, 72.0, -1.5), new Location(Main.getMain().getServer().getWorld("world"), -151.5, 73.0, -5.5), new Location(Main.getMain().getServer().getWorld("world"), -153.5, 73.0, -15.5), new Location(Main.getMain().getServer().getWorld("world"), -153.5, 73.0, -22.5), new Location(Main.getMain().getServer().getWorld("world"), -148.5, 73.0, -25.5), new Location(Main.getMain().getServer().getWorld("world"), -143.5, 73.0, -25.5), new Location(Main.getMain().getServer().getWorld("world"), -134.5, 73.0, -27.5), new Location(Main.getMain().getServer().getWorld("world"), -124.5, 74.0, -28.5), new Location(Main.getMain().getServer().getWorld("world"), -120.5, 74.0, -30.5), new Location(Main.getMain().getServer().getWorld("world"), -117.5, 74.0, -32.5), new Location(Main.getMain().getServer().getWorld("world"), -133.5, 73.0, -36.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -138.5, 74.0, -62.5), new Location(Main.getMain().getServer().getWorld("world"), -141.5, 75.0, -61.5), new Location(Main.getMain().getServer().getWorld("world"), -144.5, 76.0, -61.5), new Location(Main.getMain().getServer().getWorld("world"), -146.5, 76.0, -64.5), new Location(Main.getMain().getServer().getWorld("world"), -146.5, 76.0, -67.5), new Location(Main.getMain().getServer().getWorld("world"), -148.5, 75.0, -71.5), new Location(Main.getMain().getServer().getWorld("world"), -154.5, 75.0, -67.5), new Location(Main.getMain().getServer().getWorld("world"), -157.5, 75.0, -68.5), new Location(Main.getMain().getServer().getWorld("world"), -160.5, 75.0, -70.5), new Location(Main.getMain().getServer().getWorld("world"), -166.5, 75.0, -71.5), new Location(Main.getMain().getServer().getWorld("world"), -172.5, 75.0, -72.5), new Location(Main.getMain().getServer().getWorld("world"), -177.5, 75.0, -73.5), new Location(Main.getMain().getServer().getWorld("world"), -185.5, 75.0, -74.5), new Location(Main.getMain().getServer().getWorld("world"), -204.5, 74.0, -91.5), new Location(Main.getMain().getServer().getWorld("world"), -204.5, 74.0, -96.5), new Location(Main.getMain().getServer().getWorld("world"), -202.5, 74.0, -99.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -139.5, 72.0, -122.5), new Location(Main.getMain().getServer().getWorld("world"), -136.5, 72.0, -126.5), new Location(Main.getMain().getServer().getWorld("world"), -131.5, 72.0, -134.5), new Location(Main.getMain().getServer().getWorld("world"), -124.5, 72.0, -136.5), new Location(Main.getMain().getServer().getWorld("world"), -119.5, 72.0, -132.5), new Location(Main.getMain().getServer().getWorld("world"), -109.5, 72.0, -133.5), new Location(Main.getMain().getServer().getWorld("world"), -102.5, 72.0, -133.5), new Location(Main.getMain().getServer().getWorld("world"), -99.5, 72.0, -128.5), new Location(Main.getMain().getServer().getWorld("world"), -98.5, 72.0, -122.5), new Location(Main.getMain().getServer().getWorld("world"), -100.5, 72.0, -112.5), new Location(Main.getMain().getServer().getWorld("world"), -101.5, 72.0, -103.5), new Location(Main.getMain().getServer().getWorld("world"), -98.5, 72.0, -94.5), new Location(Main.getMain().getServer().getWorld("world"), -82.5, 73.0, -111.5), new Location(Main.getMain().getServer().getWorld("world"), -81.5, 73.0, -118.5), new Location(Main.getMain().getServer().getWorld("world"), -80.5, 73.0, -128.5), new Location(Main.getMain().getServer().getWorld("world"), -121.5, 71.0, -84.5), new Location(Main.getMain().getServer().getWorld("world"), -125.5, 71.0, -82.5), new Location(Main.getMain().getServer().getWorld("world"), -133.5, 71.0, -85.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -50.5, 81.0, -209.5), new Location(Main.getMain().getServer().getWorld("world"), -48.5, 81.0, -205.5), new Location(Main.getMain().getServer().getWorld("world"), -44.5, 81.0, -202.5), new Location(Main.getMain().getServer().getWorld("world"), -40.5, 81.0, -198.5), new Location(Main.getMain().getServer().getWorld("world"), -33.5, 81.0, -197.5), new Location(Main.getMain().getServer().getWorld("world"), -28.5, 81.0, -197.5), new Location(Main.getMain().getServer().getWorld("world"), -23.5, 81.0, -201.5), new Location(Main.getMain().getServer().getWorld("world"), -10.5, 78.0, -217.5), new Location(Main.getMain().getServer().getWorld("world"), -11.5, 77.0, -220.5), new Location(Main.getMain().getServer().getWorld("world"), -20.5, 75.0, -222.5), new Location(Main.getMain().getServer().getWorld("world"), -27.5, 74.0, -222.5), new Location(Main.getMain().getServer().getWorld("world"), -65.5, 79.0, -194.5), new Location(Main.getMain().getServer().getWorld("world"), -58.5, 79.0, -172.5), new Location(Main.getMain().getServer().getWorld("world"), -55.5, 80.0, -166.5), new Location(Main.getMain().getServer().getWorld("world"), -45.5, 78.0, -155.5), new Location(Main.getMain().getServer().getWorld("world"), -50.5, 80.0, -148.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), 38.5, 72.0, -180.5), new Location(Main.getMain().getServer().getWorld("world"), 40.5, 72.0, -178.5), new Location(Main.getMain().getServer().getWorld("world"), 42.5, 72.0, -175.5), new Location(Main.getMain().getServer().getWorld("world"), 45.5, 72.0, -174.5), new Location(Main.getMain().getServer().getWorld("world"), 50.5, 72.0, -175.5), new Location(Main.getMain().getServer().getWorld("world"), 50.5, 72.0, -181.5), new Location(Main.getMain().getServer().getWorld("world"), 50.5, 72.0, -188.5), new Location(Main.getMain().getServer().getWorld("world"), 55.5, 72.0, -193.5), new Location(Main.getMain().getServer().getWorld("world"), 68.5, 72.0, -187.5), new Location(Main.getMain().getServer().getWorld("world"), 71.5, 72.0, -178.5), new Location(Main.getMain().getServer().getWorld("world"), 65.5, 72.0, -168.5), new Location(Main.getMain().getServer().getWorld("world"), 60.5, 72.0, -158.5), new Location(Main.getMain().getServer().getWorld("world"), 58.5, 72.0, -150.5), new Location(Main.getMain().getServer().getWorld("world"), 50.5, 72.0, -149.5), new Location(Main.getMain().getServer().getWorld("world"), 44.5, 71.0, -145.5), new Location(Main.getMain().getServer().getWorld("world"), 41.5, 71.0, -139.5), new Location(Main.getMain().getServer().getWorld("world"), 48.5, 71.0, -133.5), new Location(Main.getMain().getServer().getWorld("world"), 53.5, 71.0, -126.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), 91.5, 74.0, -25.5), new Location(Main.getMain().getServer().getWorld("world"), 93.5, 74.0, -24.5), new Location(Main.getMain().getServer().getWorld("world"), 96.5, 74.0, -22.5), new Location(Main.getMain().getServer().getWorld("world"), 100.5, 73.0, -17.5), new Location(Main.getMain().getServer().getWorld("world"), 105.5, 72.0, -13.5), new Location(Main.getMain().getServer().getWorld("world"), 109.5, 72.0, -10.5), new Location(Main.getMain().getServer().getWorld("world"), 117.5, 72.0, -7.5), new Location(Main.getMain().getServer().getWorld("world"), 126.5, 72.0, -7.5), new Location(Main.getMain().getServer().getWorld("world"), 136.5, 72.0, -8.5), new Location(Main.getMain().getServer().getWorld("world"), 145.5, 72.0, -6.5), new Location(Main.getMain().getServer().getWorld("world"), 160.5, 70.0, -8.5), new Location(Main.getMain().getServer().getWorld("world"), 161.5, 70.0, -11.5), new Location(Main.getMain().getServer().getWorld("world"), 165.5, 70.0, -12.5), new Location(Main.getMain().getServer().getWorld("world"), 166.5, 70.0, -16.5), new Location(Main.getMain().getServer().getWorld("world"), 177.5, 71.0, -32.5), new Location(Main.getMain().getServer().getWorld("world"), 180.5, 71.0, -46.5), new Location(Main.getMain().getServer().getWorld("world"), 179.5, 71.0, -58.5), new Location(Main.getMain().getServer().getWorld("world"), 181.5, 71.0, -63.5), new Location(Main.getMain().getServer().getWorld("world"), 183.5, 71.0, -62.5), new Location(Main.getMain().getServer().getWorld("world"), 182.5, 71.0, -66.5), new Location(Main.getMain().getServer().getWorld("world"), 155.5, 76.0, -80.5), new Location(Main.getMain().getServer().getWorld("world"), 154.5, 76.0, -78.5), new Location(Main.getMain().getServer().getWorld("world"), 157.5, 76.0, -81.5), new Location(Main.getMain().getServer().getWorld("world"), 155.5, 76.0, -83.5), new Location(Main.getMain().getServer().getWorld("world"), 127.5, 75.0, -90.5), new Location(Main.getMain().getServer().getWorld("world"), 126.5, 75.0, -88.5), new Location(Main.getMain().getServer().getWorld("world"), 108.5, 74.0, -85.5), new Location(Main.getMain().getServer().getWorld("world"), 106.5, 74.0, -82.5), new Location(Main.getMain().getServer().getWorld("world"), 105.5, 74.0, -79.5), new Location(Main.getMain().getServer().getWorld("world"), 101.5, 74.0, -79.5), new Location(Main.getMain().getServer().getWorld("world"), 100.5, 73.0, -84.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), 89.5, 73.0, 14.5), new Location(Main.getMain().getServer().getWorld("world"), 85.5, 73.0, 10.5), new Location(Main.getMain().getServer().getWorld("world"), 81.5, 73.0, 9.5), new Location(Main.getMain().getServer().getWorld("world"), 75.5, 73.0, 9.5), new Location(Main.getMain().getServer().getWorld("world"), 71.5, 74.0, 16.5), new Location(Main.getMain().getServer().getWorld("world"), 77.5, 76.0, 25.5), new Location(Main.getMain().getServer().getWorld("world"), 76.5, 77.0, 27.5), new Location(Main.getMain().getServer().getWorld("world"), 73.5, 76.0, 35.5), new Location(Main.getMain().getServer().getWorld("world"), 76.5, 75.0, 40.5), new Location(Main.getMain().getServer().getWorld("world"), 75.5, 78.0, 43.5), new Location(Main.getMain().getServer().getWorld("world"), 73.5, 80.0, 47.5), new Location(Main.getMain().getServer().getWorld("world"), 80.5, 76.0, 49.5), new Location(Main.getMain().getServer().getWorld("world"), 89.5, 74.0, 49.5), new Location(Main.getMain().getServer().getWorld("world"), 96.5, 72.0, 51.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), 79.5, 77.0, 72.5), new Location(Main.getMain().getServer().getWorld("world"), 79.5, 77.0, 76.5), new Location(Main.getMain().getServer().getWorld("world"), 76.5, 78.0, 78.5), new Location(Main.getMain().getServer().getWorld("world"), 74.5, 78.0, 81.5), new Location(Main.getMain().getServer().getWorld("world"), 72.5, 79.0, 82.5), new Location(Main.getMain().getServer().getWorld("world"), 68.5, 81.0, 80.5), new Location(Main.getMain().getServer().getWorld("world"), 66.5, 82.0, 79.5), new Location(Main.getMain().getServer().getWorld("world"), 63.5, 83.0, 77.5), new Location(Main.getMain().getServer().getWorld("world"), 59.5, 84.0, 74.5), new Location(Main.getMain().getServer().getWorld("world"), 57.5, 85.0, 72.5), new Location(Main.getMain().getServer().getWorld("world"), 56.5, 78.0, 90.5), new Location(Main.getMain().getServer().getWorld("world"), 52.5, 77.0, 93.5), new Location(Main.getMain().getServer().getWorld("world"), 36.5, 71.0, 91.5), new Location(Main.getMain().getServer().getWorld("world"), 43.5, 71.0, 115.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), 82.5, 74.0, 180.5), new Location(Main.getMain().getServer().getWorld("world"), 84.5, 75.0, 182.5), new Location(Main.getMain().getServer().getWorld("world"), 83.5, 74.0, 185.5), new Location(Main.getMain().getServer().getWorld("world"), 84.5, 75.0, 186.5), new Location(Main.getMain().getServer().getWorld("world"), 90.5, 75.0, 190.5), new Location(Main.getMain().getServer().getWorld("world"), 98.5, 73.0, 190.5), new Location(Main.getMain().getServer().getWorld("world"), 98.5, 75.0, 185.5), new Location(Main.getMain().getServer().getWorld("world"), 101.5, 73.0, 186.5), new Location(Main.getMain().getServer().getWorld("world"), 99.5, 75.0, 182.5), new Location(Main.getMain().getServer().getWorld("world"), 102.5, 73.0, 182.5), new Location(Main.getMain().getServer().getWorld("world"), 98.5, 75.0, 178.5), new Location(Main.getMain().getServer().getWorld("world"), 102.5, 73.0, 175.5), new Location(Main.getMain().getServer().getWorld("world"), 104.5, 72.0, 170.5)},
            new Location[]{new Location(Main.getMain().getServer().getWorld("world"), 27.5, 70.0, 167.5), new Location(Main.getMain().getServer().getWorld("world"), 27.5, 70.0, 162.5), new Location(Main.getMain().getServer().getWorld("world"), 26.5, 71.0, 153.5), new Location(Main.getMain().getServer().getWorld("world"), 29.5, 71.0, 151.5), new Location(Main.getMain().getServer().getWorld("world"), 25.5, 73.0, 147.5), new Location(Main.getMain().getServer().getWorld("world"), 28.5, 73.0, 143.5), new Location(Main.getMain().getServer().getWorld("world"), 24.5, 74.0, 139.5), new Location(Main.getMain().getServer().getWorld("world"), 24.5, 75.0, 135.5), new Location(Main.getMain().getServer().getWorld("world"), 15.5, 74.0, 141.5), new Location(Main.getMain().getServer().getWorld("world"), 10.5, 74.0, 139.5), new Location(Main.getMain().getServer().getWorld("world"), 3.5, 73.0, 142.5), new Location(Main.getMain().getServer().getWorld("world"), -0.5, 73.0, 139.5), new Location(Main.getMain().getServer().getWorld("world"), -4.5, 73.0, 139.5), new Location(Main.getMain().getServer().getWorld("world"), -8.5, 73.0, 135.5), new Location(Main.getMain().getServer().getWorld("world"), -9.5, 74.0, 129.5), new Location(Main.getMain().getServer().getWorld("world"), -5.5, 74.0, 128.5), new Location(Main.getMain().getServer().getWorld("world"), -4.5, 74.0, 124.5), new Location(Main.getMain().getServer().getWorld("world"), -7.5, 74.0, 120.5), new Location(Main.getMain().getServer().getWorld("world"), -14.5, 75.0, 120.5), new Location(Main.getMain().getServer().getWorld("world"), -18.5, 76.0, 119.5), new Location(Main.getMain().getServer().getWorld("world"), -10.5, 76.0, 114.5), new Location(Main.getMain().getServer().getWorld("world"), -1.5, 90.0, 106.5), new Location(Main.getMain().getServer().getWorld("world"), -3.5, 89.0, 103.5), new Location(Main.getMain().getServer().getWorld("world"), -0.5, 89.0, 101.5), new Location(Main.getMain().getServer().getWorld("world"), 1.5, 89.0, 97.5), new Location(Main.getMain().getServer().getWorld("world"), -0.5, 89.0, 95.5), new Location(Main.getMain().getServer().getWorld("world"), -5.5, 89.0, 97.5), new Location(Main.getMain().getServer().getWorld("world"), -37.5, 75.0, 138.5), new Location(Main.getMain().getServer().getWorld("world"), -40.5, 74.0, 141.5), new Location(Main.getMain().getServer().getWorld("world"), -42.5, 73.0, 145.5), new Location(Main.getMain().getServer().getWorld("world"), -44.5, 72.0, 150.5), new Location(Main.getMain().getServer().getWorld("world"), -46.5, 72.0, 154.5), new Location(Main.getMain().getServer().getWorld("world"), -44.5, 72.0, 158.5), new Location(Main.getMain().getServer().getWorld("world"), -45.5, 72.0, 161.5), new Location(Main.getMain().getServer().getWorld("world"), -40.5, 72.0, 164.5), new Location(Main.getMain().getServer().getWorld("world"), -59.5, 71.0, 151.5), new Location(Main.getMain().getServer().getWorld("world"), -59.5, 72.0, 140.5)}

    );
    public static HashMap<SkyblockPlayer, MythologicalPerk> mythologicalPerkHashMap = new HashMap<>();
    private final List<Block> settet = new LinkedList<>();
    private final List<BurrowChain> chains = new ArrayList<>();
    private BurrowChain tracked;
    @Getter
    private final SkyblockPlayer player;
    private final HashMap<SkyblockEntity, BurrowChain> entitys = new HashMap<>();
    @Getter
    private final DianaRunner runnable;

    public MythologicalPerk(SkyblockPlayer player) {
        this.player = player;
        mythologicalPerkHashMap.put(player, this);
        for (int i = 0; i < 10; i++) {
            try {
                makeNewChain();
            } catch (Exception e) {
                player.sendMessage("§cAn Error occured why making a burrow!");
                e.printStackTrace(System.err);
            }
        }
        runnable = new DianaRunner();
    }

    public BurrowChain getTracked() {
        return (tracked == null) ? chains.get(0) : tracked;
    }

    public void makeNewChain() {
        BurrowChain chain = new BurrowChain();
        chains.add(chain);
        settet.add(chain.block);
    }

    public static MythologicalPerk getPlayer(SkyblockPlayer player) {
        return mythologicalPerkHashMap.get(player);
    }

    public static MythologicalPerk getPlayer(Player player) {
        return getPlayer(SkyblockPlayer.getSkyblockPlayer(player));
    }

    public boolean isBlock(Block block) {
        return settet.contains(block);
    }

    public void dig(Block block) {
        for (BurrowChain chain : new ArrayList<>(chains)) {
            if (!chain.block.equals(block))
                continue;
            settet.remove(block);
            if (chain.dig(player)) {
                chains.remove(chain);
                tracked = null;
                try {
                    makeNewChain();
                } catch (Exception e) {
                    player.sendMessage("§cAn Error occured why making a burrow!");
                    e.printStackTrace(System.err);
                }
            } else {
                settet.add(chain.block);
                tracked = chain;
            }
        }
    }

    public Block randomBlock() {
        for (int i = 0; i < 10; i++) {
            Block block = Tools.getRandom(Tools.getRandom(blocks)).getBlock().getRelative(BlockFace.DOWN);
            if (settet.contains(block)) continue;
            return block;
        }
        throw new IllegalArgumentException("Should not happen!");
    }
    public void kill(SkyblockEntity entity) {
        BurrowChain chain = entitys.get(entity);
        if (chain == null) return;
        chain.alive--;
        entitys.remove(entity);
    }

    @Getter
    public class BurrowChain {
        private Block block;
        private BurrowType type;
        int i = 0;
        int alive = -1;
        public BurrowChain() {
            set(BurrowType.Start);
        }

        public void set(BurrowType type) {
            block = randomBlock();
            this.type = type;
        }

        /**
         * Digs the burrow
         * @return if the burrow is finished
         */
        public boolean dig(SkyblockPlayer player) {
            ItemRarity rarity = (player.getPetEquip() != null && player.getPetEquip().getPet().itemID.startsWith("GRIFFIN;")) ? player.getPetEquip().getPet().getRarity() : ItemRarity.COMMON;
            switch (type) {
                case Mob -> {
                    if (alive == -1) {
                        Mobs mob = ((Mobs.MobLoot) Mobs.getLootTable(rarity).use(false, player).get(0)).mob;
                        alive = (mob == Mobs.SiamesLynx) ? 2 : 1;
                        SkyblockEntity entity = mob.factor(new Triple<>(rarity, Tools.getAsLocation(block).add(0, 1, 0), MythologicalPerk.this));
                        entitys.put(entity, this);
                        player.sendMessage("§c§lOi! §aYou dug out a §2" + ((entity == null) ? "Siames Lynx" : entity.getName()));
                        return false;
                    }
                    if (alive > 0) {
                        player.sendMessage("§cKill all creatures that guard this place!");
                        return false;
                    }
                    player.sendMessage("§eYou dug a burrow §7(" + (i + 1) + "/4)");
                    alive = -1;

                }
                case Start -> player.sendMessage("§eStarted a burrow chain §7(1/4)");
                case Treasure -> {
                    LootTable lootTable = switch (rarity) {
                        case UNCOMMON -> uncommonTreasure;
                        case RARE -> rareTreasure;
                        case EPIC -> epicTreasure;
                        case LEGENDARY -> legendaryTreasure;
                        default -> commonTreasure;
                    };
                    Loot loot = lootTable.use(false, player).get(0);
                    loot.consume(player, Tools.getAsLocation(block).add(0, 1, 0), false);
                    player.sendMessage("§eYou dug out " + loot.name() + " §7(" + (i + 1) + "/4)");
                }
            }
            if (i == 3) return true;
            next();
            return false;
        }
        public void next() {
            player.playSound(block.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1.7f);
            player.spawnParticle(Particle.EXPLOSION_HUGE, Tools.getAsLocation(block).add(0, 0.5, 0), 1);
            i++;
            BurrowType t = (i == 3) ? BurrowType.Treasure : ((new Random().nextDouble() <= 0.1) ? BurrowType.Treasure : BurrowType.Mob);
            set(t);
        }
    }
    public static final LootTable commonTreasure = new LootTable(true, true);
    public static final LootTable uncommonTreasure = new LootTable(true, true);
    public static final LootTable rareTreasure = new LootTable(true, true);
    public static final LootTable epicTreasure = new LootTable(true, true);
    public static final LootTable legendaryTreasure = new LootTable(true, true);
    static {
        commonTreasure.addLoot(new ItemLoot(DianaItems.GRIFFIN_FEATHER.getItem()), 0.666);
        commonTreasure.addLoot(new CoinLoot(1_000), 0.1515);
        commonTreasure.addLoot(new CoinLoot(1_500), 0.0758);
        commonTreasure.addLoot(new CoinLoot(3_000), 0.0758);
        commonTreasure.addLoot(new CoinLoot(5_000), 0.0303);


        uncommonTreasure.addLoot(new ItemLoot(DianaItems.GRIFFIN_FEATHER.getItem()), 0.666);
        uncommonTreasure.addLoot(new CoinLoot(3_000), 0.1235);
        uncommonTreasure.addLoot(new CoinLoot(5_000), 0.1235);
        uncommonTreasure.addLoot(new CoinLoot(7_500), 0.0617);
        uncommonTreasure.addLoot(new CoinLoot(10_000), 0.0247);

        rareTreasure.addLoot(new ItemLoot(DianaItems.GRIFFIN_FEATHER.getItem()), 0.666);
        rareTreasure.addLoot(new CoinLoot(4_000), 0.1797);
        rareTreasure.addLoot(new CoinLoot(8_000), 0.0898);
        rareTreasure.addLoot(new CoinLoot(12_500), 0.0449);
        rareTreasure.addLoot(new CoinLoot(20_000), 0.018);
        rareTreasure.addLoot(new CoinLoot(100_000), 0.009);

        epicTreasure.addLoot(new ItemLoot(DianaItems.GRIFFIN_FEATHER.getItem()), 0.666);
        epicTreasure.addLoot(new ItemLoot(DianaItems.CROWN_OF_GREED.getItem()), 0.0012);
        epicTreasure.addLoot(new CoinLoot(5_000), 0.1182);
        epicTreasure.addLoot(new CoinLoot(8_000), 0.1182);
        epicTreasure.addLoot(new CoinLoot(12_000), 0.0591);
        epicTreasure.addLoot(new CoinLoot(20_000), 0.0236);
        epicTreasure.addLoot(new CoinLoot(30_000), 0.0059);
        epicTreasure.addLoot(new CoinLoot(50_000), 0.0035);
        epicTreasure.addLoot(new CoinLoot(100_000), 0.0012);
        epicTreasure.addLoot(new CoinLoot(150_000), 0.0012);
        epicTreasure.addLoot(new CoinLoot(250_000), 0.0012);

        legendaryTreasure.addLoot(new ItemLoot(DianaItems.GRIFFIN_FEATHER.getItem()), 0.666);
        legendaryTreasure.addLoot(new ItemLoot(DianaItems.CROWN_OF_GREED.getItem()), 0.0039);
        legendaryTreasure.addLoot(new CoinLoot(10_000), 0.0392);
        legendaryTreasure.addLoot(new CoinLoot(15_000), 0.0588);
        legendaryTreasure.addLoot(new CoinLoot(25_000), 0.0784);
        legendaryTreasure.addLoot(new CoinLoot(40_000), 0.0588);
        legendaryTreasure.addLoot(new CoinLoot(50_000), 0.0392);
        legendaryTreasure.addLoot(new CoinLoot(75_000), 0.0196);
        legendaryTreasure.addLoot(new CoinLoot(100_000), 0.0118);
        legendaryTreasure.addLoot(new CoinLoot(250_000), 0.0078);
        legendaryTreasure.addLoot(new CoinLoot(500_000), 0.0039);
        legendaryTreasure.addLoot(new CoinLoot(750_000), 0.0039);
    }

    public enum BurrowType {
        Start,
        Treasure,
        Mob
    }
    public enum Mobs implements Factory<Triple<ItemRarity, Location, MythologicalPerk>, SkyblockEntity> {
        MinosHunter {
            @Override
            public SkyblockEntity factor(Triple<ItemRarity, Location, MythologicalPerk> b) {
                SkyblockEntity entity = new MinosHunter(b.getFirst(), b.getThird());
                entity.spawn(b.getSecond());
                return entity;
            }
        },
        SiamesLynx {
            @Override
            public SkyblockEntity factor(Triple<ItemRarity, Location, MythologicalPerk> b) {
                SkyblockEntity bagheera = new SiameseLynx(b.getFirst(), b.getThird(), "Bagheera");
                bagheera.spawn(b.getSecond());
                b.getThird().entitys.put(bagheera, b.getThird().tracked);
                SkyblockEntity azrael = new SiameseLynx(b.getFirst(), b.getThird(), "Azrael");
                azrael.spawn(b.getSecond());
                b.getThird().entitys.put(azrael, b.getThird().tracked);
                return null;
            }
        },
        Minotaur {
            @Override
            public SkyblockEntity factor(Triple<ItemRarity, Location, MythologicalPerk> b) {
                SkyblockEntity entity = new Minotaur(b.getFirst(), b.getThird());
                entity.spawn(b.getSecond());
                return entity;
            }
        },
        GaiaConstruct {
            @Override
            public SkyblockEntity factor(Triple<ItemRarity, Location, MythologicalPerk> b) {
                SkyblockEntity entity = new GaiaConstruct(b.getFirst(), b.getThird());
                entity.spawn(b.getSecond());
                return entity;
            }
        },
        MinosChampion {
            @Override
            public SkyblockEntity factor(Triple<ItemRarity, Location, MythologicalPerk> b) {
                SkyblockEntity entity = new MinosChampion(b.getFirst(), b.getThird());
                entity.spawn(b.getSecond());
                return entity;
            }
        },
        MinosInquisitor {
            @Override
            public SkyblockEntity factor(Triple<ItemRarity, Location, MythologicalPerk> b) {
                SkyblockEntity entity = new MinosInquisitor(b.getThird());
                entity.spawn(b.getSecond());
                return entity;
            }
        };
        private static final LootTable common = new LootTable(true, true);
        private static final LootTable uncommon = new LootTable(true, true);
        private static final LootTable rare = new LootTable(true, true);
        private static final LootTable epic = new LootTable(true, true);
        private static final LootTable legendary = new LootTable(true, true);
        static {
            MobLoot minosHunter = new MobLoot(MinosHunter);
            MobLoot siamesLynx = new MobLoot(SiamesLynx);
            MobLoot minotaur = new MobLoot(Minotaur);
            MobLoot gaiaConstruct = new MobLoot(GaiaConstruct);
            MobLoot minosChampion = new MobLoot(MinosChampion);
            MobLoot minosInquisitor = new MobLoot(MinosInquisitor);

            common.addLoot(minosHunter, 0.5714, false);
            common.addLoot(siamesLynx, 0.4286, false);

            uncommon.addLoot(minosHunter, 0.2667, false);
            uncommon.addLoot(siamesLynx, 0.4, false);
            uncommon.addLoot(minotaur, 0.3333, false);

            rare.addLoot(minosHunter, 0.1667, false);
            rare.addLoot(siamesLynx, 0.2778, false);
            rare.addLoot(minotaur, 0.3333, false);
            rare.addLoot(gaiaConstruct, 0.2222, false);

            epic.addLoot(minosHunter, 0.0962, false);
            epic.addLoot(siamesLynx, 0.1905, false);
            epic.addLoot(minotaur, 0.2381,false);
            epic.addLoot(gaiaConstruct, 0.2857, false);
            epic.addLoot(minosChampion, 0.1905, false);

            legendary.addLoot(minosHunter, 0.1235, false);
            legendary.addLoot(siamesLynx, 0.1852, false);
            legendary.addLoot(minotaur, 0.2469, false);
            legendary.addLoot(gaiaConstruct, 0.2469, false);
            legendary.addLoot(minosChampion, 0.1852, false);
            legendary.addLoot(minosInquisitor, 0.0123, false);
        }
        private static final class MobLoot implements Loot{
            private final Mobs mob;
            public MobLoot(Mobs mobs) {
                mob = mobs;
            }

            @Override
            public void consume(SkyblockPlayer killer, Location dropLocation, boolean toPlayer) {}

            @Override
            public String name() {
                return mob.name();
            }
        }
        public static LootTable getLootTable(ItemRarity rarity) {
            return switch (rarity) {
                case UNCOMMON -> uncommon;
                case RARE -> rare;
                case EPIC -> epic;
                case LEGENDARY -> legendary;
                default -> common;
            };
        }
    }
    public class DianaRunner extends BukkitRunnable {
        public DianaRunner() {
            runTaskTimer(Main.getMain(), 0, 2);
        }
        @Override
        public void run() {
            if (ItemHandler.getItemManager(player.getEquipment().getItemInMainHand()) != DianaItems.AncestralSpade.getItem()) return;
            for (BurrowChain chain : chains) {
                if (chain.getBlock().getLocation().distance(player.getLocation()) > 25) {
                    continue;
                }
                particle(Particle.REDSTONE, chain.getBlock(), new Particle.DustOptions(Color.GRAY, 1.5f));
                particle(Particle.REDSTONE, chain.getBlock(), new Particle.DustOptions(Color.GRAY, 1.5f));
                Particle particle = switch (chain.type) {
                    case Start -> Particle.CRIT;
                    case Mob -> Particle.ASH;
                    case Treasure -> Particle.DRIP_LAVA;
                };
                particle(particle, chain.getBlock());
            }
        }
        public void particle(Particle particle, Block base) {
            player.spawnParticle(particle, random(base), 1, 0, 0, 0 ,0);
        }
        public <T> void  particle(Particle particle, Block base, T t) {
            player.spawnParticle(particle, random(base),1, 0, 0, 0 ,0, t);
        }
        private Location random(Block base) {
            return base.getLocation().add(new Random().nextDouble(), 1.1, new Random().nextDouble());
        }
    }
}
