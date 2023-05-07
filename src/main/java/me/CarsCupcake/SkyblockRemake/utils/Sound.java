package me.CarsCupcake.SkyblockRemake.utils;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.SoundCategory;

public record Sound(org.bukkit.Sound sound, float volume, float pitch) {
    public void play(SkyblockPlayer player){
        player.playSound(player.getLocation(),sound(), SoundCategory.VOICE, volume(), pitch());
    }
}
