package me.CarsCupcake.SkyblockRemake.Skyblock.player.levels;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.CollectHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.levels.levelGetters.ReaperPepper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SkyblockLevelsHandler {
    private static final HashMap<SkyblockPlayer, Set<SkyblockLevelsGetter>> getters = new HashMap<>();
    public static void claculate(SkyblockPlayer player){
        long totalXp = 0;
        for (SkyblockLevelsGetter getter : getters.get(player))
            totalXp += getter.getSkyblockXp();
        int level = (int) (totalXp / 100);
        player.setSkyblockLevel(level);
        player.setSkyblockLevelXp((int) (totalXp - (level * 100)));
    }
    public static void initGetters(SkyblockPlayer player){
        Set<SkyblockLevelsGetter> g = new HashSet<>();
        g.add(new ReaperPepper(player));
        for (Skills skill : Skills.values())
            if(player.getSkill(skill) != null)
                g.add(player.getSkill(skill));
        g.addAll(CollectHandler.collections.get(player));
        getters.put(player, g);
    }
    public static void addStats(SkyblockPlayer player){
        player.addBaseStat(Stats.Health, player.getSkyblockLevel() * 5);
        int allfive = player.getSkyblockLevel()/5;
        player.addBaseStat(Stats.Strength, allfive);
        int allten = player.getSkyblockLevel() / 10;
        player.addBaseStat(Stats.Health, allten * 5);
    }
    public static void addXp(SkyblockPlayer player, int amount, SkyblockLevelsGetter getter){
        player.sendMessage("§b§m                                             ");
        player.sendMessage("§3Skyblock Xp has changed!");
        player.sendMessage(" ");
        int old = getter.getSkyblockXp() - amount;
        player.sendMessage("§b" + getter.getName() + " §8" + old + "➜§3" + (old + amount));
        player.sendMessage("§b§m                                             ");
        if(player.getSkyblockLevelXp() + amount >= 100){
            int before = player.getSkyblockLevel();
            int rem = amount + player.getSkyblockLevelXp();
            do {
                player.setSkyblockLevel(player.getSkyblockLevel() + 1);
                rem -= 100;
            }while (rem >= 100);
            player.setSkyblockLevelXp(rem);
            sendLevelUp(player, before);
        }else player.setSkyblockLevelXp(player.getSkyblockLevelXp() + amount);
    }
    public static void sendLevelUp(SkyblockPlayer player, int old){
        player.sendMessage("§b§m                                             ");
        player.sendMessage(" §3§lSKYBLOCK LEVEL UP §bLevel §8" + old + "➜§3" + player.getSkyblockLevel());
        player.sendMessage("§b§m                                             ");
    }
    public static String getLevelPrefix(SkyblockPlayer player){
        if(player.getSkyblockLevel() < 40)
            return "§7";
        if(player.getSkyblockLevel() < 80)
            return ItemRarity.COMMON.getPrefix();
        if(player.getSkyblockLevel() < 120)
            return ItemRarity.UNCOMMON.getPrefix();
        if(player.getSkyblockLevel() < 160)
            return ItemRarity.RARE.getPrefix();
        if(player.getSkyblockLevel() < 200)
            return ItemRarity.EPIC.getPrefix();
        if(player.getSkyblockLevel() < 240)
            return ItemRarity.LEGENDARY.getPrefix();
        if(player.getSkyblockLevel() < 280)
            return ItemRarity.MYTHIC.getPrefix();
        if(player.getSkyblockLevel() < 320)
            return ItemRarity.DIVINE.getPrefix();
        if(player.getSkyblockLevel() < 360)
            return ItemRarity.SPECIAL.getPrefix();
        return ItemRarity.VERY_SPECIAL.getPrefix();
    }
}
