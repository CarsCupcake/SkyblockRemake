package me.CarsCupcake.SkyblockRemake.abilities.WandSpells;

import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.abilities.Wand;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

public class BasicSpell extends Wand.Spell {
    @Override
    protected String getId() {
        return "Basic Spell";
    }

    @Override
    protected void hit(LivingEntity entity, SkyblockPlayer player, Location spellLocation) {
        Calculator c = new Calculator();
        c.setMagic("Basic Spell", 0.1);
        c.playerToEntityMagicDamage(SkyblockPlayer.getSkyblockPlayer(player), entity, 5000);
        c.damageEntity(entity, player);
        c.sendMagicMessage(1, player);
        spellLocation.getWorld().spawnParticle(Particle.FLASH, spellLocation, 1);
    }
    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
    @Override
    public void playEffect(Location location) {
        location.getWorld().spawnParticle(Particle.REDSTONE, location, 1, dustOptions);
    }
}
