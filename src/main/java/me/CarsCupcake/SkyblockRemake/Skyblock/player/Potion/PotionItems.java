package me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion;

import me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffects.JumpBoost;
import org.bukkit.Color;

public class PotionItems {
    public PotionItems(){
        new PotionEffect("Jump Boost", JumpBoost.class, Color.fromBGR(0x00FF00));
    }
}
