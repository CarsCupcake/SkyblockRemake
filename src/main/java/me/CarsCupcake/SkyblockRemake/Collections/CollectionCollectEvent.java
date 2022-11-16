package me.CarsCupcake.SkyblockRemake.Collections;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.NotNull;

public class CollectionCollectEvent extends Event implements Cancellable {
    private final static HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;
    private final SkyblockPlayer player;
    private final ICollection collection;
    private int amount;

    public CollectionCollectEvent(@NotNull SkyblockPlayer player, @Positive int amount,@NotNull ICollection collection){
        this.player = player;
        this.amount = amount;
        this.collection = collection;
    }
    public ICollection getCollection(){
        return collection;
    }
    public SkyblockPlayer getPlayer(){
        return player;
    }
    public int getAmount(){
        return amount;
    }
    public void setAmount(int i){
        amount = i;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
}
