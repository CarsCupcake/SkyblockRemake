package me.CarsCupcake.SkyblockRemake.Potion;

import me.CarsCupcake.SkyblockRemake.Potion.PotionEffects.JumpBoost;
import org.bukkit.Color;

public class PotionItems {
    public PotionItems(){
        new PotionEffect("Jump Boost", JumpBoost.class, Color.fromBGR(0x00FF00));
    }
}
