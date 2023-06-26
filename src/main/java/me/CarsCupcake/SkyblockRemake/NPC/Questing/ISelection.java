package me.CarsCupcake.SkyblockRemake.NPC.Questing;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

import java.util.HashMap;
import java.util.UUID;

public interface ISelection {
    void select(UUID id);

    HashMap<SkyblockPlayer, ISelection> selections = new HashMap<>();
}
