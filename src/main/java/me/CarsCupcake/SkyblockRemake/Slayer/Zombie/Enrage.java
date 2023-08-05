package me.CarsCupcake.SkyblockRemake.Slayer.Zombie;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class Enrage extends Pestilence{
    private boolean isInEnrageState = false;
    private BukkitRunnable runnable;
    public Enrage(SkyblockPlayer player) {
        super(player);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void spawn(Location loc) {
        super.spawn(loc);
        stopEnrage();
    }

    private void startEnrage(){
        isInEnrageState = true;
        equipEnrage();
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                stopEnrage();
            }
        };
        runnable.runTaskLater(Main.getMain(), 20*40);
    }
    private void stopEnrage(){
        equip((Zombie) getEntity());
        isInEnrageState = false;
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                startEnrage();
            }
        };
        runnable.runTaskLater(Main.getMain(), 20*40);

    }
    private void equipEnrage(){
        getEntity().getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setGlint(true).setLeatherColor(Color.RED).build());
    }
    protected abstract int getBaseDamage();
    @Override
    public int getDamage(){
        return (isInEnrageState) ? getBaseDamage() * 5 : getBaseDamage();
    }
}
