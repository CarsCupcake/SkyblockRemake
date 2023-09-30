package me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.Items.farming.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.Items.mining.CobblestoneCollection;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.Items.mining.MithrilCollection;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.levels.SkyblockLevelsGetter;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.levels.SkyblockLevelsHandler;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;

public abstract class ICollection implements SkyblockLevelsGetter {
    protected final SkyblockPlayer player;

    protected ICollection(SkyblockPlayer player) {
        this.player = player;
    }

    public static void init(){
        CollectHandler.registeredCollections.add(new CobblestoneCollection(null));
        CollectHandler.registeredCollections.add(new MithrilCollection(null));
        CollectHandler.registeredCollections.add(new PotatoCollection(null));
        CollectHandler.registeredCollections.add(new CarrotCollection(null));
        CollectHandler.registeredCollections.add(new NetherWartCollection(null));
        CollectHandler.registeredCollections.add(new SugarCaneCollection(null));
        CollectHandler.registeredCollections.add(new WheatCollection(null));

    }
    @Getter
    private long collected;
    public abstract String getId();
    public abstract int getMaxLevels();
    public abstract int[] collectAmount();
    public abstract GUI getInventory();
    public abstract void sendLevelUpMessage(int level);

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
        level(l);
        collected += l;
    }
    private void level(long i){
        if(getLevel() == getMaxLevels())
            return;
        long total = i + collected;
        int l = 0;
        while (collectAmount()[getLevel()] <= total){
            l++;
            sendLevelUpMessage(getLevel() + l);
            SkyblockLevelsHandler.addXp(player, 4, this);
            if(getLevel() + l >=getMaxLevels()) break;
        }
    }
    public long getColectedAmount(){
        return collected;
    }
    protected void load(){
        CustomConfig config = new CustomConfig(player, "collection");
        collected = config.get().getLong( getId(), 0);
    }
    public void save() {
        CustomConfig config = new CustomConfig(player, "collection");
        config.get().set(getId(), collected);
        config.save();
    }
    public abstract ICollection makeNew(SkyblockPlayer player);

    @Override
    public int getMaxSkyblockXp() {
        return getMaxLevels() * 4;
    }

    @Override
    public int getSkyblockXp() {
        return getLevel() * 4;
    }
}