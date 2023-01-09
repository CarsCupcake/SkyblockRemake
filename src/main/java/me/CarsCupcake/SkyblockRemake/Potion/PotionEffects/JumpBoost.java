package me.CarsCupcake.SkyblockRemake.Potion.PotionEffects;

import me.CarsCupcake.SkyblockRemake.Potion.Effect;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JumpBoost extends Effect {
    public JumpBoost(SkyblockPlayer player, Integer level, Long duration) {
        super(player, level, duration);
    }

    @Override
    public void start() {
        getPlayer().getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Math.toIntExact(getDuration() + 1), getLevel() - 1));
    }

    @Override
    public void stop() {
        getPlayer().getPlayer().removePotionEffect(PotionEffectType.JUMP);
    }

    @Override
    public String name() {
        return "§aJump Boost";
    }

    @Override
    public String id() {
        return "jump";
    }
}
