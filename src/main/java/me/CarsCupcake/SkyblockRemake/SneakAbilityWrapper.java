package me.CarsCupcake.SkyblockRemake;

import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class SneakAbilityWrapper implements FullSetBonus {
    private BukkitRunnable runnable;
    private final FullSetBonus bonus;
    private SkyblockPlayer player;
    public SneakAbilityWrapper(FullSetBonus bonus) {
        this.bonus = bonus;
    }
    @Override
    public void start() {
        if(player.isSneaking()) bonus.start();
        runnable = new BukkitRunnable() {
            private boolean aBoolean = player.isSneaking();
            @Override
            public void run() {
                if(aBoolean && !player.isSneaking()) {
                    bonus.stop();
                    aBoolean = false;
                }else if (!aBoolean && player.isSneaking()) {
                    bonus.start();
                    aBoolean = true;
                }
            }
        };
        runnable.runTaskTimer(Main.getMain(), 0, 1);
    }

    @Override
    public void stop() {
        runnable.cancel();
    }

    @Override
    public int getPieces() {
        return bonus.getPieces();
    }

    @Override
    public int getMaxPieces() {
        return bonus.getMaxPieces();
    }

    @Override
    public void setPlayer(SkyblockPlayer player) {
        this.player = player;
    }

    @Override
    public Bonuses getBonus() {
        return bonus.getBonus();
    }

    @Override
    public FullSetBonus makeNew(SkyblockPlayer player) {
        SneakAbilityWrapper wrapper = new SneakAbilityWrapper(bonus.makeNew(player));
        wrapper.setPlayer(player);
        return wrapper;
    }
}
