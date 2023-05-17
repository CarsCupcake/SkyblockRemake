package me.CarsCupcake.SkyblockRemake.NPC.Questing;

import lombok.Getter;

public enum Quests {
    TestQuest("testquest");
    @Getter
    private final String id;
    Quests(String id) {
        this.id = id;
    }
}
