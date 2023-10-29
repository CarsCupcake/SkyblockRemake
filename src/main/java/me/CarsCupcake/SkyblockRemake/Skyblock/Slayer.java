package me.CarsCupcake.SkyblockRemake.Skyblock;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.HashMap;

@Getter
public abstract class Slayer extends SkyblockEntity {
    private static final HashMap<SkyblockPlayer, Slayer> slayers = new HashMap<>();
    protected final SkyblockPlayer owner;

    public Slayer(SkyblockPlayer player) {
        owner = player;
    }

    public static void summonSlayer(Location location, Slayer slayer) {
        Assert.state(!slayers.containsKey(slayer.owner), "Multiple Slayers!");
        slayer.spawn(location);
        slayers.put(slayer.owner, slayer);
        SkyblockEntity.livingEntity.addEntity(slayer.getEntity(), slayer);
    }

    public static Slayer getSlayer(SkyblockPlayer player) {
        return slayers.get(player);
    }

    public static boolean hasActiveSlayer(SkyblockPlayer player) {
        return slayers.containsKey(player);
    }

    public abstract LivingEntity getEntity();

    public abstract int getDamage();

    public abstract void spawn(@NotNull Location loc);

    public abstract String getName();

    public void updateNameTag() {
        getEntity().setCustomName(getBaseName(this));
        getEntity().setCustomNameVisible(true);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void kill() {
        super.kill();
        if (owner != null)
            slayers.remove(owner);
    }

    public boolean hasNoKB() {
        return false;
    }

    public int getTrueDamage() {
        return 0;
    }

    public static String getBaseName(SkyblockEntity slayer) {
        return "§c" + Character.toChars(9760)[0] + " §b" + slayer.getName()
                + " §a" + Tools.toShortNumber(slayer.getHealth()) + "§c" + Stats.Health.getSymbol();
    }
}
