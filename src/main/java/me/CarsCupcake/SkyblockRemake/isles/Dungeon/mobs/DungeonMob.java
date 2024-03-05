package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs;

import me.CarsCupcake.SkyblockRemake.Skyblock.Defensive;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.IRoom;
import me.CarsCupcake.SkyblockRemake.utils.Tools;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class DungeonMob extends SkyblockEntity implements Defensive {
    protected final int maxHealth;
    protected final int damage;
    protected final double defense;
    protected boolean stared;
    protected boolean master;
    protected int floor;
    private IRoom room;
    public DungeonMob(int floor, boolean master){
        this.floor = floor;
        this.master = master;
        super.health = healthFromFloor(floor, master);
        maxHealth = health;
        damage = damageFromFloor(floor, master);
        defense = defenseFromFloor(floor, master);
    }

    @Override
    public double getDefense() {
        return defense;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getDamage() {
        return damage;
    }
    @Override
    public void updateNameTag() {
        getEntity().setCustomNameVisible(true);
        getEntity().setCustomName("§c" + getName() + " §a" + Tools.toShortNumber(getHealth()) + ((stared) ? " §6✯" : ""));
    }

    public void setStared(IRoom room){
        this.room = room;
        this.stared = true;
        room.addStar();
    }

    protected abstract int healthFromFloor(int floor, boolean master);
    protected abstract int damageFromFloor(int floor, boolean master);
    protected abstract double defenseFromFloor(int floor, boolean master);
    @Override @OverridingMethodsMustInvokeSuper
    public void kill(){
        super.kill();
        if(room != null && stared)
            room.killStarMob(this);
    }
}
