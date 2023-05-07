package me.CarsCupcake.SkyblockRemake.NPC.Questing;

public interface QuestOption {
    String name();
    void select();
    default String hoverText(){
        return "Click to select the option.";
    }
}
