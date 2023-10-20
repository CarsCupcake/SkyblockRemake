package me.CarsCupcake.SkyblockRemake.NPC.Questing;

import me.CarsCupcake.SkyblockRemake.Configs.ConfigFile;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
@SuppressWarnings("unused")
public class QuestStage {
    public static int getQuestStage(Quests quest, SkyblockPlayer player){
        ConfigFile config = new ConfigFile(player, quest.getId());
        return config.get().getInt("stage", 0);
    }
    public static void setQuestStage(Quests quest, SkyblockPlayer player, int stage){
        ConfigFile config = new ConfigFile(player, quest.getId());
        config.get().set("stage", stage);
        config.save();
    }
    public static String getQuestData(Quests quest, SkyblockPlayer player, String s){
        ConfigFile config = new ConfigFile(player, quest.getId());
        return config.get().getString(s);
    }
    public static void setQuestData(Quests quest, SkyblockPlayer player, String stage, String data){
        ConfigFile config = new ConfigFile(player, quest.getId());
        config.get().set(stage, data);
        config.save();
    }
}
