package me.CarsCupcake.SkyblockRemake.Items.Pets;


import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public abstract class PetAbility{
    protected final SkyblockPlayer player;
    public PetAbility(SkyblockPlayer player){
        this.player = player;
    }
    public abstract void start();
    public abstract void stop();

}
