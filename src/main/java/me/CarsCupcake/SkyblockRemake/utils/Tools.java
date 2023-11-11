package me.CarsCupcake.SkyblockRemake.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector2;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;
import kotlin.Triple;
import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.log.CustomLogger;
import me.CarsCupcake.SkyblockRemake.utils.maps.MapList;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockChange;
import net.minecraft.network.protocol.game.PacketPlayOutMapChunk;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.chunk.ChunkSection;
import net.minecraft.world.phys.AxisAlignedBB;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Stream;


public class Tools {
    public static double round(double num, int digit) {
        double d = Math.pow(10, digit);

        return Math.round(num * d) / d;
    }

    public static Double StringToDouble(String str) {
        int mult = 1;
        if (str.endsWith("k")) {
            mult = 1000;
            str = str.replace("k", "");
        }
        if (str.endsWith("m")) {
            mult = 1000000;
            str = str.replace("m", "");
        }
        if (str.endsWith("b")) {
            mult = 1000000000;
            str = str.replace("b", "");
        }


        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            return -1d;
        }
        return Double.parseDouble(str) * mult;


    }
    public static String cleanDouble(double d) {
        return (d % 1 == 0) ? String.format("%.0f", d) : String.valueOf(d);
    }
    public static String cleanDouble(double d, int digids) {
        return (d % 1 == 0) ? String.format("%.0f", d) : String.format("%." + digids + "f", d);
    }

    public static ArrayList<ItemStack> applyPristine(ItemManager rough, ItemManager flawed, int baseDropChance, SkyblockPlayer player) {
        double chance = Main.getPlayerStat(player, Stats.Pristine) / 100d;
        double miningFortune = Main.getPlayerStat(player, Stats.MiningFortune) / 100;
        int norm = 0;
        int pri = 0;
        Random r = new Random();
        for (int i = 0; i < baseDropChance; i++) {
            if (r.nextDouble() <= chance) {
                pri += ((int) miningFortune) + ((miningFortune - ((int) miningFortune) != 0 && miningFortune - ((int) miningFortune) >= r.nextDouble()) ? 1 : 0);
            } else
                norm += ((int) miningFortune) + ((miningFortune - ((int) miningFortune) != 0 && miningFortune - ((int) miningFortune) <= r.nextDouble()) ? 1 : 0);
        }

        ArrayList<ItemStack> ret = new ArrayList<>();
        ItemStack i = rough.createNewItemStack();
        i.setAmount(norm);
        ret.add(i);
        i = flawed.createNewItemStack();
        i.setAmount(pri);
        ret.add(i);

        return ret;
    }


    public static boolean isInRect(Player player, Location loc1, Location loc2) {
        double[] dim = new double[2];

        dim[0] = loc1.getX();
        dim[1] = loc2.getX();
        Arrays.sort(dim);
        if (player.getLocation().getX() > dim[1] || player.getLocation().getX() < dim[0]) return false;

        dim[0] = loc1.getZ();
        dim[1] = loc2.getZ();
        Arrays.sort(dim);
        if (player.getLocation().getZ() > dim[1] || player.getLocation().getZ() < dim[0]) return false;

        dim[0] = loc1.getY();
        dim[1] = loc2.getY();
        Arrays.sort(dim);
        return !(player.getLocation().getY() > dim[1]) && !(player.getLocation().getY() < dim[0]);
    }

    public static void loadShematic(File file, Location base) {
        Clipboard clipboard = null;
        ClipboardFormat format = ClipboardFormats.findByFile(file);

        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            clipboard = reader.read();
        } catch (IOException e) {
            Bukkit.broadcastMessage("§c A schematic failed to load");
            e.printStackTrace();
            return;
        }

        try (EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(base.getWorld()))) {

            Operation operation = new ClipboardHolder(clipboard).createPaste(editSession).to(BlockVector3.at(base.getX(), base.getY(), base.getZ())).build();

            Operations.complete(operation);

        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.broadcastMessage("§c A schematic failed to load");
            return;
        }
    }

    public static void loadShematic(InputStream stream, Location base) throws IOException {
        loadShematic(stream, base, "schem");

    }

    public static void loadShematic(InputStream stream, Location base, String format) throws IOException {
        loadShematic(stream, base, ClipboardFormats.findByAlias(format));
    }

    public static void loadShematic(InputStream stream, Location base, ClipboardFormat format) throws IOException {
        Clipboard clipboard = null;

        try (ClipboardReader reader = format.getReader(stream)) {
            clipboard = reader.read();
            EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(base.getWorld()));
            Operation operation = new ClipboardHolder(clipboard).createPaste(editSession).to(BlockVector3.at(base.getX(), base.getY(), base.getZ())).build();

            Operations.complete(operation);
            editSession.close();
        } catch (Exception e) {
            Bukkit.broadcastMessage("§c A schematic failed to load");
            e.printStackTrace();
            return;
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean hasFileSystem(String scheme) {
        for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
            if (scheme.equalsIgnoreCase(provider.getScheme())) {
                return true;
            }
        }
        return false;
    }

    public static void copyDirectoryCompatibityMode(File source, File destination) throws IOException {
        if (source.isDirectory()) {
            copyDirectory(source, destination);
        } else {
            copyFile(source, destination);
        }
    }

    private static void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {
        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdir();
        }
        for (String f : sourceDirectory.list()) {
            copyDirectoryCompatibityMode(new File(sourceDirectory, f), new File(destinationDirectory, f));
        }
    }

    private static File copyFile(File sourceFile, File destinationFile) throws IOException {
        try (InputStream in = new FileInputStream(sourceFile); OutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
            return new File(destinationFile, sourceFile.getName());
        }
    }

    public static void loadShematic(String resourcePath, Location base) {
        InputStream stream = Main.getMain().getResource(resourcePath);
        try {
            ClipboardFormat format = ClipboardFormats.findByAlias("schem");
            ClipboardReader reader = format.getReader(stream);
            Clipboard clipboard = reader.read();
            EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(base.getWorld()));
            Operation operation = new ClipboardHolder(clipboard).createPaste(editSession).to(BlockVector3.at(base.getX(), base.getY(), base.getZ())).build();
            Operations.complete(operation);
            editSession.close();
        } catch (Exception e) {
            Bukkit.broadcastMessage("§c A schematic failed to load");
            e.printStackTrace();
            return;
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadShematic(String resourcePath, Location base, String formateString) {
        InputStream stream = Main.getMain().getResource(resourcePath);
        try {
            ClipboardFormat format = ClipboardFormats.findByAlias(formateString);
            ClipboardReader reader = format.getReader(stream);
            Clipboard clipboard = reader.read();
            EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(base.getWorld()));
            Operation operation = new ClipboardHolder(clipboard).createPaste(editSession).to(BlockVector3.at(base.getX(), base.getY(), base.getZ())).build();
            Operations.complete(operation);
            editSession.close();
        } catch (Exception e) {
            Bukkit.broadcastMessage("§c A schematic failed to load");
            e.printStackTrace();
            return;
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadShematic(String resourcePath, Location base, ClipboardFormat formateString) {
        InputStream stream = Main.getMain().getResource(resourcePath);
        try {
            ClipboardFormat format = formateString;
            ClipboardReader reader = format.getReader(stream);
            Clipboard clipboard = reader.read();
            EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(base.getWorld()));
            Operation operation = new ClipboardHolder(clipboard).createPaste(editSession).to(BlockVector3.at(base.getX(), base.getY(), base.getZ())).build();
            Operations.complete(operation);
            editSession.close();
        } catch (Exception e) {
            Bukkit.broadcastMessage("§c A schematic failed to load");
            e.printStackTrace();
            return;
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static File getFileFromResource(String fileName) {
        return new File(fileName);
        /*try {
            Path path = getResourcePath(fileName);
            String name = path.getFileName().toString();
            InputStream stream = Main.class.getResourceAsStream(path.toString());
            ObjectInputStream data = new ObjectInputStream(new InflaterInputStream(stream));
            data.
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }*/
    }

    public static List<Path> getAllPaths() {
        List<Path> paths = new ArrayList<>();
        try {
            URI uri = Main.class.getResource("/assets/").toURI();
            Path Path;
            FileSystem fileSystem = null;
            if (uri.getScheme().equals("jar")) {
                fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                Path = fileSystem.getPath("/assets/");
            } else {
                Path = Paths.get(uri);
            }
            Stream<Path> walk = Files.walk(Path, 3);
            for (Iterator<Path> it = walk.iterator(); it.hasNext(); ) {
                Path path = it.next();
                String name = path.getFileName().toString();
                if (name.endsWith(".schem")) paths.add(path);
            }
            if (fileSystem != null) fileSystem.close();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return paths;
    }

    public static String makeProgressBar(int bars, double value, double maxValue, ChatColor notFinishedProgress, ChatColor finishedProgress) {
        return makeProgressBar(bars, value, maxValue, notFinishedProgress, finishedProgress, "-");
    }

    public static String makeProgressBar(int bars, double value, double maxValue, ChatColor notFinishedProgress, ChatColor finishedProgress, String piece) {
        double pers = value / maxValue;
        if (pers > 1) pers = 1;
        if (pers < 0) pers = 0;
        int done = (int) (bars * pers);
        String s = "";
        if (done != 0) {
            s += finishedProgress;
            for (int i = 0; i < done; i++)
                s += piece;
        }
        if (bars - done != 0) {
            s += notFinishedProgress;
            for (int i = 0; i < (bars - done); i++)
                s += piece;
        }
        return s;
    }

    public static String stripeColorCodes(String s){
        StringBuilder name = new StringBuilder();
        if (s.contains("§"))
            for (String split : s.split("§")) {
                if (split.isEmpty()) continue;
                name.append(split.substring(1));
            }
        else
            name.append(s);
        return name.toString();
    }
    @Nullable
    public static <T> Constructor<T> getConstructorIfAvailable(Class<T> clazz, Class<?>... paramTypes) {
        Assert.notNull(clazz, "Class can not be null");
        try {
            return clazz.getConstructor(paramTypes);
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static TextComponent makeClickableText(String text, String hoverText, @Nullable ClickEvent.Action actiom, @Nullable String actionText) {
        TextComponent component = new TextComponent(text);
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverText).create()));
        component.setClickEvent(new ClickEvent(actiom, actionText));

        return component;
    }
    public static Set<Block> getBlocksBetween(Block b1, Block b2) {
        if (b1.getWorld() != b2.getWorld()) return null;
        Set<Block> b = new HashSet<>();
     /*for (int x = b1.getX(); x <= b2.getX(); x++) {
         for (int y = b1.getY(); y <= b2.getY(); y++) {
             for (int z = b1.getZ(); z <= b2.getZ(); z++) {
                 Location lll = new Location(b1.getWorld(), x , y , z);
                 b.add(lll.getBlock());
             }
         }
     }*/
        if (b2.getX() > b1.getX()) {
            for (int x = b1.getX(); x <= b2.getX(); x++)
                getBlocksBetweenY(b1, b2, b, x);
        } else if (b2.getX() == b1.getX()) {
            getBlocksBetweenY(b1, b2, b, b1.getX());
        } else for (int x = b1.getX(); x >= b2.getX(); x--)
            getBlocksBetweenY(b1, b2, b, x);


        return b;
    }



    private static void getBlocksBetweenY(Block b1, Block b2, Set<Block> curr, int x) {
        if (b2.getY() > b1.getY()) {
            for (int y = b1.getY(); y <= b2.getY(); y++)
                getBlocksBetweenZ(b1, b2, curr, x, y);
        } else if (b2.getY() == b1.getY()) {
            getBlocksBetweenZ(b1, b2, curr, x, b1.getY());
        } else for (int y = b1.getY(); y >= b2.getY(); y--)
            getBlocksBetweenZ(b1, b2, curr, x, y);
    }

    private static void getBlocksBetweenZ(Block b1, Block b2, Set<Block> curr, int x, int y) {
        if (b2.getZ() > b1.getZ()) {
            for (int z = b1.getZ(); z <= b2.getZ(); z++)
                curr.add(new Location(b1.getWorld(), x, y, z).getBlock());
        } else if (b2.getZ() == b1.getZ()) {
            curr.add(new Location(b1.getWorld(), x, y, b1.getZ()).getBlock());
        } else for (int z = b1.getZ(); z >= b2.getZ(); z--)
            curr.add(new Location(b1.getWorld(), x, y, z).getBlock());
    }

    public static <T> T getOneItemFromLootTable(HashMap<T, Double> lootTable) {
        double totalCount = 0;
        for (double d : lootTable.values())
            totalCount += d;
        double rand = new Random().nextDouble(totalCount);
        for (T loot : lootTable.keySet()) {
            totalCount -= lootTable.get(loot);
            if(totalCount <= rand) return loot;
        }
        throw new Error("Not Possible");
    }

    /**
     * Generates items from the lootabel
     *
     * @param lootTable Loot Table for the loot double is not allowed to be higher than 1
     * @param <T>       is the type of the random item
     * @return the items from the randomization
     */
    public static <T> List<T> generateItems(HashMap<T, Double> lootTable) {
        List<T> result = new ArrayList<>();
        for (T t : lootTable.keySet()) {
            if (new Random().nextDouble() <= lootTable.get(t)) result.add(t);
        }
        return result;
    }

    public static double getFallSpeedFromTimeElapsed(int tick) {
        double part1 = 392d / 5d;
        double part2 = Math.pow(98d / 100d, tick) - 1;
        double mainpart1 = part1 * part2;

        return mainpart1 / 20;
    }


    public static @NotNull ItemStack CustomHeadTexture(String url) {
        //, (short) 3
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);

        if (url == null || url.isEmpty()) return skull;

        ItemMeta skullMeta = skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;

        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        profileField.setAccessible(true);

        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static @NotNull ItemMeta CustomHeadTextureMeta(String url) {
        //, (short) 3
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);

        if (url == null || url.isEmpty()) return skull.getItemMeta();

        ItemMeta skullMeta = skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;

        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        profileField.setAccessible(true);

        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return skullMeta;
    }

    public static @NotNull ItemMeta CustomHeadTextureMeta(String url, String id) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        if (url == null || url.isEmpty()) return skull.getItemMeta();
        ItemMeta skullMeta = skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.fromString(id), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return skullMeta;
    }

    public static ItemStack getCustomTexturedHeadFromSkullValue(String value) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", value));
        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        head.setItemMeta(meta);
        return head;
    }

    public static ItemMeta getCustomTexturedHeadFromSkullValueMeta(String value) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", value));
        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        head.setItemMeta(meta);
        return meta;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack PlayerHeadTexture(String playerName) {

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);


        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        skullMeta.setOwner(playerName);
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static ItemStack CustomHeadTexture(String url, String customUUID) {
        //, (short) 3
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);

        if (url == null || url.isEmpty()) return skull;

        ItemMeta skullMeta = skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.fromString(customUUID), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;

        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        profileField.setAccessible(true);

        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static double[] rotatePoint2D(double[] arr, double angle) {
        angle = Math.toRadians(angle);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = arr[0];
        double y = arr[1];
        arr[0] = ((x * cos) + (y * sin));
        arr[1] = (-(x * sin) + (y * cos));
        return arr;
    }

    public static void removeAllItemsFromInventory(SkyblockPlayer player, ItemManager manager) {

        for (ItemStack item : player.getInventory())
            if (item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(manager.itemID)) {
                item.setAmount(0);

            }
    }
    public static int itemsInInv(SkyblockPlayer player, ItemManager manager){
        int i = 0;
        for (ItemStack item : player.getInventory()){
            if(item == null) continue;
            if(item.getType() == Material.AIR) continue;
            if(manager.itemID.equals(ItemHandler.getOrDefaultPDC("id", item, PersistentDataType.STRING, " "))){
                i += item.getAmount();
            }
        }
        return i;
    }

    public static void removeItemsFromInventory(SkyblockPlayer player, ItemManager manager, int amount) {

        for (ItemStack item : player.getInventory())
            if (item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(manager.itemID)) {
                int itemAmount = item.getAmount();
                if (amount > itemAmount) {
                    amount -= itemAmount;
                    item.setAmount(0);
                } else {
                    item.setAmount(itemAmount - amount);
                    amount -= itemAmount;
                }


                if (amount <= 0) return;

            }
    }

    public static String intToRoman(int num) {
        if (num == 0) return "0";

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLetters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num = num - values[i];
                roman.append(romanLetters[i]);
            }
        }
        return roman.toString();
    }

    public static String addDigits(int number) {
        String str = number + "";
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        str = sb.toString();
        String newString = "";
        int digitRunner = 0;
        for (int i = 0; i < str.toCharArray().length; i++) {
            newString = newString + str.toCharArray()[i];
            digitRunner++;
            if (digitRunner == 3 && (i + 1) != str.toCharArray().length) {
                digitRunner = 0;
                newString = newString + ",";
            }
        }
        sb = new StringBuilder(newString);


        return sb.reverse().toString();
    }

    public static String addDigits(long number) {
        String str = number + "";
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        str = sb.toString();
        String newString = "";
        int digitRunner = 0;
        for (int i = 0; i < str.toCharArray().length; i++) {
            newString = newString + str.toCharArray()[i];
            digitRunner++;
            if (digitRunner == 3 && (i + 1) != str.toCharArray().length) {
                digitRunner = 0;
                newString = newString + ",";
            }
        }
        sb = new StringBuilder(newString);


        return sb.reverse().toString();
    }

    public static String addDigits(double number) {
        String str = String.format("%.0f", number);
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        str = sb.toString();
        String newString = "";
        int digitRunner = 0;
        for (int i = 0; i < str.toCharArray().length; i++) {
            newString = newString + str.toCharArray()[i];
            digitRunner++;
            if (digitRunner == 3 && (i + 1) != str.toCharArray().length) {
                digitRunner = 0;
                newString = newString + ",";
            }
        }
        sb = new StringBuilder(newString);

        String finalString = sb.reverse().toString();
        String dotS = String.format("%.0f", number * 10);
        int dot = Integer.parseInt(dotS.charAt(dotS.length() - 1) + "");
        return finalString + ((dot != 0) ? ("." + dot) : "");
    }

    public static int getItemInPlayerInventory(ItemManager manager, SkyblockPlayer player) {
        int amount = 0;
        for (ItemStack item : player.getInventory())
            if (item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(manager.itemID))
                amount += item.getAmount();
        return amount;
    }

    public boolean deleteWorld(File world) {
        if (!world.exists() || !world.isDirectory()) return false;

        boolean success = true;

        for (File file : Objects.requireNonNull(world.listFiles())) {
            if (file.isDirectory())
                for (File file1 : Objects.requireNonNull(file.listFiles())) success = file1.delete();
            success = (file.delete() && success);
        }

        success = (world.delete() && success);

        return success;
    }


    public static double getPlayerFacingPI(Player p) {

        double deg = 0;
        double fin = 0;
        double x = p.getLocation().getDirection().getX();
        double z = p.getLocation().getDirection().getZ();

        if (x > 0.0 && z > 0.0) {
            deg = x;
            fin = (Math.PI / 2) * deg;
        }
        if (x > 0.0 && z < 0.0) {
            deg = z * (-1);
            fin = ((Math.PI / 2) * deg) + (Math.PI / 2);
        }
        if (x < 0.0 && z < 0.0) {
            deg = x * (-1);
            fin = (Math.PI / 2) * deg + Math.PI;
        }
        if (x < 0.0 && z > 0.0) {
            System.out.println("SW");
            deg = z;
            fin = (Math.PI / 2) * deg + (Math.PI / 2 * 3);
        }
        return fin;
    }

    /**
     * @param ran1   lower range is out of range!
     * @param ran2   higher range is out of range!
     * @param number the number you want to compare
     * @return if its in range
     */
    public static boolean isInRange(double ran1, double ran2, double number) {
        if (ran1 > number && number > ran2) {
            return true;
        }
        return ran1 < number && number < ran2;
    }


    public static String toShortNumber(double num) {
        String str;
        if (num > 999) {
            if (num > 9999) {
                if (num > 999999) {
                    if (num > 9999999) {
                        if (num > 999999999d) {
                            if (num > 9999999999d) str = (int) ((num / 1000000000)) + "b";
                            else str = round(num / 1000000000, 1) + "b";
                        } else str = (int) ((num / 1000000)) + "M";

                    } else str = round(num / 1000000, 1) + "M";
                } else str = (int) ((num / 1000)) + "k";
            } else str = round(num / 1000, 1) + "k";
        } else str = num + "";

        return str;
    }

    public static boolean hasFreeSlot(@NotNull SkyblockPlayer player) {

        int slot = player.getInventory().firstEmpty();


        return (slot < 55 && slot >= 0);
    }

    public static String ticksAsTime(long l) {
        double seconds = l / 20;
        int minutes = (int) (seconds / 60d);

        return minutes + ":" + ((int) (seconds - (minutes * 60)));
    }

    public static Set<Entity> getNearestEntitysInSight(Player player, int range) {
        List<Entity> entities = player.getNearbyEntities(range, range, range);
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity next = iterator.next();
            if (!(next instanceof LivingEntity) || next == player) {
                iterator.remove();
            }
        }
        List<Block> sight = player.getLineOfSight((Set) null, range);
        Set<Entity> ee = new HashSet<>();
        for (Block block : sight) {
            if (block.getType() != Material.AIR) {
                break;
            }
            Location low = block.getLocation();
            Location high = low.clone().add(1, 1, 1);
            AxisAlignedBB blockBoundingBox = new AxisAlignedBB(low.getX(), low.getY(), low.getZ(), high.getX(), high.getY(), high.getZ()); //The bounding or collision box of the block

            for (Entity entity : entities) {
                if (entity.getLocation().distance(player.getEyeLocation()) <= range && ((CraftEntity) entity).getHandle().getBoundingBox().c(blockBoundingBox)) {
                    ee.add(entity);
                }
            }

        }
        return ee;
    }

    public static Location getAsLocation(Block block) {
        return block.getLocation().add(0.5, 0, 0.5);
    }

    public static class SmalerLargerEqualsNumber implements Comparator<Double> {
        @Override
        public int compare(Double o1, Double o2) {
            return (o1 < o2) ? -1 : (o1 == o2) ? 0 : 1;
        }
    }

    public static class SmalerLargerEqualsNumberBundle implements Comparator<Bundle<Integer, ?>> {

        @Override
        public int compare(Bundle<Integer, ?> o1, Bundle<Integer, ?> o2) {
            return (o1.getFirst() < o2.getFirst()) ? -1 : (o1.getFirst() == o2.getFirst()) ? 0 : 1;
        }
    }

    public static <T, K> HashMap<T, K> mapOf(List<T> ts, List<K> ks) {
        Assert.isTrue(!(ts.isEmpty() || ks.isEmpty()), "Map is empty");
        Assert.isTrue(ts.size() == ks.size(), "The maps are not equal full");
        HashMap<T, K> map = new HashMap<>();
        int i = 0;
        for (T t : ts) {
            map.put(t, ks.get(i));
            i++;
        }
        return map;
    }

    public static BlockPosition asNmsBlock(Block block) {
        return new BlockPosition(block.getX(), block.getY(), block.getZ());
    }
    public static Block asBukkitBlock(BlockPosition block, World world) {
        return new Location(world,block.getX(), block.getY(), block.getZ()).getBlock();
    }

    public static void makeAir(Location a, Location b) {
        makeAir(a, b, null);
    }

    private static final IBlockData AIR = Blocks.a.getBlockData();
    public static void makeAir(Location a, Location b, @Nullable Runnable whenDone) {
        long totalBlocks = Math.abs(a.getBlockX() - b.getBlockX()) * Math.abs(a.getBlockY() - b.getBlockY()) * Math.abs(a.getBlockZ() - b.getBlockZ());
        Main.getMain().getLogger().log(Level.INFO, "Replacing " + totalBlocks + " blocks");
        Main.getMain().getLogger().log(Level.INFO, "Start fetching chuncks");
        new BukkitRunnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                long maxT = time;
                /*List<Integer> min = List.of(Math.min(a.getBlockX(), b.getBlockX()), Math.min(a.getBlockY(), b.getBlockY()), Math.min(a.getBlockZ(), b.getBlockZ()));
                List<Integer> max = List.of(Math.max(a.getBlockX(), b.getBlockX()), Math.max(a.getBlockY(), b.getBlockY()), Math.max(a.getBlockZ(), b.getBlockZ()));
                BlocksSpecialHashMap map = new BlocksSpecialHashMap(min, max);
                for (int i = min.get(0); i < max.get(0) + 16; i += 16) {
                    Chunk c = a.getWorld().getChunkAt(((int) (i / 16)), ((int) (min.get(2) / 16)));
                    double z1 = (min.get(2) > c.getX() * 16) ? min.get(2) : (c.getX() * 16);
                    double x1 = (min.get(0) > i) ? min.get(0) : i;

                    double z2 = (max.get(2) < ((c.getX() + 1) * 16) - 1) ? max.get(2) : (((c.getX() + 1) * 16));
                    double x2 = (max.get(0) < i + 15) ? max.get(0) : (i + 15);
                    map.add(new Pair<>(new Location(c.getWorld(), x1, min.get(1), z1), new Location(c.getWorld(), x2, max.get(1), z2)));
                }*/
                long next = System.currentTimeMillis();/*
                Main.getMain().getLogger().log(Level.INFO, "Loaded " + map.size() + " chunks in " + (next - time) + "ms");*/
                Set<Chunk> chunks = new HashSet<>();
                Counter counter = new Counter(3, new Runnable[]{() -> {
                    long t = System.currentTimeMillis();
                    Main.getMain().getLogger().log(Level.INFO, "Sendet Packets! (" + (t - next) + "ms)");
                    Main.getMain().getLogger().log(Level.INFO, "Total time: " + (t - maxT) + "ms");
                    CustomLogger logger = new CustomLogger("Cleanup Task");
                    logger.info("Cleaning up the JVM (This may cause a short lag spike!)");
                    final long before = System.currentTimeMillis();
                    System.gc();
                    Runtime.getRuntime().gc();
                    final long after = System.currentTimeMillis();
                    logger.info("It took " + (after - before) + "ms to cleanup the JVM heap");
                    System.out.println("Updating " + chunks.size() + " chunks...");
                    for (Chunk chunk : chunks){
                        WorldServer nmsWorld = ((CraftWorld) chunk.getWorld()).getHandle();
                        net.minecraft.world.level.chunk.Chunk nmsChunk = nmsWorld.getChunkAt(chunk.getX() >> 4, chunk.getZ() >> 4);
                        for (EntityPlayer ep : (List<EntityPlayer>) nmsWorld.getPlayers()) {
                            ep.b.sendPacket(new PacketPlayOutMapChunk(nmsChunk));
                        }
                    }
                }});
                Main.getMain().getLogger().log(Level.INFO, "Sending packets");
                CustomLogger debug = new CustomLogger("[DEBUG - SkyblockRemake]");
                long xSteps = (long)((Math.abs(b.getX() - a.getX()))/3d);
                CuboidRegion r1 = new CuboidRegion(BlockVector3.at(a.getX(), a.getY(), a.getZ()), BlockVector3.at(b.getX(), a.getY() + xSteps, b.getZ()));
                CuboidRegion r2 = new CuboidRegion(BlockVector3.at(a.getX(), a.getY() + xSteps, a.getZ()), BlockVector3.at(b.getX(), a.getY() + (xSteps*2), b.getZ()));
                CuboidRegion r3 = new CuboidRegion(BlockVector3.at(a.getX(), a.getY() + (xSteps*2), a.getZ()), BlockVector3.at(b.getX(), b.getY(), b.getZ()));

                for (BlockVector2 v2 : r1.getChunks())
                    a.getWorld().getChunkAt(v2.getX(), v2.getZ());
                for (BlockVector2 v2 : r2.getChunks())
                    a.getWorld().getChunkAt(v2.getX(), v2.getZ());
                for (BlockVector2 v2 : r3.getChunks())
                    a.getWorld().getChunkAt(v2.getX(), v2.getZ());
                Main.getMain().getServer().getScheduler().runTaskAsynchronously(Main.getMain(), () -> r1.forEach((bP) -> quickBlock(a.getWorld().getBlockAt(bP.getBlockX(), bP.getBlockY(), bP.getBlockZ()), AIR)));
                Main.getMain().getServer().getScheduler().runTaskAsynchronously(Main.getMain(), () -> r2.forEach((bP) -> quickBlock(a.getWorld().getBlockAt(bP.getBlockX(), bP.getBlockY(), bP.getBlockZ()), AIR)));
                Main.getMain().getServer().getScheduler().runTaskAsynchronously(Main.getMain(), () -> r3.forEach((bP) -> quickBlock(a.getWorld().getBlockAt(bP.getBlockX(), bP.getBlockY(), bP.getBlockZ()), AIR)));


                /*for (Set<Pair<Location>> c : map.packages) {
                    tasks++;
                    int chunkTask = tasks;
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Main.getMain().getLogger().log(Level.INFO, "Startup Chunk task " + chunkTask);
                            int run = 0;
                            for (Pair<Location> l : c) {
                                run++;
                                Set<Block> blocks = getBlocksBetween(l.getFirst().getBlock(), l.getLast().getBlock());
                                for (Block b : blocks) {
                                    if(b.getType() != Material.AIR){
                                        if (b.getX() % 100 == 0) {
                                            debug.info("x: " + b.getX() + " at " + b);
                                        }
                                        if (b.getZ() % 100 == 0) {
                                            debug.info("z: " + b.getZ() + " at " + b);
                                        }
                                    }quickBlock(b, data);
                                    try {

                                    }catch (Exception e){errors.add(e); e.printStackTrace(); Main.getMain().getLogger().log(Level.WARNING, "An Error occured while pasting: " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());}
                                }
                                WorldServer nmsWorld = ((CraftWorld) l.getFirst().getWorld()).getHandle();
                                net.minecraft.world.level.chunk.Chunk nmsChunk = nmsWorld.getChunkAt(l.getFirst().getBlockX() >> 4, l.getFirst().getBlockZ() >> 4);
                                for (EntityPlayer ep : (List<EntityPlayer>) nmsWorld.getPlayers()) {
                                    ep.b.sendPacket(new PacketPlayOutMapChunk(nmsChunk));
                                }
                                counter.add();
                            }
                            Main.getMain().getLogger().log(Level.INFO, "Finished Chunk task " + chunkTask);
                        }
                    }.runTaskAsynchronously(Main.getMain());
                }*/
            }
        }.runTaskAsynchronously(Main.getMain());

    }
    private static void quickBlock(Block b, IBlockData data){
        WorldServer nmsWorld = ((CraftWorld) b.getWorld()).getHandle();
        net.minecraft.world.level.chunk.Chunk nmsChunk = nmsWorld.getChunkAt(b.getX() >> 4, b.getZ() >> 4);

        ChunkSection cs = nmsChunk.getSections()[b.getY() >> 4];
        if (cs == nmsChunk.a() || cs == null) {
            cs = new ChunkSection(b.getY() >> 4 << 4);
            nmsChunk.getSections()[b.getY() >> 4] = cs;
        }
        if(cs == null){
            System.out.println("No ChunkSektion at " + b.getX() + " " + b.getY() + " " + b.getZ());
        }else {
            nmsWorld.removeTileEntity(asNmsBlock(b));
            cs.getBlocks().b(b.getX() & 15, b.getY() & 15, b.getZ() & 15, data);
        }
    }
    private static Set<Chunk> getSBlocksBetween(Block b1, Block b2) {
        Set<Chunk> chunks = new HashSet<>();
        if (b2.getX() > b1.getX()) {
            for (int x = b1.getX(); x <= b2.getX(); x++)
                getSBlocksBetweenY(b1, b2, chunks,x);
        } else if (b2.getX() == b1.getX()) {
            getSBlocksBetweenY(b1, b2, chunks,b1.getX());
        } else for (int x = b1.getX(); x >= b2.getX(); x--)
            getSBlocksBetweenY(b1, b2, chunks,x);
        return chunks;
    }



    private static void getSBlocksBetweenY(Block b1, Block b2, Set<Chunk> chunks, int x) {
        if (b2.getY() > b1.getY()) {
            for (int y = b1.getY(); y <= b2.getY(); y++)
                getSBlocksBetweenZ(b1, b2, chunks,x, y);
        } else if (b2.getY() == b1.getY()) {
            getSBlocksBetweenZ(b1, b2, chunks,x, b1.getY());
        } else for (int y = b1.getY(); y >= b2.getY(); y--)
            getSBlocksBetweenZ(b1, b2, chunks,x, y);
    }

    private static void getSBlocksBetweenZ(Block b1, Block b2, Set<Chunk> chunks, int x, int y) {
        if (b2.getZ() > b1.getZ()) {
            for (int z = b1.getZ(); z <= b2.getZ(); z++) {
                Block b = b1.getWorld().getBlockAt(x, y, z);
                quickBlock(b, AIR);
                chunks.add(b.getChunk());
            }
        } else if (b2.getZ() == b1.getZ()) {
            Block b = b1.getWorld().getBlockAt(x, y, b1.getZ());
            quickBlock(b, AIR);
            chunks.add(b.getChunk());
        } else for (int z = b1.getZ(); z >= b2.getZ(); z--) {
            Block b = b1.getWorld().getBlockAt(x, y, z);
            quickBlock(b, AIR);
            chunks.add(b.getChunk());
        }
    }
    /**
     * Breaks down a number into a time form
     * @param l time number
     * @return return the parts sec|min|hour
     */
    public static Triple<Long, Long, Long> breakDownTime(long l){
        long hours = l / 3600;
        long minutes = (l % 3600) / 60;
        long seconds = l % 60;
        return new Triple<>(seconds, minutes, hours);
    }


    public static <T> T getRandom(T[] t){
        return t[new Random().nextInt(t.length)];
    }
    public static <T> T getRandom(List<T> t){
        return t.get(new Random().nextInt(t.size()));
    }

    public static FakeBlock placeFakeBlock(Block b, Material m){
        FakeBlock f = new FakeBlock(b);
        f.change(m);
        return f;
    }
    public static <T> T[] combine(T[] t1, T[] t2) {
        T[] ts = Arrays.copyOf(t1, t1.length + t2.length);
        int i = t1.length;
        for (T o : t2) {
            ts[i] = o;
            i++;
        }
        return ts;
    }
    public static class FakeBlock{
        @Getter
        private static final MapList<Block, FakeBlock> blocks = new MapList<>();
        @Getter
        private boolean released = false;
        @Getter
        public final Block block;
        @Getter
        private Material material;
        private FakeBlock(Block b){
            block = b;
            material = b.getType();
            blocks.add(b, this);
        }
        public void change(Material m){
            material = m;
            Assert.state(!released, "Block is no longer a fakeblock!");
            for (Player p : Bukkit.getOnlinePlayers()) ((CraftPlayer) p).getHandle().b.sendPacket(new PacketPlayOutBlockChange(asNmsBlock(block), ((CraftBlockData) m.createBlockData()).getState()));
        }
        public void release(){
            Assert.state(!released, "Block is no longer a fakeblock!");
            released = true;
            blocks.removeFromList(block, this);
            if(!blocks.containsKey(block)) block.getState().update();
        }
    }

    private static class Counter {
        private final long target;
        private final Runnable[] whenDone;
        private long i;

        public Counter(long target, Runnable[] whenDone) {
            this.target = target;
            this.whenDone = whenDone;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (i >= target) cancel();
                    Main.getMain().getLogger().log(Level.INFO, "Progress: " + (((float) i/(float) target) * 100) + "%");
                }
            }.runTaskTimer(Main.getMain(), 20, 20);
        }

        public synchronized void add() {
            add(1);
        }

        public synchronized void add(long i) {
            this.i += i;
            if (this.i >= target) {
                for (Runnable r : whenDone) r.run();
            }
        }
    }

    private static class BlocksSpecialHashMap {
        private final List<Set<Pair<Location>>> packages = new ArrayList<>();
        private final Set<Chunk> chunks = new HashSet<>();
        private final List<Integer> min;
        private final List<Integer> max;
        int pointer = 0;

        public BlocksSpecialHashMap(List<Integer> min, List<Integer> max) {
            Assert.isTrue(min.size() == 3 && max.size() == 3);
            this.max = max;
            this.min = min;
        }

        public void add(Pair<Location> value) {
            Chunk key = value.getFirst().getChunk();
            int maxZ = (int) max.get(2) / 16;
            if (key.getZ() == maxZ) {
                if (!chunks.contains(key)) addToPackage(key, value);
                return;
            }
            if (max.get(2) % 16 != 0) maxZ++;
            for (int i = key.getZ() + 1; i < maxZ; i++) {
                Chunk c = key.getWorld().getChunkAt(key.getX(), i);
                if (chunks.contains(c)) continue;
                double x1 = (min.get(0) > c.getX() * 16) ? min.get(0) : (c.getX() * 16);
                double z1 = (min.get(2) > i * 16) ? min.get(2) : (i * 16);

                double x2 = (max.get(0) < ((c.getX() + 1) * 16) - 1) ? max.get(0) : (((c.getX() + 1) * 16));
                double z2 = (max.get(2) < ((i + 1) * 16) - 1) ? max.get(2) : (((i + 1) * 16) - 1);
                addToPackage(c, new Pair<>(new Location(key.getWorld(), x1, min.get(1), z1), new Location(key.getWorld(), x2, max.get(1), z2)));
            }
            addToPackage(key, value);
        }

        public void addToPackage(Chunk c, Pair<Location> node) {
            if (packages.size() == pointer) packages.add(new HashSet<>());
            Set<Pair<Location>> nodes = packages.get(pointer);
            nodes.add(node);
            pointer++;
            if (pointer == 5) pointer = 0;
            chunks.add(c);
        }

        public int size() {
            int size = 0;
            for (Set<Pair<Location>> s : packages) size += s.size();
            return size;
        }
    }
}