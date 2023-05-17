package me.CarsCupcake.SkyblockRemake.NPC.Questing;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.configuration.ConfigurationSection;

import java.lang.invoke.SerializedLambda;

public class QuestStage {
    public static int getQuestStage(Quests quest, SkyblockPlayer player){
        CustomConfig config = new CustomConfig(player, quest.getId());
        return config.get().getInt("stage", 0);
    }
    public static void setQuestStage(Quests quest, SkyblockPlayer player, int stage){
        CustomConfig config = new CustomConfig(player, quest.getId());
        config.get().set("stage", stage);
        config.save();
    }
    public static String getQuestData(Quests quest, SkyblockPlayer player, String s){
        CustomConfig config = new CustomConfig(player, quest.getId());
        return config.get().getString(s);
    }
    public static void setQuestData(Quests quest, SkyblockPlayer player, String stage, String data){
        CustomConfig config = new CustomConfig(player, quest.getId());
        config.get().set(stage, data);
        config.save();
    }
}
