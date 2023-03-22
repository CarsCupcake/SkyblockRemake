package me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections;

import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.Items.farming.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.Items.mining.CobblestoneCollection;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.Items.mining.MithrilCollection;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;

public abstract class ICollection {
    protected static CustomConfig config;
    protected final SkyblockPlayer player;

    protected ICollection(SkyblockPlayer player) {
        this.player = player;
    }

    public static void init(){
        config = new CustomConfig("collections");
        CollectHandler.registeredCollections.add(new CobblestoneCollection(null));
        CollectHandler.registeredCollections.add(new MithrilCollection(null));
        CollectHandler.registeredCollections.add(new PotatoCollection(null));
        CollectHandler.registeredCollections.add(new CarrotCollection(null));
        CollectHandler.registeredCollections.add(new NetherWartCollection(null));
        CollectHandler.registeredCollections.add(new SugarCaneCollection(null));
        CollectHandler.registeredCollections.add(new WheatCollection(null));

    }
    private long collected;
    public abstract String getId();
    public abstract int getMaxLevels();
    public abstract int[] collectAmount();
    public abstract GUI getInventory();
    public abstract void sendLevelUpMessage(int level);
    public long getCollected(){
        return collected;
    }
    public int getLevel(){
        int level = 0;
        for(int bar : collectAmount())
            if(bar <= collected)
                level++;
        return level;
    }
    public void collect(CollectionCollectEvent event){
        if(event.getCollection() == this){
            addCollected(event.getAmount());
        }
    }
    public void addCollected(long l){
        if(isLevelUp(l))
            sendLevelUpMessage(getLevel() + 1);
        collected += l;
        config.get().set(player.getUniqueId() + "." + getId(), collected);
        config.save();
        config.reload();
    }
    private boolean isLevelUp(long i){
        if(getLevel() == getMaxLevels())
            return false;
        return collectAmount()[getLevel()] <= collected + i;
    }
    public long getColectedAmount(){
        return collected;
    }
    protected void load(){
        collected = config.get().getLong( player.getUniqueId() + "." + getId(), 0);
    }
    public abstract ICollection makeNew(SkyblockPlayer player);
}