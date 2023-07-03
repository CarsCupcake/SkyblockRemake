package me.CarsCupcake.SkyblockRemake.abilities.WandSpells;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.abilities.Wand;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

public class AvadaKedavra extends Wand.Spell {
    public AvadaKedavra() {
        SPEED = 2;
    }

    @Override
    protected String getId() {
        return "Avada Kedavra";
    }

    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.GREEN, 1);

    @Override
    protected void hit(LivingEntity entity, SkyblockPlayer player, Location location) {
        Location l = location.clone().add(0, entity.getEyeHeight() / 2, 0);
        l.getWorld().spawnParticle(Particle.REDSTONE, location, 15,0.5, entity.getEyeHeight() / 2, 0.5,dustOptions);
        SkyblockEntity.killEntity(entity, player);
    }

    @Override
    public void playEffect(Location location) {
        location.getWorld().spawnParticle(Particle.REDSTONE, location, 1, dustOptions);
    }
}
