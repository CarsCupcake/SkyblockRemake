package me.CarsCupcake.SkyblockRemake.utils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.*;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;


import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import net.minecraft.FileUtils;
import net.minecraft.world.phys.AxisAlignedBB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.FileUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class Tools {
    public static double round(double num, int digit) {
        double d = Math.pow(10, digit);

        return Math.round(num * d) / d;
    }

    public static Double StringToDouble(String str){
        int mult = 1;
        if(str.endsWith("k")) {
            mult = 1000;
            str =str.replace("k", "");
        }
        if(str.endsWith("m")) {
            mult = 1000000;
            str =str.replace("m", "");
        }
        if(str.endsWith("b")) {
            mult = 1000000000;
            str = str.replace("b", "");
        }


        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            return -1d;
        }
        return Double.parseDouble(str)*mult ;


    }

    public static ArrayList<ItemStack> applyPristine(ItemManager rough, ItemManager flawed,int baseDropChance ,SkyblockPlayer player){
        double chance = Main.getPlayerStat(player, Stats.Pristine) / 100d;
        double miningFortune = Main.getPlayerStat(player, Stats.MiningFortune) / 100;
        int norm = 0;
        int pri = 0;
        Random r = new Random();
        for(int i = 0; i < baseDropChance; i++){
            if(r.nextDouble() <= chance) {
                pri += ((int) miningFortune) + ((miningFortune - ((int) miningFortune) != 0 && miningFortune - ((int) miningFortune) >= r.nextDouble()) ? 1 : 0);
            }
            else
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


    public static boolean isInRect(Player player, Location loc1, Location loc2)
    {
        double[] dim = new double[2];

        dim[0] = loc1.getX();
        dim[1] = loc2.getX();
        Arrays.sort(dim);
        if(player.getLocation().getX() > dim[1] || player.getLocation().getX() < dim[0])
            return false;

        dim[0] = loc1.getZ();
        dim[1] = loc2.getZ();
        Arrays.sort(dim);
        if(player.getLocation().getZ() > dim[1] || player.getLocation().getZ() < dim[0])
            return false;

        dim[0] = loc1.getY();
        dim[1] = loc2.getY();
        Arrays.sort(dim);
        return !(player.getLocation().getY() > dim[1]) && !(player.getLocation().getY() < dim[0]);
    }
    public static void loadShematic(File file, Location base){
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

            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(base.getX(), base.getY(), base.getZ()))
                    .build();

            Operations.complete(operation);

        }catch (Exception e) {
            e.printStackTrace();
            Bukkit.broadcastMessage("§c A schematic failed to load");
            return;
        }
    }
    public static void loadShematic(InputStream stream, Location base) throws IOException {
        Clipboard clipboard = null;
        ClipboardFormat format = ClipboardFormats.findByAlias("schem");

        try (ClipboardReader reader = format.getReader(stream)) {
            clipboard = reader.read();
            EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(base.getWorld()));
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(base.getX(), base.getY(), base.getZ()))
                    .build();

            Operations.complete(operation);
            editSession.close();
        } catch (Exception e) {
            Bukkit.broadcastMessage("§c A schematic failed to load");
            e.printStackTrace();
            return;
        }finally {
            try {
                stream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public static boolean hasFileSystem(String scheme){
        for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
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
    private static File copyFile(File sourceFile, File destinationFile)
            throws IOException {
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
            return new File(destinationFile, sourceFile.getName());
        }
    }
    public static void loadShematic(String resourcePath, Location base){
        InputStream stream = Main.getMain().getResource(resourcePath);
        try {
            ClipboardFormat format = ClipboardFormats.findByAlias("schem");
            ClipboardReader reader = format.getReader(stream);
            Clipboard clipboard = reader.read();
            EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(base.getWorld()));
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(base.getX(), base.getY(), base.getZ()))
                    .build();
            Operations.complete(operation);
            editSession.close();
        } catch (Exception e) {
            Bukkit.broadcastMessage("§c A schematic failed to load");
            e.printStackTrace();
            return;
        }finally {
            try {
                stream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static File getFileFromResource(String fileName){
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
    public static List<Path> getAllPaths () {
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
            for (Iterator<Path> it = walk.iterator(); it.hasNext();) {
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
    @Nullable
    public static <T> Constructor<T> getConstructorIfAvailable(Class<T> clazz, Class<?>... paramTypes) {
        Assert.notNull(clazz, "Class can not be null");
        try {
            return clazz.getConstructor(paramTypes);
        }
        catch (NoSuchMethodException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static ArrayList<Block> getBlocksBetween(Block b1, Block b2){
        if (b1.getWorld() != b2.getWorld())
            return null;
        ArrayList<Block> b = new ArrayList<>();
	 /*for (int x = b1.getX(); x <= b2.getX(); x++) {
		 for (int y = b1.getY(); y <= b2.getY(); y++) {
			 for (int z = b1.getZ(); z <= b2.getZ(); z++) {
				 Location lll = new Location(b1.getWorld(), x , y , z);
				 b.add(lll.getBlock());
			 }
		 }
	 }*/
        if(b2.getX() > b1.getX()){
            for(int x = b1.getX(); x <= b2.getX(); x++)
                getBlocksBetweenY(b1,b2, b, x);
        }else if(b2.getX() == b1.getX()){
            getBlocksBetweenY(b1,b2, b, b1.getX());
        }else for(int x = b1.getX(); x >= b2.getX(); x--)
            getBlocksBetweenY(b1,b2, b, x);


        return b;
    }
    private static void getBlocksBetweenY(Block b1, Block b2, ArrayList<Block> curr, int x){
        if(b2.getY() > b1.getY()){
            for(int y = b1.getY(); y <= b2.getY(); y++)
                getBlocksBetweenZ(b1,b2, curr, x,y);
        }else if(b2.getY() == b1.getY()){
            getBlocksBetweenZ(b1,b2, curr, x, b1.getY());
        }else for(int y = b1.getY(); y >= b2.getY(); y--)
            getBlocksBetweenZ(b1,b2, curr, x,y);
    }
    private static void getBlocksBetweenZ(Block b1, Block b2, ArrayList<Block> curr, int x, int y){
        if(b2.getZ() > b1.getZ()){
            for(int z = b1.getZ(); z <= b2.getZ(); z++)
                curr.add(new Location(b1.getWorld(),x,y,z).getBlock());
        }else if(b2.getZ() == b1.getZ()){
            curr.add(new Location(b1.getWorld(),x,y,b1.getZ()).getBlock());
        }else for(int z = b1.getZ(); z >= b2.getZ(); z--)
            curr.add(new Location(b1.getWorld(),x,y,z).getBlock());
    }
    public static  <T> T  getOneItemFromLootTable(HashMap<T, Double> lootTable){
        double totalCount = 0;
        for (double d : lootTable.values())
            totalCount += d;
        double rand = new Random().nextDouble(totalCount);
        for(T loot : lootTable.keySet()){
            if(rand <= lootTable.get(loot))
                return loot;
            rand -= lootTable.get(loot);
        }
        return lootTable.keySet().iterator().next();
    }

    /**
     * Generates items from the lootabel
     * @param lootTable Loot Table for the loot double is not allowed to be higher than 1
     * @return the items from the randomization
     * @param <T> is the type of the random item
     */
    public static <T> List<T> generateItems(HashMap<T, Double> lootTable){
        List<T> result = new ArrayList<>();
        for (T t : lootTable.keySet()){
            if(new Random().nextDouble() <= lootTable.get(t))
                result.add(t);
        }
        return result;
    }

    public static double getFallSpeedFromTimeElapsed(int tick){
        double part1 = 392d/5d;
        double part2 = Math.pow(98d/100d, tick) -1;
        double mainpart1 = part1*part2;

        return mainpart1 /20;
    }


    public static @NotNull ItemStack CustomHeadTexture(String url) {
        //, (short) 3
        ItemStack skull= new ItemStack(Material.PLAYER_HEAD, 1);

        if (url == null || url.isEmpty())
            return skull;

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
        ItemStack skull= new ItemStack(Material.PLAYER_HEAD, 1);

        if (url == null || url.isEmpty())
            return skull.getItemMeta();

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
        //, (short) 3
        ItemStack skull= new ItemStack(Material.PLAYER_HEAD, 1);

        if (url == null || url.isEmpty())
            return skull.getItemMeta();

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

        ItemStack skull= new ItemStack(Material.PLAYER_HEAD, 1);



        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        skullMeta.setOwner(playerName);
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static ItemStack CustomHeadTexture(String url, String customUUID) {
        //, (short) 3
        ItemStack skull= new ItemStack(Material.PLAYER_HEAD, 1);

        if (url == null || url.isEmpty())
            return skull;

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
    public static void removeAllItemsFromInventory(SkyblockPlayer player, ItemManager manager){

        for(ItemStack item : player.getInventory())
            if(item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(manager.itemID)) {
                item.setAmount(0);

            }
    }
    public static void removeItemsFromInventory(SkyblockPlayer player, ItemManager manager, int amount){

        for(ItemStack item : player.getInventory())
            if(item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(manager.itemID)) {
                int itemAmount = item.getAmount();
                if(amount > itemAmount){
                    amount -= itemAmount;
                    item.setAmount(0);
                }else{
                    item.setAmount(itemAmount-amount);
                    amount -= itemAmount;
                }



                if(amount <= 0)
                    return;

            }
    }

    public static String intToRoman(int num) {
        if(num == 0)
            return "0";

        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++)
        {
            while(num >= values[i])
            {
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
        for(int i = 0; i < str.toCharArray().length; i++) {
            newString = newString + str.toCharArray()[i];
            digitRunner++;
            if(digitRunner == 3 && (i+ 1) != str.toCharArray().length) {
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
        for(int i = 0; i < str.toCharArray().length; i++) {
            newString = newString + str.toCharArray()[i];
            digitRunner++;
            if(digitRunner == 3 && (i+ 1) != str.toCharArray().length) {
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
        for(int i = 0; i < str.toCharArray().length; i++) {
            newString = newString + str.toCharArray()[i];
            digitRunner++;
            if(digitRunner == 3 && (i+ 1) != str.toCharArray().length) {
                digitRunner = 0;
                newString = newString + ",";
            }
        }
        sb = new StringBuilder(newString);

        String finalString = sb.reverse().toString();
        String dotS = String.format("%.0f",number * 10);
        int dot = Integer.parseInt(dotS.charAt(dotS.length() - 1) + "");
        return finalString + ((dot != 0) ? ("." + dot) : "");
    }
    public static int getItemInPlayerInventory(ItemManager manager, SkyblockPlayer player){
        int amount = 0;
        for(ItemStack item : player.getInventory())
            if(item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(manager.itemID))
                amount += item.getAmount();
        return amount;
    }

    public static double getPlayerFacingPI(Player p) {

        double deg = 0;
        double fin = 0;
        double x = p.getLocation().getDirection().getX();
        double z = p.getLocation().getDirection().getZ();

        if(x > 0.0 && z >0.0) {
            deg = x;
            fin = (Math.PI/2)*deg;
        }
        if(x> 0.0 && z<0.0) {
            deg = z*(-1);
            fin = ((Math.PI/2)*deg)+(Math.PI/2);
        }
        if(x<0.0 && z<0.0) {
            deg = x*(-1);
            fin = (Math.PI/2)*deg+ Math.PI;
        }
        if(x<0.0 && z>0.0) {
            System.out.println("SW");
            deg = z;
            fin = (Math.PI/2)*deg+(Math.PI/2 *3);
        }
        return fin;
    }

    /**
     *
     * @param ran1 lower range is out of range!
     * @param ran2 higher range is out of range!
     * @param number the number you want to compare
     * @return if its in range
     */
    public static boolean isInRange(double ran1, double ran2, double number) {
        if(ran1 > number && number > ran2) {
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
                            if (num > 9999999999d)
                                str = (int)((num /1000000000)) + "b";
                            else
                                str = round(num /1000000000, 1) + "b";
                        }else
                            str = (int)((num /1000000)) + "m";

                    }else
                        str = round(num /1000000, 1) + "m";
                }else
                    str = (int)((num /1000)) + "k";
            }else
                str = round(num /1000, 1) + "k";}else str = num + "";

        return str;
    }
    public static boolean hasFreeSlot(@NotNull SkyblockPlayer player){

        int slot = player.getInventory().firstEmpty();


        return (slot < 55 && slot >= 0);
    }

    public static String ticksAsTime(long l){
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
    public static Location getAsLocation(Block block){
        return block.getLocation().add(0.5, 0, 0.5);
    }
    public static class SmalerLargerEqualsNumber implements Comparator<Double>{
        @Override
        public int compare(Double o1, Double o2) {
            return (o1 < o2) ? -1 : (o1 == o2) ? 0 : 1;
        }
    }
    public static class SmalerLargerEqualsNumberBundle implements Comparator<Bundle<Integer, ?>>{

        @Override
        public int compare(Bundle<Integer, ?> o1, Bundle<Integer, ?> o2) {
            return (o1.getFirst() < o2.getFirst()) ? -1 : (o1.getFirst() == o2.getFirst()) ? 0 : 1;
        }
    }
}